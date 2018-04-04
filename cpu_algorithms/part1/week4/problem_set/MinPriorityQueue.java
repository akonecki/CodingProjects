import java.util.Iterator;
import java.util.Comparator;

public class MinPriorityQueue <Key> implements Iterable<Key> {
    //**************************************************************************
    // START PRIVATE MEMBERS
    //**************************************************************************
    // Queue fo the min priority queue follows a LIFO (stack) design.
    private Key [] mQueue = null;
    private Comparator<Key> mComparator = new DefaultKeyComparator();
    private int mSize = 0;

    private void exch(int indexA, int indexB) {
        Key key = this.mQueue[indexA];
        this.mQueue[indexA] = this.mQueue[indexB];
        this.mQueue[indexB] = key;
    }

    // Ensure that the parent is the minimum of its children, otherwise demote.
    private void demote(int index) {
        // 0 1 2 3 4 5 6
        // 0 : 1 2
        // 1 : 3 4
        // 2 : 5 6
        int leftChildIndex = (index * 2) + 1;
        int rightChildIndex = leftChildIndex + 1;
        int keyIndex = -1;

        if (index < 0 || index >= this.mSize) {
            // Can not perform demote operation due to indicies outside of
            // supported ranges.
            return;
        }

        if (rightChildIndex < this.mSize) {
            if (this.mComparator.compare(
                this.mQueue[leftChildIndex], 
                this.mQueue[rightChildIndex]) < 0) {
                // Left child is the least.
                keyIndex = leftChildIndex;
            }
            else {
                // Right child is the least.
                // Right biased on equality.
                keyIndex = rightChildIndex;
            }
        }
        else if (leftChildIndex < this.mSize) {
            keyIndex = leftChildIndex;
        }
        else {
            // Index is a leaf.
            return; 
        }

        // Inspect between the parent index and the least child.
        if (this.mComparator.compare(this.mQueue[index], 
            this.mQueue[keyIndex]) > 0) {
            // Exchange
            this.exch(index, keyIndex);
            // Recursively go down the sub-tree.
            this.demote(keyIndex);
        }

        return;
    }

    // A parent must be smaller than both of its children in order for the 
    // invariant to hold.  Thus promotion is used to move children to their
    // correct positions within the priority queue.
    private void promote(int index) {
        // 0 1 2 3 4 5 6
        // 0 : 1 2
        // 1 : 3 4
        // 2 : 5 6
        int parentIndex = 0;
        
        // Performing non-recursive serial loop to not in-cur over-head of 
        // function call stack operations.
        do {
            parentIndex = (index - 1) / 2;

            if (parentIndex < index) {
                if (this.mComparator.compare(this.mQueue[parentIndex], this.mQueue[index]) > 0) {
                    this.exch(parentIndex, index);
                    index = parentIndex;
                }
                else {
                    index = -1;
                }
            }
            else {
                index = -1;
            }
        } while (index > 0);
    }

    private void resize(int newCapacity) {
        Key [] tempArray = (Key []) new Comparable[newCapacity];

        for (int index = 0; index < this.mSize; index++) {
            tempArray[index] = this.mQueue[index];
        }

        this.mQueue = tempArray;
    }

