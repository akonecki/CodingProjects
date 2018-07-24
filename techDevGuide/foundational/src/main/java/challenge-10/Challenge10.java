public class Challenge10 { 

    public static int counter(int [] nums) {
        return recursiveCount(nums, 0);
    }

    private static int recursiveCount(int [] nums, int index) {
        if (index == nums.length) {
            return 1;
        }
        else {
            int included = recursiveCount(nums, index + 1);
            int excluded = recursiveCount(nums, index + 1);

            return included + excluded;
        }
    }

    public static void main(String [] args) {
        assert (counter(new int [] {1}) == 2);
        assert (counter(new int [] {1,2}) == 4);
        assert (counter(new int [] {1,2,3}) == 8);
        assert (counter(new int [] {1,2,3,4}) == 16);
    }
}