import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;

public class Solution {
    // [F]unctional

    public static int magicIndex(int [] A) {
        HashSet<Integer> results = new HashSet<>();
        int result = magicIndex(A, 0, A.length - 1);

        for (int index = 0; index < A.length; index++) {
            if (A[index] == index) {
                results.add(index);
            }
        }

        if (results.isEmpty()) {
            results.add(-1);
        }

        assert (results.contains(result));

        return result;
    }

    // Data assumption is numbers are sorted.
    private static int magicIndex(int [] A, int minIndex, int maxIndex) {
        if (minIndex <= maxIndex && minIndex >= 0 && maxIndex < A.length) {
            int index = (maxIndex - minIndex) / 2 + minIndex;

            if (A[index] == index) {
                return index;
            }
            else if (A[index] < 0) {
                // dont need to go left at all.
                return magicIndex(A, index + 1, maxIndex);
            }
            else if (A[index] >= A.length) {
                // dont need to go right at all.
                return magicIndex(A, minIndex, index - 1);
            }
            else if (A[index] < index) {
                int result = -1;

                if (A[index] >= minIndex) {
                    // Still must go left to the index
                    result = magicIndex(A, minIndex, A[index]);
                }

                if (result == -1) {
                    // go right but no bound jump.
                    result = magicIndex(A, index + 1, maxIndex);
                }

                return result;
            }
            else {
                // A[index] > index
                int result = -1;
                
                if (A[index] <= maxIndex) {
                    // go right with bound jump.
                    result = magicIndex(A, A[index], maxIndex);
                }

                if (result == -1) {
                    // Still must go left to the index
                    result = magicIndex(A, minIndex, index - 1);
                }

                return result;
            }
        }
        
        return -1;        
    }    

    // [A]nalysis
    // 1. Does the problem contain optimal sub-structure?
    // Yes each level of the recursive function is self contained.
    // 2. Does the problem have re-occurring sub-problems?
    // No because each index is called only once.

    // Memory Impact
    // Stack Height of the recursive method can approach O(lgN) where N is the
    // number of elements within the array.

    // Performance Impact
    // O(N) - in the case you must access each element within the matrix.  This
    // only happens once on the implementation so is O(N). 

    public static void main(String [] args) {
        final int cases = 100;
        Random random = new Random();

        for (int caseIndex = 0; caseIndex < cases; caseIndex++) {
            int [] data = new int [random.nextInt(100) + 1];

            for (int index = 0; index < data.length; index++) {
                data[index] = random.nextInt(26) * (random.nextBoolean() ? -1 : 1);
            }

            Arrays.sort(data);
            int result = magicIndex(data); 
            if (result != -1) {
                System.out.println("Case " + caseIndex + " " + data.length);
                for (int num : data) {
                    System.out.print(num + " ");
                }    
                System.out.println("");
            }
            
        }

    }
}