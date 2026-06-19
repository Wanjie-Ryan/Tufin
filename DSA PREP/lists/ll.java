package lists;

import java.util.LinkedList;
import java.util.List;

public class ll {

    public static void main(String[] args){
        // create a linkedlist
        List<String> names = new LinkedList<>();

        // add to end - O(1)
        names.add("Alice");
        names.add("Bob");
        names.add("Mary");

        // add to beginning - O(1)
        ((LinkedList<String>) names).addFirst("Adam");
        System.out.println(names); // [Ryan, Alice, Bob, Mary]

        // add to end explicitly - O(1)
        ((LinkedList<String>) names).addLast("Jael");
        System.out.println(names); // [Ryan, Alice, Bob, Mary, Jael]

        // remove from beginning - O(1)
        ((LinkedList<String>) names).removeFirst();
        System.out.println(names); // [Alice, Bob, Mary, Jael]


        // acess by index - O(n) - must walk from start
        System.out.println(names.get(0)); // Alice

        for(String name: names) {
            System.out.println(name);

        }


    }
}

// each node is an object with 2 parts, data and a pointer to the next node
// head
// ↓
//[Alice | •]——→[Bob | •]——→[Mary | null]

// ADDING TO THE BEGINNING - O(1)
//New node [Ryan] just needs to point to current head
//No shifting needed at all
//
//[Ryan | •]——→[Alice | •]——→[Bob | •]——→[Mary | null]
//        ↑
//        new head

// FINDING ITEM AT INDEX 2 - O(n)
//Start at head → Alice (0)
//Follow pointer → Bob (1)
//Follow pointer → Mary (2) → found
//
//Had to walk the whole way. Can't skip.



