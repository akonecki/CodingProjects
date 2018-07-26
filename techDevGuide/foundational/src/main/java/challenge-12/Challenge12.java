public class Challenge12 {
    // [F]unctional Implementation
    // Initial Public API Method
    public static int maxSubSquareMatrix(boolean [][] matrix) {
        return maxSubSquareMatrix(matrix, 0, 0).max;
    }

    public static Result maxSubSquareMatrix(boolean [][] matrix, int row, int col) {
        if (row >= matrix.length || col >= matrix[row].length) {
            return new Result(0,0);
        }
        else {
            Result down = maxSubSquareMatrix(matrix, row + 1, col);
            Result diag = maxSubSquareMatrix(matrix, row + 1, col + 1);
            Result right = maxSubSquareMatrix(matrix, row, col + 1);

            int max = Math.max(down.max, Math.max(diag.max, right.max));
            int value = 0;
            
            if (matrix[row][col]) {
                value = Math.min(down.value, Math.min(diag.value, right.value)) + 1;
            }

            if (value > max) {
                max = value;
            }

            return new Result(value, max);
        }
    }

    private static class Result {
        private int max;
        private int value;

        public Result(int value, int max) {
            this.value = value;
            this.max = max;
        }
    }

    // [A]nalyze for optimal subproblems and subproblem re-use
    // [1] Is the problem follow the optimal subproblem definition.
    // Yes - each level is reliant on only its current level within the traversal
    //       and does not work on global or instance values.
    // [2] Do the subproblems exhibit reuse of values.
    // Yes - there will be multiple calls to the same row, col pair multiple
    //       times due to the 3-branch traversal.

    // Can the performance be improved with DP?
    // So the current run-time performance is as follows.
    /*
        [1] The max height for the problem shall be defined as N.  Max height
            is limited by the largest dimension of the matrix, for now lets 
            assume that it is a large matrix such that L === W thus N = L;
        [2] The branching factor is 3 for each logical branch of going 
            i) down
            ii) diag
            iii) right
    */
    // So the expected runtime should be in the order of O(N^3)

    // Memory impact is most impacted by the recusive calls due to state 
    // information being saved and thus is equal to the max height O(N) in
    // this case.

    // [S]ubproblem Inspection for Mometization
    // The difficult part is having to maintain two pieces of information. The
    // current subsquare size w.r.t the current position, and the max that has
    // been seen w.r.t the position.  This is required at still due to the top
    // down approach.
    
    // The saving of the values allows to trim the tree to by O(N^2) for 
    // run time performance assumming L === W for the matrix dimensions.
    public static int maxSubSquareMatrixDB(boolean [][] matrix) {
        int [][] dpValue = new int [matrix.length + 1][matrix[0].length + 1];
        int [][] dpMax = new int [matrix.length + 1][matrix[0].length + 1];
        maxSubSquareMatrix(matrix, 0, 0, dpValue, dpMax);

        return dpMax[0][0];
    }

    public static void maxSubSquareMatrix(boolean [][] matrix, int row, int col, int [][] dpValue, int [][] dpMax) {
        if (row >= matrix.length || col >= matrix[row].length) {
            return;
        }
        
        if (dpValue[row][col] != 0) {
            return;
        }

        maxSubSquareMatrix(matrix, row + 1, col, dpValue, dpMax);
        maxSubSquareMatrix(matrix, row, col + 1, dpValue, dpMax);
        maxSubSquareMatrix(matrix, row + 1, col + 1, dpValue, dpMax);

        int value = Integer.MIN_VALUE;

        if (matrix[row][col]) {
            value = Math.min(dpValue[row + 1][col], Math.min(dpValue[row][col + 1], dpValue[row + 1][col + 1]));
            if (value == Integer.MIN_VALUE) {
                value = 1;
            }
            else {
                value += 1;
            }
        }

        dpValue[row][col] = value;
        int max = Math.max(value, Math.max(dpMax[row + 1][col], Math.max(dpMax[row + 1][col + 1], dpMax[row][col + 1])));
        dpMax[row][col] = max;
    }

    // [T]urn Around the Solution
    // Previously was going top down or from the upper left corner down to the
    // bottom right corner and then back up to determine the max size and value
    // impact for each cell.

    // now should be able to turn it around 
    // each cell in the value indicates largest consecutive size up to this point
    // (i.e. is the bottom right most cell).
    // each cell in the max indicates largest square seen up to this cell growing
    // down to the bottom.

    public static int maxSubSquareMatrixSerialDB(boolean [][] matrix) {
        // Memory impact can be reduced to only the size of the row since 
        // processing the matrix on a row by row basis.
        int [][] dpValue = new int [matrix.length + 1][matrix[0].length + 1];
        int [][] dpMax = new int [matrix.length + 1][matrix[0].length + 1];
        
        for (int row = 1; row < dpValue.length; row++) {
            for (int col = 1; col < dpValue[row].length; col++) {
                int value = 0;
                if (matrix[row - 1][col - 1]) {
                    value = Math.min(dpValue[row - 1][col - 1], Math.min(dpValue[row - 1][col], dpValue[row][col - 1])) + 1;
                }
                
                dpValue[row][col] = value;
                dpMax[row][col] = Math.max(value, Math.max(dpMax[row - 1][col - 1], Math.max(dpMax[row - 1][col], dpMax[row][col - 1])));
            }
        }

        /*
        for (int row = 0; row < dpValue.length; row++) {
            for (int col = 0; col < dpValue[row].length; col++) {
                System.out.print(dpValue[row][col]);
            }
            System.out.println("");
        }
        System.out.println("");

        System.out.println(dpMax[matrix.length][matrix[0].length]);
        */
        return dpMax[matrix.length][matrix[0].length];
    }

    public static void main(String [] args) {
        assert (maxSubSquareMatrix(new boolean [][] {{false, true, false},{true, true, true},{false, true, true}}) == maxSubSquareMatrixDB(new boolean [][] {{false, true, false},{true, true, true},{false, true, true}}));
        assert (maxSubSquareMatrix(new boolean [][] {{true}}) == maxSubSquareMatrixDB(new boolean [][] {{true}}));
        assert (maxSubSquareMatrix(new boolean [][] {{false}}) == maxSubSquareMatrixDB(new boolean [][] {{false}}));
        assert (maxSubSquareMatrix(new boolean [][] {{true, true, true},{true, false, true},{true, true, true}}) == maxSubSquareMatrixDB(new boolean [][] {{true, true, true},{true, false, true},{true, true, true}}));
        assert (maxSubSquareMatrix(new boolean [][] {{false, true, true},{true, true, true},{true, true, false}}) == maxSubSquareMatrixDB(new boolean [][] {{false, true, true},{true, true, true},{true, true, false}}));
        assert (maxSubSquareMatrixSerialDB(new boolean [][] {{true, true, true},{true, false, true},{true, true, true}}) == 1);
    }
}