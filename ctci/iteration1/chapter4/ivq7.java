import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class ivq7 <T> {
    private Node<T> mRoot = null;

    private class NodeWrapper<T> {
        private Node<T> node = null;
        boolean hadLeftRight = false;
    
        public NodeWrapper(Node<T>node) {
            this.node = node;
        }
    }

    public static class Node<T> {
        private T value = null;
        private Node<T> left = null;
        private Node<T> right = null;

        public Node(T value) {
            this.value = value;
        }
    }

    public Node<T> ancestor(Node<T> node1, Node<T> node2) {
        if (node1 == null || node2 == null) {
            return null;
        }

        NodeWrapper<T> nodeWrapper = this.ancestor(this.mRoot, node1, node2);

        if (nodeWrapper != null && nodeWrapper.hadLeftRight) {
            return nodeWrapper.node;
        }
        else {
            return null;
        }
    }

    private NodeWrapper<T> ancestor(Node<T> root, Node<T> node1, Node<T> node2) {
        if (root == null) {
            return null;
        }

        NodeWrapper<T> left = this.ancestor(root.left, node1, node2);

        if (left != null && left.hadLeftRight) {
            return left;
        }

        NodeWrapper<T> right = this.ancestor(root.right, node1, node2);

        if (right != null && right.hadLeftRight) {
            return right;
        }

        if (left != null && right != null) {
            left.hadLeftRight = true;
            left.node = root;
            return left;    
        }
        else if (root.value.equals(node1.value) || root.value.equals(node2.value)) {
            if (left == null && right == null) {
                return new NodeWrapper<T>(root);
            }
            else if (left != null) {
                right = new NodeWrapper<T>(root);
                right.hadLeftRight = true;
                return right;
            }
            else if (right != null) {
                left = new NodeWrapper<T>(root);
                left.hadLeftRight = true;
                return left;
            }
            else {
                throw new java.lang.IllegalArgumentException("Should never have gotten here.");
            }
        }
        else if (left != null) {
            return left;
        }
        else if (right != null) {
            return right;
        }
        else {
            return null;
        }
    }

    public void insert(T value) {
        if (value == null) {
            return;
        }

        this.mRoot = this.insert(this.mRoot, value);
    }

    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<T>(value);
        }

        if (root.value.equals(value)) {
            return root;
        }

        Random random = new Random();

        if (random.ints(1, 0, 2).toArray()[0] == 0) {
            root.left = this.insert(root.left, value);
        }
        else {
            root.right = this.insert(root.right, value);
        }

        return root;
    }

    public Node<T> find(T value) {
        if (value == null) {
            return null;
        }

        return this.find(this.mRoot, value);
    }

    private Node<T> find(Node<T> root, T value) {
        if (root == null || root.value.equals(value)) {
            return root;
        }

        Node<T> node = this.find(root.left, value);

        if (node == null) {
            return this.find(root.right, value);
        }
        else {
            return node;
        }
    }

    public static void main(String [] args) {
        ivq7<Integer> integers = new ivq7<Integer>();
        Set<Integer> usedIntegers = new HashSet<Integer>();

        Random random = new Random();

        for (int number : random.ints(100, 0, 10000).toArray()) {
            // Making sure no duplicates.
            if (!usedIntegers.contains(number)) {
                usedIntegers.add(number);
                integers.insert(number);
            }
        }

        Integer num1 = null;
        Integer num2 = null;
        for (Integer integer : usedIntegers) {
            if (num1 == null) {
                num1 = integer;
            }
            else if (num2 == null) {
                num2 = integer;
            }
            else {
                break;
            }
        }

        Node<Integer> node1 = new Node<Integer>(num1);
        Node<Integer> node2 = new Node<Integer>(num2);
        Node<Integer> node3 = new Node<Integer>(-1);

        assert (integers.ancestor(node1, node2) != null);
        assert (integers.ancestor(node1, node3) == null);
        assert (integers.ancestor(node2, node3) == null);
    }
}