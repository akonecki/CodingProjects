import java.util.Iterator;

public class ivq5 implements Iterable<Integer> {
    private Node head = null;
    private int count = 0;

    private class Node {
        private Integer value = null;
        private Node next = null;

        public Node(Integer value) {
            this.value = value;
        }
    }

    private class LinkedListIterator implements Iterator<Integer> {
        private Node mNode = head;
        
        public Integer next() {
            Node node = this.mNode;

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

    private Node insert(Node head, Integer value) {
        if (head == null) {
            this.count++;
            return new Node(value);
        }
        else {
            head.next = insert(head.next, value);
            return head;
        }
    }

    public int size() {
        return this.count;
    }

    public void insert(Integer value) {
        if (value == null) {
            throw new java.lang.IllegalArgumentException("Null value detected.");
        }

        this.head = this.insert(this.head, value);
    }

    public Iterator<Integer> iterator() {
        return new LinkedListIterator();
    }

    public void add(ivq5 num1, ivq5 num2) {
        if (num1 == null || num2 == null) {
            throw new java.lang.IllegalArgumentException("Invalid argument inputs.");
        }
        
        this.head = this.add(num1.head, num2.head, 0);
    }

    private Node add(Node num1, Node num2, int carry) {
        Node sum = null;

        if (num1 != null && num2 != null) {
            sum = new Node((num1.value + num2.value + carry) % 10);
            carry = (num1.value + num2.value + carry) / 10;
            sum.next = add(num1.next, num2.next, carry);
        }
        else if (num1 != null) {
            sum = new Node((num1.value + carry) % 10);
            carry = (num1.value + carry) / 10;
            sum.next = add(num1.next, null, carry);
        }
        else if (num2 != null) {
            sum = new Node((num2.value + carry) % 10);
            carry = (num2.value + carry) / 10;
            sum.next = add(null, num2.next, carry);
        }
        else if (carry != 0) {
            sum = new Node(carry % 10);
            carry = carry / 10;
            sum.next = add(null, null, carry);
        }

        return sum;
    }

    public static void main(String [] args) {
        ivq5 num1 = new ivq5();
        ivq5 num2 = new ivq5();
        num1.insert(1);
        num1.insert(1);
        num1.insert(1);
        num1.insert(9);

        num2.insert(2);
        num2.insert(2);
        num2.insert(2);
        num2.insert(2);
        
        ivq5 sum = new ivq5();
        sum.add(num1, num2);

        for (Integer integer : sum) {
            System.out.print(integer + " ");
        }

        System.out.println("");
    }
}