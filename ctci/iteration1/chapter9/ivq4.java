import java.util.ArrayList;

public class ivq4 {

    // [F]unctional Implementation
    public static ArrayList<ArrayList<Integer>> getSubSets(int [] set) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        getSubSets(set, result, new ArrayList<Integer>(), 0);
        
        return result;
    }

    public static void getSubSets(int [] set, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int index) {
        if (index >= set.length) {
            result.add(list);
        }
        else {

            // exclude
            getSubSets(set, result, new ArrayList<Integer>(list), index + 1);
            // include
            list.add(set[index]);
            getSubSets(set, result, list, index + 1);
        }
    }

    // [A]nalysis
    // 1. Does the problem contain optimal substructure
    // The creation of the dynamic list within the step that does impact the 
    // lower level so it would not necessarily be ideal in the current 
    // implementation form
    // 2. Does the problem contain repeating sub-problems
    // Yes by revisiting sets it is seen that revisiting does occur even for the
    // set population just from the output.

    // Run time analysis
    // Current form there are two branches of execution.
    // 1. include
    // 2. exclude

    // Thus the branching factor is 2.

    // The depth of the tree is the height of the array.

    // The resulting worse case run time for execution is O(2^N).

    // The run time memory impact is also 2^N * N, since 2^N is the number of 
    // entries while N would be the max length.

    public static void main(String [] args) {
        int [] set = {1,2,3,4,5,6,7};

        assert (getSubSets(set).size() == 1 << (set.length));

        for (ArrayList<Integer> list : getSubSets(set)) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}