public class SelectionSort {
    public static void sort(int [] data) {
        int pivot = 0;

        if (data == null || data.length < 2) {
            return;
        }

        for (pivot = 0; pivot < data.length; pivot++) {
            int minIndex = pivot;

            for (int j = pivot + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }    
            }

            int temp = data[pivot];
            data[pivot] = data[minIndex];
            data[minIndex] = temp;
        }
    }

    public static void printArray(int [] array) {
        for (int num : array) {
            System.out.print(Integer.toString(num) + " ");
        }
        System.out.println();
    }
    
    public static void main(String [] args) {
        int[] array = {9,8,7,6,5,4,3,2,1,0};

        SelectionSort.sort(array);
        SelectionSort.printArray(array);        
    }
}