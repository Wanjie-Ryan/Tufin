// Find the first duplicate username in a list of strings.
//Input:  ["alice", "bob", "charlie", "alice", "david"]
//Output: "alice"

// STEP 1:: Think before you code
// How do i know if sth has happened before?
// You need a memory - something that remembers what you've already seen as you walk through the list.
// That memory is a HashSet. Why HashSet?
//1. Adding to it is O(1)
//2. Checking if sth exists is O(1)
//3. It does not allow duplicates - perfect for tracking "have i seen this before?"

// STEP 2:: The mental model
// walk through the list one by one, for each username:
// "alice" -> never seen before -> add to set > set: [alice]
// "bob" -> never seen before -> add to set -> set: [alice, bob]
// "charlie" -> never seen before -> add to set: [alice, bob, charlie]
// "alice" -> ALREADY IN SET -> return "alice"

// STEP 3:: KEY TRICK
//HashSet.add() returns a boolean
// true -> item was added successfully (wasn't there before)
// false -> item was not added (already existed)

// STEP 4:: CODE

import java.util.*;

public class Main {

    public static String findFirstDuplicateUsername(List<String> usernames) {
        // guard clause - if list is null or has 1 item
        // duplicates are impossible, return null immediately

        if (usernames == null || usernames.size() <= 1) {
            return null;
        }

        // our memory tracks every username we've walked past
        Set<String> seen = new HashSet<>();

        for (String username : usernames) {
            // skip null entries so we don't crash
            if (username == null) {
                continue;
            }
            // seen.add returns false if username already exists in set, meaning we've seen it before -> its our first duplicate

            if (!seen.add(username)) {
                return username;
            }
            // if add() returned true, username was new -> keep going
        }

        // walked the entire list, nothing appeared twice, return null
        return null;


    }

    public static void main(String[] args) {
        List<String> test1 = Arrays.asList("alice", "bob", "charlie", "alice", "david");
        System.out.println("Test 1: " + findFirstDuplicateUsername(test1));
    }


}

// Difference btn a hashset and Hashmap
//- set stores VALUES only while map stores KEY-VALUE pairs
//- set is used used when you just need to remember "have i seen this?" while map is used when
// you need to remember "how many times/what data?"
// set -> {"alice", "bob"} map -> {"alice" =2, "bob"=1}