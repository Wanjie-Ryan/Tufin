import java.util.HashSet;
import java.util.Set;

public class Hashset {

    public static void main(String[] args){
        // create a hashset
        // Type = String (the items we're storing)
        Set<String> names = new HashSet<>();

        // add items using .add()
        names.add("Adam");
        names.add("Jael");
        names.add("Adam"); // duplicate, will not be added

        // Size
        System.out.println("Size of the Hashset: " + names.size());

        // check if sth exists - o(1) - instant
        System.out.println("Does Adam exist? " + names.contains("Adam")); // true
        System.out.println("Does Wanjie exist? " + names.contains("Wanjie")); //false

        // remove an item
        names.remove("Jael");
        System.out.println("Does Jael exist? " + names.contains("Jael")); // false

        for(String name : names){
            System.out.println("Printed names: " + name);
        }

    }
}
