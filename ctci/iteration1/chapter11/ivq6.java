public class ivq6 {
    
    public static class Position {
        private int row = -1;
        private int col = -1;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(Position position) {
            return this.row == position.row && this.col == position.col;
        }
    }

    public static Position findElementStrict(int [][] matrix, int value) {
        int rMin = 0, rMax = matrix.length - 1;

        while (rMin <= rMax) {
            int rIndex = (rMax - rMin) / 2 + rMin;
            
            if (matrix[rIndex][0] > value) {
                rMax = rIndex - 1;
            }
            else if (matrix[rIndex][matrix[rIndex].length - 1] < value) {
                rMin = rIndex + 1;
            }
            else {
                int cMin = 0, cMax = matrix[rIndex].length - 1;
                int cIndex = -1;

                while (cMin <= cMax) {
                    int index = (cMax - cMin) / 2 + cMin;

                    if (matrix[rIndex][index] < value) {
                        cMin = index + 1;
                    }
                    else if (matrix[rIndex][index] > value) {
                        cMax = index - 1;
                    }
                    else {
                        cIndex = index;
                        break;
                    }
                }

                if (cIndex != -1) {
                    return new Position(rIndex, cIndex);
                }
                else {
                    return null;
                }
            }
        }

        return null;
    }
    
    public static void main(String [] args) {
        assert (findElementStrict(new int [][] {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}}, 6) != null);
        assert (findElementStrict(new int [][] {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}}, 6).equals(new Position(1, 1)));
    }
}