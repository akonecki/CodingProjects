public class Challenge11 {
    public static int minChangeCoins(int amount, int [] reverseSortedCoinSet) {
        return makeChange(amount, reverseSortedCoinSet);
    }

    // [F]unctional 

    // First looks more like a greedy algorithm, so see if can come up with a
    // nagative case that will force non-greedly.
    /*
        Break of greedy will occur when taking a larger denomenation actually 
        results in more smaller coins that say two less large coins.  So is 
        there a case that I can find that this is actually true?

        The following must hold true for all cases:
            The approach can be implemented as a greddy algorithm iff I can 
            continuously take away the largest denomentation of coin until the
            result that is left is less than the current largest denomentation.
            This is performed until all possilbe coin amounts is complete.
        
            Dollar amounts are repetitive so should be able to find a case in 
            the first 100 value if one exists.

            49 -> 25 -> 24 -> 10 -> 10 -> 1 -> 1 -> 1 -> 1

            In only say american coin sets this is true.  However this not true
            for any coin set.

            12 {1, 6, 10} => 10, 1, 1 instead of 6, 6
        
        So if was implementing a coin set that was american then greedy can be
        performed, otherwise best to implement the 
    */

    // Implementation of a recursive solution to address the minimum number of 
    // coins it takes to make a specific amount for any given coin set with the 
    // only rule being that there always exists a single coin denomination.
    private static int makeChange(int amount, int [] reverseSortedCoinSet) {
        int min = Integer.MAX_VALUE;

        // will have to iterate on each.
        for (int index = 0; index < reverseSortedCoinSet.length; index++) {
            int value = reverseSortedCoinSet[index];

            if (value == amount) {
                return 1;
            }
            else if (value < amount) {
                int result = makeChange(amount - value, reverseSortedCoinSet);

                if (result + 1 < min + 1) {
                    min = result + 1;
                }
            }
        } 

        // Dont want to perform the add of one here or else will skew from max.
        return min;
    }
    
    // [A]nalysis
    // The number of branches is equal to the length of the coinset due to 
    // the iteration that occurs for each call.  

    // Let us call the number of elements in the coin set to be M thus the 
    // branching set is M.

    // The max depth in the case in which one is provided (worse case) would be 
    // linear to the amount given on the call stack.
    // Memory efficiency O(N).

    // With the number of max branching sets along with the max depth then it is
    // O(M^N) in time complexity with a O(N) space complexity due to the stack 
    // space.

    // [1] Does this fit the requirement of optimal subproblems? 
    // yes, each step of the problem only has its own scope and can make decisions
    // based on its current value.

    // [2] Does this have reocccurring steps.
    // yes, I repeat a few values a couple of times even with the simple.

    // With the analysis it looks like this problem is a good candidate for dp.

    public static void main(String [] args) {
        assert (minChangeCoins(24, new int [] {1, 6, 10}) == 4);
    }
}