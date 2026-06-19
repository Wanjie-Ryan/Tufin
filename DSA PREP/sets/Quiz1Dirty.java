package sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Given a list of email addresses, write a method that returns true if any email appears more than once, false if all emails are unique.
//Input:  ["ryan@gmail.com", "alice@gmail.com", "ryan@gmail.com"]
//Output: true
//
//Input:  ["ryan@gmail.com", "alice@gmail.com", "bob@gmail.com"]
//Output: false
public class Quiz1Dirty(List<String> emails){ // this is wrong, a class does not take parameters,
    // only methods do

    // use a hashset - cause a hashset reject duplicate values.
    // it can only hold unique values

    // declare a hashset, which we will first insert all the incoming emails via the loop, then ater
    // use the contains keyword to check if the mail exists inside the seenemails hashset and
    // print it out
    // if not found, then print out the unique mails
    // time complexity for this is O(1) I picked linear cause even when the size of List of
    // emails increases, for it to track an email, it will pass the mail through a hashcode and
    // using this hashcode, check if there's a matching hashcode in memory then go pick it and
    // return the value, hence O(1) goes it goes and pick no looping
    // space complexity is O(n), as the size of the list increases it also occupied more memory

    Set<String> seenEmails = new HashSet<>();

    for(String mail: emails){
        seenEmails.add(mail);

        if (seenEmails.contains(mail)){
            System.out.println("Duplicate email found: " + mail);
        } else {
            System.out.println("Email is unique: " + mail);
        }
    }

}
