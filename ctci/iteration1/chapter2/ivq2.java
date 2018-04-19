import java.util.Iterator;

public class ivq2 <T> implements Iterable <T> {
    private Node<T> head = null;
    private int count = 0;

    private class Node<T> {
        private T value = null;
        private Node<T> next = null;

        public Node(T value) {
            this.value = value;
        }
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

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public T getKthElementFromTail(int k) {
        Node<T> leading = null, tailing = null;

        if (k < 0 || this.head == null) {
            return null;
        }

        leading = this.head;

        while (k > 0 && leading != null) {
            leading = leading.next;
            k--;
        }

        if (k != 0) {
            return null;
        }

        tailing = this.head;

        while (leading != null && leading.next != null) {
            leading = leading.next;
            tailing = tailing.next;
        }

        return tailing.value;
    }

    public static void main(String [] args) {
        ivq2<Integer> integers = new ivq2<Integer>();
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
     
        System.out.println(integers.getKthElementFromTail(5));
    }
}