import java.util.*;

public class Problem3 {

    public static List<List<Integer>> longestIncreasingSubsequence(List<Integer> l) {
        // Checking input
        if (l == null || l.isEmpty()) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        longestIncreasingSubsequence(l, 0, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private static int longestIncreasingSubsequence(
        List<Integer> nums, 
        int index, 
        int maxLength,
        List<Integer> list, 
        List<List<Integer>> result) 
    {
        if (index >= nums.size()) {
            if (list.size() == maxLength) {
                result.add(new ArrayList<Integer>(list));
                return maxLength;
            }
            else if (list.size() > maxLength) {
                result.clear();
                result.add(new ArrayList<Integer>(list));
                return list.size();
            }
            else {
                return maxLength;
            }
        }

        int startingValue = nums.get(index).intValue();
        int length = maxLength;
        
        // Inclusion
        if (list.isEmpty() || (list.get(list.size() - 1).intValue() < startingValue)) {
            list.add(startingValue);
            length = longestIncreasingSubsequence(nums, index + 1, maxLength, list, result);
            list.remove(list.size() - 1);

            // this is the case in which the array of nums is already sorted, thus 
            // there is no chance that another array will equal or be greater than 
            // the current list.
            if (length >= nums.size() - index) {
                return length;
            }
        }

        // exclusion
        return longestIncreasingSubsequence(nums, index + 1, length, list, result);
    }

    public static void main(String [] args) {
        List<Integer> list = new ArrayList<Integer>();
        // {9,6,1,5,3,7,55,12}
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(12);
        list.add(11);
        list.add(10);
        list.add(9);
        list.add(16);
        list.add(15);
        list.add(14);
        list.add(13);
        System.out.println(longestIncreasingSubsequence(list).size());
        for (List<Integer> nums : longestIncreasingSubsequence(list)) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}