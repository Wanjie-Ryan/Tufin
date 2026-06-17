package Quiz1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Explain what this code does
//What it does
//How it works step by step
//What the time complexity is
//What the space complexity is
//Input:  ["banana", "apple", "banana", "orange", "apple"]
//Output: ???
public class Quiz2 {

    // the mystery function accepts a List of items of type string
//    declare a hashset called seen, and an array that holds String data called result.
    // loop through the list of items which is O(n), as you loop add the looped items inside a
    // hashset seen, add returns false if a value being entered is duplicate, then we store the
    // duplicated values inside the result Array, then return the duplicated results

    // time complexity is O(n) cause if the list grows, the time it takes to compute grows too.

    public static List<String> mystery(List<String> items) {
        Set<String> seen = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (String item : items) {
            if (seen.add(item)) {
                //seen.add() returns true when the item is NEW — not when it's a duplicate.
                result.add(item);
            }
        }
        return result;
    }


}

// RESULT //
//        Input: ["banana", "apple", "banana", "orange", "apple"]
//
//        Step 1 — "banana"
//        seen.add("banana") → returns true (new item)
//        → add "banana" to result
//        seen    = [banana]
//        result  = [banana]
//
//        Step 2 — "apple"
//        seen.add("apple") → returns true (new item)
//        → add "apple" to result
//        seen    = [banana, apple]
//        result  = [banana, apple]
//
//        Step 3 — "banana"
//        seen.add("banana") → returns false (duplicate, rejected)
//        → condition is false, nothing added to result
//        seen    = [banana, apple]
//        result  = [banana, apple]
//
//        Step 4 — "orange"
//        seen.add("orange") → returns true (new item)
//        → add "orange" to result
//        seen    = [banana, apple, orange]
//        result  = [banana, apple, orange]
//
//        Step 5 — "apple"
//        seen.add("apple") → returns false (duplicate, rejected)
//        → condition is false, nothing added to result
//        seen    = [banana, apple, orange]
//        result  = [banana, apple, orange]
