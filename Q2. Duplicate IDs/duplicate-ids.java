//Q2. Find all duplicate IDs in a list of integers and return them as a list.
//Input:  [1, 2, 3, 2, 4, 3, 5]
//Output: [2, 3]

//Wrong implementation here
//public class Main {
//
//
//    public static List<Integer> findDuplicateIDs(List<Integer> ids) {
//
//        if (ids == null || ids.size() <= 1) {
//            return null;
//
//        }
//
//        Set<Integer> duplicateIds = new HashSet<>();
//
//        for (Integer id : ids) {
//            if (id == null) {
//                continue;
//            }
//
//            if (!duplicateIds.add(id)) {
//                return List < id >
//            }
//        }
//
//        return null;
//    }
//
//    public static void main(String[] args) {
//
//    }
//
//
//}
// CORRECT IMPLEMENTATION BELOW

//the keyword here is that it should return a List,
//not a single value,
//therefore one hasset will be fore checking duplicates,
//and then store these duplicates in another hashset

import java.util.*;

public class Main {

    public static List<Integer> findDuplicateIds(List<Integer> ids) {

        if (ids == null || ids.size() <= 1) {
//            return null;
            return new ArrayList<>();
        }

        // first hashset to track duplicates entered
        Set<Integer> seenDeuplicates = new HashSet<>();

        // second hashset that now collects and stores the duplicate ids
        // LinkedHashset preserves the order we found them in.

        Set<Integer> storeDuplicates = new LinkedHashSet<>();

        for (Integer id : ids) {
            // skip null entries
            if (id == null) {
                continue;
            }
            if (!seenDeuplicates.add(id)) {
                // seen.add returned false -> we've seen this id before
                // get the duplicate ids and add them to the second hashset
                storeDuplicates.add(id);
            }
            // if seen.add returned true -> first time seeing it, keep going.


        }

        // convert to list and return
//        we wrap in ArrayList because our return type is List<Integer>
        return new ArrayList<>(storeDuplicates);


    }


}
