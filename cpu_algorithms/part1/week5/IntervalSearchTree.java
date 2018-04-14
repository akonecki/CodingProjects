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

        public int compare(Node<Key> low, Node<Key> high) {
            return this.max_node().compare(low, high);
        }

        private class MaxNode implements Comparator<Node <Key>> {
            public int compare(Node<Key> left, Node<Key> right) {
                // -1 : left
                //  0 : parent
                //  1 : right

                if (left != null && right != null) {
                    if (right.mMaxKey.compareTo(left.mMaxKey) >= 0) {
                        if (right.mMaxKey.compareTo(Node.this.mMaxKey) > 0) {
                            return 1;
                        }
                        else {
                            return 0;
                        }
                    }
                    else {// if (right.mMaxKey.compareTo(left.mMaxKey) < 0) {
                        if (left.mMaxKey.compareTo(Node.this.mMaxKey) > 0) {
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

        // Invariant fix up the path.
        if (root.compare(root.mLeftChild, root.mRightChild) > 0) {
            root.mMaxKey = root.mRightChild.mMaxKey;
        }
        else if (root.compare(root.mLeftChild, root.mRightChild) < 0) {
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
        keys.add(root.mMaxKey);
        this.max_key_inorder_traversal(root.mRightChild, keys);
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
        return 0;
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
    }
}