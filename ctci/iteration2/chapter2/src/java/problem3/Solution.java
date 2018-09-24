public class Solution {

    private static class LinkedListNode<T> {
        private T data;
        private LinkedListNode<T> next;
    }

    // O(N) - this one is a bit regulated as bad idea in general so goes more on
    // the tricky side.  Since dont have access to the other nodes ahead of it 
    // in the list, can move all the data values up, and remove the tail node.
    // The reason why the tail node will never be given is due to the fact that
    // this algorithm can not be performed on it.
    public static <T> void removeNodeFromList(LinkedListNode<T> root) {
        LinkedListNode<T> prev = null;
        LinkedListNode<T> node = root;
        
        while (node != null && node.next != null) {
            node.data = node.next.data;
            prev = node;
            node = node.next;
        }

        prev.next = null;
        node = null;
    }
}