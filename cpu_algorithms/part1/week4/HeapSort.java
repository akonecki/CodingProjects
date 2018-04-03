public class HeapSort <Key extends Comparable<Key>> {
    public void sort(Key [] keys) {
        if (keys == null) {
            return;
        }

        // First need to get the array into a max priority queue, specifically
        // a binary heap.
        for (int index = keys.length / 2; index >= 0; index--) {
            this.demote(keys, index, keys.length);
        }

        for (int index = 0, range = keys.length - 1; range > 0; range--) {
            // System.out.println(Integer.toString(index) + " " + Integer.toString(range));
            this.exch(keys, index, range);
            this.demote(keys, index, range - 1);
        }
    }

    private void exch(Key [] key, int aIndex, int bIndex) {
        Key temp = key[aIndex];

        if (bIndex < key.length) {
            key[aIndex] = key[bIndex];
            key[bIndex] = temp;
        }
    }

    private int less(Key a, Key b) {
        // a > b  :: 1
        // a == b :: 0
        // a < b  :: -1
        return a.compareTo(b);
    }

    // Sink
    private void demote(Key [] key, int demotionIndex, int length) {
        while (demotionIndex < length) {
            int leftChildIndex = demotionIndex * 2 + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (rightChildIndex < length) {
                // Determine the largest child.
                if (this.less(key[leftChildIndex], key[rightChildIndex]) > 0) {
                    // Left child is bigger than right.
                    if (this.less(key[leftChildIndex], key[demotionIndex]) > 0) {
                        this.exch(key, leftChildIndex, demotionIndex);
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
                    if (this.less(key[rightChildIndex], key[demotionIndex]) > 0) {
                        this.exch(key, rightChildIndex, demotionIndex);
                        demotionIndex = rightChildIndex;    
                    }
                    else {
                        // the right child, the greater of the two children is 
                        // less than the parent thus the invariant holds.
                        return;
                    }
                }
            }
            else if (leftChildIndex < length) {
                // Left child only.
                if (this.less(key[leftChildIndex], key[demotionIndex]) > 0) {
                    this.exch(key, leftChildIndex, demotionIndex);
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
        Integer [] data = {18,384, 3746, 12, 34, 5, 1, -1, 3948, 74, 29};
        HeapSort performSort = new HeapSort();

        performSort.sort(data);

        for (Integer integer : data) {
            System.out.print(Integer.toString(integer) + " ");
        }

        System.out.println("");
        return;
    }
}