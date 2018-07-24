public class Challenge9 {

    // Does the recursive implementation have optimal substructure?
    // Yes - solving recursively into self contained subproblems.
    // Does this have overlapping subproblems?
    /*  
        fib (4) 
            fib (3)
                fib (2)
                    return 1
                fib (1)
                    return 1
            fib (2)
                return 1
    */
    // Yes - does have overlapping subproblems.  Multiple occurrences of
    //       fib(2).
    // TIME COMPLEXITY Calculation
    //
    // Height of tree is equal to (n - 1) due to return condition.
    // Has 2 recusive calls so branching factor of (2)
    // Number of calls is roughly 2^(4-1) or roughly 2^N
    // Space complexity is O(N) due to the minimum height of the tree.
    //
    // So this is a perfect candidate for this problem.
    public static int recursiveFib(int n) {
        if (n == 1) {
            return 1;
        }
        else if (n == 2) {
            return 1;
        }
        else {
            return recursiveFib(n - 1) + recursiveFib(n - 2);
        }
    }

    // Identify subproblems to supplement with memoizing.
    // the subproblems that it calls is fib(n-1) and fib(n-2)
    // [S]ubproblem Identification & translation to memoization.

    /*
        So now with memoizing the already computed values, those branches 
        are no longer explored.
    */

    // wrapper function
    public static int fib(int n) {
        int [] dp = new int[n+1];
        return fib(n, dp);
    }

    private static int fib(int n, int[] dp) {
        if (dp[n] == 0) {
            if (n==0) return 0;
            if (n==1) return 1;
            dp[n] = fib(n-1, dp) + fib(n-2, dp);
        }
        return dp[n];
    } 

    // [T]urn Around the Solution
    // Recusion is often slower due to function call stack
    // Use subproblem previous and then build up each to get desired result.

    // Time & space complexity are now O(N).
    public static int bottomUpFib(int n) {
        int [] dp = new int[n + 1];

        // Start with base cases 
        for (int index = 0; index <= n; index++) {
            if (index == 0) {
                dp[0] = 0;
            }
            else if (index == 1) {
                dp[1] = 1;
            }
            else {
                dp[index] = dp[index - 1] + dp[index - 2];
            }
        }

        return dp[n];
    }

    public static void main(String [] args) {
        assert (recursiveFib(1) == fib(1) && fib(1) == bottomUpFib(1));
        assert (recursiveFib(2) == fib(2)&& fib(2) == bottomUpFib(2));
        assert (recursiveFib(3) == fib(3)&& fib(3) == bottomUpFib(3));
        assert (recursiveFib(10) == fib(10)&& fib(10) == bottomUpFib(10));

    }
}