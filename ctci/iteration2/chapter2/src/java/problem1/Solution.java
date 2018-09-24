import java.util.HashSet;

public class Solution {

    private class LinkedListNode {
        private int data;
        private LinkedListNode next;
    }

    // O(N^2) - just rebuild the linked list one element at a time effectively
    // removing from the original list and creating a new list.  Iterate through
    // the new list and add only if it gets to the end of the new list without
    // finding a duplicate.

    // O(NlgN) - sort the linked list, then remove if neighbor is equal to the 
    // current node.  Would need to be careful in the sorting if want to prevent
    // the usage of extra space.  If using a true linked list this may be quite
    // difficult.

    // O(N^2) - iterate through the linked list one element at a time and 
    // perform forward inspection only from the specific pivot.  Any found
    // duplicates can be removed.

    // O(N) - create a set of all the values within the linked list while 
    // traversing.  At each node see if the value already exist if does remove.
    public static void removeDuplicates(LinkedListNode root) {

    }

    private static void removeDuplicatesHash(LinkedListNode root) {
        HashSet<Integer> seenValues = new HashSet<>();
        LinkedListNode node = root;
        LinkedListNode prev = null;

        while (node != null) {
            if (seenValues.contains(node.data)) {
                // need to remove.
                prev.next = node.next;
                node.next = null;
                node = prev.next;
            }
            else {
                seenValues.add(node.data);
                prev = node;
                node = node.next;
            }
        }
    }

    private static void removeDuplicatesTwoPointers(LinkedListNode root) {
        LinkedListNode prev = null, node = root;

        while (node != null) {
            LinkedListNode next = node.next;
            prev = node;

            while (next != null) {
                if (next.data == node.data) {
                    prev.next = next.next;
                    next.next = null;
                    next = prev.next;
                }
                else {
                    prev = next;
                    next = next.next;
                }
            }

            node = node.next;
        }
    }

    public static void main(String [] args) {

    }
}