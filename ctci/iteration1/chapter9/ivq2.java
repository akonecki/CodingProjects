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

    public static void main(String [] args) {
        int [][] matrix = new int [][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        assert (totalPaths(matrix) == 2);
    }
}