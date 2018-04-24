import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Queue;

public class ivq2 <T> { 
    private static final int VISITING = 0;
    private static final int VISITED = 1;
    private static final int NOT_VISITED = 2;
    private Node<T> mRoot = null;

    private class Node<T> implements Iterable<Node<T>> {
        private T value = null;
        private List<Node<T>> nodes = new ArrayList<Node<T>>();
        private int visited = ivq2.NOT_VISITED;

        public Node(T value) {
            this.value = value;
        }

        public Iterator<Node<T>> iterator() {
            return new NodeIterator(this);
        }

        private class NodeIterator implements Iterator<Node<T>> {
            private Queue<Node<T>> it_nodes = new LinkedList<Node<T>>();

            public NodeIterator(Node<T> root) {
                this.it_nodes.add(root);
            }

            public boolean hasNext() {
                return !(this.it_nodes.isEmpty());
            }
    
            public Node<T> next() {
                Node<T> node = this.it_nodes.remove();

                node.visited = ivq2.VISITED;

                for (Node<T> a_node : node.nodes) {
                    if (a_node.visited != ivq2.VISITED) {
                        this.it_nodes.add(a_node);
                    }
                }

                return node;
            }
    
            public void remove() {
    
            }
        } 
    }

    public boolean isConnected(T value1, T value2) {
        if (value1 == null || value2 == null) {
            return false;
        }

        return this.isConnected(this.mRoot, value1, value2);
    }

    private boolean isConnected(Node<T> root, T value1, T value2) {
        if (root == null) {
            return false;
        }

        for (Node<T> node : root) {
            node.visited = ivq2.NOT_VISITED;
        }

        Node<T> node1 = this.find(root, value1);
        Node<T> node2 = this.find(node1, value2);

        if (node1 != null && node2 != null) {
            return true;
        }

        return false;
    }

    private Node<T> find(Node<T> root, T value) {
        if (root == null) {
            return root;
        }

        if (root.value.equals(value)) {
            return root;
        }
        else {
            if (root.visited != ivq2.VISITED) {
                root.visited = ivq2.VISITED;

                for (Node<T> node : root.nodes) {
                    node = this.find(node, value);

                    if (node != null) {
                        return node;
                    }
                }
            }
        }

        return null;
    }
}