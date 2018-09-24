public class Solution {
    public static class LinkedListNode {
        private char data;
        private LinkedListNode next;
    }
    
    // O(N) - use two pointer system to first transform the list from the 
    // halfway point and put into reverse.  Then compute the palindrome
    // by two pointer from the reversed list to the start of the original.
    // Then switch the list back in place.
    public static boolean isPalindrome(LinkedListNode root) {
        boolean result = true;
        LinkedListNode reversedHead = null, reversedTail = null;
        LinkedListNode tail = null;

        LinkedListNode slow = root, fast = root, node = null;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        tail = slow;
        // all values after tail will now be reversed.
        node = slow.next;
        reversedTail = node;
        LinkedListNode prev = null;

        while (node != null) {
            LinkedListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        // second half is now reversed.  Can now perform palindrome check with 
        // two pointer system.
        reversedHead = prev;

        slow = root;
        fast = reversedHead;

        while (slow != null && fast != null) {
            if (slow.data != fast.data) {
                result = false;
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }

        // Now need to fix the second half.
        node = reversedHead;
        prev = null;

        while (node != null) {
            LinkedListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        tail.next = prev;

        return result;
    }
}