import java.util.List;
import java.util.LinkedList;

public class Solution {
    // [F]unctional

    // This is a classic permutation problem.
    // Guess will want only unique subsets
    public static List<List<Integer>> subsets(int [] set) {
        List<List<Integer>> result = new LinkedList<>();
        
        subsets(set, 0, /*set.length, */new LinkedList<Integer>(), result);
        return result;
    }

    private static void subsets(int [] set, int index, /*int lengthAvailable, */List<Integer> subset, List<List<Integer>> result) {
        if (index >= set.length) { // || lengthAvailable <= 0) {
            // nothing left to compute.
            result.add(new LinkedList<Integer>(subset));
            return;
        }

        // First exclude.
        subsets(set, index + 1, /*lengthAvailable, */subset, result);

        // Second include.
        /*
        for (int subindex = index; subindex < lengthAvailable; subindex++) {
            int valueAdded = set[subindex];
        */    
            // Add the value to the subset.
            subset.add(set[index]);
        /*    
            // swap
            set[subindex] = set[lengthAvailable - 1];
            set[lengthAvailable - 1] = valueAdded;
        */
            subsets(set, index + 1, /*lengthAvailable - 1, */subset, result);

        /*
            // unswap
            set[lengthAvailable - 1] = set[subindex];
            set[subindex] = valueAdded;
        */
            // Remove value from the subset
            subset.remove(subset.size() - 1);
        //}
    }

    // [A]nalysis
    // 1. Does the problem have optimal sub structure?
    // Yes each stage is independent.
    // 2. Does the problem have re-occurring subproblems.
    // With the current implementation no due to the subset parameter.

    // Memory Impact
    // O(2^N) for the possibilities and O(N) for the stack depth

    // Performance Impact
    // O(2^N)

    public static void main(String [] args) {
        for (List<Integer> list : subsets(new int [] {1,2,3})) {
            System.out.print("{");
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println("}");
        }
        System.out.println("");
    }
}