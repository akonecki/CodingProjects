public class Challenge13 {
    // [F]uncional Recursive Implementation
    // Can this be addressed using greedy
    /*
        If it can not then there must be a counter example.
        Greedy implementation would likely sort based off weight first
        then value.  So low weight then high value or a ratio.

        Input: 
            weightValues : {1,6},{2,10},{4,17}
            maxWeight : 4
        Output:
            16 
        
        Explaination lets assume that the sorting occurs based on ratio, then
        the input of the weightValues is already ordered.  This means that 
        total value at a weight of 3 is equal to 16 but would be less than 
        a value of 17 at a weight of 17
    */
    public static int knapsack(int [][] weightValues, int maxWeight) {
        return knapsack(weightValues, maxWeight, 0);
    }

    private static int knapsack(int [][] weightValues, int maxWeight, int index) {
        if (index >= weightValues.length) {
            return 0;
        }
        System.out.println(index);
        int [] weightValue = weightValues[index];

        int includedValue = knapsack(weightValues, 
            maxWeight >= weightValue[0] ? maxWeight - weightValue[0] : maxWeight, 
            index + 1);

        if (maxWeight >= weightValue[0]) {
            includedValue += weightValue[1];
        }

        int notIncludedValue =  knapsack(weightValues, 
            maxWeight, 
            index + 1);

        if (includedValue > notIncludedValue) {
            return includedValue;
        }
        else {
            return notIncludedValue;
        }
    }
    
    // [A]nalysis
    // [1] Does the problem contain optimal substructure?
    // Yes - each level of the recursion is self contained.
    // [2] Does the problem have re-occurring states?
    // Yes - there are multiple states in dealing with the the same item.

    // Run Time Performance
    // [1] Maximum height of the tree is equal to the total length of the list
    // of items N.
    // [2] The branching factor is (2) one for the include state and another for
    // the not included item state.

    // With this, the result is O(2^N).

    // Memory Performance
    // The memory performance impact is strictly hit by the depth of the 
    // recursion tree so the max height of the tree is N thus O(N) for memory
    // impact.

    public static void main(String [] args) {
        assert (knapsack(new int [][] {{2,6},{2,10},{3,12}}, 5) == 22);
    }
}