public static String findFirstDuplicateUsername(List<String> usernames)
↑       ↑      ↑                              ↑      ↑
|       |      |                              |      |
|       |      |                              |      └── parameter name (your variable)
|       |      |                              └── List that only holds Strings
|       |      └── return type (this method gives back a String)
|       └── no object needed to call this method
└── anyone can call this method



Set<String> seen = new HashSet<>();
↑    ↑      ↑         ↑
|    |      |         └── the actual implementation (HashSet)
|    |      └── variable name
|    └── this set will only hold Strings
└── the type (Set is the interface)


- HashSet        → stores items in no particular order
fast, but output order is unpredictable

- LinkedHashSet  → stores items in insertion order
slightly more memory, but output is predictable


- HashSet        → a bag, you throw things in, no idea what order they come out
- LinkedHashSet  → a queue, things come out in the order they went in