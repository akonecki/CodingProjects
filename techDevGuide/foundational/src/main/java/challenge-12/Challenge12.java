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

    public static void main(String [] args) {
        assert (maxSubSquareMatrix(new boolean [][] {{false, true, false},{true, true, true},{false, true, true}}) == 2);
        assert (maxSubSquareMatrix(new boolean [][] {{true}}) == 1);
        assert (maxSubSquareMatrix(new boolean [][] {{false}}) == 0);
        assert (maxSubSquareMatrix(new boolean [][] {{true, true, true},{true, false, true},{true, true, true}}) == 1);
        assert (maxSubSquareMatrix(new boolean [][] {{false, true, true},{true, true, true},{true, true, false}}) == 2);

    }
}