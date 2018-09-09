import java.util.*;

public class Problem4 {

    // Likely the total number of permutations will be equal to (N!)/((I!)(J!))
    // where N is the total number of numbers in the list
    // where I is the total number of numbers that do not have duplicates
    // where J is the total number of number that do have duplicates
    public static List<List<Integer>> permutations(int [] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        unique(nums, 0, new ArrayList<Integer>(nums.length), result);

        return result;
    }

    // each recursion level represents the position within the array.
    private static void unique(int [] nums, int numIndex, List<Integer>numList, List<List<Integer>> result) {
        if (numIndex >= nums.length) {
            result.add(new ArrayList<Integer>(numList));
            return;
        }

        HashSet<Integer> setSeenAtLevel = new HashSet<Integer>();

        for (int index = 0; index < nums.length - numIndex; index++) {
            if (!setSeenAtLevel.contains(nums[index])) {
                setSeenAtLevel.add(nums[index]);
                numList.add(nums[index]);
                // swap with the end.
                int temp = nums[nums.length - numIndex - 1];
                nums[nums.length - numIndex - 1] = nums[index];
                nums[index] = temp;

                unique(nums, numIndex + 1, numList, result);

                // un-wind
                nums[index] = nums[nums.length - numIndex - 1];
                nums[nums.length - numIndex - 1] =  temp;
                numList.remove(numList.size() - 1);
            }
        } 
    }

    public static void main (String [] args) {
        assert (permutations(new int [] {1,2,3}).size() == 6);
        for (List<Integer> list : permutations(new int [] {1,1,1,2,3,4,5})) {
            for (int num : list) {
                System.out.print(num + ", ");
            }
            System.out.println("");
        }
        System.out.println(permutations(new int [] {1,1,1,2,2,2,5}).size());
    }
}