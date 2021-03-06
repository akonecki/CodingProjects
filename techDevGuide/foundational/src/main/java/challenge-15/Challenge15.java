public class Challenge15 {

    // [F]unctional implementation
    public static int operationCount(String s1, String s2) {
        if (s1.length() > s2.length()) {
            // Not supporting the delete operation only the insertion and 
            // modification operation
            return 0;
        }

        return operationCount(s1, s2, 0, 0, s1.length());
    }

    private static int operationCount(String s1, String s2, int s1Index, int s2Index, int s1ModLength) {
        if (s1Index >= s1.length() && s2Index >= s2.length()) {
            return 0;
        }

        if (s1Index >= s1.length()) {
            // ran out of characters to process, just insert until s2Index is caught up.
            return operationCount(s1, s2, s1Index, s2Index + 1, s1ModLength + 1) + 1;
        }

        // There are two operations possible for each character in a string.
        // [1] A new character can be added to the string.
        //     A character can only be added if s1 length is less than the 
        //     length of s2.
        // [2] A character can be modified to another character.
        if (s1.charAt(s1Index) != s2.charAt(s2Index)) {
            int insertCost = Integer.MAX_VALUE;
            int modifyCost = Integer.MAX_VALUE;

            if (s1ModLength < s2.length()) {
                insertCost = operationCount(s1, s2, s1Index, s2Index + 1, s1ModLength + 1) + 1;
            }

            modifyCost = operationCount(s1, s2, s1Index + 1, s2Index + 1, s1ModLength) + 1;

            if (insertCost < modifyCost) {
                return insertCost;
            }
            else {
                return modifyCost;
            }
        }
        else {
            return operationCount(s1, s2, s1Index + 1, s2Index + 1, s1ModLength);
        }
    }

    // [A]nalysis
    // [1] Does the problem have optimal substructure?
    // Yes the problem has optimal substructure due to the fact that it is self
    // contained at each iteration / call of the recursive method.
    // [2] Does the problem have re-occurring subproblems?

    /*
        Call Graph
        Input: ABCD, ACBD
        
        (s1,s2, 0, 0, 4)
        // only has a single call graph tree since the lengths are already the 
        // same, this forces all possible traversals to be only the 
        // modification operation.

        (s1,s2, 0, 0, 4)
            (Match) -> (s1,s2, 1, 1, 4)
                            (Modification) -> (s1,s2, 2, 2, 4)
                                                    (Modification) -> (s1,s2, 3, 3, 4)
                                                                            (Match) -> (s1,s2, 4, 4, 4)
                                                                            <- 0
                                                    <- (1 + 0)
                            <- (1 + 1)
            <- 2
        <- 2


        Input: AD, ACBD

        (s1, s2, 0, 0, 2)
            (Match) -> (s1, s2, 1, 1, 2)
                            (Insert) -> (s1, s2, 1, 2, 3)
                                            (Insert) -> (s1, s2, 1, 3, 4)
                                                            (Match) -> (s1, s2, 2, 4, 4)
                                                                            (Base Case)
                                                                            <- 0
                                                            <- 0
                                            <- (1 + 0)
                                            (Modification) -> (s1, s2, 2, 3, 3)
                                                                    (Insert) -> (s1, s2, 2, 4, 4)
                                                                                    (Base Case)
                                                                                    <- 0
                                                                    <- (1 + 0)
                                            <- (1 + 1)
                                            Actual Returned
                                            <- (1 + 0)
                            <- (1 + 1)
                            (Modification) -> (s1, s2, 2, 2, 2)
                                                    (Insert) -> (s1, s2, 2, 3, 3)
                                                                    (Insert) -> (s1, s2, 2, 4, 4)
                                                                                    (Base Case)
                                                                                    <- 0
                                                                    <- (1 + 0)
                                                                    // Cant do Modification
                                                    <- (1 + 1)
                                                    // Cant do Modification
                            <- (1 + 2)
                            Actual Returned
                            <- (1 + 1)
            <- (2)
        <- (20)
    */

    // Repeating cases are present so [2] for repeating subproblems so YES.

    // Run Time & Memory Analysis
    // The number of possilbe branching factors is [2] 
    //  one for the insertion case 
    //  one for the modification case

    // The maximum height of the tree is the length of the second string, the 
    // longer of the two (N).

    // therefore the total run-time performance that can be expected in the 
    // worse case scenario is O(2^N) exponential factor.

    // Memory consumption is bounded in this problem by the stack used for the
    // recursion calls, thus is tied to the maximum height of the tree.

    // O(N)

    // [S]ubproblem Identification & Memoization
    // The subproblem with the use of caching allows for stating 
    // "what is the minimum value that can be index i to N.
    public static int operationCountDP(String s1, String s2) {
        if (s1.length() > s2.length()) {
            // Not supporting the delete operation only the insertion and 
            // modification operation
            return 0;
        }

        int [][] dp = new int [s1.length() + 1][s2.length() + 1];

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                dp[row][col] = -1;
            }
        }

        return operationCount(s1, s2, 0, 0, s1.length(), dp);
    }

    private static int operationCount(String s1, String s2, int s1Index, int s2Index, int s1ModLength, int [][] dp) {
        if (s1Index >= s1.length() && s2Index >= s2.length()) {
            return 0;
        }

        if (dp[s1Index][s2Index] != -1) {
            return dp[s1Index][s2Index];
        }

        int amount = 0;

        if (s1Index >= s1.length()) {
            // ran out of characters to process, just insert until s2Index is caught up.
            amount = operationCount(s1, s2, s1Index, s2Index + 1, s1ModLength + 1) + 1;
        }
        // There are two operations possible for each character in a string.
        // [1] A new character can be added to the string.
        //     A character can only be added if s1 length is less than the 
        //     length of s2.
        // [2] A character can be modified to another character.
        else if (s1.charAt(s1Index) != s2.charAt(s2Index)) {
            int insertCost = Integer.MAX_VALUE;
            int modifyCost = Integer.MAX_VALUE;

            if (s1ModLength < s2.length()) {
                insertCost = operationCount(s1, s2, s1Index, s2Index + 1, s1ModLength + 1) + 1;
            }

            modifyCost = operationCount(s1, s2, s1Index + 1, s2Index + 1, s1ModLength) + 1;

            if (insertCost < modifyCost) {
                amount = insertCost;
            }
            else {
                amount = modifyCost;
            }
        }
        else {
            amount = operationCount(s1, s2, s1Index + 1, s2Index + 1, s1ModLength);
        }

        dp[s1Index][s2Index] = amount;
        return amount;
    }

    // [T]urn the Problem Around
    public static int operationCountDPSerial(String s1, String s2) {
        if (s1.length() > s2.length()) {
            // Not supporting the delete operation only the insertion and 
            // modification operation
            return 0;
        }

        int [][] dp = new int [s1.length() + 1][s2.length() + 1];

        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[row].length; col++) {
                // diag means match
                // left will mean insertion
                // top will mean modification
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    if (row - 1 > 0) {
                        dp[row][col] = dp[row - 1][col - 1];
                    }
                    else {
                        dp[row][col] = dp[row][col - 1];
                    }
                }
                else if (row - 1 > 0) {
                    dp[row][col] = Math.min(dp[row - 1][col - 1], dp[row][col - 1]) + 1;
                }
                else {
                    dp[row][col] = dp[row][col - 1] + 1;
                }
            }
        }

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                System.out.print(dp[row][col] + " ");
            }
            System.out.println("");
        }

        System.out.println("");

        return dp[s1.length()][s2.length()];
    }

    public static void main(String [] args) {
        assert (operationCount("ABCD", "ACBD") == 2);
        assert (operationCount("D", "ACBD") == 3);
        assert (operationCountDP("ABCD", "ACBD") == 2);
        assert (operationCountDP("D", "ACBD") == 3);
        assert (operationCountDPSerial("ABCD", "ACBD") == 2);
        assert (operationCountDPSerial("D", "ACBD") == 3);
    }
}