# Data Structure 
- Way of organizing and storing data so you can use it efficiently.

1. list - shopping list, items in order, one after another.
2. Hashmap - Dictionary, you look up for a word and get its definition. (key-value)
3. Hashset - a bag that rejects duplicates, **UNIQUE** items only

1. **HASHMAP**

- Stores data as **key-value** pairs.
- Key -> what you search by.
- Value -> What you get back.

- Think of a phone book:
- Alice -> 07123678420
- **Alice** is the **key** while the **number** is the **value**
- You look up a name, you get a phone number back.

# HashMap Rules
1. Keys must be unique
- if you put the same key twice, the second value overwrites the first.
2. Values can be duplicated.
- Two keys can have the same value.
3. One key can have one value only
4. Order is NOT guaranteed

# How does hashmap work internally?
- When you call ages.put("Alice", 25), Java does this internally
1. Take the key 'Alice'
2. Run it through a hash function -> produces a number eg. 12345
3. Use that number to find a bucket (slot in memory)
4. Store "Alice" -> 25 in that bucket.

# REPRESENTATION VISUALLY

Key      hashCode()    Bucket
"Alice"  →  12345   →  [bucket 5]  stores ("Alice", 25)
"Bob"    →  67890   →  [bucket 2]  stores ("Bob", 30)
"Mary"   →  11111   →  [bucket 7]  stores ("Mary", 28)

