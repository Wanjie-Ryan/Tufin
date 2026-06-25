# Week 2 - Day 1: SpringFramework & Dependency Injection
- Spring - set of tools that handles the boring, repetitive infra work so you can focus on your actual business logic.
- Spring handles for you:
1. Object creation - you don't create objects manually
2. Wiring objects together - you don't connect them manually
3. Transactions - you don't manage DB commits manually
4. Security - authentication/authorization
5. REST APIs - handling HTTP requests
6. Configuration - managing app settings.

# Problem spring solves
- building a user management system, without spring.
- // ❌ Without Spring — you do everything manually
  public class UserController {

  private UserService userService;

  public UserController() {
  // you create every dependency yourself
  UserRepository repository = new UserRepository();
  UserService userService = new UserService(repository);
  this.userService = userService;
  }
  }

- Problems with this:
1. Tightly coupled
- UserController creates userService itself
- if userService changes, userController must change too.

2. Hard to test
- You can't swap userService for a fake test version because its hardcoded inside the constructor

3. You manage everything
- If userRepository needs a DB connection, you create that too. And if that needs something else, you create that 
  too. It cascades.

# With Spring
- // ✅ With Spring — Spring handles the wiring
  @RestController
  public class UserController {

  @Autowired
  private UserService userService; // Spring injects this automatically
  }

- Just say, "I need a userService" and spring providers one. You don't create it, you don't wire it. Spring does it.


# PART 3 - IOC (Inversion of Control)
- Normally in code, you are in control
1. You create objects
2. You call methods
3. You manage everything

- With IOC, you give up control:
1. Spring creates objects.
2. Spring manages them.
3. Spring providers them when needed.
- Control is inverted - hence Inversion of Control.

# Real world analogy
- Normal:
- You go to the shop, pick ingredients, cook your own meal. You are in control of every step.

- IOC:
- You call a restaurant, tell them what you want. They source ingredients, cook it, deliver it.
- Control is inverted - they manage the process, you just consume.

- // Normal — YOU control object creation
  UserRepository repo = new UserRepository();

- // IoC — SPRING controls object creation
// you just declare what you need
@Autowired
private UserRepository repo;

# PART 4 - DEPENDENCY INJECTION
- How IOC actually works.
- A dependency is anything your class needs to do its job.
- Injection means providing that dependency from outside rather than creating it inside.

# Ways of injecting in spring
1. Field injection
- @Service
  public class UserService {

  @Autowired  // Spring injects this field automatically
  private UserRepository repository;
  }

2. Constructor Injection
- @Service
  public class UserService {

  private final UserRepository repository;

  // Spring sees this constructor and injects UserRepository
  public UserService(UserRepository repository) {
  this.repository = repository;
  }
  }

# Why constructor injection is best
- field injection
1. Repo can be null if spring fails to inject
2. harder to test 
3. hides dependencies

- constructor injection
1. Repo can never be null
2. Easy to test
3. Dependencies are visible

# PART 5 - SPRING ANNOTATIONS
1. @component

@component
public class EmailValidator{
// a general purpose Spring managed object
// use when it doesn't fit any specific category
}
- tells spring: "manage this class as a bean"
- use for: utility classes, helpers, general components

2. @service

@service
public class UserService{
// contains business logic
// eg. creating users, validating users
}

- tells spring "this is a service layer class"
- used for business logic, processing, rules

3. @Repository

@Repository
public class userRepo{
// handles DB ops
}

4. @RestController

@RestController
public class UserController{
// handles httprequests (REST)
}

# How the layers connect

- HTTP REQUEST -> RestController -> service -> Repository -> DB

# PART 6 - SPRING BEAN SCOPES
- A bean is just any object that spring manages.
- Every time spring creates a bean, it has to decide - should it create one instance or many?
- That decision is called **scope**

1. Singleton (default)

@Service // singleton by default
public class UserService{
}
- one single instance created.
- Everyone who needs Userservice gets the SAME object
- Lives for the entire lifetime of the application.

Application starts
Spring creates ONE UserService instance
↓
Controller A needs UserService → gets that same instance
Controller B needs UserService → gets that SAME instance
Controller C needs UserService → gets that SAME instance

All pointing to the exact same object in memory.


2. Prototype

@component
@Scope("prototype")
public class ReportGenerator{
}

- A new instance is created every time someone requests it.
- Each caller gets their own separate object.

Controller A requests ReportGenerator → new instance created → instance 1
Controller B requests ReportGenerator → new instance created → instance 2
Controller C requests ReportGenerator → new instance created → instance 3

All different objects in memory.

# When to use each:
1. Singleton 
- stateless classes (no instance variables that change)
- UserService, UserRepository.... Most spring beans are singleton

2. Prototype
- stateful classes (holds data specific to one request)
- ReportGenerator that stores report specific data
- Objects that must not be shared btn users.


