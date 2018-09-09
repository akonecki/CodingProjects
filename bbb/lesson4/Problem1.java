import java.util.*;

public class Problem1 {

    public static int maxProduct(int [][] values) {
        List<List<Integer>> pathsToMax = new ArrayList<List<Integer>>();
        int result = maxProduct(values, 0, 0, 1, 0, pathsToMax, new ArrayList<Integer>(values.length + values[0].length));
        
        for (List<Integer> path : pathsToMax) {
            for (int num : path) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }

        Integer [][] dp = new Integer [values.length][values[0].length];
        assert (result == maxProduct(values, dp, 0, 0));

        return result;
    }

    // Functional 
    // Recursive functional implementation.
    private static int maxProduct(int [][] values, int row, int col, int product, int max, List<List<Integer>> pathsToMax, List<Integer> path) {
        System.out.println(row + " " + col);
        
        if (row >= values.length || col >= values[row].length) {
            return max;
        }
        else if (row == values.length - 1 && col == values[row].length - 1) {
            product *= values[row][col];
            
            // at n-1, m-1
            if (product > max || pathsToMax.isEmpty()) {
                path.add(values[row][col]);
                pathsToMax.clear();
                pathsToMax.add(new ArrayList<Integer>(path));
                path.remove(path.size() - 1);
                return product;
            }
            else if (product == max) {
                path.add(values[row][col]);
                pathsToMax.add(new ArrayList<Integer>(path));
                path.remove(path.size() - 1);
                return max;
            }
            else {
                return max;
            }
        }
        else if (row >= values.length || col >= values[row].length) {
            return max;
        }
        int foundMax = max;

        // go right first
        path.add(values[row][col]);
        foundMax = maxProduct(values, row, col + 1, product * values[row][col], foundMax, pathsToMax, path);
    
        // go down second
        foundMax = maxProduct(values, row + 1, col, product * values[row][col], foundMax, pathsToMax, path);
        path.remove(path.size() - 1);

        return foundMax;
    }

    // [A]nalysis
    // 1. Does the problem have optimal sub-structure?
    // For finding the maximum product it does.

    // 2. Does the problem have re-occurring subproblems?
    // yes there are multiple places where the same row/col is used.

    // Branching Factor
    // There are two supported branches per level at most, one for increase in row
    // one for increase in col.

    // Max depth
    // Can go through all the columns first then the rows thus expecting a max 
    // depth of N + M where N is the number of rows and M is the number of columns.
    
    // Run time 
    // O(2^(M+N) + ((M + N - 2)! / ((M-1)!)) / ((N - 1)!) * (M*N)) for just the max product
    // With the copy of M + N for each valid path that is more
    // assume that each path leads to a more then the max total number of paths
    // ((M + N - 2)! / ((M-1)!)) / ((N - 1)!) == 5! / (3! * 2!) = 10 
    
    // Space complexity
    // Worse case
    // Each path is equal to the max
    // ((M + N - 2)! / ((M-1)!)) / ((N - 1)!) * (M*N) + (M+N)

    // [S]ubproblem Identification & Momeization
    // Ignore the population of the path for this only concerned about the max
    // product computation.
    private static int maxProduct(int [][] values, Integer [][] dp, int row, int col) {
        if (row == values.length - 1 && col == values[row].length - 1) {
            dp[row][col] = values[row][col];
            return values[row][col];
        }
        else if (dp[row][col] != null) {
            return dp[row][col].intValue();
        }
        else {
            int max = values[row][col];

            if (row + 1 < values.length && col + 1 < values[row].length) {
                max *= Math.max(maxProduct(values, dp, row + 1, col), maxProduct(values, dp, row, col + 1));
            }
            else if (row + 1 < values.length) {
                max *= maxProduct(values, dp, row + 1, col);
            }
            else if (col + 1 < values[row].length) {
                max *= maxProduct(values, dp, row, col + 1);
            }

            dp[row][col] = max;
            return max;
        }
    }

    public static void main (String [] args) {
        int [][] matrix = new int [][] {
            {1,2,3,4},
            {4,5,6,7},
            {-7,8,0,1}
        };
        System.out.println(maxProduct(matrix));
        //assert (maxProduct(matrix) == 1440);
    }
}