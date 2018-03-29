public class MergeSort {
    
    public static boolean isSorted(int [] array, int startIndex, int endIndex) {
        for (int index = startIndex + 1; index <= endIndex; index++) {
            if (array[index - 1] > array[index]) {
                return false;
            }
        }

        return true;
    }

    private static void merge(int [] array, int [] aux, int startIndex, int midIndex, int endIndex) {
        // Pre-condition check of required values.
        assert(isSorted(array, startIndex, midIndex - 1));
        assert(isSorted(array, midIndex, endIndex));
        assert(startIndex < midIndex);
        assert(midIndex <= endIndex);

        for (int index = startIndex; index <= endIndex; index++) {
            // Copy to the auxilary storage area.
            aux[index] = array[index];
        }

        // Perform the merge back to the original data array.
        for (int leftIndex = startIndex, rightIndex = midIndex, index = startIndex; 
            index <= endIndex; index++) {
            if (leftIndex < midIndex && rightIndex <= endIndex) {
                if (aux[leftIndex] < aux[rightIndex]) {
                    array[index] = aux[leftIndex];
                    leftIndex++;    
                }
                else {
                    array[index] = aux[rightIndex];
                    rightIndex++;
                }
            }
            else if (leftIndex < midIndex) {
                array[index] = aux[leftIndex];
                leftIndex++;
            }
            else if (rightIndex <= endIndex) {
                array[index] = aux[rightIndex];
                rightIndex++;
            }
        }
    }

    // Don't want to expose auxiliary data structure.
    public static void sort(int [] data) {
        int [] aux = new int[data.length];
        MergeSort.sort(data, aux, 0, data.length);
    }

    public static void sort(int [] data, int [] aux, int startIndex, int length) {
        int half = length / 2;
        if (length < 2) {
            // Already sorted
            return;
        }

        System.out.println(Integer.toString(length) + " " 
            + Integer.toString(startIndex) + " " 
            + Integer.toString(startIndex + half) + " "
            + Integer.toString(startIndex + length - 1) + " ");

        // Sort Left
        MergeSort.sort(data, aux, startIndex, half);
        // Sort Right
        MergeSort.sort(data, aux,  half + startIndex, length - half);
        // Merge Left & Right
        MergeSort.merge(data, aux, startIndex, startIndex + half, startIndex + length - 1);
    }

    public static void main(String [] args) {
        int [] data = {9,8,7,6,5,4,3,2,1};
        MergeSort.sort(data);
        assert(MergeSort.isSorted(data, 0, data.length - 1));
    }
}