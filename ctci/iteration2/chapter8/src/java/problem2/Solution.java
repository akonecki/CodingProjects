import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class Solution {
    // [F]unctional
    // Although the problem only indicates needing to find a single path.  Will
    // break this out into multiple parts in order to practice DP in addition
    // to recursion.  

    // will Assume that the matrix cell values have the range of 0 and 1.
    // a value of 0 indicates that the cell is open.
    // a value of 1 indicates that the cell is occupied.
    private static int totalPaths(int [][] matrix, int row, int col) {
        if (row == matrix.length - 1 && col == matrix[row].length - 1 && matrix[row][col] != 1) {
            return 1;
        }
        else if (row >= matrix.length || col >= matrix[row].length || matrix[row][col] == 1) {
            return 0;
        }

        return totalPaths(matrix, row + 1, col) + totalPaths(matrix, row, col + 1);
    }

    private static void validPaths(int [][] matrix, int row, int col, List<List<Integer>> path, List<List<List<Integer>>> paths) {
        if (row == matrix.length - 1 && col == matrix[row].length - 1 && matrix[row][col] != 1) {
            paths.add(new LinkedList<List<Integer>>(path));
            return;
        }
        else if (row >= matrix.length || col >= matrix[row].length || matrix[row][col] == 1) {
            return;
        }
        
        List<Integer> newPoint = new LinkedList<Integer>();
        newPoint.add(row);
        newPoint.add(col);
        path.add(newPoint);

        // go right
        validPaths(matrix, row, col + 1, path, paths);

        // go down
        validPaths(matrix, row + 1, col, path, paths);

        // Remove the last element form the list.
        path.remove(path.size() - 1);
    }

    private static boolean validPath(int [][] matrix, int row, int col, List<List<Integer>> path) {
        if (row == matrix.length - 1 && col == matrix[row].length - 1 && matrix[row][col] != 1) {
            return true;
        }
        else if (row >= matrix.length || col >= matrix[row].length || matrix[row][col] == 1) {
            return false;
        } 

        List<Integer> newPoint = new LinkedList<Integer>();
        newPoint.add(row);
        newPoint.add(col);
        path.add(newPoint);

        if (validPath(matrix, row, col + 1, path)) {
            return true;
        }

        if (validPath(matrix, row + 1, col, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    // [A]nalysis
    // 1. Does the problem contain optimal sub-structure?
    // All contains optimal sub-structure
    // 2. Does the problem have re-occurring calls?
    // Only the total number of paths contains re-occurring calls.

    // Memory
    // Total # of Valid Paths
    // Subjected to the maximum height of the call tree.  Worse case would be 
    // all right then all down resulting in a O(M + N).

    // Valid Path
    // O(M + N) + O(M + N) so O(M + N).

    // Valid Paths
    // O(M + N) due to logic above, but not including the path saving.
    // So assume a grid with no obstructions, then need to account for total 
    // branching factor as well. O(2^(M+N)).

    // Performance
    // Total # of Paths
    // O(2^(M+N))

    // Valid Path
    // O(2^(M+N))

    // Valid Paths
    // O(2^(M+N)).

    // [S]ubproblem Identification & Momeization
    private static int totalPaths(int [][] matrix, int row, int col, int [][] dp) {
        if (row == matrix.length - 1 && col == matrix[row].length - 1 && matrix[row][col] != 1) {
            return 1;
        }
        else if (row >= matrix.length || col >= matrix[row].length || matrix[row][col] == 1) {
            return 0;
        }

        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        dp[row][col] = totalPaths(matrix, row + 1, col) + totalPaths(matrix, row, col + 1);
        return dp[row][col];
    }

    // [T]urn the Problem Around
    public static int totalPaths(int [][] matrix) {
        int [][] dp = new int [matrix.length + 1][matrix[0].length + 1];

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                if (row != 0 && col != 0) {
                    if (row == 1 && col == 1) {
                        dp[row][col] = 1;
                    }
                    else if (matrix[row - 1][col - 1] == 0) {
                        dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                    }
                }
            }
        }

        int result = totalPaths(matrix, 0, 0, new int [matrix.length][matrix[matrix.length - 1].length]);
        assert (totalPaths(matrix, 0, 0) == result);
        
        System.out.println(result + " " + dp[matrix.length][matrix[matrix.length - 1].length]);
        
        assert (result == dp[matrix.length][matrix[matrix.length - 1].length]);

        return result;
    }

    public static void main(String [] args) {
        final int caseCount = 100;
        Random random = new Random();

        for (int caseIndex = 0; caseIndex < caseCount; caseIndex++) {
            int [][] matrix = new int [random.nextInt(24) + 1][random.nextInt(24) + 1];
            System.out.println("Case Number " + caseIndex);

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    if (row == 0 && col == 0) {
                        matrix[row][col] = 0;
                    }
                    else {
                        if (random.nextInt(100) >= 85) {
                            matrix[row][col] = 1;
                        }
                        else {
                            matrix[row][col] = 0;
                        }
                    }
                    System.out.print(matrix[row][col] + " ");
                }
                System.out.println("");
            }

            System.out.println("");
            totalPaths(matrix);
        }
    }
}