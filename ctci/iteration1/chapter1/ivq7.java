public class ivq7 {
    public static void zeroMatrix(int [][] cells, int rowCount, int colCount) {
        boolean [] rowsToZero = null;
        boolean [] colsToZero = null; 

        if (cells == null || rowCount < 1 || colCount < 1) {
            return;
        }

        rowsToZero = new boolean[rowCount];
        colsToZero = new boolean[colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (cells[i][j] == 0) {
                    rowsToZero[i] = true;
                    colsToZero[j] = true;
                }
            }
        }

        for (int row = 0; row < rowCount; row++) {
            if (rowsToZero[row]) {
                ivq7.zeroRow(cells, colCount, row);
            }
        }

        for (int col = 0; col < colCount; col++) {
            if (colsToZero[col]) {
                ivq7.zeroCol(cells, rowCount, col);
            }
        }
    } 

    private static void zeroRow(int [][] cells, int colCount, int rowIndex) {
        for (int index = 0; index < colCount; index++) {
            cells[rowIndex][index] = 0;
        }
    }

    private static void zeroCol(int [][] cells, int rowCount, int colIndex) {
        for (int index = 0; index < rowCount; index++) {
            cells[index][colIndex] = 0;
        }
    }

    public static void main(String [] args) {
        int [][] data = {{0,0,1,1,1,1,1,1,1}, {1,1,1,1,1,1,1,1,1}, {0,0,1,1,1,1,1,1,1}, {1,1,1,1,1,1,1,1,1}, {0,0,1,1,1,1,1,1,1}};
        ivq7.zeroMatrix(data, 5, 9);
        for (int [] integers : data) {
            for (int integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println("");
        }
    }
}