public class ivq7 {
    
    // [F]unctional
    public static void fillWithValue(int [][] matrix, int x, int y, int value) {
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