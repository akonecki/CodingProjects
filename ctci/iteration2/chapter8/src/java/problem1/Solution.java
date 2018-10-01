import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    // [F]unctional - Recurisve implementation to support the general 
    // implementation.

    // Start off on step 0 then go up to step N.
    // expected branches would be branch step + 1, + 2, + 3
    // Count the number of ways in which it reaches Nth step or greater.
    private static int waysToGo(int N, int step) {
        if (step == N) {
            return 1;
        }
        else if (step > N) {
            return 0;
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

    //[S]ubproblem Identification & Momeization
    // N is the total number of unique paths from 0 to step N.
    private static int waysToGo(int N, int step, int [] dp) {
        int count = 0;

        if (step == N) {
            return 1;
        }
        else if (step > N) {
            return 0;
        }

        if (dp[step] != 0) {
            return dp[step];
        }

        count = waysToGo(N, step + 1) + waysToGo(N, step + 2) + waysToGo(N, step + 3);
        dp[step] = count;

        return dp[step];
    }

    // [T]urn Around the Problem
    // Use the dp to remove the reliance on the recursion of the problem.

    // [Er]adicate Memory
    // Now reduce the memory footprint even further since don't need to hold an
    // entire array of length N for the problem.
    public static int waysToGo(int N) {
        if (N < 1) {
            return 0;
        }
        else if (N == 1) {
            return 1;
        }
        else if (N == 2) {
            return 2;
        }
        else if (N == 3) {
            return 4;
        }

        int dp [] = new int [N + 1];

        // Populate the first three ways
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        Queue<Integer> values = new LinkedList<Integer>();
        int total = 1 + 2 + 4;

        values.add(1);
        values.add(2);
        values.add(4);

        for (int index = 4; index < dp.length; index++) {
            dp[index] = dp[index - 1] + dp[index - 2] + dp[index - 3];
            
            values.add(total);
            total += total - values.remove().intValue();
        }
        
        while (!values.isEmpty()) {
            total = values.remove().intValue();
        }

        assert (total == dp[N]);
        return dp[N];
    }

    public static void main(String [] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("Case " + i + " result is " + waysToGo(i));
        }
    }
}