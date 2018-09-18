import java.util.Iterator;

public class Solution {

    private static class Node implements Iterable<Node> {
        private int value = 0;
        private Node left = null;
        private Node right = null;

        public Node(int value) {
            this.value = value;
        }

        public Iterator<Node> iterator() {
            return new InorderTraversal();
        }

        private class InorderTraversal implements Iterator<Node> {
            public boolean hasNext() {
                return false;
            }

            public Node next() {
                return null;
            }

            public void remove() {

            }
        }
    } 

    public static void main(String [] args) {


    }
}