public class ivq6 {
    public static <T> void rotateClockWise(T [][] dataToRotate) {
        if (dataToRotate == null) {
            return;
        }

        // i - starting row
        for (int i = 0; i < (dataToRotate.length / 2); i++) {
            // j - index in row
            for (int j = 0; j < (dataToRotate.length - 1 - (i * 2)); j++) {
                T value = dataToRotate[i][i + j];
                // System.out.println(dataToRotate[i][i + j]);
                value = ivq6.<T>moveLeftToRight(dataToRotate, dataToRotate.length, i, j, value);

                // System.out.println(dataToRotate[i + j][dataToRotate.length - 1 - (i * 2) + i]);
                value = ivq6.<T>moveTopToBottom(dataToRotate, dataToRotate.length, i, j, value);

                // System.out.println(dataToRotate[dataToRotate.length - 1 - (i * 2) + i][dataToRotate.length - 1 - (i * 2) + i - j]);
                value = ivq6.<T>moveRightToLeft(dataToRotate, dataToRotate.length, i, j, value);

                // System.out.println(dataToRotate[dataToRotate.length - 1 - (i * 2) - j + i][i]);
                ivq6.<T>moveBottomToTop(dataToRotate, dataToRotate.length, i, j, value);
            }
        }
    }

    private static <T> T moveLeftToRight(T [][] dataToRotate, int length, int i, int j, T value) {
        T temp = dataToRotate[i + j][length - 1 - i];
        dataToRotate[i + j][length - 1 - i] = value;
        return temp;
    }

    private static <T> T moveTopToBottom(T [][] dataToRotate, int length, int i, int j, T value) {
        T temp = dataToRotate[length - 1 - i][length - 1 - i - j];
        dataToRotate[length - 1 - i][length - 1 - i - j] = value;
        return temp;    
    }

    private static <T> T moveRightToLeft(T [][] dataToRotate, int length, int i, int j, T value) {
        T temp = dataToRotate[length - 1 - i - j][i];
        dataToRotate[length - 1 - i - j][i] = value;
        return temp;    
    }

    private static <T> T moveBottomToTop(T [][] dataToRotate, int length, int i, int j, T value) {
        T temp = dataToRotate[i][i + j];
        dataToRotate[i][i + j] = value;
        return temp;
    }
    
    public static void main(String [] args) {
        Integer [][] data = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};

        ivq6.<Integer>rotateClockWise(data);

        for (Integer [] integers : data) {
            for (Integer integer : integers) {
                System.out.println(integer);
            }
        }
    }
}