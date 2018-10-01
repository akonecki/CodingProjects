public class Solution {
    // [F]unctional

    public static int magicIndex(int [] A) {
        return magicIndex(A, 0, A.length - 1);
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

    public static void main(String [] args) {
        assert (magicIndex(new int [] {1,2,3,4,5,6}) == -1);
        assert (magicIndex(new int [] {-1, -1, 1,2,3,4,5,6}) == -1);
        assert (magicIndex(new int [] {-1,1,2,3,4,5,6}) != -1);
        assert (magicIndex(new int [] {-1,1,1,1,1,1,1}) == 1);
        assert (magicIndex(new int [] {-1,1,1,1,4,5,6}) != -1);
        assert (magicIndex(new int [] {0,2,3,9,14,15,16}) == 0);
        assert (magicIndex(new int [] {-1,2,3,6,6,6,6}) == 6);
    }
}