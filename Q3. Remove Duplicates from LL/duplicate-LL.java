// Linked List
// think of a treasure hunt
// Each clue doesn't tell you all the locations at once - it just tells you the CURRENT location,
// and where THE NEXT CLUE is. Thats how a linekd list works.
// [value:1 | next:->] (node 1) -> [value:2 | next: ->] (node 2) -> [value:3 | next:null] (node 3)
// every node holds 2 things:
// 1. value -> the actual data (the treasure at this location)
// 2. next -> pointer to the next node (where to go next)
// when the next is null - meaning the hunt is over, no more nodes.

// Arrays - the items sit next to each other in memory, like seats in a row
// [1][2][3][4]
// you can jumo directly to index 3
// Linked lists - items are scattered in memory, conencted by pointers
//[1] -> ....somewhere in memory ... [2]-> .... somewhere else.... [3|null]

//You can only start from the head and follow the chain, no jumping to index 3 directly.

// this is the building block, every linked list is made of nodes.

class ListNode {
    int value; // the data this node carries
    ListNode next; // the address of the next node (null if last)

    ListNode(int value) {
        this.value = value; // set the data
        this.next = null; // no next node yet by default
    }
}

// when you write
ListNode node = new ListNode(5);
// in the memory you create this
[value:5|next:null]

// building a linked list
ListNode head = new ListNode(1); // head is always the first node
head.next =new

ListNode(2); // 1 points to 2

head.next.next =new

ListNode(3) // 2 points to 3
// result
// 1 -> 2 -> 3 -> null
// head is special, its is the entry point, you always start from head and follow next pointers
// to traverse the list. Lose head and lose the entire list.


// TRAVERSING A LINKED LIST
// point the ListNode to the head
ListNode current = head; // start at the beginning

while(current !=null){ // keep going until end

        System.out.println(current.value);  // print out the current node

current =current.next; // move to the next node
//
}

//Q3. Remove duplicates from a linked list.
//Input:  1 -> 2 -> 3 -> 2 -> 4 -> 3
//Output: 1 -> 2 -> 3 -> 4


// to remove a duplicate value, you skip over it, making the previous node point to the other one
// after it
// the skipped node will still exists in memory but nothing points to it anymore.

// PLAN
//walk through the list. For each node:
//1. if we've seen its value before -> skip/remove it
//2. If we haven't seen it -> keep it, add value to our hashset

//we need:
// currentNode -> this node we're looking at right now
// previousnode -> the node just before current
// seen -> HashSet remembering values we've already visited
//
import java.util .*;

class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        // the next is null by default
    }

}

public class RemoveLinkedListDuplicates {

    public static ListNode removeDuplicates(ListNode head) {
        // empty list or single node - nothing to deduplicate

        if (head == null || head.next == null) {
            return head;
        }

        // a hashset for memory and tracking duplicates
        set<Integer> seen = new HashSet<>();

        ListNode currentNode = head; // node we're currently looking at.
        ListNode previousNode = null; // node just before current

        // seen.add() returns false if value already is set.
        // add head's value first, mark it as visited.

        seen.add(head.value);
        // start traversing from second node.

        currentNode = head.next;
        previousNode = head;


        while (currentNode != null) {
            if (!seen.add(currentNode.value)) {
                // seen.add () // returns false
                // this value already exists in our set
                // -> this is a duplicate, SKIP this node

                // make previous previous node jump over current node
                // effectively removing current from the list.

                previousNode.next = currentNode.next;

                // previousNode stays where it is
                // because we removed current, previous
                // now needs to check the next node too


            } else {
                // seen.add() returned true
                // first time seeing this value -> keep this node
                //move previousNode forward to current
                previousNode = currentNode
            }
            // head never changed - still points to start of cleaned list
            return head;
        }

        // helper to print the list
        public static void printList (ListNode head){
            ListNode current = head;
            while (current != null) {
                System.out.print(current.value);
                if (current.next != null) System.out.print(" -> ");
                current = current.next;
            }
            System.out.println();
        }

    }


}


// groupuserbyDepartment
public static Map<String, List<String>> groupByDepartment(List<Employee> employees) {

    if (employees == null || employees.size() <= 1) {
        return new HashMap<>();
    }


    return new HashMap<>();
}