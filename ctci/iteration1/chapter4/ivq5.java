import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class ivq5 <T extends Comparable <T>> {
    private Node<T> mRoot = null;

    private class Node<T extends Comparable <T>> {
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

        return;
    }

    public void insertRandom(T value) {
        if (value == null) {
            return;
        }

        this.mRoot = this.insertRandom(this.mRoot, value);

        return;
    }

    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<T>(value);
        }

        assert (root.value != null);

        if (root.value.compareTo(value) > 0) {
            root.left = this.insert(root.left, value);
        }
        else {
            // Handling equal case.
            root.right = this.insert(root.right, value);
        }

        return root;
    }

    private Node<T> insertRandom(Node<T> root, T value) {
        if (root == null) {
            return new Node<T>(value);
        }

        Random random = new Random();
        if (random.ints(1, 0, 2).sum() == 0) {
            root.left = this.insertRandom(root.left, value);
        } 
        else {
            root.right = this.insertRandom(root.right, value);
        }

        return root;
    }

    public boolean isBSTArrayBase() {
        List<T> values = new LinkedList<T>();

        this.inorderTraversal(this.mRoot, values);

        T previous = null;
        for (T value : values) {
            if (previous == null) {
                previous = value;
            }
            
            if (previous.compareTo(value) > 0) {
                return false;
            }

            previous = value;
        }

        return true;
    }

    private void inorderTraversal(Node<T> root, List<T> values) {
        if (root == null) {
            return;
        }

        this.inorderTraversal(root.left, values);
        values.add(root.value);
        this.inorderTraversal(root.right, values);

        return;
    }

    public boolean isBST() {
        return this.isBST(this.mRoot, null, null);
    }

    private boolean isBST(Node<T> root, T min, T max) {
        boolean value = true;

        if (root == null) {
            return true;
        }

        assert(root.value != null);

        if (min == null || max == null) {
            min = root.value;
            max = root.value;
            assert (root.equals(this.mRoot));
        }

        if (root.value.compareTo(min) < 0 || root.value.compareTo(max) > 0) {
            return false;
        }

        if (root.left != null) {
            assert(root.left.value != null);

            if (root.left.value.compareTo(root.value) > 0) {
                return false;
            }
            else {
                value = value && this.isBST(root.left, root.left.value, root.value);
            }
        }

        if (root.right != null) {
            assert(root.right.value != null);

            if (root.right.value.compareTo(root.value) < 0) {
                return false;
            }
            else {
                value = value && this.isBST(root.right, root.value, root.right.value);
            }
        }

        return value;
    }

    public static void main(String [] args) {
        ivq5<Integer> treeOfIntegers = new ivq5<Integer>();

        for (int index = 0; index < 100; index++) {
            treeOfIntegers.insert(index);
        }

        assert (treeOfIntegers.isBST());
        assert (treeOfIntegers.isBSTArrayBase());

        for (int index = 0; index < 100; index++) {
            treeOfIntegers.insertRandom(index);
        }

        assert (!treeOfIntegers.isBST());
        assert (!treeOfIntegers.isBSTArrayBase());
    }
}