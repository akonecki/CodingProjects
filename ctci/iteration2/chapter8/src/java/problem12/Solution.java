import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;

public class Solution {

    // [F]unctional

    // Given a number representing the number of queens determine all the
    // valid ways to arrange the queens on an N*N board.

    // Want to leverage the fact that only one queen can exist per row / col.
    // Should be able to perform lookups of an array of either row or col 
    // representation that contains the col / row in which the queen exists.

    // Queen movement limited by diagnal, and vertical and horiztonal movements.

    public static void nQueens(int [] colLocations, int index, List<String> result, HashSet<Integer> colsAvailable) {
        if (index >= colLocations.length) {
            result.add(buildBoard(colLocations));
        }

        int row = index;
        Integer [] list = null;
        list = colsAvailable.toArray(new Integer [colsAvailable.size()]);

        for (int colLocation : list) {
            // Account for in the locations array.
            colLocations[row] = colLocation;

            // Remove it from the colsAvailable
            colsAvailable.remove(colLocation);

            // Check to see if there is a violation
            // 1. dont need to check row or col since the set prevents duplicate
            //    columns being used, and the index prevent duplicate rows.
            // 2. need to check the diagonal, looking for slope of +1 or -1 and
            //    will need to look at all the currently populated column 
            //   locations.
            if (isValidLocation(colLocations, row)) {
                // can proceed to next queen.
                nQueens(colLocations, index + 1, result, colsAvailable);
            }

            // Add it back to be available
            colsAvailable.add(colLocation);
        }
    }

    private static boolean isValidLocation(int [] colLocations, int row) {
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            // row is the y
            // col is the x
            if (Math.abs(rowIndex - row) == Math.abs(colLocations[rowIndex] - colLocations[row])) {
                return false;
            }
        }

        return true;
    }

    private static String buildBoard(int [] colLocations) {
        StringBuilder board = new StringBuilder(colLocations.length * (colLocations.length + 1));

        for (int row = 0; row < colLocations.length; row++) {
            for (int col = 0; col < colLocations.length; col++) {
                if (colLocations[row] == col) {
                    board.append('Q');
                }
                else {
                    board.append('-');
                }
            }
            board.append('\n');
        }

        return board.toString();
    }

    public static List<String> nQueens(int queenCount) {
        List<String> result = new LinkedList<String>();
        HashSet<Integer> colsAvailable = new HashSet<>();
        HashSet<String> boards = new HashSet<String>();

        for (int location = 0; location < queenCount; location++) {
            colsAvailable.add(location);
        }

        nQueens(new int [queenCount], 0, result, colsAvailable);

        for (String board : result) {
            if (boards.contains(board)) {
                assert (false);
            }
            boards.add(board);
        }

        return result;
    }

    public static void main(String [] args) {
        for (String board : nQueens(8)) {
            System.out.println(board);
        }
    }
}