public class ivq2 {

    // [F]unctional Implementation
    // Will first approach the case without any blocking cells within the matrix
    public static int totalPaths(int [][] matrix) {
        return totalPaths(matrix, 0, 0);
    }

    private static int totalPaths(int [][] matrix, int row, int col) {
        if (row + 1 == matrix.length && col + 1 == matrix[row].length) {
            return 1;
        }
        else if (row >= matrix.length || col >= matrix[row].length) {
            return 0;
        }
        else if (matrix[row][col] == 1) {
            return 0;
        }

        return totalPaths(matrix, row + 1, col) + totalPaths(matrix, row, col + 1);
    }

    // [A]nalysis
    // 1. Does the problem contain optimal substructure?
    // yes - each iteration of the recursive method is self contained and does 
    // not leverage any state information of the implemented instance.
    // 2. Does the problem contain re-occurring subproblems?
    // yes - as present from the investigation there are multiple calls to the
    // function with the same row and col arguments even in the simple 3 by 3 
    // case.

    /*
        Reoccurring Subproblem Investigation

        [
            [0, 0, 0],
            [0, 0, 0],
            [0, 0, 0],
        ]

        (0, 0)
            -> (1, 0)
                -> (2, 0)
                    -> (3, 0)
                        -> (4, 0)
                        <- Returns 0
                        -> (3, 1)
                            -> (4, 1)
                            <- Returns 0
                            -> (3, 2)
                                -> (4, 2)
                                <- Returns 0
                                -> (3, 3)
                                <- Returns 1
                            <- Returns (0 + 1)
                        <- Returns (0 + 1)
                    <- Returns (0 + 1)
                    -> (2, 1)
                        -> (3, 1)
                            -> (4, 1)
                            <- Returns 0
                            -> (3, 2)
                                -> (4, 2)
                                <- Returns 0
                                -> (3, 3)
                                <- Returns 1
                            <- Returns (0 + 1)
                        <- Returns (0 + 1)
    */

    // Run Time Analysis
    // There are two branching conditions that are supported, one for going
    // right and the other going down.  Therefore the branching factor is
    // 2.
    // The total height of the tree is M+N+1 where M is the total number of
    // rows and N is the total number of columns contained within the matrix.

    // The total expected worse case execution run time is therefore
    // O(2^(N + M + 1))

    // Memory Analysis
    // Is based on the height of the tree to account for the stack, and is
    // therefore O(M+N+1).

    // [S]ubproblem Identification & Momeization
    public static int totalPathsDP(int [][] matrix) {
        int [][] dp = new int [matrix.length][matrix[matrix.length - 1].length];

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                dp[row][col] = -1;
            }
        }

        return totalPaths(matrix, 0, 0, dp);
    }

    private static int totalPaths(int [][] matrix, 
        int row, 
        int col, 
        int [][] dp) 
    {
        if (row + 1 == matrix.length && col + 1 == matrix[row].length) {
            return 1;
        }
        else if (row >= matrix.length || col >= matrix[row].length) {
            return 0;
        }
        else if (matrix[row][col] == 1) {
            dp[row][col] = 0;
            return 0;
        }
        else if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int sum = totalPaths(matrix, row + 1, col, dp) + 
            totalPaths(matrix, row, col + 1, dp);
        dp[row][col] = sum;

        return sum;
    }

    // [T]urn the Problem Around
    public static int totalPathsDPSerial(int [][] matrix) {
        int [][] dp = new int [matrix.length + 1][matrix[matrix.length - 1].length + 1];

        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[row].length; col++) {
                if (matrix[row - 1][col - 1] == 0) {
                    if (row == 1 && col == 1) {
                        dp[row][col] = 1;
                    }
                    else {
                        dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                    }
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

        return dp[matrix.length][matrix[matrix.length - 1].length];
    }

    public static void main(String [] args) {
        int [][] matrix = new int [][] {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 0, 0, 0}};

        assert (totalPathsDPSerial(matrix) == totalPathsDP(matrix));
    }
}