public class Node<Key extends Comparable<Key>, Value> {
    private Key key = null;
    private Value value = null;
    private Node leftChild = null;
    private Node rightChild = null;

    public Node(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return this.key;
    }

    public Value getValue() {
        return this.value;
    }

    public Node getLeft() {
        return this.leftChild;
    }

    public Node getRight() {
        return this.rightChild;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setLeft(Node child) {
        this.leftChild = child;
    }

    public void setRight(Node child) {
        this.rightChild = child;
    }

    public String toString() {
        return "Key " + this.key.toString() + 
            " Value " + this.value.toString(); 
    }

    public static void main(String [] args) {
        Node<Integer, Integer> node = new Node<Integer, Integer>(10, 10);
        System.out.println(node);
    }
}   