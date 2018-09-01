public class ivq6 {

    public static int [] findSortingIndices(int [] nums) {
        if (nums == null || nums.length < 2) {
            return new int [] {-1, -1};
        }

        int index = 1;

        // Find the first occurrence in which it is not sorted
        for (; index < nums.length; index++) {
            if (nums[index - 1] > nums[index]) {
                index--;
                break;
            }
        }

        if (index == nums.length) {
            // array is already sorted
            return new int [] {-1, -1};
        }

        int lowerBound = index;
        int upperBound = index + 1;
        int max = nums[index];
        int min = nums[index + 1];

        // Find the last occurrence in which the array is not sorted
        for (index += 2; index < nums.length; index++) {
            if (nums[index] < max) {
                upperBound = index;

                if (nums[index] < min) {
                    min = nums[index];
                }
            }
            else if (nums[index] > max) {
                max = nums[index];
            }
        }

        // now search for the lower bound
        return new int [] {searchMinIndex(nums, 0, lowerBound, min), upperBound};
    }

    private static int searchMinIndex(
        int [] nums, 
        int lowerBound, 
        int upperBound, 
        int value)
    {
        if (lowerBound == upperBound || value < nums[lowerBound]) {
            return lowerBound;
        }
        int low = lowerBound, high = upperBound;

        while (low <= high && low >= lowerBound && high <= upperBound) {
            int index = (high - low) / 2 + low;

            if (nums[index] <= value) {
                // see if need to go right
                if (index + 1 <= upperBound && nums[index + 1] <= value) {
                    low = index + 1;
                }
                else {
                    return index + 1;
                }
            }
            else if (nums[index] > value) {
                // need to see if can go left
                high = index - 1;
            }
        }

        return 0;
    }

    private static boolean isEqual(int [] result, int [] key) {
        if (result == null || key == null || result.length != key.length || result.length != 2) {
            return false;
        }

        for (int index = 0; index < result.length; index++) {
            System.out.println(result[index] + " " + key[index]);
            if (result[index] != key[index]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        assert (searchMinIndex(new int [] {1,2,3,4,5}, 0, 4, -1) == 0);
        assert (searchMinIndex(new int [] {1,2,3,4,5}, 0, 4, 1) == 1);
        assert (searchMinIndex(new int [] {1,2,3,4,5}, 0, 4, 2) == 2);
        assert (searchMinIndex(new int [] {1,2,3,4,5}, 0, 4, 3) == 3);
        assert (searchMinIndex(new int [] {1,2,3,4,5}, 0, 4, 4) == 4);
        assert (searchMinIndex(new int [] {1,2,3,4,5}, 0, 4, 5) == 5);
        assert (searchMinIndex(new int [] {1,2,3,4,5}, 0, 4, 6) == 5);
        assert (isEqual(findSortingIndices(new int [] {1,2,4,7,10,11,7,12,6,7,16,18,19}), new int [] {3,9}));
        assert (isEqual(findSortingIndices(new int [] {1,2,4,7,10,11,7,12,6,7,16,18,-1}), new int [] {0,12}));
        assert (isEqual(findSortingIndices(new int [] {1,2,4,7,10,11,11,12,14,15,16,18,19}), new int [] {-1,-1}));
    }
}