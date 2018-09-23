import java.util.BitSet;

public class Solution {

    public static void zeroMatrix(int [][] matrix) {
        BitSet cols = new BitSet(matrix[0].length);

        for (int row = 0; row < matrix.length; row++) {
            boolean rowFlag = false;

            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    rowFlag = true;
                    cols.set(col);
                }
            }

            if (rowFlag) {
                // clear the row
                for (int col = 0; col < matrix[row].length; col++) {
                    matrix[row][col] = 0;
                }    
            }
        }
        
        for (int col = 0; col < matrix[0].length; col++) {
            if (cols.get(col)) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        }
    }

    public static void main(String [] args) {
        int [][] matrix = new int [][] {
            {1,2,0,4},
            {5,6,0,8},
            {9,10,11,12},
            {0,14,15,16}
        };
        
        zeroMatrix(matrix);

        for (int [] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }
}