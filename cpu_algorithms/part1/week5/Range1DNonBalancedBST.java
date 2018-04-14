import java.util.List;
import java.util.ArrayList;

public class Range1DNonBalancedBST <Key extends Comparable<Key>, Value> {

    //*************************************************************************
    //* PRIVATE FACING API
    //************************************************************************* 
    Node<Key, Value> mRoot = null;

    private class Node <Key extends Comparable<Key>, Value> {
        private Key mKey = null;
        private Value mValue = null;
        private int mCount = 1;
        private Node<Key, Value> mLeftChild = null;
        private Node<Key, Value> mRightChild = null;

        public Node(Key key, Value value) {
            this.mKey = key;
            this.mValue = value;
        }
    }

    public int size(Node<Key, Value> node) {
        if (node == null) {
            return 0;
        }

        return node.mCount;
    }

    private Node<Key, Value> search(Node<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }

        if (root.mKey.compareTo(key) > 0) {
            return this.search(root.mLeftChild, key);
        }
        else if (root.mKey.compareTo(key) < 0) {
            return this.search(root.mRightChild, key);
        }
        else {
            return root;
        }
    }

    private Node<Key, Value> insert(Node<Key, Value> root, Key key, Value value) {
        if (root == null) {
            return new Node<Key, Value>(key, value);
        }

        if (root.mKey.compareTo(key) > 0) {
            root.mLeftChild = this.insert(root.mLeftChild, key, value);
        }
        else if (root.mKey.compareTo(key) < 0) {
            root.mRightChild = this.insert(root.mRightChild, key, value);
        }
        else {
            root.mValue = value;
        }

        root.mCount = 1 + this.size(root.mLeftChild) + this.size(root.mRightChild);

        return root;
    }

    private int rank(Node<Key, Value> root, Key key) {
        if (root == null) {
            return 0;
        }

        if (root.mKey.compareTo(key) >= 0) {
            // Go left
            return this.rank(root.mLeftChild, key);
        }
        else { // (root.mKey.compareTo(key) < 0) {
            return 1 + this.size(root.mLeftChild) + this.rank(root.mRightChild, key);
        }
    }

    private void range_search(Node<Key, Value> root, Key low, Key high, List<Key> keys) {
        if (root == null) {
            return;
        }
        
        if (root.mKey.compareTo(high) > 0) {
            // Go left
            this.range_search(root.mLeftChild, low, high, keys);
        }
        else if (root.mKey.compareTo(high) <= 0 && root.mKey.compareTo(low) >= 0) {
            this.range_search(root.mLeftChild, low, high, keys);
            keys.add(root.mKey);
            this.range_search(root.mRightChild, low, high, keys);
        } 
    }

    private void keys(Node<Key, Value> root, List<Key> keys) {
        if (root == null) {
            return;
        }

        this.keys(root.mLeftChild, keys);
        keys.add(root.mKey);
        this.keys(root.mRightChild, keys);
    }

    //*************************************************************************
    //* PUBLICLY FACING API
    //************************************************************************* 
    public int size() {
        return this.size(this.mRoot);
    }
    
    public boolean contains(Key key) {
        if (key == null) {
            throw new java.lang.IllegalArgumentException("Key can not be null for contains.");
        }

        if (this.search(this.mRoot, key) == null) {
            return false;
        }

        return true;
    }

    // This BST will not support collisions on the Key for inserting.  If a 
    // Key symbol already exists then the new value will replace the old value.
    public void insert(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null for insertion.");
        }

        this.mRoot = this.insert(this.mRoot, key, value);
    }

    public int rank(Key key) {
        if (key == null) {
            throw new java.lang.IllegalArgumentException("Invalid key for rank not accepted.");
        }

        return this.rank(this.mRoot, key);
    }

    public int range_count(Key keyLow, Key keyHigh) {
        if (keyLow == null || keyHigh == null) {
            throw new java.lang.IllegalArgumentException("Null key not supported for range count.");
        }

        if (keyLow.compareTo(keyHigh) > 0) {
            throw new java.lang.IllegalArgumentException("Does not support out of range i.e. low key is more than high key.");
        }

        if (this.contains(keyHigh)) {
            return 1 + this.rank(this.mRoot, keyHigh) - this.rank(this.mRoot, keyLow);
        }
        else {
            return this.rank(this.mRoot, keyHigh) - this.rank(this.mRoot, keyLow);
        }
    }

    public List<Key> range_search(Key keyLow, Key keyHigh) {
        List<Key> keys = new ArrayList<Key>();
        
        if (keyLow == null || keyHigh == null) {
            throw new java.lang.IllegalArgumentException("Null key not supported for range search.");
        }

        if (keyLow.compareTo(keyHigh) > 0) {
            throw new java.lang.IllegalArgumentException("Does not support out of range i.e. low key is more than high key.");
        }

        this.range_search(this.mRoot, keyLow, keyHigh, keys);

        return keys;
    }

    public List<Key> keys() {
        List<Key> keys = new ArrayList<Key>();
        this.keys(this.mRoot, keys);
        return keys;
    }

    public static void main(String [] args) {
        Range1DNonBalancedBST<Integer, Integer> test = new Range1DNonBalancedBST<Integer, Integer>();
        test.insert(1, 1);
        test.insert(2, 2);
        test.insert(1, 1);
        test.insert(-1, 1);
        test.insert(0, 1);
        test.insert(3, 1);
        test.insert(4, 1);
        test.insert(8, 1);
        test.insert(5, 1);
        test.insert(9, 1);
        test.insert(1, 1);
        test.insert(1, 1);

        for(Integer integer : test.keys()) {
            System.out.print(integer + " ");
        }
        System.out.println("");

        System.out.println(test.rank(1));
        System.out.println(test.rank(-1));
        System.out.println(test.range_count(-1,1));
        for(Integer integer : test.range_search(-1,1)) {
            System.out.print(integer);
            System.out.print(" ");
        }

        System.out.println("");
    }
}