import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

// Sparse vector is an application involving hashing, specifically where the 
// provided data set may have a large amount invalid data or un-interesting 
// data but want to be able to quickly access the valid / interesting data.
//
// Take an NxN matrix of values where there are few points above a certain
// threshold, don't want to perform O(N^2) for performing operations on the
// large data set so sparse vector can be used to reduce this to O(N).
public class SparseVector {

    // Each row of the matrix will be represented as a HashMap instead of just
    // the array of values.
    private List<HashMap<Integer, Integer>> mReducedMatrix = null; 

    public SparseVector() {
        this.mReducedMatrix = new ArrayList<HashMap<Integer, Integer>>();
    }

    public SparseVector(int [][] matrix) {
        if (matrix == null) {
            this.mReducedMatrix = new ArrayList<HashMap<Integer, Integer>>();
            return;
        }
        else {
            this.mReducedMatrix = new ArrayList<HashMap<Integer, Integer>>(matrix.length);
        }

        // Now need to iterate through the provided matrix and localize a copy.
        for (int i = 0; i < matrix.length; i++) {
            // Add a new hash.
            HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
            
            // Assumming NxN matrix for simplicity.
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    hash.put(j, matrix[i][j]);
                }
            }

            this.mReducedMatrix.add(hash);
        }
    }

    public List<ArrayList<Integer>> reducedMatrix() {
        // 2D matrix of the reduced set of values.
        List<ArrayList<Integer>> values = new ArrayList<ArrayList<Integer>>();

        for (HashMap<Integer, Integer> hash : this.mReducedMatrix) {
            ArrayList<Integer> rowValues = new ArrayList<Integer>();

            for (Integer integer : hash.keySet()) {
                // Will return the index.
                rowValues.add(integer);
            }

            values.add(rowValues);
        }

        return values;
    }

    public static void main(String [] args) {
        int [][] data = {{0, 0, 0, 0, 1, 0, 0, 1, 0, 1}, 
                         {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                         {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                         {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                         {1, 1, 0, 1, 1, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                         {1, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                         {0, 1, 1, 1, 0, 0, 0, 0, 0, 0}};
        
        SparseVector reducedData = new SparseVector(data);

        for (ArrayList<Integer> row : reducedData.reducedMatrix()) {
            for (Integer integer : row) {
                System.out.print(integer + " ");
            }
            System.out.println("");
        }
    }
}