- When you call ages.get("Alice):
1. Take the key "Alice"
2. Run it through the same has function -> 12345
3. Go directly to bucket 5 in memory
4. Return 25
- No searching, no looping. Jump straight to the answer. That's why its fast.

# Hash Collision
- Sometimes 2 different keys produce the same bucket location.
  "Alice"  →  hashCode 12345  →  bucket 5
  "David"  →  hashCode 12345  →  bucket 5  ← same bucket!
- Java handles it by storing multiple entries in the same bucket as a **list**, then using .equals() to find the right 
  one.

bucket 5: [("Alice", 25), ("David", 40)]
→ search by .equals() to find exact match

- This is why collisions slow things down - instead of jumping directly, Java now has to search within the bucket.

# When to use a Hashmap
Use HashMap when:
✅ You need to look something up by a key
✅ You need to count how many times something appears
✅ You need to group data
✅ You need to cache results
✅ You need key → value relationships

// 1. Count word frequencies
Map<String, Integer> wordCount = new HashMap<>();
wordCount.put("hello", 3);
wordCount.put("world", 1);

// 2. User lookup by ID
Map<String, User> userById = new HashMap<>();
userById.put("user123", new User("Alice"));

// 3. Caching — store results you've already computed
Map<Integer, Integer> cache = new HashMap<>();

2. HashSet

- Stores values only - no key, no value pair.
- Just a collection of unique items.

Think of a guest list at an event:

["Alice", "Bob", "Mary"]

If Alice tries to get in twice, the bouncer says NO.
No duplicates allowed. Ever.

That is exactly how a HashSet works.

# How does a HashSet work internally?
- Hashset is just a Hashmap underneath.
- When you add "Alice" to a hashset:
  - HashSet stores:  "Alice" →                  (dummy value)
                        ↑                              ↑
                        key                         placeholder, ignored

- it uses the same hashing mechanism as Hashmap. Thats why its so fast.
- Thats why it rejects duplicates - same key can't exist twice.

# When to use a HashSet
Use HashSet when:
✅ You only need to track existence (have I seen this before?)
✅ You need to remove duplicates
✅ You need fast membership checks (is this item in the collection?)
✅ You DON'T need key → value pairs
✅ You DON'T need ordering

# Examples

// 1. Track visited pages
Set<String> visitedUrls = new HashSet<>();
visitedUrls.add("google.com");
visitedUrls.add("github.com");

// 2. Remove duplicates from a list
List<String> withDuplicates = Arrays.asList("a", "b", "a", "c", "b");
Set<String> unique = new HashSet<>(withDuplicates);
// unique = ["a", "b", "c"]

// 3. Blacklist check
Set<String> blacklist = new HashSet<>();
blacklist.add("spammer@email.com");

if (blacklist.contains(userEmail)) {
System.out.println("Blocked!");
}

# Hashmap VS Hasset

HashMap                 HashSet
What it stores      key → value pairs       values only
Duplicates          keys must be unique     all values unique
Main use            lookup, counting,       existence check,
grouping                duplicate removal
How to add          .put(key, value)        .add(value)
How to find         .get(key)               .contains(value)
Built on            hash table              HashMap internally

# Time & Space complexity
1. Time complexity
- measures how the speed of your code changes as the amount of data grows.

- Write it as O(something) - Big O notation
- You have a task to do.
  How much longer does it take if you double the data?

O(1)   → same time, doesn't matter how much data -> constant time
O(n)   → double the data → double the time -> Linear time
O(n²)  → double the data → FOUR TIMES the time

1. O(1) - Constant time
- No matter how much data you have, it always takes the same amount of time.

// HashMap get — always instant, doesn't matter if 10 or 10 million entries
Map<String, Integer> map = new HashMap<>();
map.get("Alice"); // O(1) — jump directly to bucket
10 entries    → 1 operation
1000 entries  → 1 operation
1M entries    → 1 operation

2. O(n) - Linear Time
- Time grows proportionally with data.
- Double the data -> double the time

// Looping through a list — visit every item once
List<String> names = new ArrayList<>();
for (String name : names) {  // O(n)
System.out.println(name);
}

10 items    → 10 operations
1000 items  → 1000 operations
1M items    → 1M operations

3. O(n²) - Quadratic Time
- Nested loops. For every item, you loop through all items again.
- Double the data -> 4 times the time.

// Nested loop — for every user, check every blocked user
for (String user : users) {           // n iterations
for (String blocked : blockedUsers) { // n iterations each time
if (user.equals(blocked)) { ... }
}
}
// Total: n × n = n²

10 items    → 100 operations
1000 items  → 1,000,000 operations
1M items    → 1,000,000,000,000 operations ← unusable

2. Space Complexity
- How much extra memory your code uses as data grows.

0(1) - uses the SAME memory regardless of input size.
O(n) - memory grows proportionally with input size.

// O(1) space — just 3 variables, no matter how big the list
int count = 0;
int sum = 0;
int max = 0;

// O(n) space — storing every item in a new structure
Set<String> seen = new HashSet<>();
for (String name : names) {
seen.add(name); // set grows with input
}

Operation         Time        Why
─────────────────────────────────────────────────
HashMap.put()     O(1)        hash → jump to bucket
HashMap.get()     O(1)        hash → jump to bucket
HashMap.remove()  O(1)        hash → jump to bucket

HashSet.add()     O(1)        hash → jump to bucket
HashSet.contains() O(1)       hash → jump to bucket
HashSet.remove()  O(1)        hash → jump to bucket

List.contains()   O(n)        must check every item one by one




---------------------------------------------
- Convert a List to a hashset when doing lookups inside a loop.

// BAD — O(n²)
for (String user : users) {
if (blockedList.contains(user)) { // List.contains = O(n)
count++;
}
}

// GOOD — O(n)
Set<String> blockedSet = new HashSet<>(blockedList); // convert once O(n)
for (String user : users) {
if (blockedSet.contains(user)) { // HashSet.contains = O(1)
count++;
}
}

# Lists and LinkedLists
- An array is the most primitive way to store multiple items in java. It has fixed size - once you create it, you 
  cannot change its size.
- // declare an array of size 3
  String[] names = new String[3];

names[0] = "Alice";
names[1] = "Bob";
names[2] = "Mary";

// now try to add a 4th item
names[3] = "Ryan"; // ❌ crashes — ArrayIndexOutOfBoundsException

- Size is locaked at creation
- Need more space? Too bad
1. List
- This is just an interface, An ArrayList and LinkedLists are its implementations.

2. ArrayList
- Dynamic Array, grows and shrinks depending on size.
  Index:  0       1       2       3
  Data: [Alice] [Bob]  [Mary]  [John]

- Each item has a position starting from 0. You can jump directly to any position instantly.
- Operation              Time       Why
  ─────────────────────────────────────────────────────
  get(index)             O(1)       jump directly to position
  add(item) at end       O(1)       just append to end
  add(item) at index 0   O(n)       must shift everything right
  remove(index 0)        O(n)       must shift everything left
  contains(item)         O(n)       must check every item one by one
  size()                 O(1)       stored as a variable

- Insert "Ryan" at position 0:

Before: [Alice][Bob][Mary]
0      1    2

Must shift everything right first:
[     ][Alice][Bob][Mary]
0     1     2    3

Then insert:
[Ryan][Alice][Bob][Mary]
0     1     2    3

3. LinkedLists
- A linkedlist stores data as a chain of nodes. Each node holds:
- Actual data, pointer to the next node.
- [Alice | •]→[Bob | •]→[Mary | •]→[null]
  0            1          2
- there are no indexes. To find item 2, you must walk from the start.
- Start at Alice -> follow pointer -> Bob -> follow pointer -> Mary -> Found it.
- Operation                Time      Why
  ─────────────────────────────────────────────────────
  addFirst() / addLast()   O(1)      just update pointer
  removeFirst()            O(1)      just update pointer
  get(index)               O(n)      must walk from head
  contains(item)           O(n)      must walk from head
  add(item) middle         O(n)      must walk to position first

  - ArrayList VS LinkedList
    ArrayList                                           LinkedList
      ─────────────────────────────────────────────────────
      Internal structure  dynamic array       chain of nodes
      Access by index     O(1) ← winner      O(n)
      Add to end          O(1)               O(1)
      Add to beginning    O(n)               O(1) ← winner
      Remove from middle  O(n)               O(n)
      Memory              less               more (stores pointers too)
      Use when            frequent reads     frequent add/remove
      access by index    at start or end

- Use ArrayList when:
  ✅ You read data more than you write
  ✅ You need to access items by index
  ✅ You're returning results from an API
  ✅ Most day to day use cases

- Use LinkedList when:
✅ You frequently add or remove from the beginning
✅ You're building a queue (first in first out)
✅ You're building a task scheduler
✅ Order of insertion and removal matters more than lookup

- EXCEPTION
- Something unexpected happened and I don't know how to continue.
- You're driving to a destination. Normally you follow the road - that's the normal program flow.
- Suddenly there's a roadblock - that's an exception.

- You have 2 choices:
- Handle it - find a detour and keep going.
- Ignore it - crash into the roadblock

String name = null;
System.out.println(name.length); // crash - NullPointerException.

// java hits null.length(), has no idea wjhat to do, throws an exception, and your program stops.

# Types of Exceptions
1. Checked Exceptions
- These are exceptions Java forces you to handle. If you don't code, your code won't even compile.
- These represent situations that are outside your control
  but you can reasonably recover from.

// Reading a file — file might not exist
// Java forces you to handle this
public void readFile() throws IOException {
FileReader reader = new FileReader("data.txt"); // checked exception
}

IOException        → file operations
SQLException       → database operations
FileNotFoundException → file not found

2. Unchecked exceptions
- Exceptions Java does not force you to handle, they happen at runtime due to programming mistakes.
- // These compile fine but crash at runtime

String name = null;
name.length();              // NullPointerException

int[] arr = new int[3];
arr[10] = 5;                // ArrayIndexOutOfBoundsException

String number = "abc";
Integer.parseInt(number);   // NumberFormatException

int x = 10 / 0;            // ArithmeticException

- NullPointerException         → calling method on null
  ArrayIndexOutOfBoundsException → accessing index that doesn't exist
  NumberFormatException        → parsing "abc" as a number
  ArithmeticException          → dividing by zero
  IllegalArgumentException     → passing invalid argument

- Checked   → Java warns you at compile time
  "you MUST handle this before I let you run"

- Unchecked → Java only discovers it at runtime
"your code runs but then blows up"

// difference btn == and .equals while comparing
== compares memory addresses, are these two variables pointing to the exact same obejct in memory
.equals() compares actual content, do these two strings contain the same characters?
