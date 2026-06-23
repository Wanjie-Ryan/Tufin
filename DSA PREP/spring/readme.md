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
- 