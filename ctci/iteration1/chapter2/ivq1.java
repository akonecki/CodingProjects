import java.util.Iterator;

public class ivq1 <T> implements Iterable <T> {
    private Node<T> head = null;
    private int count = 0;

    private class Node<T> {
        private T value = null;
        private Node<T> next = null;

        public Node(T value) {
            this.value = value;
        }
    }

    private void removeDuplicates(Node<T> head) {
        if (head == null) {
            return;
        }

        Node<T> pivot = head, prev = null, next = null;

        while (pivot != null) {
            prev = pivot;
            next = pivot.next;
            while (next != null) {
                if (pivot.value.equals(next.value)) {
                    prev.next = next.next;
                    next.next = null;
                    next.value = null;
                    next = prev.next;
                    this.count--;
                }
                else {
                    prev = next;
                    next = next.next;
                }
            }

            pivot = pivot.next;
        }
    }

    private Node<T> insert(Node<T> head, T value) {
        if (head == null) {
            this.count++;
            return new Node<T>(value);
        }
        else {
            head.next = insert(head.next, value);
            return head;
        }
    }

    public int size() {
        return this.count;
    }

    public void insert(T value) {
        if (value == null) {
            throw new java.lang.IllegalArgumentException("Null value detected.");
        }

        this.head = this.insert(this.head, value);
    }

    public void removeDuplicates() {
        this.removeDuplicates(this.head);
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> mNode = head;
        
        public T next() {
            Node<T> node = this.mNode;

            if (node == null) {
                throw new java.lang.IllegalArgumentException("Calling next on empty.");
            }

            this.mNode = this.mNode.next;

            return node.value;
        }
        public void remove() {
            throw new java.lang.IllegalArgumentException("Un-supported operation.");
        }
        public boolean hasNext() {
            return (this.mNode != null);
        }
    }

    public static void main(String [] args) {
        ivq1<Integer> integers = new ivq1<Integer>();
        integers.insert(1);
        integers.insert(2);
        integers.insert(1);
        integers.insert(1);
        integers.insert(1);
        integers.insert(1);
        integers.insert(1);

        System.out.println("Size is " + integers.size());

        for (Integer integer : integers) {
            System.out.println(integer);
        }

        integers.removeDuplicates();
     
        System.out.println("Size is " + integers.size());

        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}