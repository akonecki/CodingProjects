import java.util.Iterator;

public class ivq7 <T> {
    private Node<T> head = null;
    private int count = 0;

    private class Node<T> {
        private T value = null;
        private Node<T> next = null;

        public Node() {}

        public Node(T value) {
            this.value = value;
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

    public boolean isPalindrom() {
        if (this.head == null) {
            return false;
        }

        Node<T> prev = new Node<T>(this.head.value);
        Node<T> current = null, node = this.head.next;

        while (node != null) {
            current = new Node<T>(node.value);
            current.next = prev;
            prev = current;
            node = node.next;
        }

        node = this.head;

        while (prev != null && node != null) {
            if (!prev.value.equals(node.value)) {
                return false;
            }

            prev = prev.next;
            node = node.next;
        }

        if (prev != null && node != null) {
            return false;
        }

        return true;
    }

    public static void main(String [] args) {
        ivq7<Integer> integers = new ivq7<Integer>();

        integers.insert(1);
        integers.insert(2);
        integers.insert(4);
        integers.insert(1);

        System.out.println(integers.isPalindrom());
    }
}