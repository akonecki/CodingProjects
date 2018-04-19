import java.util.Iterator;

public class ivq3 <T> implements Iterable <T> {
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

    private Node<T> delete(Node<T>head, T value) {
        if (head == null) {
            return null;
        }

        if (head.value.equals(value)) {
            Node<T> temp = head.next;
            head.next = null;
            head.value = null;
            this.count--;
            return temp;
        }
        else {
            head.next = this.delete(head.next, value);
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

    private void delete(Node<T> node) {
        if (node == null) {
            return;
        }
        
        if (node == this.head) {
            this.head = node.next;
            this.count--;
            node.value = null;
            node.next = null;
            return;
        }
        else {
            Node<T> prev = node;
            Node<T> next = node.next;

            // Need to perform value shifting.
            while (next != null) {
                prev.value = next.value;
                
                // Don't want zombie pointer to going to be removed node.
                if (next.next == null) {
                    prev.next = null;
                }

                prev = next;
                next = next.next;
            }

            // prev is at the end of the linked list
            // next is at null
            // don't need the last node any more
            prev.next = null;
            prev.value = null;

            // Will having dangling pointer if node is the tail of the linked 
            // list caller would have to clean it up & will not be garabage 
            // collected until this is performed.
            this.count--;
        }
    }

    public void delete(T value) {
        if (value == null) {
            return;
        }

        this.head = this.delete(this.head, value);
    }

    public static void main(String [] args) {
        ivq3<Integer> integers = new ivq3<Integer>();
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
     
        integers.delete(2);

        System.out.println("Size is " + integers.size());

        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}