# PART 7 - CODE REVIEW 
// XX problematic code XX
@service
public class UserService{
    private UserRepository repo = new UserRepository();
}

- Whats Wrong:
1. Spring is NOT managing this dependency
- new UserRepository creates it manually.
- Spring has no idea this object exists.
- Spring cannot inject it, replace it, or manage its lifecycle.

2. Tightly coupled
- Userservice is hardcoded to userRepository.
- You cannot mock it in tests.

3. Difficult to test

# THE FIX
@Service
public class UserService{

private final UserRepository repo;

// spring sees this constructor
// finds userrepository bean and injects it
public UserService(UserRepository repos){
this.repo = repos;
}

}

# OR

@Service
public class UserService{

@Autowired
private UserRepository repo;

}


# PART 8 - NETWORK BASICS
1. IP ADDRESS - the building - gets you to the right machine. Unique identifier
2. PORT - the room number - gets you to the right service. - service running on a machine.

3. Firewall 
- Gatekeeper that decides what traffic is allowed in and what is blocked.
- Incoming request: IP=10.0.0.5, Port=8080

Firewall checks its rules:
Rule 1: Allow 192.168.1.0/24 on port 443  → doesn't match
Rule 2: Block 10.0.0.5 on port 8080       → MATCH → BLOCK

Request is rejected.

# FIREWALL IN JAVA - DATA STRUCTURE DECISION

// model a single firewall rule
public class FirewallRule{
    private String ipAddress; // which IP
    private int port; // which port
    private boolean allowed; // allow or block?

    public FireWallRule(String ipAddress, int port, boolean allowed){
        this.ipAddress = ipAddress;
        this.port = port;
        this.allowed = allowed;

}
    
    public String getIPAddress() {
    return ipAddress;
    }
    
    public int getPort(){
    return port;
    }

    public boolean isAllowed(){
    return allowed;
    }
}

- Q. Which data structure to store rules?
  List<FirewallRule>
  → simple, ordered
  → lookup is O(n) — must check every rule
  → fine for 10 rules, terrible for 1 million rules

- Map<String, FirewallRule>  where key = "ip:port"
→ lookup is O(1) — jump directly to the rule
→ "10.0.0.5:8080" → check instantly
→ scales to millions of rules


// Efficient firewall using HashMap
public class FirewallService {

    // key = "ipAddress:port" e.g "10.0.0.5:8080"
    // value = the rule
    private Map<String, FirewallRule> rules = new HashMap<>();

    // add a rule
    public void addRule(String ip, int port, boolean allowed) {
        String key = ip + ":" + port;
        rules.put(key, new FirewallRule(ip, port, allowed));
    }

    // check if a request is allowed — O(1)
    public boolean isAllowed(String ip, int port) {
        String key = ip + ":" + port;
        FirewallRule rule = rules.get(key);

        if (rule == null) {
            return true; // no rule found — allow by default
        }

        return rule.isAllowed();
    }

    public static void main(String[] args) {
        FirewallService firewall = new FirewallService();

        // add rules
        firewall.addRule("10.0.0.5", 8080, false); // block this IP on 8080
        firewall.addRule("192.168.1.1", 443, true); // allow this IP on 443

        // check requests
        System.out.println(firewall.isAllowed("10.0.0.5", 8080));   // false — blocked
        System.out.println(firewall.isAllowed("192.168.1.1", 443)); // true — allowed
        System.out.println(firewall.isAllowed("172.16.0.1", 80));   // true — no rule
    }
}


# Transactions
- Operations must succeded or fail all together, no in between

# ACID

- ATOMICITY - succeed or fail together
- Consistency - DB must move from one valid state to another
- Isolation - conccurent transactions should not know of each others partial states
- Durability - After comitting, the change should be saved permanently

@Service
public class OrderService {

    @Transactional  // ← this one annotation does all the work
    public void createOrder(Order order) {
        saveOrder(order);       // step 1
        processPayment(order);  // step 2 — if this throws exception
        updateInventory(order); // step 3 — this never runs
        // @Transactional automatically rolls back step 1
    }
}

1. Before method runs  → Spring opens a database transaction
2. Method runs         → all operations join the same transaction
   3a. Method succeeds    → Spring commits everything to database
   3b. Method throws      → Spring rolls back everything automatically


# Review 3
public String firstActiveEmail(List<User> users) {
return users.stream()
.filter(User::isActive)
.map(User::getEmail)
.findFirst()
.get(); // ❌ dangerous
}

The bug:
.findFirst() returns an Optional<String>
.get() on an empty Optional throws NoSuchElementException

If no active users exist → Optional is empty → .get() crashes

The fix:
.orElse(null)         → return null if not found
.orElse("")           → return empty string if not found
.orElseThrow(...)     → throw meaningful exception


// ✅ safe version
return users.stream()
.filter(User::isActive)
.map(User::getEmail)
.findFirst()
.orElse(null); // return null if no active user found

