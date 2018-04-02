public class MaxBinaryHeap <Key extends Comparable<Key>> {
    // Queue is more stack based in design, operations occur on the last element
    // in the array.
    private Key [] mMaxPriorityQueue;
    // it is possible that mMaxPriorityQueue.length != mQueueLength.
    // mMaxPriorityQueue.lengthis the capacity where mQueueLength is the number
    // of active elements in the heap.
    private int mQueueLength;

    public MaxBinaryHeap() {
        this.mMaxPriorityQueue = (Key []) new Comparable[0];  
        this.mQueueLength = 0;  
    }

    public MaxBinaryHeap(int size) {
        if (size < 0) {
            size = 0;  
        }

        // User defines a starting size of the heap data structure.
        this.mMaxPriorityQueue = (Key []) new Comparable[size];
        this.mQueueLength = 0;
    }

    public MaxBinaryHeap(Key [] data) {
        // User is providing the starting set of data of keys.
        this.mQueueLength = 0;
        if (data == null) {
            this.mMaxPriorityQueue = (Key []) new Comparable[0];
            return;
        }

        this.mMaxPriorityQueue = (Key []) new Comparable[data.length];

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
            temp[index] = mMaxPriorityQueue[index];
        }

        // Reference pointer update.
        mMaxPriorityQueue = temp;

        // Length remains the same.
    }

    public void insert(Key key) {
        if (this.mMaxPriorityQueue == null) {
            // throw new NullArgumentException("Priority queue is null.  Please call the constructor.");
        }

        // Check to see if the number of entries is equal to the capacity.
        if (this.mQueueLength == this.mMaxPriorityQueue.length) {
            // Double the capacity to aid in insertion of new keys.
            if (this.mMaxPriorityQueue.length == 0) {
                this.resize(this.mMaxPriorityQueue.length + 1);
            }
            else {
                this.resize(this.mMaxPriorityQueue.length * 2);
            }
        }

        // Insert the new key to the end of the heap.
        mMaxPriorityQueue[mQueueLength] = key;
        mQueueLength++;    
        
        // Ensure that the new inserted node addresses the invariant.
        this.promote(mQueueLength - 1);
    }

    public Key deleteMax() {
        Key key = null;

        if (this.mQueueLength > 0) {
            key = this.mMaxPriorityQueue[0];
            this.exch(0, this.mQueueLength - 1);
            this.mQueueLength--;
            this.demote(0);

            if (this.mQueueLength <= (this.mMaxPriorityQueue.length / 4)) {
                this.resize(this.mMaxPriorityQueue.length / 2);
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

    public Key max() {
        if (this.mQueueLength > 0) {
            return this.mMaxPriorityQueue[0];
        }
        else {
            return null;
        }
    }

    public void printHeap() {
        for (int index = 0; index < this.mQueueLength; index++) {
            Key key = this.mMaxPriorityQueue[index];
            System.out.print(key.toString() + " ");
        }
        System.out.println("");
    }

    private void exch(int aIndex, int bIndex) {
        Key temp = this.mMaxPriorityQueue[aIndex];

        if (bIndex < this.mQueueLength) {
            this.mMaxPriorityQueue[aIndex] = this.mMaxPriorityQueue[bIndex];
            this.mMaxPriorityQueue[bIndex] = temp;
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
            if (this.less(this.mMaxPriorityQueue[indexForPromotion], this.mMaxPriorityQueue[parentIndex]) > 0) {
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
                // Determine the largest child.
                if (this.less(this.mMaxPriorityQueue[leftChildIndex], this.mMaxPriorityQueue[rightChildIndex]) > 0) {
                    // Left child is bigger than right.
                    if (this.less(this.mMaxPriorityQueue[leftChildIndex], this.mMaxPriorityQueue[demotionIndex]) > 0) {
                        this.exch(leftChildIndex, demotionIndex);
                        demotionIndex = leftChildIndex;    
                    }
                    else {
                        // the left child, the greater of the two children is 
                        // less than the parent thus the invariant holds.
                        return;
                    }
                }
                else {
                    // Right child is biger than left.
                    if (this.less(this.mMaxPriorityQueue[rightChildIndex], this.mMaxPriorityQueue[demotionIndex]) > 0) {
                        this.exch(rightChildIndex, demotionIndex);
                        demotionIndex = rightChildIndex;    
                    }
                    else {
                        // the right child, the greater of the two children is 
                        // less than the parent thus the invariant holds.
                        return;
                    }
                }
            }
            else if (leftChildIndex < mQueueLength) {
                // Left child only.
                if (this.less(this.mMaxPriorityQueue[leftChildIndex], this.mMaxPriorityQueue[demotionIndex]) > 0) {
                    this.exch(leftChildIndex, demotionIndex);
                    demotionIndex = leftChildIndex;    
                }
                else {
                    // the left child, the greater of the two children is 
                    // less than the parent thus the invariant holds.
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
        MaxBinaryHeap <Integer> heap = new MaxBinaryHeap<Integer>();
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
        heap.max();
        heap.deleteMax();
        heap.deleteMax();
        heap.deleteMax();
        heap.printHeap();
        heap.insert(13);
        heap.printHeap();
        return;
    }
}