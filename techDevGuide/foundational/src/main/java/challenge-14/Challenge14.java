public class Challenge14 {

    // [F]unctional Implementation
    // Normal recursion, cant do greedy just due to wording of the problem 
    // makes it quite obvious (two possible operations).  Also want a count of
    // all combinations.
    public static int targetSum(int [] nums, int target) {
        return targetSum(nums, target, 0);
    }

    private static int targetSum(int [] nums, int target, int index) {
        if (index >= nums.length) {
            if (target == 0) {
                return 1;
            }
            else {
                return 0;
            }
        }

        return targetSum(nums, target + nums[index], index + 1) +
            targetSum(nums, target - nums[index], index + 1);
    }

    public static void main (String [] args) {
        assert (targetSum(new int [] {1,2,3,4}, 0) == 2);
    }
}