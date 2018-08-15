public class ivq3 {
    public static int findIndex(int [] nums, int value) {
        int lowIndex = 0, highIndex = nums.length - 1;
        int index = 0;

        while (lowIndex <= highIndex) {
            index = (highIndex - lowIndex) / 2 + lowIndex;

            if (nums[index] < value) {
                // See if the right is upward only
                if (nums[index] <= nums[highIndex]) {
                    if (value <= nums[highIndex]) {
                        // go right
                        lowIndex = index + 1;
                    }
                    else {
                        // number might acutally be in the lower half, but not 
                        // in right half for sure.
                        highIndex = index - 1;
                    }
                }
                else {
                    lowIndex = index + 1;
                }
            }
            else if (nums[index] > value) {
                // See if the right is upward only
                if (nums[index] >= nums[lowIndex]) {
                    if (value >= nums[lowIndex]) {
                        // go right
                        highIndex = index - 1;
                    }
                    else {
                        // number might acutally be in the lower half, but not 
                        // in right half for sure.
                        lowIndex = index + 1;
                    }
                }
                else {
                    // there is a break
                    highIndex = index - 1;
                }
            }
            else {
                return index;
            }
        }

        return -1;
    }
    
    public static void main(String [] args) {
        assert (findIndex(new int [] {12, 18, 21, 28, 31, 33, 41, 52, -23, 3}, 12) == 0);
        assert (findIndex(new int [] {3, 12, 18, 21, 28, 31, 33, 41, 52, -23}, 12) == 1);
        assert (findIndex(new int [] {-23, 3, 12, 18, 21, 28, 31, 33, 41, 52}, 12) == 2);
        assert (findIndex(new int [] {52, -23, 3, 12, 18, 21, 28, 31, 33, 41}, 12) == 3);
        assert (findIndex(new int [] {41, 52, -23, 3, 12, 18, 21, 28, 31, 33}, 12) == 4);
        assert (findIndex(new int [] {33, 41, 52, -23, 3, 12, 18, 21, 28, 31}, 12) == 5);
        assert (findIndex(new int [] {31, 33, 41, 52, -23, 3, 12, 18, 21, 28}, 12) == 6);
        assert (findIndex(new int [] {28, 31, 33, 41, 52, -23, 3, 12, 18, 21}, 12) == 7);
        assert (findIndex(new int [] {21, 28, 31, 33, 41, 52, -23, 3, 12, 18}, 12) == 8);
        assert (findIndex(new int [] {18, 21, 28, 31, 33, 41, 52, -23, 3, 12}, 12) == 9);
        // assert (findIndex(new int [] {33, 41, 52, -23, 3, 12, 18, 21, 28, 31}, 12) == 5);
    }
}