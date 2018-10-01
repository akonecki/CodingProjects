import java.util.List;
import java.util.LinkedList;

public class Solution {
    // [F]unctional
    // Although the problem only indicates needing to find a single path.  Will
    // break this out into multiple parts in order to practice DP in addition
    // to recursion.  

    // will Assume that the matrix cell values have the range of 0 and 1.
    // a value of 0 indicates that the cell is open.
    // a value of 1 indicates that the cell is occupied.
    private static int totalPaths(int [][] matrix, int row, int col) {
        if (row > matrix.length || (row != matrix.length && col > matrix[col].length)) {
            return 0;
        }
        else if (row == matrix.length && col == matrix[row - 1].length) {
            // at the bottom right cell of the matrix.
            return 1;
        }
        else if (row == matrix.length || col == matrix[row - 1].length || matrix[row][col] == 1) {
            return 0;
        }

        return totalPaths(matrix, row + 1, col) + totalPaths(matrix, row, col + 1);
    }

    private static void validPaths(int [][] matrix, int row, int col, List<List<Integer>> path, List<List<List<Integer>>> paths) {
        if (row > matrix.length || (row != matrix.length && col > matrix[col].length)) {
            return;
        }
        else if (row == matrix.length && col == matrix[row - 1].length) {
            // at the bottom right cell of the matrix.
            paths.add(new LinkedList<List<Integer>>(path));
            return;
        }
        else if (row == matrix.length || col == matrix[row - 1].length || matrix[row][col] == 1) {
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

    public static void main(String [] args) {

    }
}