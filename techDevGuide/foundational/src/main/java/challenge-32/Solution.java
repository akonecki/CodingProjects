import java.util.Iterator;
import java.util.Stack;

public class Solution {

    private static class Node implements Iterable<Node> {
        private int value = 0;
        private Node left = null;
        private Node right = null;

        public Node(int value) {
            this.value = value;
        }

        public Iterator<Node> iterator() {
            return new InorderTraversal(this);
        }

        private class InorderTraversal implements Iterator<Node> {
            private Stack<Node> nodes = new Stack<Node>();

            public InorderTraversal(Node root) {
                Node node = root;

                // only pushing on the left most branch
                while (node != null) {
                    nodes.push(node);
                    node = node.left;
                }
            }
            
            public boolean hasNext() {
                return !nodes.isEmpty();
            }

            public Node next() {
                Node node = null;
                Node traversalNode = null;

                if (nodes.isEmpty()) {
                    return node;
                }

                node = nodes.pop();
                traversalNode = node.right;

                while (traversalNode != null) {
                    nodes.push(traversalNode);
                    traversalNode = traversalNode.left;
                }

                return node;
            }

            public void remove() {
                throw new java.lang.UnsupportedOperationException("Remove not supported.");
            }
        }
    } 

    public static void main(String [] args) {
        Node root = new Node(1);

        for (Node node : root) {
            System.out.println(node.value);
        }

    }
}