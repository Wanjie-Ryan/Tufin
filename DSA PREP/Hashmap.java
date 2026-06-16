import java.util.HashMap;
import java.util.Map;


public class Hashmap{
    public static void main(String[] args){

        // create a hashmap
        // key type -> string (name)
        // value type -> integer (age)

        Map<String, Integer> ages = new HashMap<>();

        // add entries using .put(key, value)
        ages.put("Adam", 33);
        ages.put("Jael", 15);

        // retrieve a value using .get(key)
        System.out.println(ages.get("Adam")); // 33
        System.out.println(ages.get("Jael")); // 15

        // check if a key exists
        System.out.println(ages.containsKey("Adam")); // true
        System.out.println(ages.containsKey("Wanjie")); // false

        // check if a value exists
        System.out.println(ages.containsValue(15)); // true

        // remove an entry
        ages.remove("Adam");
        // now check if the key exists using the countainsKey method
        System.out.println(ages.containsKey("Adam")); // false

        // how many entries
        System.out.println(ages.size()); // 1

        // looping through all entries

        for(Map.Entry<String, Integer> entry : ages.entrySet()){
            System.out.println(entry.getKey() + " is " + entry.getValue() + " years old ");
        }


    }
}