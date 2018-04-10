public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED_LINK = true;
    private static final boolean BLACK_LINK = false;
    private Node<Key, Value> mRoot = null;

    private class Node<Key extends Comparable<Key>, Value> {
        // Left leaning node in a Red Black BST implementation.
        private Key mKey = null;
        private Node<Key, Value> mLeftChild = null;
        private Node<Key, Value> mRightChild = null;
        private Value mValue = null;
        private int mCount = 1;
        private boolean mLinkColor = RedBlackBST.RED_LINK;

        public Node(Key key, Value value) {
            this.mKey = key;
            this.mValue = value;
        }
    }

    public void insert(Key key, Value value) {
        if (key == null || value == null) {
            return;
        }

        // Update the root post insert.
        this.mRoot = this.insert(this.mRoot, key, value);
    }

    private Node<Key, Value> insert(Node<Key, Value> root, Key key, Value value) {
        if (root == null) {
            // Not found create a new node.
            return new Node<Key, Value>(key, value);
        }

        if (root.mKey.compareTo(key) > 0) {
            // key is less than go left.
            root.mLeftChild = this.insert(root.mLeftChild, key, value);
        }
        else if (root.mKey.compareTo(key) < 0) {
            // key is greater than go right.
            root.mRightChild = this.insert(root.mRightChild, key, value);
        }
        else {
            // key is equal, update since not supporting duplicate keys.
            root.mValue = value;
        }
        
        // Apply upward invariant for Red Black Tree.

        // At this point the tree is collapsing upward.  Local sub-tree that was
        // traversed for the insert must have a correct invariant, otherwise bad
        // things will occur.


        return root;
    }

    // Localized flipping of the link colors.
    private void flip() {

    }

    // Localized rotation right (e.g. the left becomes the parent)
    private void rotateRight() {

    }

    // Localized rotation left (e.g. the right becomes the parent)
    private void rotateLeft() {

    }

    public Value delete(Key key) {
        return null;
    }

    public Value search(Key key) {
        return null;
    }

    public int rank(Key key) {
        return 0;
    }

    public int ceil(Key key) {
        return 0;
    }

    public int floor(Key key) {
        return 0;
    }

    public static void main(String [] args) {

    }
}