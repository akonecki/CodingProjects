import java.util.ArrayList;

public class Problem1 {

    public static ArrayList<Integer> printOdd(int [] nums) {
        ArrayList<Integer> printed = new ArrayList<Integer>();
        
        if (nums == null || nums.length < 1) {
            // No work to do.
            return printed;
        }

        printed = new ArrayList<Integer>(nums.length / 2);
        printOdd(nums, 1, printed);
        return printed;
    }

    private static void printOdd(int [] nums, int index, ArrayList<Integer> printed) {
        // function assumption should never get here if the index is even.
        assert (index % 2 != 0);
        
        if (index < nums.length) {
            // perform work since the index falls within the supported range.
            System.out.println(nums[index]);
            printed.add(nums[index]);
            printOdd(nums, index + 2, printed);
        }
    }

    private static boolean areEqual(ArrayList<Integer> list, int [] nums) {
        if (nums == null && list.isEmpty()) {
            return true;
        }
        else if (nums == null || list.isEmpty() || list.size() != nums.length) {
            return false;
        }
        else {
            for (int index = 0; index < nums.length; index++) {
                if (list.get(index) != nums[index]) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String [] args) {
        assert (areEqual(printOdd(new int [] {2,4,6,8,10,12}), new int [] {4,8,12}));
        assert (areEqual(printOdd(new int [] {2}), null));
        assert (areEqual(printOdd(new int [0]), null));
        assert (areEqual(printOdd(new int [] {2,4,6,8,10,12, 14}), new int [] {4,8,12}));
    }
}