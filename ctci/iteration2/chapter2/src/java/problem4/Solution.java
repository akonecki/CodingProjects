import java.util.Comparator;

public class Solution {
    private static class LinkedListNode<T extends Comparable<T>> {
        private T data;
        private LinkedListNode<T> next;
    }
    
    // O(N) - two pointer system will be needed to manage the two partitions 
    // from the original list.  One will be the last less than node, the other
    // will be greater or equal to the specified value.
    // will need to maintain the heads for each partition.
    // have the left parition next be set to the head of the second partition.
    // return the head of the first partition if not null, else return head of
    // second partition.
    public static <T extends Comparable<T>> LinkedListNode<T> parition(
        LinkedListNode<T> root, 
        T value) 
    {
        LinkedListNode<T> lessHead = null, lessTail = null;
        LinkedListNode<T> moreHead = null, moreTail = null;
        LinkedListNode<T> node = root;

        while (node != null) {
            LinkedListNode<T> next = node.next;
            node.next = null;

            if (node.data.compareTo(value) < 0) {
                if (lessHead == null) {
                    lessHead = node;
                    lessTail = node;
                }
                else {
                    lessTail.next = node;
                    lessTail = node;
                }
            }
            else {
                if (moreHead == null) {
                    moreHead = node;
                    moreTail = node;
                }
                else {
                    moreTail.next = node;
                    moreTail = node;
                }
            }
            
            node = next;
        }
        
        if (lessTail != null) {
            lessTail.next = moreHead;
            return lessHead;
        }
        else {
            // No values were changed since all equal to or greater than 
            // specified
            return root;
        }
    }
}