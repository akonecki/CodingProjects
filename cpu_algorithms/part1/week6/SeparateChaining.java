import java.util.List;
import java.util.ArrayList;

// Basic implementation for hash functions that use separate chaining.
public class SeparateChaining<Key, Value> {
    //**************************************************************************
    //* PRIVATE API
    //**************************************************************************
    private static final int DEFAULT_HASH_CAPACITY = 10;
    private Node<Key, Value>[] mArrayOfLinkLists = null;
    private int mHashCapacity = DEFAULT_HASH_CAPACITY;
    private int mKeyCount = 0;

    private class Node<Key, Value> {
        private Key mKey = null;
        private Value mValue = null;
        private Node<Key, Value> mNext = null;
    }

    private void resize(int newCapacity) {
        Node<Key, Value>[] temp = null; 
        List<Node<Key, Value>> nodes = null;

        if (newCapacity < SeparateChaining.DEFAULT_HASH_CAPACITY) {
            newCapacity = SeparateChaining.DEFAULT_HASH_CAPACITY;
        }

        temp = (Node<Key, Value>[]) new Object[newCapacity];

        // Now need to re-hash all the existing keys and place them in
        // potentially new positions.
        // 1. Obtain a list of all the nodes.
        nodes = this.nodes(this.mArrayOfLinkLists);

        // 2. Insert on the newly constructed hash array using the new capacity.
        for (Node<Key, Value> node : nodes) {
            this.insert(temp, newCapacity, node.mKey, node.mValue);
        }

        // 3. Set the updated hash table temp to be the instance copy.
        this.mArrayOfLinkLists = temp;

        // 4. Set the new capacity for the class instance.
        this.mHashCapacity = newCapacity;

        // 5. Number of elements in the hash is left alone, their count does not change.
    }

    private List<Node<Key, Value>> nodes(Node<Key, Value>[] arrayOfLinkLists) {
        List<Node<Key, Value>> nodes = new ArrayList<Node<Key, Value>>();
        int count = this.size();

        if (arrayOfLinkLists != null) {
            for (Node<Key, Value> node : arrayOfLinkLists) {
                while (node != null) {
                    nodes.add(node);
                    node = node.mNext;
                    count--;
                } 

                // Don't need to look at rest since count has been met, the rest 
                // should be empty.
                if (count <= 0) {
                    break;
                }
            }
        }

        assert(count == 0);

        return nodes;
    }

    private void keys(Node<Key, Value>[] arrayOfLinkLists, List<Key> keys) {
        if (arrayOfLinkLists == null || keys == null) {
            return;
        }

        for (Node<Key, Value> node : this.nodes(arrayOfLinkLists)) {
            keys.add(node.mKey);
        }
    }

    private void values(Node<Key, Value>[] arrayOfLinkLists, List<Value> values) {
        if (arrayOfLinkLists == null || values == null) {
            return;
        }

        for (Node<Key, Value> node : this.nodes(arrayOfLinkLists)) {
            values.add(node.mValue);
        }
    }

    private void insert(Nodes<Key, Value> [] arrayOfLinkLists, int capacity, Key key, Value value) {
        if (arrayOfLinkLists == null || capacity <= 0 || key == null) {
            return;
        }


    }

    //**************************************************************************
    //* PUBLIC API
    //**************************************************************************
    public SeparateChaining() {
        mArrayOfLinkLists = (Node<Key, Value>[]) new Object[DEFAULT_HASH_CAPACITY];
    }

    public int size() {
        return this.mKeyCount;
    }

    // Will use java provided library for returning iterable for simplicity.
    public List<Key> keys() {
        List<Key> keys = new ArrayList<Key>();

        this.keys(this.mArrayOfLinkLists, keys);

        return keys;
    }

    // Will use java provided library for returning iterable for simplicity.
    public List<Value> values() {
        List<Value> values = new ArrayList<Value>();

        this.values(this.mArrayOfLinkLists, values);

        return values;
    }

    public int hash(Key key) {
        return key.hashCode();
    }

    public void insert(Key key, Value value) {
        return;
    }

    public Value get(Key key) {
        return null;
    }

    public boolean containsKey(Key key) {
        return false;
    }

    public boolean containsValue(Value value) {
        return false;
    }

    public void delete(Key key) {
        return;
    }

    public static void main(String [] args) {

    }
}