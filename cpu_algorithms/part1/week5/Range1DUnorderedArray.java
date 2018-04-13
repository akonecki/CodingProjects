import java.util.List;
import java.util.ArrayList;

// Will use an underlying unordered array to perform all operations.  The array
// will be dynamic in space.
public class Range1DUnorderedArray <Key extends Comparable<Key>, Value> {

    //*************************************************************************
    //* PRIVATE FACING API
    //************************************************************************* 
    private List<Node<Key, Value>> mUnorderedNodes = null;

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
    public Range1DUnorderedArray() {
        this.mUnorderedNodes = new ArrayList<Node<Key, Value>>();
    }

    // Although in theory the insertion only takes constant time (i.e appending)
    // to the end of the unordered array, if it is required that symbols are 
    // unique then the time cost is O(N) instead.  This implementation will 
    // support the theory representation.
    //
    // Actual handling of underlying memory is handled by ArrayList.
    public void insert(Key key, Value value) {
        Node<Key, Value> node = null;
        
        if (key == null) {
            throw new java.lang.IllegalArgumentException("Null key value, insertion failure.");
        }

        node = new Node<Key, Value>(key, value);

        this.mUnorderedNodes.add(node);
         
        return;
    }

    // Inclusive range count on the keys.
    public int range_count(Key keyLow, Key keyHigh) {
        int count = 0;
        
        for (Node<Key, Value> node : this.mUnorderedNodes) {
            if (node.mKey.compareTo(keyLow) >= 0 && node.mKey.compareTo(keyHigh) <= 0) {
                count++;
            }
        }

        return count;
    }

    // Inclusive range search on the keys.
    public List<Key> range_search(Key keyLow, Key keyHigh) {
        List<Key> values = new ArrayList<Key>();

        for (Node<Key, Value> node : this.mUnorderedNodes) {
            if (node.mKey.compareTo(keyLow) >= 0 && node.mKey.compareTo(keyHigh) <= 0) {
                values.add(node.mKey);
            }
        }        

        return values;
    }

    // Normal linear search w.r.t values.
    public List<Value> find_all(Key key) {
        List<Value> values = new ArrayList<Value>();

        for (Node<Key, Value> node : this.mUnorderedNodes) {
            if (node.mKey.compareTo(key) == 0) {
                values.add(node.mValue);
            }
        }

        return values;
    }

    public static void main(String[] args) {
        Range1DUnorderedArray<Integer, Integer> test = new Range1DUnorderedArray<Integer, Integer>();
        test.insert(1, 1);
        test.insert(2, 1);
        test.insert(3, 1);
        test.insert(4, 1);
        test.insert(5, 1);
        test.insert(6, 1);
        test.insert(1, 1);
        test.insert(9, 1);

        System.out.println(test.range_count(4, 9));
        for(Integer integer : test.range_search(4, 9)) {
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