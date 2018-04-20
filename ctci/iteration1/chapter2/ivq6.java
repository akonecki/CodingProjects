public class ivq6 <T> {
    private Node<T> mCircularLinkedList = null;

    public class Node <T> {
        private T value = null;
        private Node<T> next = null;
    }

    public Node <T> getLoopHead() {
        Node <T> fast = null, slow = null;

        if (this.mCircularLinkedList == null) {
            return null;
        }

        fast = this.mCircularLinkedList.next.next;
        slow = this.mCircularLinkedList;

        while (fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Move slow back to the head.
        slow = this.mCircularLinkedList;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}