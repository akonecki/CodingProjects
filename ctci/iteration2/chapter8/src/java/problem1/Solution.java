public class Solution {
    public static int waysToGo(int N) {

    }

    // [F]unctional - Recurisve implementation to support the general 
    // implementation.

    // Start off on step 0 then go up to step N.
    // expected branches would be branch step + 1, + 2, + 3
    // Count the number of ways in which it reaches Nth step or greater.
    private static int waysToGo(int N, int step) {
        if (step > N) {
            return 1;
        }
        return waysToGo(N, step + 1) + waysToGo(N, step + 2) + waysToGo(N, step + 3);
    }

    // [A]nalysis 
    // 1 - Does the problem contain optimal substructure.
    // Yes - each step is self contained.
    // 2 - Does the problem have re-occurring calls.
    // Yes - there will be multiple times in which the later step values are
    // called multiple times.

    // Space Complexity
    // Stack space is purely impacted the most. 
    // Worse case will be the height of the call tree, which would be for the 
    // step 1 increments leading to a stack height of N in the worse case where
    // N is a positive number indicating the total number of steps to go up.

    // O(N)

    // Time Complexity
    // 3 branching factors
    // max height is N, where N is the number of steps in the problem.
    
    // O(3^N)

    private static int waysToGoSerial(int N) {

    }

    public static void main(String [] args) {

    }
}