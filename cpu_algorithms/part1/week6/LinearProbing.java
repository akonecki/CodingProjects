import java.lang.Math;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

// This linear probing uses the traditional linear probing implmentation such
// that if the hashed index is full go to the next location until an empty 
// space is encountered.  Once an empty space is encountered then fill with the
// new value, which is acting as the key.  On the delete side will need to 
// account for the an invalid node with tomb stones.  
public class LinearProbing <Value> {

    private static final int DEFAULT_SIZE = 128; 
    private Value [] mValueArrayHash = null;
    private boolean [] mTombStoneAtIndex = null;
    private int mCapacity = 0;
    private int mSize = 0;

    private void resize(int newCapacity) {
        List<Value> values = null;
        int prevSize = 0;

        if (newCapacity < LinearProbing.DEFAULT_SIZE) {
            newCapacity = LinearProbing.DEFAULT_SIZE;
        }

        // Get a copy of all the values locally.
        values = this.values();
        prevSize = this.mSize;

        // Change the size of the array based hash.
        this.mCapacity = newCapacity;
        this.mValueArrayHash = (Value []) new Object[newCapacity];
        this.mTombStoneAtIndex = new boolean[newCapacity];
        this.mSize = 0;

        // Insert the old nodes into the new hash array table.
        for (Value value : values) {
            this.insert(value);
        }

        // Make sure the the new instance size equals the old instance size.
        assert(this.mSize == prevSize);
    }

    public LinearProbing() {
        this.mValueArrayHash = (Value []) new Object[DEFAULT_SIZE];
        this.mTombStoneAtIndex = new boolean[DEFAULT_SIZE];
    }

    // Provide a hash of the value.
    public int hash(Value value) {
        if (this.mCapacity == 0) {
            return 0;
        }
        else {
            return Math.abs(value.hashCode()) % this.mCapacity;
        }
    }

    // Return the array size.
    public int capacity() {
        return this.mCapacity;
    }

    // Return the number of active values within the array.
    public int size() {
        return this.mSize;
    }

    // Insert a new value within the array.
    public void insert(Value value) {
        if (value == null) {
            throw new java.lang.IllegalArgumentException("Null argument.");
        }

        if (this.mSize >= this.mCapacity) {
            this.resize(this.mCapacity * 2);
        }

        int index = this.hash(value);

        while (this.mValueArrayHash[index] != null && !this.mValueArrayHash[index].equals(value)) {
            index = (index + 1) % this.mCapacity;
        }

        if (!value.equals(this.mValueArrayHash[index])) {
            this.mSize++;
        }

        this.mTombStoneAtIndex[index] = false;
        this.mValueArrayHash[index] = value;
    }

    public void delete(Value value) {
        if (value == null) {
            throw new java.lang.IllegalArgumentException("Null argument.");
        }

        int index = this.hash(value);
        int startIndex = index;

        while (index != startIndex && !this.mValueArrayHash[index].equals(value) && 
            (this.mValueArrayHash[index] != null || this.mTombStoneAtIndex[index])) {
            index = (index + 1) % this.mCapacity;
        }

        // Value not found in the array.
        if ((index == startIndex && !value.equals(this.mValueArrayHash[index])) || 
            this.mValueArrayHash[index] == null) {
            return;
        }

        this.mValueArrayHash[index] = null;
        this.mTombStoneAtIndex[index] = true;
        this.mSize--;

        // Now determine if need to adjust the size of the array based hash.
        if (this.mSize * 4 <= this.mCapacity && this.mCapacity > LinearProbing.DEFAULT_SIZE) {
            this.resize(this.mCapacity / 2);
        } 
    }

    public List<Value> values() {
        List<Value> values = new ArrayList<Value>();

        for (Value value : this.mValueArrayHash) {
            if (value != null) {
                values.add(value);
            }
        }

        return values;
    }

    public static void main(String [] args) {
        LinearProbing<Integer> integers = new LinearProbing<Integer>();
        
        // T1 - Make sure that unique entries only exist.
        integers.insert(1);
        integers.insert(1);
        integers.insert(1);
        integers.insert(1);
        assert(integers.size() == 1);
        assert(integers.capacity() == LinearProbing.DEFAULT_SIZE);

        // T2 - Attempt to delete an entry that has never existed.
        integers.delete(2);
        assert(integers.size() == 1);
        assert(integers.capacity() == LinearProbing.DEFAULT_SIZE);

        // T3 - Delete an entry that does exist
        integers.delete(1);
        assert(integers.size() == 0);
        assert(integers.capacity() == LinearProbing.DEFAULT_SIZE);

        // T4 - Delete an entry that did exist
        integers.delete(1);
        assert(integers.size() == 0);
        assert(integers.capacity() == LinearProbing.DEFAULT_SIZE);

        // T5 - Fill the array based hash.
        for (int index = 0; index < LinearProbing.DEFAULT_SIZE; index++) {
            integers.insert(index);
        }
        assert(integers.size() == LinearProbing.DEFAULT_SIZE);
        assert(integers.capacity() == LinearProbing.DEFAULT_SIZE);

        // T6 - Add a new value.
        integers.insert(LinearProbing.DEFAULT_SIZE);
        assert(integers.size() == (LinearProbing.DEFAULT_SIZE + 1));
        assert(integers.capacity() == (LinearProbing.DEFAULT_SIZE * 2));
    }
}