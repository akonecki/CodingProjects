public class BinarySearchTree <Key extends Comparable<Key>, Value> {
    
    private Node root = null;
    private int count = 0;

    public void insert(Key key, Value value) {
        this.root = this.insert(root, key, value);    
    }

    // Generic BST tree insert, non-duplicate keys, and un-balanced.
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            this.count++;
            return new Node(key, value);
        }
        else if (node.getKey().compareTo(key) > 0) {
            node.setLeft(insert(node.getLeft(), key, value));
        }
        else if (node.getKey().compareTo(key) < 0) {
            node.setRight(insert(node.getRight(), key, value));
        }
        else {
            if (node.getValue() == null) {
                this.count++;
            }
            node.setValue(value);
        }

        return node;
    }
    
    public Node find(Key key) {
        Node node = null;

        node = this.root;

        while (node != null) {
            if (node.getKey().compareTo(key) > 0) {
                node = node.getLeft();
            }
            else if (node.getKey().compareTo(key) < 0) {
                node = node.getRight();
            }
            else {
                break;
            }
        }

        return node;
    }

    // Easy delete, just NULL the value but keep the node key in the graph.
    public void delete(Key key) {
        Node node = this.find(key);

        if (node != null) {
            node.setValue(null);
            this.count--;
        }
    }

    public int count() {
        return this.count;
    }

    public static void main(String [] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        bst.insert(7, 7);
        bst.insert(9, 9);
        bst.insert(2, 2);
        bst.insert(4, 4);
        bst.insert(5, 5);
        bst.insert(-1, -1);
        bst.insert(11, 11);
        bst.insert(3, 3);
        bst.insert(17, 17);
        System.out.println(bst.count);
    }
}