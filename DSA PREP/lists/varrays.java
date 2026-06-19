package lists;
import java.util.ArrayList;
import java.util.List;

public class varrays {

    public static void main(String[] args){
        // create an ArrayList that holds Strings.
        List<String> names = new ArrayList<>();

        // add items - goes to the end by default
        names.add("Alice");
        names.add("Bob");
        names.add("Mary");

        // accessing by index is O(1) - constant time - just jump straight to the value, just
        // like a hashset.
        System.out.println(names.get(0)); // Alice
        System.out.println(names.get(1)); // Bob

        // size
        System.out.println(names.size()); //3
        names.remove(0);
        System.out.println(names); // names[Bob, Mary]

        System.out.println(names.contains("Mary")); // true


        for (String name: names){
            System.out.println(name);
        }

    }
}
// when you access the Array by index, it jumps directly
// names.get(2) -> go to position 2 -> return Mary -> done
// one operation. O(1) - constant time.


// LINKEDLISTS
