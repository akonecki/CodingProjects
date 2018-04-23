import java.util.Queue;
import java.util.LinkedList;

public class ivq1 <T extends Comparable<T>> {
    private Node<T> mRoot = null;
    
    private class Node<T extends Comparable<T>> {
        private Node<T> mLeft = null;
        private Node<T> mRight = null;
        private T value = null;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node(value);
        }

        if (root.value.compareTo(value) > 0) {
            root.mLeft = this.insert(root.mLeft, value);
        }
        else if (root.value.compareTo(value) < 0) {
            root.mRight = this.insert(root.mRight, value);
        }

        return root;
    }

    public void insert(T value) {
        if (value == null) {
            return;
        }

        this.mRoot = this.insert(this.mRoot, value);
    }  

    public boolean isBalanced() {
        return this.isBalanced(this.mRoot);
    }

    private boolean isBalanced(Node<T> root) {
        int limit = 0, count = 0;
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        boolean flag = false;

        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            count++;
            if (node == null && !flag) {
                flag = true;
                limit = (~count) & ((~count) ^ (~(count << 1)));
            }
            else if (node != null) {
                // Assumes that null can be added to the queue.
                queue.add(node.mLeft);
                queue.add(node.mRight);
            }

            if (flag && count >= limit && node != null) {
                return false;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        ivq1<Integer> tree = new ivq1<Integer>();

        assert (tree.isBalanced());

        tree.insert(0);
        assert (tree.isBalanced());
        tree.insert(1);
        assert (tree.isBalanced());
        tree.insert(2);
        assert (!tree.isBalanced());
        tree.insert(-1);
        assert (tree.isBalanced());
    }
}