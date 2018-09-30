import java.util.Queue;
import java.util.LinkedList;

public class Solution {

    private class static Node {
        private int value;
        private Node left, right;
        private int height = 1;

        public Node (int value) {
            this.value = value;
        }
    }

    // no height attribute
    // bfs
    public static boolean isBalanced(Node root) {
        Queue<Node> currentLevel = new LinkedList<Node>();
        Queue<Node> nextLevel = new LinkedList<Node>();
        
        // ask you interviewer for claraification.
        if (root == null) {
            return true;
        }

        nextLevel.add(root);
        int level = 0, minLevel = -1;

        while (!currentLevel.isEmpty() || !nextLevel.isEmpty()) {
            if (!currentLevel.isEmpty()) {
                Node node = currentLevel.remove();

                if (node != null) {
                    if (minLevel == -1 && (node.left == null || node.right == null)) {
                        minLevel = level;
                    }

                    if (node.left != null) {
                        nextLevel.add(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.add(node.right);
                    }
                }
            }
            else {
                level++;
                currentLevel = nextLevel;
                nextLevel = new LinkedList<Node>();
            }
        }
    }

    public static Node insertDFSSerial(Node root, int value) {
        Node node = root;

        if (root == null) {
            return new Node(value);
        }

        while (node != null) {
            if (node.value >= value) {
                // left
                if (node.left == null) {
                    node.left = new Node(value);
                    // break out of the while loop in some fashion.
                    node = null;
                }
                else {
                    node = node.left;
                }
            }
            else {
                // right
                if (node.right == null) {
                    node.right = new Node(value);
                    // break out of the while loop in some fashion.
                    node = null;
                }
                else {
                    node = node.right;
                }
            }  
        }

        return root;
    }

    // root == null (empty tree), 10
    // root(10).left == null, .right == null, 5
    //   -> null, 5
    //   <- node(5)
    // root(10).left == node(5), .right == null, 3
    //   -> node(5).left == null, 3
    //        -> null, 3
    //        <- node(3)
    //   <- node(5).left = node(3)
    // root(10).left = node(5).left = node(3)
    public static Node insertDFSRecursive(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        else {
            // what to do with equal
            if (root.value >= value) {
                // going left
                root.left = insertDFSRecursive(root.left, value);
            }
            else {
                root.right = insertDFSRecursive(root.right, value);
            }
        }

        /*
        root.left != null ? root.left.height : 0
        int temp;

        if (root.left != null) {
            temp = root.left.height;
        }
        else {
            temp = 0;
        }
        */

        /*
                    10
                5       3

        */

        root.height = Math.max(
            root.left != null ? root.left.height : 0, 
            root.right != null ? root.right.height : 0) + 1;
        return root;
    }

    public static int getMaxHieghtPath(Node root) {
        return root.height;
    }

    public static void main(String [] args) {

    }

}