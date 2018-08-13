public class ivq7 {
    
    // [F]unctional
    public static void fillWithValue(int [][] matrix, int x, int y, int value) {
        // In the event that the desired value is equal to the current value at 
        // the desired starting position, would lead to infinite loop with an
        // eventual stack space exhaustion error for the application.
        if (matrix[x][y] == value) {
            return;
        }

        fillWithValue(matrix, x, y, value, matrix[x][y]);
    }

    private static void fillWithValue(int [][] matrix, int x, int y, int value, int from) {
        if (x >= matrix.length || x < 0 || y >= matrix[x].length || y < 0 || matrix[x][y] != from) {
            return;
        }
        else {
            matrix[x][y] = value;

            fillWithValue(matrix, x + 1, y, value, from);
            fillWithValue(matrix, x - 1, y, value, from);
            fillWithValue(matrix, x, y + 1, value, from);
            fillWithValue(matrix, x, y - 1, value, from);
        }
    }

    // [A]nalysis
    // 1. Does the problem contain optimal sub-structure?
    // Yes, each iteration of the recursive loop is visited only once.
    // 2. Does the problem contain re-occurring sub-problems?
    // No, although an index might be visited twice (first for modification)
    // the second time will always differ thus will immediately return.
    
    // Branching Factor is Four but the nature of the problem does not lead
    // to a 4^(N*M) complexity.  Even if all the values within the provided
    // matrix are the same each index could be visited 4 times, first for 
    // modification, the others for invalid resolution which return immediately.
    // This means that the worse case run-time should actually be around the
    // O(4*M*N).

    // Memory Impact
    // Due to worse case possible position, height of the stack could be the
    // full M*N matrix space.  Due to the right / left and up / down ordering
    // worse case should be reduced to M + N, since going in the opposite 
    // direction after exhausting current direction will always just return.

    public static void main(String [] args) {
        int [][] matrix = {
            {1, 1, 2, 4},
            {1, 2, 3, 5},
            {1, 1, 4, 7},
            {1, 8, 9, 10},
            {5, 1, 1, 2}};

        fillWithValue(matrix, 0, 0, 5);

        for (int [] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println("");
        } 
        System.out.println("");
    }
}