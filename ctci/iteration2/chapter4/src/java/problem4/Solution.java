import java.util.Random;

public class Solution {
    private static class Node<T> {
        private T data;
        private Node<T> left, right;

        public Node(T data) {
            this.data = data;
        }
    }

    public static <T> Node<T> insert(Node<T> root, T data) {
        Node<T> node = root;

        if (root == null) {
            return new Node<T>(data);
        }

        while (node != null) {
            // randomly select to go left or right.
            if (new Random().nextBoolean()) {
                // go left
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node.left = new Node<T>(data);
                    node = null;
                }
            }
            else {
                // go right
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    node.right = new Node<T>(data);
                    node = null;
                }
            }
        }

        return root;
    }

    // If the common case of trees is that most are balanced then would want to
    // implement a recursive implementation to check the height from the root to
    // each leaf node and make sure the difference among them all is one.

    // if the common case is that most trees are not balanced and distributed 
    // randomly, then a BFS might be a better solution in terms of memory impact.

    // recursive is significantly easier to implement.
    public static <T> boolean isBalanced(Node<T> root) {
        return heights(root) != -1;
    }

    private static <T> int heights(Node<T> root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = 0, rightHeight = 0;

        leftHeight = heights(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        rightHeight = heights(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static Node<Integer> createMinimalTree(int [] data) {
        return createMinimalTree(data, 0, data.length - 1);
    }

    // O(N) for run time complexity.  Must visit each node once.
    // In terms of stack space height will be O(lgN), since the creation will
    // be a balanced tree since always going from the middle index for creation
    // of a new element.
    private static Node<Integer> createMinimalTree(int [] data, int low, int high) {
        Node<Integer> root = null;

        if (low <= high && low >= 0 && high < data.length) {    
            int index = (high - low) / 2 + low;

            root = new Node<Integer>(data[index]);
            root.left = createMinimalTree(data, low, index - 1);
            root.right = createMinimalTree(data, index + 1, high);
            // root.height += Math.max(root.left != null ? root.left.height : 0, root.right != null ? root.right.height : 0);
        }

        return root;
    }

    public static void main(String [] args) {
        Node<Integer> root = null;
        Node<Integer> balancedRoot = null;
        int [] data = new int [100];

        for (int i = 0; i < 100; i++) {
            root = insert(root, i);
            data[i] = i;
        }

        // it is highly unlikely that the random insertion will lead to a balanced tree.
        assert (!isBalanced(root));
        assert (isBalanced(createMinimalTree(data)));
    } 
}