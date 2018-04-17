import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

// Basic implementation for hash functions that use separate chaining.
public class SeparateChaining<Key, Value> {
    //**************************************************************************
    //* PRIVATE API
    //**************************************************************************
    private static final int DEFAULT_HASH_CAPACITY = 10;
    private Node<Key, Value>[] mArrayOfLinkLists = null;
    private int mHashCapacity = DEFAULT_HASH_CAPACITY;
    private int mNodeCount = 0;

    private class Node<Key, Value> {
        private Key mKey = null;
        private Value mValue = null;
        private Node<Key, Value> mNext = null;

        public Node(Key key, Value value) {
            this.mKey = key;
            this.mValue = value;
        }
    }

    private void resize(int newCapacity) {
        Node<Key, Value>[] temp = null; 
        List<Node<Key, Value>> nodes = null;
        int count = 0;

        if (newCapacity < SeparateChaining.DEFAULT_HASH_CAPACITY) {
            newCapacity = SeparateChaining.DEFAULT_HASH_CAPACITY;
        }

        temp = (Node<Key, Value>[]) new Object[newCapacity];

        // Now need to re-hash all the existing keys and place them in
        // potentially new positions.
        // 1. Obtain a list of all the nodes.
        nodes = this.nodes(this.mArrayOfLinkLists);

        // 2a. Save the old count.
        count = this.mNodeCount;

        // 2b. Reset the instance node count (it will be updated by the insert).
        this.mNodeCount = 0;
        
        // 3. Insert on the newly constructed hash array using the new capacity.
        for (Node<Key, Value> node : nodes) {
            this.insert(temp, newCapacity, node.mKey, node.mValue);
        }

        // 4. Set the updated hash table temp to be the instance copy.
        this.mArrayOfLinkLists = temp;

        // 5. Set the new capacity for the class instance.
        this.mHashCapacity = newCapacity;

        // 6. Number of elements in the hash is updated on the insert, but
        // will be checked to make sure it was the same as before.
        assert(count == this.mNodeCount);
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

    private Node<Key, Value> insertNodeInLinkedList(Node<Key, Value> head, Key key, Value value) {
        if (head == null) {
            this.mNodeCount++;
            return new Node<Key, Value>(key, value);
        }

        if (head.mKey.equals(key)) {
            head.mValue = value;
        }
        else {
            head.mNext = this.insertNodeInLinkedList(head.mNext, key, value);
        }

        return head;
    }

    private void insert(Node<Key, Value> [] arrayOfLinkLists, int capacity, Key key, Value value) {
        int hashValue = 0;
        Node<Key, Value> head = null;

        if (arrayOfLinkLists == null || capacity <= 0 || key == null) {
            return;
        }

        // Re-sizing check prior attempting to perform the insert.
        if (this.mNodeCount >= this.mHashCapacity) {
            this.resize(capacity * 2);
        }

        // 1. Obtain the hash value of the key for this specific hash array.
        hashValue = this.hash(key, capacity);

        // 2. Obtain the head of the linked list at the position within the 
        // hash array.
        head = arrayOfLinkLists[hashValue];

        // 3. Attempt to insert the key value pair into the linked list.
        head = this.insertNodeInLinkedList(head, key, value);

        // 4. Save the head of the link list back into the hash array.
        arrayOfLinkLists[hashValue] = head;
    }

    private int hash(Key key, int capacity) {
        return Math.abs(key.hashCode()) % capacity;
    }

    //**************************************************************************
    //* PUBLIC API
    //**************************************************************************
    public SeparateChaining() {
        mArrayOfLinkLists = (Node<Key, Value>[]) new Object[DEFAULT_HASH_CAPACITY];
    }

    public int size() {
        return this.mNodeCount;
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
        this.insert(this.mArrayOfLinkLists, this.mHashCapacity, key, value);
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