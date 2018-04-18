import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

// Basic implementation for hash functions that use separate chaining.
public class SeparateChaining<Key, Value> {
    //**************************************************************************
    //* PRIVATE API
    //**************************************************************************
    private static final int DEFAULT_HASH_CAPACITY = 10;
    private Node[] mArrayOfLinkLists = new Node[DEFAULT_HASH_CAPACITY];
    private int mHashCapacity = DEFAULT_HASH_CAPACITY;
    private int mNodeCount = 0;

    private static class Node {
        private Object mKey = null;
        private Object mValue = null;
        private Node mNext = null;

        public Node(Object key, Object value) {
            this.mKey = key;
            this.mValue = value;
        }
    }

    private void resize(int newCapacity) {
        Node[] temp = null; 
        List<Node> nodes = null;
        int count = 0;

        if (newCapacity < SeparateChaining.DEFAULT_HASH_CAPACITY) {
            newCapacity = SeparateChaining.DEFAULT_HASH_CAPACITY;
        }

        temp = new Node[newCapacity];

        // Now need to re-hash all the existing keys and place them in
        // potentially new positions.
        // 1. Obtain a list of all the nodes.
        nodes = this.nodes(this.mArrayOfLinkLists);

        // 2a. Save the old count.
        count = this.mNodeCount;

        // 2b. Reset the instance node count (it will be updated by the insert).
        this.mNodeCount = 0;
        
        // 3. Insert on the newly constructed hash array using the new capacity.
        for (Node node : nodes) {
            this.insert(temp, newCapacity, (Key) node.mKey, (Value) node.mValue);
        }

        // 4. Set the updated hash table temp to be the instance copy.
        this.mArrayOfLinkLists = temp;

        // 5. Set the new capacity for the class instance.
        this.mHashCapacity = newCapacity;

        // 6. Number of elements in the hash is updated on the insert, but
        // will be checked to make sure it was the same as before.
        assert(count == this.mNodeCount);
    }

    private List<Node> nodes(Node[] arrayOfLinkLists) {
        List<Node> nodes = new ArrayList<Node>();
        int count = this.size();

        if (arrayOfLinkLists != null) {
            for (Node node : arrayOfLinkLists) {
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

    private void keys(Node[] arrayOfLinkLists, List<Key> keys) {
        if (arrayOfLinkLists == null || keys == null) {
            return;
        }

        for (Node node : this.nodes(arrayOfLinkLists)) {
            keys.add((Key) node.mKey);
        }
    }

    private void values(Node[] arrayOfLinkLists, List<Value> values) {
        if (arrayOfLinkLists == null || values == null) {
            return;
        }

        for (Node node : this.nodes(arrayOfLinkLists)) {
            values.add((Value) node.mValue);
        }
    }

    private Node insertNodeInLinkedList(Node head, Key key, Value value) {
        if (head == null) {
            this.mNodeCount++;
            return new Node((Key) key, (Value) value);
        }

        if (head.mKey.equals(key)) {
            head.mValue = value;
        }
        else {
            head.mNext = this.insertNodeInLinkedList(head.mNext, key, value);
        }

        return head;
    }

    private void insert(Node [] arrayOfLinkLists, int capacity, Key key, Value value) {
        int hashValue = 0;
        Node head = null;

        if (arrayOfLinkLists == null || capacity <= 0 || key == null) {
            return;
        }

        // Re-sizing check prior attempting to perform the insert.
        if (this.mNodeCount * 10 >= this.mHashCapacity) {
            // this.resize(capacity * 2);
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

    private Node getNode(Node [] arrayOfLinkLists, Key key) {
        Node node = null;
        
        if (arrayOfLinkLists == null || key == null) {
            return node;
        }

        // Get the head of the linked list.
        node = arrayOfLinkLists[this.hash(key, this.mHashCapacity)];

        // Traverse the list to search for the node.
        while (node != null) {
            if (node.mKey.equals(key)) {
                return node;
            }

            node = node.mNext;
        }

        return node;
    }

    private void delete(Node [] arrayOfLinkLists, Key key) {
        int index = 0;
        
        if (arrayOfLinkLists == null || key == null) {
            return;
        }

        // 1. Need to hash the key.
        index = this.hash(key, this.mHashCapacity);

        // 2. Attempt to delete from the list and update the new head for the hash.
        arrayOfLinkLists[index] = this.deleteFromList(arrayOfLinkLists[index], key);

        // 3. Resize if necessary.
        if (this.mHashCapacity / this.mNodeCount >= 100) {
            // this.resize(this.mHashCapacity / 2);
        }
    }

    private Node deleteFromList(Node head, Key key) {
        Node node = null;
        
        if (head == null) {
            return null;
        }

        if (head.mKey.equals(key)) {
            node = head.mNext;
            node.mNext = null;
            node.mKey = null;
            node.mValue = null;
            this.mNodeCount--;
            return node;
        } 
        else {
            head.mNext = this.deleteFromList(head.mNext, key);
            return head;
        }
    }

    //**************************************************************************
    //* PUBLIC API
    //**************************************************************************
    public SeparateChaining() {
        // Could create all the heads
        mArrayOfLinkLists = new Node[10];// (Node []) new Object[SeparateChaining.DEFAULT_HASH_CAPACITY];
    }

    public int size() {
        return this.mNodeCount;
    }

    public int capacity() {
        return this.mHashCapacity;
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
        return this.hash(key, this.mHashCapacity);
    }

    public void insert(Key key, Value value) {
        if (key == null) {
            throw new java.lang.IllegalArgumentException("Invalid key value");
        }

        this.insert(this.mArrayOfLinkLists, this.mHashCapacity, key, value);
        return;
    }

    public Value get(Key key) {
        Node node = null;

        node = this.getNode(this.mArrayOfLinkLists, key);

        if (node == null) {
            return null;
        }
        else {
            return (Value) node.mValue;
        }   
    }

    public boolean containsKey(Key key) {
        Node node = null;

        node = this.getNode(this.mArrayOfLinkLists, key);
        
        if (node == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean containsValue(Value value) {
        for (Value tempValue : this.values()) {
            if (tempValue.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void delete(Key key) {
        this.delete(this.mArrayOfLinkLists, key);
        return;
    }

    public static void main(String [] args) {
        SeparateChaining<String, Integer> words = new SeparateChaining<String, Integer>();

        for (int index = 0; index < 1000; index++) {
            words.insert("abc" + Integer.toString(index), index);
        }

        System.out.println(words.size() + " " + words.capacity());
    }
}