import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class ivq6 <T extends Comparable<T>> { 
    private Node<T> mRoot = null;

    private class Node<T extends Comparable<T>> {
        private T value = null;
        private Node<T> left = null;
        private Node<T> right = null;

        public Node(T value) {
            this.value = value;
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
            return new Node<T> (value);
        }

        if (root.value.compareTo(value) > 0) {
            root.left = this.insert(root.left, value);
        }
        else if (root.value.compareTo(value) < 0) {
            root.right = this.insert(root.right, value);
        }
        else {
            return root;
        }

        return root;
    }

    public T successor(T value) {
        if (value == null) {
            return value;
        }

        Node<T> node = this.successor(this.mRoot, value);

        if (node != null) {
            return node.value;
        }
        else {
            return null;
        }
    }

    private Node<T> successor(Node<T> root, T value) {
        if (value == null) {
            return null;
        }

        Node<T> node = this.find(root, value);
        Node<T> parent = this.findParent(root, value);
        return this.successor(node, parent);
    }

    private Node<T> successor(Node<T> root, Node<T> parent) {
        if (root == null || parent == null) {
            return null;
        }

        Node<T> node = this.min(root.right);

        if (node == null) {
            // No right children.
            if (parent.value.compareTo(root.value) >= 0) {
                // Parent is larger thus would be the next largest.
                return parent;
            }
            else {
                // no one is larger than root.
                return root;
            }
        }
        else {
            return node;
        }
    }

    private Node<T> findParent(Node<T> root, T value) {
        if (root == null) {
            // Node could not be found.
            return null;
        }

        if (root.value.compareTo(value) > 0) {
            if (root.left != null) {
                if (root.left.value.compareTo(value) != 0) {
                    return this.findParent(root.left, value);
                }
                else {
                    return root;
                }
            }
            else {
                return null;
            }
        }
        else if (root.value.compareTo(value) < 0) {
            if (root.right != null) {
                if (root.right.value.compareTo(value) != 0) {
                    return this.findParent(root.right, value);
                }
                else {
                    return root;
                }
            }
            else {
                return null;
            }
        }
        else {
            // if it is the main root itself, it is its own parent.
            assert (this.mRoot.equals(root));
            return root;
        }
    }

    private Node<T> find(Node<T> root, T value) {
        if (root == null) {
            return null;
        }

        if (root.value.compareTo(value) > 0) {
            return this.find(root.left, value);
        }
        else if (root.value.compareTo(value) < 0) {
            return this.find(root.right, value);
        }
        else {
            return root;
        }
    }

    private Node<T> min(Node<T> root) {
        if (root == null) {
            return root;
        }

        if (root.left != null) {
            return this.min(root.left);
        }
        else {
            return root;
        }
    }

    public static void main(String [] args) {
        ivq6<Integer> tree = new ivq6<Integer>();
        List<Integer> addedValues = new ArrayList<Integer>();
        Random random = new Random();

        for (int number : random.ints(1000).toArray()) {
            tree.insert(number);
            addedValues.add(number);
        }

        for (int number : addedValues) {
            System.out.println(number + " " + tree.successor(number));
        }
    }
}