public class Solution {
    public static void rotateMatrix(int [][] matrix) {
        // Ensure assumption about the matrix being square.
        assert (matrix.length == matrix[matrix.length - 1].length);

        for (int rowOffSet = 0; rowOffSet < matrix.length / 2; rowOffSet++) {
            int leftColumnIndex = rowOffSet;
            int topRowIndex = rowOffSet;

            for (int colOffSet = rowOffSet; colOffSet < matrix[rowOffSet].length - rowOffSet - 1; colOffSet++) {
                int rightColumnIndex = matrix[rowOffSet].length - rowOffSet - 1;
                int bottomRowIndex = matrix[rowOffSet].length - rowOffSet - 1;
                int current = 0, target = 0;
            
                current = matrix[topRowIndex][leftColumnIndex + colOffSet];

                // left to right
                target = matrix[topRowIndex + colOffSet][rightColumnIndex];
                matrix[topRowIndex + colOffSet][rightColumnIndex] = current;
                current = target;

                // top to bottom
                target = matrix[bottomRowIndex][rightColumnIndex - colOffSet];
                matrix[bottomRowIndex][rightColumnIndex - colOffSet] = current;
                current = target;

                // right to left
                target = matrix[bottomRowIndex - colOffSet][leftColumnIndex];
                matrix[bottomRowIndex - colOffSet][leftColumnIndex] = current;
                current = target;
                
                // bottom to top
                matrix[topRowIndex][leftColumnIndex + colOffSet] = current;
            }
        }
    }
    
    public static void main(String [] args) {
        int [][] matrix = new int [][] {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };
        
        rotateMatrix(matrix);

        for (int [] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }
}