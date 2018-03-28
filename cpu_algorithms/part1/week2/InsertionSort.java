public class InsertionSort {
    
    public static void sort(int [] data) {
        int pivot = 1;

        if (data == null || data.length < 2) {
            return;
        }

        for (pivot = 1; pivot < data.length; pivot++) {
            for (int j = pivot; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    int temp = data[j];
                    data[j] = data[j - 1];
                    data [j - 1] = temp;
                }
                else {
                    break;
                }
            }
            
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

        InsertionSort.sort(array);
        InsertionSort.printArray(array);        
    }
}