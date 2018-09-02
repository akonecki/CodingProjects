import java.util.*;

public class Problem2 {
    public static List<List<Integer>> arrayOfArrays(int[][] arr) {
        // Input checking.
        if (arr == null || arr.length < 1) {
            return new ArrayList<List<Integer>>();
        }

        for (int [] nums : arr) {
            if (nums == null || nums.length < 1) {
                // cannot build a solution if a sub-array within the input
                // is not populated.
                return new ArrayList<List<Integer>>();
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        arrayOfArrays(arr, 0, result, new ArrayList<Integer>(arr.length));
        return result;
    }

    private static void arrayOfArrays(
        int [][] arr, 
        int index, 
        List<List<Integer>> result, 
        List<Integer>list) 
    {
        if (index >= arr.length) {
            // full copy will now only occur at a completed path.
            result.add(new ArrayList<Integer>(list));
            return;
        }

        // Add in reducing the number of copies to be equal to the number of 
        // levels.
        for (int num : arr[index]) {
            // add to the temp list
            list.add(num);
            arrayOfArrays(arr, index + 1, result, list);
            // remove from the temp list
            list.remove(list.size() - 1);
        }
    }

    public static void main(String [] args) {
        int [][] arrayOfNums = new int [][] {{1,2,7,9}, {3,6}, {4,5,13}};
        for (List<Integer> nums : arrayOfArrays(arrayOfNums)) {
            for (int num : nums) {
                System.out.print(num + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}