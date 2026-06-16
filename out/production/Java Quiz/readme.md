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