public class Solution {
    public static Node {
        private int value = 0;
        private Node parent, left, right;
    }

    public static Node successor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            // success is guaranteed to be within this branch
            node = node.right;

            // go to the left most
            while (node != null && node.left != null) {
                node = node.left;
            }

            return node;
        }
        else {
            // need to go up the parent link until first to left is traversed 
            // backwards
            Node parent = node.parent;

            while (parent != null && parent.left != node) {
                parent = parent.parent;
                node = node.parent;
            }

            return parent;
        }
    }
}