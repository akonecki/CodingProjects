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


    public static void main(String [] args) {
        assert (maxSubSquareMatrix(new boolean [][] {{false, true, false},{true, true, true},{false, true, true}}) == 2);
        assert (maxSubSquareMatrix(new boolean [][] {{true}}) == 1);
        assert (maxSubSquareMatrix(new boolean [][] {{false}}) == 0);
        assert (maxSubSquareMatrix(new boolean [][] {{true, true, true},{true, false, true},{true, true, true}}) == 1);
        assert (maxSubSquareMatrix(new boolean [][] {{false, true, true},{true, true, true},{true, true, false}}) == 2);

    }
}