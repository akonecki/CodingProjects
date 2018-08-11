public class ivq3 {

    public static boolean magicIndexExists(int [] values) {

        if (values != null && values.length > 0) {
            int index = 0;

            while (index < values.length && index >= 0) {
                // System.out.println("Iterating at index " + index);

                if (values[index] == index) {
                    return true;
                }
                else if (values[index] < 0) {
                    // negative value at the index, search for the first 
                    // non-negative number.
                    index = search(values, index + 1, values.length - 1, 0);
                }
                else if (values[index] > index) {
                    index = values[index];
                }
                else {
                    index = search(values, index + 1, values.length - 1, values[index] + 1);
                }
            }
        }

        return false;
    }

    private static int search(int [] values, int lowIndex, int highIndex, int value) {
        int low = lowIndex, high = highIndex;

        // System.out.println("Searching for value " + value + " low index " + lowIndex + " high index " + highIndex);

        while (low <= high && high >= lowIndex && low <= highIndex) {
            int index = (high - low) / 2 + low;
            int valueAtIndex = values[index];

            if (valueAtIndex == value || values[index] == index) {
                return index;
            }
            else if (valueAtIndex > value) {
                // System.out.println("Comparison " + valueAtIndex + " " + value + " " + index + " " + low + " " + high);
                // greater
                if (index == lowIndex) {
                    return high;
                }

                high = index - 1;

                if (high >= lowIndex && values[high] < value && values[high] >= 0) {
                    return high;
                }
            }
            else {
                // less
                low = index + 1;

                if (low < values.length && values[low] > value) {
                    return low;
                }
            }
        }

        return -1;
    }

    public static boolean magicIndexExistsRecursive(int [] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        return magicIndexExistsRecursive(values, 0, values.length - 1);
    }

    private static boolean magicIndexExistsRecursive(int [] values, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return false;
        }
        
        int index = (rightIndex - leftIndex) / 2 + leftIndex;

        if (values[index] == index) {
            return true;
        }

        return magicIndexExistsRecursive(values, leftIndex, index - 1) || magicIndexExistsRecursive(values, index + 1, rightIndex);
    }

    public static void main(String [] args) {
        int [] negativeArray = {-1, -1, -1};
        int [] falseArray = {-9, -1, 0, 0, 1, 7, 19, 22, 23, 34};
        int [] trueArrayStart = {0, 0, 1, 7, 19, 22, 23, 34};
        int [] trueArrayEnd = {-9, -1, 0, 0, 1, 5};
        int [] trueArrayDup = {-9, -1, 0, 0, 5, 5};
        int [] trueArrayDupPostOdd = {-9, -1, 0, 0, 1, 2, 2, 7, 7, 34};
        int [] trueArrayDupPreOdd = {-9, -1, 0, 0, 1, 2, 7, 7, 10, 34};
        assert (magicIndexExists(negativeArray) == magicIndexExistsRecursive(negativeArray));
        assert (magicIndexExists(falseArray) == magicIndexExistsRecursive(falseArray));
        assert (magicIndexExists(trueArrayStart) == magicIndexExistsRecursive(trueArrayStart));
        assert (magicIndexExists(trueArrayEnd) == magicIndexExistsRecursive(trueArrayEnd));
        assert (magicIndexExists(trueArrayDup) == magicIndexExistsRecursive(trueArrayDup));
        assert (magicIndexExists(trueArrayDupPostOdd) == magicIndexExistsRecursive(trueArrayDupPostOdd));
        assert (magicIndexExists(trueArrayDupPreOdd) == magicIndexExistsRecursive(trueArrayDupPreOdd));
    }
}