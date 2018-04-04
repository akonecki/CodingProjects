import java.util.Iterator;
import java.util.Queue;

// Want to iterate over Nodes
public class AdvancedBinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root = null;
    
    public void insert(Key key, Value value) {
        this.root = insert(root, key, value);
    }

    // Generic BST tree insert, non-duplicate keys, and un-balanced.
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        else if (node.key.compareTo(key) > 0) {
            node.left = insert(node.left, key, value);
            node.count = 1 + node.left.count;
            if (node.right != null) {
                node.count += node.right.count;
            }
        }
        else if (node.key.compareTo(key) < 0) {
            node.right = insert(node.right, key, value);
            node.count = 1 + node.right.count;
            if (node.left != null) {
                node.count += node.left.count;
            }
        }
        else {
            node.value = value;
        }

        return node;
    }
    
    public Node get(Key key) {
        Node node = null;

        node = this.root;

        while (node != null) {
            if (node.key.compareTo(key) > 0) {
                node = node.left;
            }
            else if (node.key.compareTo(key) < 0) {
                node = node.right;
            }
            else {
                break;
            }
        }

        return node;
    }

    // Easy delete, just NULL the value but keep the node key in the graph.
    public Node delete(Key key) {
        if (key == null) {
            return null;
        }

        return this.delete(this.root, key);
    }

    private Node delete(Node root, Key key) {
        Node node = null;

        if (root.key.compareTo(key) < 0) {
            node = this.delete(root.right, key);
        }
        else if (root.key.compareTo(key) > 0) {
            node = this.delete(root.left, key);
        }
        else {
            return root;
        }

        // Case 0 : no children
        // Case 1 : one child
        // Case 2 : two children
        return null;
    }

    // Public facing API for min of the overall tree.
    public Node min() {
        return this.min(this.root);
    }

    // Obtain the node with the minimum key for the given subtree with root set
    // to node.
    private Node min(Node root) {
        Node node = root;

        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    public Node max() {
        return this.max(this.root);
    }

    // Obtain the node with the maximum key for the given subtree with the 
    // root set to node.
    private Node max(Node root) {
        Node node = root;

        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> linkedList = new LinkedList<Key>();
        this.inorderTraversal(this.root, linkedList);
        return linkedList;
    }

    private void inorderTraversal(Node node, LinkedList<Key> linkedList) {
        if (node == null) {
            return;
        }
        this.inorderTraversal(node.left, linkedList);
        linkedList.enqueue((Key) node.key);
        this.inorderTraversal(node.right, linkedList);
    }

    public int size() {
        return this.size(this.root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    // Largest key <= to the given key
    public Key floor(Key key) {
        return this.floor(this.root, key);
    } 

    private Key floor(Node root, Key key) {
        Key rightKey = null;

        if (root == null) {
            return null;
        }
        else if (root.key.compareTo(key) == 0) {
            return (Key) root.key;
        }
        else if (root.key.compareTo(key) > 0) {
            return this.floor(root.left, key);
        }
        else {
            rightKey = this.floor(root.right, key);
            if (rightKey == null) {
                return (Key) root.key;
            }
            else {
                return rightKey;
            }
        }
    }

    // Smallest key >= to the given key.
    public Key ceil(Key key) {
        return this.ceil(this.root, key);
    }

    private Key ceil(Node root, Key key) {
        Key leftKey = null;

        if (root == null) {
            return null;
        }
        else if (root.key.compareTo(key) < 0) {
            return this.ceil(root.right, key);
        }
        else if (root.key.compareTo(key) > 0) {
            leftKey = ceil(root.left, key);
            if (leftKey == null) {
                return (Key) root.key;
            }
            else {
                return leftKey;
            }
        }
        else {
            // Equal
            return (Key) root.key;
        }
    }

    // Return the number of nodes that have keys < k.
    // recursive.
    public int rank(Key key) {
        return this.rank(this.root, key);
    }

    private int rank(Node root, Key key) {
        if (root == null) {
            return 0;
        }

        if (root.key.compareTo(key) == 0) {
            return this.size(root.left);
        }
        else if (root.key.compareTo(key) > 0) {
            return this.rank(root.left, key);
        }
        else {
            // root.key < k
            return this.size(root.left) + this.rank(root.right, key) + 1;
        }
    }

    private class LinkedList<T> implements Iterable<T> {
        private Link mHead = null;
        private Link mTail = null;
        private int count = 0;

        public Iterator iterator() {
            return new LinkedListIterator();
        }

        // Place at the back of the linked list.
        public void enqueue(T element) {
            Link newLink;
            
            // Not going to operate on a null element.
            if (element == null) {
                return;
            }

            newLink = new Link(element);

            if (this.mTail != null) {
                this.mTail.next = newLink;
                this.mTail = newLink;
            }
            else {
                // this.mTail and this.mHead are null.
                this.mTail = newLink;
                this.mHead = newLink;
            }

            this.count++;
        }

        // Dequeue the list item from the front.
        public T dequeue() {
            Link link = null;
            T data = null;

            if (mHead == null) {
                return data;
            }

            link = this.mHead;

            this.mHead = link.next;
            if (link == this.mTail) {
                this.mTail = null;
            }

            data = link.data;
            link.data = null;
            link.next = null;

            this.count--;
            return data;
        }

        public int size() {
            return this.count;
        }

        private class LinkedListIterator implements Iterator<T> {
            private T[] values = (T[]) new Object[count];
            private Link head = mHead;
            private int mIndex = 0;

            public LinkedListIterator() {
                Link link = this.head;

                for (int index = 0; index < this.values.length && link != null; index++, link = link.next) {
                    values[index] = link.data;
                }
            }
            
            public T next() {
                T ret = this.values[this.mIndex++]; 
                return ret;
            }

            public boolean hasNext() {
                return this.mIndex < values.length;
            }

            public void remove() {
                return;
            }
        }

        private class Link {
            private Link next = null;
            private T data = null;

            public Link(T data) {
                this.data = data;
            }
        }
    }

    private class Node <Key extends Comparable<Key>, Value> {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }

        public String toString() {
            return "Key " + this.key.toString() + " Value " + this.value.toString();
        }
    }

    public static void main(String [] args) {
        AdvancedBinarySearchTree<Integer, Integer> bst = new AdvancedBinarySearchTree<Integer, Integer>();

        bst.insert(0,0);
        bst.insert(10,10);
        bst.insert(3,3);
        bst.insert(2,2);

        for (Object key : bst.keys()) {
            System.out.println(key);
        }

        System.out.println(bst.size());
        System.out.println(bst.min());
        System.out.println(bst.max());
        System.out.println(bst.get(3));
        System.out.println(bst.get(7));
        System.out.println(bst.ceil(11));
        System.out.println(bst.rank(0));
        System.out.println(bst.rank(3));
    }
}