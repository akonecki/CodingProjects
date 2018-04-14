import java.util.List;
import java.util.ArrayList;

// Will use an underlying unordered array to perform all operations.  The array
// will be dynamic in space.
public class Range1DOrderedArray <Key extends Comparable<Key>, Value> {

    //*************************************************************************
    //* PRIVATE FACING API
    //************************************************************************* 
    private List<Node<Key, Value>> mOrderedNodes = null;

    private class Node <Key extends Comparable<Key>, Value> {
        private Key mKey = null;
        private Value mValue = null;

        public Node(Key key, Value value) {
            this.mKey = key;
            this.mValue = value;
        }
    }

    //*************************************************************************
    //* PUBLICLY FACING API
    //************************************************************************* 
    public Range1DOrderedArray() {
        this.mOrderedNodes = new ArrayList<Node<Key, Value>>();
    }

    // Although in theory the insertion only takes constant time (i.e appending)
    // to the end of the unordered array, if it is required that symbols are 
    // unique then the time cost is O(N) instead.  This implementation will 
    // support the theory representation.
    //
    // Actual handling of underlying memory is handled by ArrayList.
    public void insert(Key key, Value value) {
        Node<Key, Value> node = null;
        int index = 0;

        if (key == null) {
            throw new java.lang.IllegalArgumentException("Null key value, insertion failure.");
        }

        node = new Node<Key, Value>(key, value);

        index = this.find_last(key);

        if (index < 0 || index >= this.mOrderedNodes.size()) {
            // Append to the end, dynamically allocate memory if needed.
            this.mOrderedNodes.add(node);
        }
        else {
            this.mOrderedNodes.add(index, node);
        }

        try {
            assert(this.isSorted());
        }
        catch (java.lang.AssertionError e) {
            System.out.println("Array Dump");
            for(Key x : this.keys()) {
                System.out.print(x + " ");
            }
            System.out.println("");
            throw new java.lang.AssertionError(e);
        }
        return;
    }

    private int search_for_key(Key key, int lowIndex, int highIndex) {
        int index = 0;

        if (key == null) {
            throw new java.lang.IllegalArgumentException("Key can not be null.");   
        }

        if (this.mOrderedNodes.size() == 0) {
            return -1;
        }

        index = ((highIndex - lowIndex) / 2) + lowIndex;
        // System.out.println("Halfway index being set is " + index);

        if (this.mOrderedNodes.get(index).mKey.compareTo(key) > 0) {
            if (lowIndex <= (index - 1)) {
                // Go left
                index = this.search_for_key(key, lowIndex, index - 1);   
            }
            else {
                // Cant go left any more but the new element be to the left of 
                // this index, so return the current index.
                return index;
            }
        }
        else if (this.mOrderedNodes.get(index).mKey.compareTo(key) < 0) {
            if (highIndex >= (index + 1)) {
                // Go right
                index = this.search_for_key(key, index + 1, highIndex);
            }
            else {
                return index + 1;
            }
        }
        else {
            return index;
        }

        return index;
    }

    public int find_first(Key key) {
        int index = this.search_for_key(key, 0, this.mOrderedNodes.size() - 1);

        if (index < 0 || index >= this.mOrderedNodes.size()) {
            index = -1;
        }
        else {
            while ((index - 1 >= 0) && this.mOrderedNodes.get(index - 1).mKey.compareTo(key) == 0) {
                index--;
            }
        }

        return index;
    }

    public int find_last(Key key) {
        int index = this.search_for_key(key, 0, this.mOrderedNodes.size() - 1);

        if (index < 0 || index >= this.mOrderedNodes.size()) {
            index = -1;
        }
        else {
            while ((index + 1 < this.mOrderedNodes.size()) && this.mOrderedNodes.get(index + 1).mKey.compareTo(key) == 0) {
                index++;
            }
        }

        return index;
    }

    public boolean contains(Key key) {
        int index = 0;

        if (key == null) {
            throw new java.lang.IllegalArgumentException("Null key detected.");
        }

        if (this.mOrderedNodes.size() < 1) {
            return false;
        }

        index = this.search_for_key(key, 0, this.mOrderedNodes.size() - 1);

        if (index >= this.mOrderedNodes.size() || index < 0) {
            return false;
        }
        else if (this.mOrderedNodes.get(index).mKey.compareTo(key) != 0) {
            return false;
        }

        return true;
    }

    // Inclusive range count on the keys.
    public int range_count(Key keyLow, Key keyHigh) {
        int lowCount = 0, highCount = 0;
        
        if (keyLow.compareTo(keyHigh) > 0) {
            throw new java.lang.IllegalArgumentException("The low key must be lower in comparison to the high key.");
        }

        lowCount = this.find_first(keyLow);
        highCount = this.find_last(keyHigh);

        if (this.contains(keyHigh)) {
            return highCount - lowCount + 1;
        }
        else {
            return highCount - lowCount;
        }
    }

    // Inclusive range search on the keys.
    public List<Key> range_search(Key keyLow, Key keyHigh) {
        List<Key> values = new ArrayList<Key>();
        int lowCount = 0, highCount = 0;
        
        if (keyLow.compareTo(keyHigh) > 0) {
            throw new java.lang.IllegalArgumentException("The low key must be lower in comparison to the high key.");
        }

        lowCount = this.find_first(keyLow);
        highCount = this.find_last(keyHigh);

        if (this.contains(keyHigh)) {
             highCount++;
        }

        for (; lowCount < highCount; lowCount++) {
            values.add(this.mOrderedNodes.get(lowCount).mKey);
        }

        return values;
    }

    public List<Key> keys() {
        List<Key> values = new ArrayList<Key>();

        for (int index = 0; index < this.mOrderedNodes.size(); index++) {
            values.add(this.mOrderedNodes.get(index).mKey);
        }        

        return values;
    }

    public boolean isSorted() {
        int index = 1;

        if (this.mOrderedNodes.size() < 2) {
            return true;
        }

        for (index = 1; index < this.mOrderedNodes.size(); index++) {
            if (this.mOrderedNodes.get(index - 1).mKey.compareTo(this.mOrderedNodes.get(index).mKey) > 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Range1DOrderedArray<Integer, Integer> test = new Range1DOrderedArray<Integer, Integer>();
        test.insert(1, 1);
        System.out.println(Integer.toString(test.find_first(1)));
        test.insert(2, 2);
        System.out.println(Integer.toString(test.find_first(2)));
        test.insert(1, 1);
        System.out.println(Integer.toString(test.find_first(1)));
        System.out.println(Integer.toString(test.find_last(1)));

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

        
        System.out.println(test.range_count(-1,1));
        for(Integer integer : test.range_search(-1,1)) {
            System.out.print(integer);
            System.out.print(" ");
        }

        System.out.println("");

        for(Integer integer : test.find_all(1)) {
            System.out.print(integer);
            System.out.print(" ");
        }
        
        System.out.println("");
        
    }
}