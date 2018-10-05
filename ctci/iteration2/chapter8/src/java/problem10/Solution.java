public class Solution {
    // [F]uncational

    // Would have to know if diagonal would also be subjected to the fill.
    public static void fill(int [][] matrix, int pointX, int pointY, int newValue) {
        // input validation
        if (matrix == null || matrix.length <= 0 || matrix[pointX][pointY] == newValue) {
            // No work, invalid input.
            return;
        }

        fill(matrix, pointX, pointY, matrix[pointX][pointY], newValue);
    }

    private static void fill(int [][] matrix, int pointX, int pointY, int oldValue, int newValue) {
        if (pointX < 0 || pointY < 0 || 
            pointX >= matrix.length || pointY >= matrix[pointX].length || 
            matrix[pointX][pointY] != oldValue) 
        {
            return;
        }

        matrix[pointX][pointY] = newValue;
        fill(matrix, pointX + 1, pointY, oldValue, newValue);
        fill(matrix, pointX - 1, pointY, oldValue, newValue);
        fill(matrix, pointX, pointY + 1, oldValue, newValue);
        fill(matrix, pointX, pointY - 1, oldValue, newValue);
    }

    public static void main(String [] args) {
        int [][] matrix = new int [][] {
            {1,1,1,1},
            {1,2,3,4},
            {1,2,1,1}
        };

        fill(matrix, 2, 3, 4);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}