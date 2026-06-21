# Java Streams
- Streams let you process a collection of data in a pipeline
- Chain of ops applied one after another.

- Instead of writing a loop manually, you describe what you want and java figures out how to do it.

// Traditional loop
List<String> activeEmails = new ArrayList<>();
for (User user : users) {
if (user.isActive()) {
activeEmails.add(user.getEmail());
}
}

// Same thing with Streams
List<String> activeEmails = users.stream() // start the stream
.filter(User::isActive) // keep the active only
.map(User::getEmail) // get their email
.collect(Collectors.toList()); // gather into list

collection.stream()     → start the stream
.filter(...)        → keep only items matching condition
.map(...)           → transform each item
.collect(...)       → gather results back into a collection

// COUNT - count matching items
long activeCount = users.stream().filter(User::isActive).count();

// DISTINCT - remove duplicates
List<String> unique = emails.stream().distinct().collect(Collectors.toList())

// GROUPINGBY() - group into a map
// group users by department
Map<String, List<User>> byDepartment = users.stream().collect(Collectors.groupingBy(User::getDepartment));


- Use Streams when:
✅ filtering a collection
✅ transforming data
✅ grouping data
✅ counting occurrences
✅ code reads more clearly

- Use Loops when:
✅ complex business logic
✅ multiple things happening per iteration
✅ you need to modify external state
✅ streams would make it harder to read