public class MinBinaryHeap <Key extends Comparable<Key>> {
    // Queue is more stack based in design, operations occur on the last element
    // in the array.
    private Key [] mMinPriorityQueue;
    // it is possible that mMinPriorityQueue.length != mQueueLength.
    // mMinPriorityQueue.lengthis the capacity where mQueueLength is the number
    // of active elements in the heap.
    private int mQueueLength;

    public MinBinaryHeap() {
        this.mMinPriorityQueue = (Key []) new Comparable[0];  
        this.mQueueLength = 0;  
    }

    public MinBinaryHeap(int size) {
        if (size < 0) {
            size = 0;  
        }

        // User defines a starting size of the heap data structure.
        this.mMinPriorityQueue = (Key []) new Comparable[size];
        this.mQueueLength = 0;
    }

    public MinBinaryHeap(Key [] data) {
        // User is providing the starting set of data of keys.
        this.mQueueLength = 0;
        if (data == null) {
            this.mMinPriorityQueue = (Key []) new Comparable[0];
            return;
        }

        this.mMinPriorityQueue = (Key []) new Comparable[data.length];

        for (int index = 0; index < (data.length); index++) {
            // Want to be safe and provide immutable, no references available
            // for application space by user to make modifications to the key.
            if (data[index] != null) {
                // Increment the overall length.  The length of the array is 
                // the capacity of the heap.
                this.mQueueLength++;
                this.insert(data[index]);
            }    
        }
    }

    private void resize(int newCapacity) {
        Key [] temp = (Key []) new Comparable[newCapacity];
        for (int index = 0; index < mQueueLength; index++) {
            // Copy over to the temporary array of keys.
            temp[index] = mMinPriorityQueue[index];
        }

        // Reference pointer update.
        mMinPriorityQueue = temp;

        // Length remains the same.
    }

    public void insert(Key key) {
        if (this.mMinPriorityQueue == null) {
            // throw new NullArgumentException("Priority queue is null.  Please call the constructor.");
        }

        // Check to see if the number of entries is equal to the capacity.
        if (this.mQueueLength == this.mMinPriorityQueue.length) {
            // Double the capacity to aid in insertion of new keys.
            if (this.mMinPriorityQueue.length == 0) {
                this.resize(this.mMinPriorityQueue.length + 1);
            }
            else {
                this.resize(this.mMinPriorityQueue.length * 2);
            }
        }

        // Insert the new key to the end of the heap.
        mMinPriorityQueue[mQueueLength] = key;
        mQueueLength++;    
        
        // Ensure that the new inserted node addresses the invariant.
        this.promote(mQueueLength - 1);
    }

    public Key deleteMin() {
        Key key = null;

        if (this.mQueueLength > 0) {
            key = this.mMinPriorityQueue[0];
            this.exch(0, this.mQueueLength - 1);
            this.mQueueLength--;
            this.demote(0);

            if (this.mQueueLength <= (this.mMinPriorityQueue.length / 4)) {
                this.resize(this.mMinPriorityQueue.length / 2);
            }

            return key;
        }
        else {
            return key;
        }
    }

    public boolean isEmpty() {
        return this.mQueueLength == 0;
    }

    public Key min() {
        if (this.mQueueLength > 0) {
            return this.mMinPriorityQueue[0];
        }
        else {
            return null;
        }
    }

    public void printHeap() {
        for (int index = 0; index < this.mQueueLength; index++) {
            Key key = this.mMinPriorityQueue[index];
            System.out.print(key.toString() + " ");
        }
        System.out.println("");
    }

    private void exch(int aIndex, int bIndex) {
        Key temp = this.mMinPriorityQueue[aIndex];

        if (bIndex < this.mQueueLength) {
            this.mMinPriorityQueue[aIndex] = this.mMinPriorityQueue[bIndex];
            this.mMinPriorityQueue[bIndex] = temp;
        }
    }

    private int less(Key a, Key b) {
        // a > b  :: 1
        // a == b :: 0
        // a < b  :: -1
        return a.compareTo(b);
    }

    // Swim
    // -------------------------------------
    // | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
    // -------------------------------------
    // k = 2       k = 3
    // parent = 0  parent = 1
    private void promote(int indexForPromotion) {
        int parentIndex = (indexForPromotion / 2);
        
        if (indexForPromotion % 2 == 0) {
            parentIndex--;
        }

        while (indexForPromotion > 0) {
            if (this.less(this.mMinPriorityQueue[indexForPromotion], this.mMinPriorityQueue[parentIndex]) < 0) {
                this.exch(indexForPromotion, parentIndex);
                indexForPromotion = parentIndex;
                parentIndex /= 2;

                if (indexForPromotion % 2 == 0) {
                    parentIndex--;
                }
            }
            else {
                break;
            }    
        }
    }

    // Sink
    private void demote(int demotionIndex) {
        while (demotionIndex < mQueueLength) {
            int leftChildIndex = demotionIndex * 2 + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (rightChildIndex < mQueueLength) {
                // Determine the smallest child.
                if (this.less(this.mMinPriorityQueue[leftChildIndex], this.mMinPriorityQueue[rightChildIndex]) < 0) {
                    // Left child is smaller than right.
                    if (this.less(this.mMinPriorityQueue[leftChildIndex], this.mMinPriorityQueue[demotionIndex]) < 0) {
                        this.exch(leftChildIndex, demotionIndex);
                        demotionIndex = leftChildIndex;    
                    }
                    else {
                        // the left child, the smaller of the two children is 
                        // greater than the parent thus the invariant holds.
                        return;
                    }
                }
                else {
                    // Right child is smaller than left.
                    if (this.less(this.mMinPriorityQueue[rightChildIndex], this.mMinPriorityQueue[demotionIndex]) < 0) {
                        this.exch(rightChildIndex, demotionIndex);
                        demotionIndex = rightChildIndex;    
                    }
                    else {
                        // the right child, the smaller of the two children is 
                        // greater than the parent thus the invariant holds.
                        return;
                    }
                }
            }
            else if (leftChildIndex < mQueueLength) {
                // Left child only.
                if (this.less(this.mMinPriorityQueue[leftChildIndex], this.mMinPriorityQueue[demotionIndex]) < 0) {
                    this.exch(leftChildIndex, demotionIndex);
                    demotionIndex = leftChildIndex;    
                }
                else {
                    // the left child, the smaller of the two children is 
                    // greater than the parent thus the invariant holds.
                    return;
                }
            }
            else {
                // left and right child are over thus at a leaf.
                return;
            }
        }
    }

    public static void main(String [] args) {
        MinBinaryHeap <Integer> heap = new MinBinaryHeap<Integer>();
        heap.isEmpty();
        heap.insert(5);
        heap.insert(5);
        heap.insert(10);
        heap.insert(5);
        heap.insert(7);
        heap.insert(13);
        heap.insert(1);
        heap.insert(4);
        heap.insert(6);
        heap.printHeap();
        heap.min();
        heap.deleteMin();
        heap.deleteMin();
        heap.deleteMin();
        heap.printHeap();
        heap.insert(13);
        heap.printHeap();
        return;
    }
}