    private boolean isMinQueue(int index) {
        if (this.mSize == 0) {
            return true;
        }

        for (; index < this.mSize; index++) {
            if (((index * 2) + 1) < this.mSize) {
                if (this.mComparator.compare(this.mQueue[index], this.mQueue[(index * 2) + 1]) > 0) {
                    return false;
                }
            }

            if (((index * 2) + 1 + 1) < this.mSize) {
                if (this.mComparator.compare(this.mQueue[index], this.mQueue[(index * 2) + 1 + 1]) > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private class DefaultKeyComparator implements Comparator<Key> {
        public int compare(Key keyA, Key keyB) {
            // -1 keyA < keyB
            //  0 keyA == keyB
            //  1 keyA > keyB

            if (keyA != null && keyB != null) {
                if (keyA instanceof Comparable && keyB instanceof Comparable) {
                    return ((Comparable)keyA).compareTo((Comparable)keyB);
                }
                else {
                    throw new java.lang.UnsupportedOperationException("Key type class is not comparable.");
                }
            }
            else if (keyA != null) {
                return -1;
            }
            else if (keyB != null) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    private class MinPriorityQueueIterator implements Iterator<Key> {
        private Key [] iteratorQueue = (Key []) new Object[mSize];
        private int index = 0;

        public MinPriorityQueueIterator() {
            // Copy the elements over on instance creation.
            for (int index = 0; index < this.iteratorQueue.length; index++) {
                this.iteratorQueue[index] = mQueue[index];
            }
        }
        
        public boolean hasNext() {
            return this.index < this.iteratorQueue.length;
        }

        public Key next() {
            Key key = null;

            if (this.index < this.iteratorQueue.length) {
                key = this.iteratorQueue[this.index];
                this.index++;
            }

            return key;
        }

        public void remove() {
            return;
        }
    }

    //**************************************************************************
    // END PRIVATE MEMBERS
    //**************************************************************************

    //**************************************************************************
    // START PUBLIC API
    //**************************************************************************
    public MinPriorityQueue() {
        this.mQueue = (Key []) new Object[0];
    }

    public MinPriorityQueue(Comparator<Key> comparator) {
        this();
        this.mComparator = comparator;
    }

    public MinPriorityQueue(int initCapacity) {
        if (initCapacity > 0) {
            this.mQueue = (Key []) new Object[initCapacity];
        }
        else {
            this.mQueue = (Key []) new Object[0];
        }
    }

    public MinPriorityQueue(Comparator<Key> comparator, int initCapacity) {
        this(initCapacity);
        this.mComparator = comparator;
    }

    public MinPriorityQueue(Key [] keys) {
        if (keys == null) {
            this.mQueue = (Key []) new Object[0];
            return;
        }

        this.mQueue = (Key []) new Object[keys.length];

        for (int index = 0; index < keys.length; index++) {
            Key key = keys[index];

            if (key != null) {
                this.mQueue[this.mSize] = key;
                this.mSize++;
            }
        }

        // Need to perform the demotion bottom up approach to ensure that the
        // class instance of Key [] is a min priority queue.
        for (int index = this.mSize / 2; index >= 0; index--) {
            this.demote(index);
        }

        if (this.mSize > 0) {
            assert(this.isMinQueue(0));
        }
    }

    public Key delMin() {
        Key key = null;
        
        if (this.mSize == 0) {
            return key;
        }

        // First element is always the min.
        key = this.mQueue[0];

        // Set the first element to null.
        this.mQueue[0] = null;

        // Exchange the first and last element in the queue.
        this.exch(0, this.mSize - 1);

        // Decrement the queue size;
        this.mSize--;

        // Perform demotion.
        this.demote(0);

        // Dynamic memory cleanup if necessary.
        if (this.mSize <= (this.mQueue.length / 4)) {
            this.resize(this.mQueue.length / 2);
        }

        return key;
    }

    public void insert(Key x) {
        if (x == null) {
            return;
        }

        // Dynamically adjust the instance array if necessary.
        if (this.mSize == this.mQueue.length) {
            if (this.mQueue.length == 0) {
                this.resize(1);
            }
            else {
                this.resize(this.mQueue.length * 2);
            }
        }

        this.mQueue[this.mSize] = x;
        this.mSize++;

        // Fix invariant if necessary.
        this.promote(this.mSize - 1);

        try {
            assert(this.isMinQueue(0));
        }
        catch (java.lang.AssertionError e) {
            System.out.println("Min queue invariant does not hold.");
            for (Key key : this) {
                System.out.print(key.toString() + " ");
            }
            System.out.println("");
            throw new java.lang.AssertionError(e);
        }
    }

    public boolean isEmpty() {
        return this.mSize == 0;
    }

    public Iterator<Key> iterator() {
        return new MinPriorityQueueIterator();
    }

    public Key min() {
        if (this.mSize <= 0) {
            return null;
        }
        return this.mQueue[0];
    }

    public int size() {
        return this.mSize;
    }

    public int capacity() {
        if (this.mQueue == null) {
            return 0;
        }
        return this.mQueue.length;
    }

    public static void main(String [] args) {
        Integer [] keys = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        MinPriorityQueue<Integer> mpq_a = new MinPriorityQueue<Integer>();
        MinPriorityQueue<Integer> mpq_b = new MinPriorityQueue<Integer>(keys);
        mpq_a.insert(10);
        mpq_a.insert(9);
        mpq_a.insert(8);
        mpq_a.insert(7);
        mpq_a.insert(6);
        mpq_a.insert(5);

        for (Integer integer : mpq_a) {
            System.out.print(integer.toString() + " ");
        }

        System.out.println("");
        System.out.println("Deleting " + mpq_a.delMin().toString());
        System.out.println("Deleting " + mpq_a.delMin().toString());
        System.out.println("Deleting " + mpq_a.delMin().toString());
        System.out.println("Deleting " + mpq_a.delMin().toString());
        System.out.println("Deleting " + mpq_a.delMin().toString());
        System.out.println("Deleting " + mpq_a.delMin().toString());
        System.out.println("Deleting " + mpq_a.delMin());
        System.out.println(mpq_a.size());
        System.out.println(mpq_a.capacity());
        mpq_a.insert(10);
        mpq_a.insert(9);
        mpq_a.insert(8);
        mpq_a.insert(7);
        mpq_a.insert(6);
        mpq_a.insert(5);
        System.out.println(mpq_a.size());
        System.out.println(mpq_a.capacity());

        for (Integer integer : mpq_b) {
            System.out.print(integer.toString() + " ");
        }

        System.out.println("");

        return;
    }
    //**************************************************************************
    // END PUBLIC API
    //**************************************************************************
}