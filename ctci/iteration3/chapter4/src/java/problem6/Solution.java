public class Solution {
    public static class Node {
        private int value = 0;
        private Node parent, left, right;
    }

    public static Node successor(Node givenNode) {
        Node node = givenNode;

        // input validation
        if (node == null) {
            return null;
        }

        // Case 1 -
        // if node has right, then get the left most of the right node
        if (node.right != null) {
            // Node prev = node;
            node = node.right;

            // now go left most
            while (node != null && node.left != null) {
                // prev = node;
                node = node.left;
            }

            // when drop out node == null

            // santity check - asssumptions that i am testing against.
            assert (node != null);
            // assert (prev != null);

            return node;
        }

        // Case 2 -
        // node has no right, then get parent if possible
        else {
            Node parent = node.parent;

            // look at Node(7)

            while (parent != null) {
                if (parent.value < node.value) {
                    parent = parent.parent;
                    // node = node.parent;
                }
                else {
                    return parent;
                }
            }

            return null;
        }
    }

























    /*
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
    */
}