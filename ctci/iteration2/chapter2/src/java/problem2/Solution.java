public class Solution {

    private static class LinkedListNode<T> {
        private T data;
        private LinkedListNode<T> next;
    }

    // O(N) - two pointer system, first move a pointer ahead by k.  Then add the
    // second pointer and move both by one until the ahead pointer is on the last 
    // element in the linked list.

    // Test Cases
    // 1-2-3-4-5-6-7
    // 1
    // 2
    // 7
    // 8
    public static <T> T returnKthToLast(LinkedListNode<T> head, int k) {
        // No elements.
        if (k < 1) {
            return null;
        }
        
        LinkedListNode<T> node = head;

        while (node != null && node.next != null && k > 1) {
            node = node.next;
            k--;
        }

        // Make sure the linked list had enough room.
        if (k != 1 || node == null) {
            return null;
        }

        LinkedListNode<T> slow = head;
        while (node != null && node.next != null) {
            node = node.next;
            slow = slow.next;
        }
        
        return slow.data;
    }
    
    public static void main(String [] args) {

    }
}