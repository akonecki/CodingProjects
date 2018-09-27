import java.util.Stack;

public class Solution {
    private static class Node {
        private int value;
        private Node left, right;
        // Agumentation
        private int height;

        public Node (int value) {
            this.value = value;
        }

        public boolean isEqual(Node root2) {
            return isEqual(this, root2);
        }

        private static boolean isEqual(Node root1, Node root2) {
            Stack<Node> stack1 =  new Stack<Node>();
            Stack<Node> stack2 =  new Stack<Node>();
            Node node1 = root1, node2 = root2;

            if (root1 == root2) {
                // Same object resolution.
                return true;
            }
            else if (root1 == null || root2 == null) {
                return false;
            }

            while ((!stack1.isEmpty() && !stack2.isEmpty()) || 
                (node1 != null && node2 != null)) 
            {
                if (node1 != null && node2 != null) {
                    System.out.println(node1.value + " " + node2.value);
                    if (node1.value != node2.value) {
                        return false;
                    }
                    stack1.push(node1);
                    stack2.push(node2);
                    node1 = node1.left;
                    node2 = node2.left;
                }
                else if (node1 != null || node2 != null) {
                    return false;
                }
                else if (stack1.isEmpty() && stack2.isEmpty()) {
                    // never going to hit since node1 & node2 == null at this
                    // point.  Thus if both stack1 & stack2 are empty then wont
                    // even run the iteration.
                    return true;
                }
                else if (!stack1.isEmpty() && !stack2.isEmpty()) {
                    node1 = stack1.pop().right;
                    node2 = stack2.pop().right;
                }
                else {
                    return false;
                }
            }

            return true;            
        } 
    }

    // First would need to determine if subtree is based on object allocation,
    // or data value.  Data value would require match and then maybe performing
    // a dfs or bfs for value matching between the two.  For this one there is
    // an edge case in which the t2 within t1 does not equal t2 in the lower
    // leaf nodes of the tree (i.e. has additional nodes after the scope of t2).
    // This would not be present in address matching scheme since the object
    // addresses would be the same.

    // if performing object equality then once you find the occurrence of the 
    // second object in the first tree, then they must be identical, don't need
    // to go down the tree for checking remaining addresses.

    // if was to work on a standard tree would need to possibly go through all
    // tree1.  if heights where known and entered a branch of a tree1 that is 
    // not tall enough to hold t2, then dont need to actually traverse it.

    // Would likely want to iterate through tree1 with BFS if heights arent known,
    // or if heights are known dfs down to the equal height and then perform the
    // the check.  if not equal then dont need to keep going down.

    public static boolean checkSubtree(Node root1, Node root2) {
        return searchForRoot(root1, root2);
    }

    private static boolean searchForRoot(Node root1, Node root2) {
        Stack<Node> stack = new Stack<Node>();
        Node node = root1;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                System.out.println(node.value);
                if (node.value == root2.value) {
                    if (node.isEqual(root2)) {
                        return true;
                    }
                }
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop().right;
            }
        }

        return false;
    }

    public static void main(String [] args) {
        Node root1 = null, root2 = null, root3 = null, root4 = null;
        Node temp = null;

        root1 = new Node(10);
        root1.right = new Node(3);
        temp = new Node(5);
        root1.left = temp;
        temp.left = new Node(3);
        temp = temp.left;
        temp.left = new Node(1);
        temp.right = new Node(4);

        root2 = new Node(3);
        root2.left = new Node(1);
        root2.right = new Node(4);

        root3 = new Node(3);
        root3.right = new Node(1);
        root3.left = new Node(4);
        
        root4 = new Node(3);
        root4.left = new Node(1);
        temp = root4.left;
        temp.right = new Node(4);

        assert (checkSubtree(root1, root2));
        assert (!checkSubtree(root1, root3));
        assert (!checkSubtree(root1, root4));
    }
}