import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class IntervalSearchTree <Key extends Comparable<Key>> {
    //*************************************************************************
    //* PRIVATE FACING API
    //*************************************************************************
    // This interval search tree is not a balanced tree.
    private Node<Key> mRoot = null;
    
    private class Node <Key extends Comparable<Key>> {
        private Key mLowKey = null;
        private Key mHighKey = null;
        private Key mMaxKey = null;
        private Node<Key> mLeftChild = null;
        private Node<Key> mRightChild = null;

        public Node(Key lowKey, Key highKey) {
            this.mLowKey = lowKey;
            this.mHighKey = highKey;
            this.mMaxKey = highKey;
        }

        public Comparator<Node <Key>> max_node() {
            return new MaxNode();
        } 

        public Comparator<Key> interval() {
            return new NodeInterval();
        }

        public int compare(Node<Key> left, Node<Key> right) {
            return this.max_node().compare(left, right);
        }

        public int compare(Key low, Key high) {
            return this.interval().compare(low, high);
        }

        private class NodeInterval implements Comparator<Key> {
            public int compare(Key low, Key high) {
                // -1 : Node interval is less than specified interval.
                //  0 : Node interval is within the specified interval.
                //  1 : Node interval is greater than specified interval.
                System.out.println(Node.this.mLowKey + " " + low + " " + high);

                if (Node.this.mHighKey.compareTo(low) < 0) {
                    return -1;
                }
                else if (Node.this.mLowKey.compareTo(high) > 0) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }

        private class MaxNode implements Comparator<Node <Key>> {
            public int compare(Node<Key> left, Node<Key> right) {
                // -1 : left
                //  0 : parent
                //  1 : right

                if (left != null && right != null) {
                    if (right.mMaxKey.compareTo(left.mMaxKey) >= 0) {
                        if (right.mMaxKey.compareTo(Node.this.mHighKey) > 0) {
                            return 1;
                        }
                        else {
                            return 0;
                        }
                    }
                    else {// if (right.mMaxKey.compareTo(left.mMaxKey) < 0) {
                        if (left.mMaxKey.compareTo(Node.this.mHighKey) > 0) {
                            return -1;
                        }
                        else {
                            return 0;
                        }
                    }
                }
                else if (left != null) {
                    if (left.mMaxKey.compareTo(Node.this.mHighKey) > 0) {
                        return -1;
                    }
                    else {
                        return 0;
                    }
                }
                else if (right != null) {
                    if (right.mMaxKey.compareTo(Node.this.mHighKey) > 0) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
                else {
                    return 0;
                }
            }
        }
    }

    private Node<Key> insert(Node<Key> root, Key low, Key high) {
        int compareValue = 0;
        
        if (root == null) {
            return new Node<Key>(low, high);
        }

        if (root.mLowKey.compareTo(low) > 0) {
            // Go left.
            root.mLeftChild = this.insert(root.mLeftChild, low, high);
        }
        else if (root.mLowKey.compareTo(low) < 0) {
            // Go right.
            root.mRightChild = this.insert(root.mRightChild, low, high);
        }
        else {
            // Match.
            root.mHighKey = high;
        }

        compareValue = root.compare(root.mLeftChild, root.mRightChild);

        // Invariant fix up the path.
        if (compareValue > 0) {
            root.mMaxKey = root.mRightChild.mMaxKey;
        }
        else if (compareValue < 0) {
            root.mMaxKey = root.mLeftChild.mMaxKey;
        }
        else {
            root.mMaxKey = root.mHighKey;
        }

        return root;
    }

    // Ugly multiple traversals, same logic.
    private void low_key_inorder_traversal(Node<Key> root, List<Key> keys) {
        if (root == null) {
            return;
        }

        this.low_key_inorder_traversal(root.mLeftChild, keys);
        keys.add(root.mLowKey);
        this.low_key_inorder_traversal(root.mRightChild, keys);
    }

    private void high_key_inorder_traversal(Node<Key> root, List<Key> keys) {
        if (root == null) {
            return;
        }

        this.high_key_inorder_traversal(root.mLeftChild, keys);
        keys.add(root.mHighKey);
        this.high_key_inorder_traversal(root.mRightChild, keys);
    }

    private void max_key_inorder_traversal(Node<Key> root, List<Key> keys) {
        if (root == null) {
            return;
        }

        this.max_key_inorder_traversal(root.mLeftChild, keys);
        System.out.println("Low Key : " + root.mLowKey + " Max Key : " + root.mMaxKey);
        keys.add(root.mMaxKey);
        this.max_key_inorder_traversal(root.mRightChild, keys);
    }

    private Node<Key> interval_occurrence(Node<Key> root, Key low, Key high) {
        if (root == null) {
            return null;
        }

        // Check to see if the root is within the key interval.
        if (root.compare(low, high) == 0) {
            return root;
        }
        else if (root.mLeftChild != null && root.mLeftChild.mMaxKey.compareTo(low) >= 0) {
            // Go left.
            return this.interval_occurrence(root.mLeftChild, low, high);
        }
        else if (root.mLowKey.compareTo(high) < 0) {
            // Go right.
            return this.interval_occurrence(root.mRightChild, low, high);
        }
        else {
            // No where to go.
            return null;
        }
    }

    private int interval_count(Node<Key> root, Key low, Key high) {
        int count = 0;

        if (root == null) {
            return 0;
        }

        // Check to see if the root is within the key interval.
        if (root.compare(low, high) == 0) {
            count++; 
        }

        // Try to go left.
        if (root.mLeftChild != null && root.mLeftChild.mMaxKey.compareTo(low) >= 0) {
            // Now explore the left of the root.
            count += this.interval_count(root.mLeftChild, low, high);
        }

        // Determine if need to even try to go right.
        if (root.mLowKey.compareTo(high) < 0) {
            // Now explore the right of the root.
            count += this.interval_count(root.mRightChild, low, high);
        }

        return count;
    }

    private void interval_search(Node<Key> root, Key low, Key high, List<Key> keys) {
        if (root == null) {
            return;
        }

        // Try to go left.
        if (root.mLeftChild != null && root.mLeftChild.mMaxKey.compareTo(low) >= 0) {
            // Now explore the left of the root.
            this.interval_search(root.mLeftChild, low, high, keys);
        }

        // Check to see if the root is within the key interval.
        if (root.compare(low, high) == 0) {
            keys.add(root.mLowKey); 
        }

        // Determine if need to even try to go right.
        if (root.mLowKey.compareTo(high) < 0) {
            // Now explore the right of the root.
            this.interval_search(root.mRightChild, low, high, keys);
        }   
    }

    //*************************************************************************
    //* PUBLIC FACING API
    //************************************************************************* 
    public void insert(Key low, Key high) {
        if (low == null || high == null) {
            throw new java.lang.IllegalArgumentException("Keys can not be null.");
        }

        if (low.compareTo(high) > 0) {
            throw new java.lang.IllegalArgumentException("Low key must be less than or equal to high key.");
        }

        this.mRoot = this.insert(this.mRoot, low, high);
    }

    public List<Key> low_keys() {
        List<Key> keys = new ArrayList<Key>();

        this.low_key_inorder_traversal(this.mRoot, keys);

        return keys;
    }

    public List<Key> high_keys() {
        List<Key> keys = new ArrayList<Key>();

        this.high_key_inorder_traversal(this.mRoot, keys);

        return keys;
    }

    public List<Key> max_keys() {
        List<Key> keys = new ArrayList<Key>();

        this.max_key_inorder_traversal(this.mRoot, keys);

        return keys;
    }

    // Returns the number of points that exist within the interval.
    public int interval_count(Key low, Key high) {
        if (low == null || high == null) {
            throw new java.lang.IllegalArgumentException("Keys can not be null.");
        }

        return this.interval_count(this.mRoot, low, high);
    }

    // Returns all the sorting keys that exist wihtin the interval.
    public List<Key> interval_search(Key low, Key high) {
        List<Key> keys = new ArrayList<Key>();

        if (low == null || high == null) {
            throw new java.lang.IllegalArgumentException("Keys can not be null.");
        }

        this.interval_search(this.mRoot, low, high, keys);

        return keys;
    }

    public Key interval_occurrence(Key low, Key high) {
        Node<Key> node = null;

        if (low == null || high == null) {
            throw new java.lang.IllegalArgumentException("Keys can not be null.");
        }

        node = this.interval_occurrence(this.mRoot, low, high);

        if (node == null) {
            return null;
        }
        else {
            return node.mLowKey;
        }
    }

    public static void main(String [] args) {
        IntervalSearchTree<Integer> test = new IntervalSearchTree<Integer>();

        test.insert(17, 19);
        test.insert(5, 8);
        test.insert(4, 8);
        test.insert(15, 18);
        test.insert(7, 10);
        test.insert(21, 24);
        
        for (Integer integer : test.low_keys()) {
            System.out.print(integer + " ");
        }
        System.out.println("");

        for (Integer integer : test.high_keys()) {
            System.out.print(integer + " ");
        }
        System.out.println("");

        for (Integer integer : test.max_keys()) {
            System.out.print(integer + " ");
        }
        System.out.println("");

        System.out.println(test.interval_occurrence(9, 12));
        System.out.println(test.interval_count(9, 21));

        for (Integer integer : test.interval_search(9, 21)) {
            System.out.print(integer + " ");
        }
        System.out.println("");
    }
}