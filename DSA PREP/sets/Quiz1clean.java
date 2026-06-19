package sets;

//Given a list of email addresses, write a method that returns true if any email appears more than once, false if all emails are unique.
//Input:  ["ryan@gmail.com", "alice@gmail.com", "ryan@gmail.com"]
//Output: true
//
//Input:  ["ryan@gmail.com", "alice@gmail.com", "bob@gmail.com"]
//Output: false

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// a class never takes parameters. Only methods take parameters
public class Quiz1clean {

    // the method below has a return type of boolean hence we should return true or false
    // overall method will have a time complexity of O(n) because you loop through every email once.
    // O(1) is correct for each individual Hashset ops inside the loop
    public static boolean checkEmails (List<String> emails){

        if (emails == null || emails.size() <=1) {
            return false;
        }
        // declare a hashset to track the seen mails
        Set<String> seenEmails = new HashSet<>();

        for(String mail: emails){
//            .add returns false if email already exists in set
            // hence you've to inverse it to return true if duplicate is found.

            if (!seenEmails.add(mail)){
                return true;
            }


        }

        return false; //looped through everything, nothing appeared twice.


    }

    // main function where we call the method and pass our data.
    public static void main(String[] args){

        // Test 1 — has duplicate
        List<String> emails1 = Arrays.asList(
                "ryan@gmail.com",
                "alice@gmail.com",
                "ryan@gmail.com"   // duplicate
        );
        System.out.println("Test 1: " + checkEmails(emails1)); // true

        // Test 2 — all unique
        List<String> emails2 = Arrays.asList(
                "ryan@gmail.com",
                "alice@gmail.com",
                "bob@gmail.com"
        );
        System.out.println("Test 2: " + checkEmails(emails2)); // false

        // Test 3 — empty list
        List<String> emails3 = Arrays.asList();
        System.out.println("Test 3: " + checkEmails(emails3)); // false
    }



}
