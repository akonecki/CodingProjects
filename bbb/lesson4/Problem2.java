import java.util.*;

public class Problem2 {

    private static class Node {
        private int value;
        private Node left = null;
        private Node right = null;
    
        public Node(int value) {
            this.value = value;
        }
    }

    public static Node insert(Node root, int value, List<Node> nodesAdded) {
        if (root == null) {
            Node node = new Node(value);
            nodesAdded.add(node);
            return node;
        }
        else {
            if (root.value >= value) {
                // go left
                root.left = insert(root.left, value, nodesAdded);
            }
            else {
                // go right
                root.right = insert(root.right, value, nodesAdded);
            }
            return root;
        }
    }

    public static Node lowestCommonAncestor(Node root, Node nodeA, Node nodeB) {
        List<Node> found = new ArrayList<Node>(1);
        lowestCommonAncestor(root, nodeA, nodeB, found);
        
        if (found.isEmpty()) {
            return null;
        }
        else {
            return found.get(0);
        }
    }

    private static LCA_STATUS lowestCommonAncestor(Node root, Node nodeA, Node nodeB, List<Node> found) {
        if (root == null) {
            return LCA_STATUS.NONE_FOUND;
        }
        else {
            if (root == nodeA && root == nodeB) {
                found.add(root);
                return LCA_STATUS.BOTH_FOUND;
            }

            LCA_STATUS left = lowestCommonAncestor(root.left, nodeA, nodeB, found);
            LCA_STATUS right = lowestCommonAncestor(root.right, nodeA, nodeB, found);

            // System.out.println(root + " " + nodeA + " " + nodeB);
            // System.out.println(root.value + " " + nodeA.value + " " + nodeB.value + " " + left + " " + right + " " + Boolean.toString(root == root) + " " + Boolean.toString(root.equals(nodeA)) + " " + Boolean.toString(root == nodeB));

            if (left == LCA_STATUS.BOTH_FOUND || right == LCA_STATUS.BOTH_FOUND) {
                return LCA_STATUS.BOTH_FOUND;
            } 
            else if ((left == LCA_STATUS.A_FOUND && right == LCA_STATUS.B_FOUND) || 
                    (left == LCA_STATUS.B_FOUND && right == LCA_STATUS.A_FOUND)) {
                found.add(root);
                return LCA_STATUS.BOTH_FOUND;
            }
            else if (left == LCA_STATUS.A_FOUND || left == LCA_STATUS.B_FOUND) {
                if (root == nodeA || root == nodeB) {
                    found.add(root);
                    return LCA_STATUS.BOTH_FOUND;
                }
                else {
                    return left;
                }
            }
            else if (right == LCA_STATUS.A_FOUND || right == LCA_STATUS.B_FOUND) {
                if (root == nodeA || root == nodeB) {
                    found.add(root);
                    return LCA_STATUS.BOTH_FOUND;
                }
                else {
                    return right;
                }
            }
            else if (root == nodeA) {
                return LCA_STATUS.A_FOUND;
            }
            else if (root == nodeB) {
                return LCA_STATUS.B_FOUND;
            }
            else {
                return LCA_STATUS.NONE_FOUND;
            }
        }
    }

    private enum LCA_STATUS {
        NONE_FOUND {
            public String toString() {
                return "None Found";
            }
        },
        A_FOUND {
            public String toString() {
                return "A Found";
            }
        },
        B_FOUND {
            public String toString() {
                return "B Found";
            }
        },
        BOTH_FOUND {
            public String toString() {
                return "Both Found";
            }
        }
    }

    public static void main(String [] args) {
        List<Node> addedNodes = new ArrayList<Node>();
        Node root = null;

        root = insert(root, 10, addedNodes);
        root = insert(root, 5, addedNodes);
        root = insert(root, 15, addedNodes);
        root = insert(root, 0, addedNodes);
        root = insert(root, 20, addedNodes);
        root = insert(root, 3, addedNodes);
        root = insert(root, 7, addedNodes);
        root = insert(root, 12, addedNodes);
        root = insert(root, 17, addedNodes);

        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(1)) == addedNodes.get(0));
        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(2)) == addedNodes.get(0));
        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(3)) == addedNodes.get(0));
        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(4)) == addedNodes.get(0));
        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(5)) == addedNodes.get(0));
        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(6)) == addedNodes.get(0));
        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(7)) == addedNodes.get(0));
        // assert (lowestCommonAncestor(root, addedNodes.get(0), addedNodes.get(8)) == addedNodes.get(0));
        assert (lowestCommonAncestor(root, addedNodes.get(1), addedNodes.get(2)) == addedNodes.get(0));
        assert (lowestCommonAncestor(root, addedNodes.get(2), addedNodes.get(1)) == addedNodes.get(0));
    }
}