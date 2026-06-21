package streams;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class streams {
//
//    // finding active users, and adding their emails to the activeEmails arrayList
//    // traditional loop
////    List<String> activeEmails = new ArrayList<>();
////    for (User user: users){
////        if(user.isActive()){
////            activeEmails.add(user.getEmail());
////        }
////    }
//
//    // using streams
////    List<String> activeEmails = users.stream().filter(User::isActive).map(User::getEmail).collect(Collectors.toList());
////    .stream -> start the stream
////            .filter -> keep only items matching condition
////            .map -> transform each item
////            .collect -> gather results back into collection
//
//}


import java.util.*;
import java.util.stream.*;

class User {
    String name;
    String email;
    String department;
    boolean active;

    User(String name, String email, String department, boolean active) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.active = active;
    }

    public boolean isActive() { return active; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
}

public class streams {
    public static void main(String[] args) {

        List<User> users = Arrays.asList(
                new User("Alice",   "alice@test.com",   "Engineering", true),
                new User("Bob",     "bob@test.com",     "Marketing",   false),
                new User("Charlie", "charlie@test.com", "Engineering", true),
                new User("Diana",   "diana@test.com",   "HR",          true),
                new User("Eve",     "eve@test.com",     "Marketing",   false)
        );

        // 1. get emails of active users only
        List<String> activeEmails = users.stream()
                .filter(User::isActive)
                .map(User::getEmail)
                .collect(Collectors.toList());
        System.out.println("Active emails: " + activeEmails);

        // 2. count inactive users
        long inactiveCount = users.stream()
                .filter(user -> !user.isActive())
                .count();
        System.out.println("Inactive count: " + inactiveCount);

        // 3. get all names sorted alphabetically
        List<String> sortedNames = users.stream()
                .map(User::getName)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted names: " + sortedNames);

        // 4. group by department
        Map<String, List<User>> byDept = users.stream()
                .collect(Collectors.groupingBy(User::getDepartment));
        byDept.forEach((dept, members) ->
                System.out.println(dept + " → " + members.stream()
                        .map(User::getName)
                        .collect(Collectors.toList()))
        );

        // 5. count per department
        Map<String, Long> countByDept = users.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("Count by dept: " + countByDept);
    }
}