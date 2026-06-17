import java.util.*;

public class General {

    public static void main(String[] args) {

        // ── HashMap: count how many times each name appears ──
        List<String> names = Arrays.asList(
                "Alice", "Bob", "Alice", "Mary", "Bob", "Alice"
        );

        Map<String, Integer> frequency = new HashMap<>();

        for (String name : names) {
            // getOrDefault:
            // if key exists   → return its current count
            // if key missing  → return 0
            // then add 1 and store it back
            frequency.put(name, frequency.getOrDefault(name, 0) + 1);
        }

        System.out.println("Frequencies: " + frequency);
        // {Alice=3, Bob=2, Mary=1}

        // ── HashSet: find duplicates ──
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new LinkedHashSet<>();

        for (String name : names) {
            if (!seen.add(name)) {      // add() returns false = duplicate
                duplicates.add(name);
            }
        }

        System.out.println("Duplicates: " + duplicates);
        // [Alice, Bob]

        // ── HashSet: blacklist check — O(1) lookup ──
        Set<String> blacklist = new HashSet<>();
        blacklist.add("Bob");
        blacklist.add("Mary");

        int blockedCount = 0;
        for (String name : names) {
            if (blacklist.contains(name)) { // O(1) per lookup
                blockedCount++;
            }
        }

        System.out.println("Blocked count: " + blockedCount); // 3
    }
}