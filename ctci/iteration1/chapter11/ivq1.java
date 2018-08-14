import java.util.Random;
import java.util.Arrays;

public class ivq1 {
    public static void merge(int [] A, int [] B) {
        int fillIndex = A.length - 1;
        int aIndex = A.length - B.length - 1;
        int bIndex = B.length - 1;

        while (aIndex >= 0 || bIndex >= 0) {
            if (aIndex >= 0 && bIndex >= 0) {
                if (A[aIndex] > B[bIndex]) {
                    A[fillIndex] = A[aIndex];
                    aIndex--;
                }
                else {
                    A[fillIndex] = B[bIndex];
                    bIndex--;
                }
            }
            else if (bIndex >= 0) {
                A[fillIndex] = B[bIndex];
                bIndex--;
            }
            fillIndex--;
        }
    }
    
    public static void main(String [] args) {
        int [] problemArrayA = new int [200];
        int [] sortingArrayA = new int [100];
        int [] sortingArrayB = new int [100];
        int [] spare = new int [200];

        for (int index = 0; index < 100; index++) {
            sortingArrayA[index] = new Random().nextInt();
            sortingArrayB[index] = new Random().nextInt();
        }

        Arrays.sort(sortingArrayA);
        Arrays.sort(sortingArrayB);

        // Populate the problem array
        for (int index = 0; index < 100; index++) {
            problemArrayA[index] = sortingArrayA[index];
            spare[index] = sortingArrayA[index];
            spare[index + 100] = sortingArrayB[index];
        }

        Arrays.sort(spare);

        merge(problemArrayA, sortingArrayB);

        for (int index = 0; index < problemArrayA.length; index++) {
            assert (spare[index] == problemArrayA[index]);
        }
    }
}