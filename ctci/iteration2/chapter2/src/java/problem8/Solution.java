public class Solution {
    private static class LinkedListNode <T> {
        private T data;
        private LinkedListNode<T> next = null;
    }

    // O(N) - two pointer system of fast and slow.  if a Null is hit then the
    // list is not circular.  If no null is hit then a overlap of both the
    // fast and slow will occur at some point, stop when this does.  Now
    // reset one of the pointers back to the beginning and increment again
    // by one for both of the pointers.  When they hit again then that node
    // is the start of the loop.  This is due to the modulus of the loop 
    // w.r.t the starting distance of the non-loop.
    public static <T> LinkedListNode<T> startOfLoop(LinkedListNode<T> list) {
        LinkedListNode<T> slow = list, fast = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            // Not a looping linked list.
            return null;
        }

        assert (fast == slow);

        fast = list;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}