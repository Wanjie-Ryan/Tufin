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