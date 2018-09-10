import java.util.*;

public class Solution {
    public static List<Integer> slidingWindowMax(int [] nums, int windowSize) {
        List<Integer> result = new ArrayList<Integer>(nums.length - windowSize + 1);
        LinkedList<Integer> maxOfWindow = new LinkedList<Integer>();
        LinkedList<Integer> maxIndices = new LinkedList<Integer>();

        int windowLength = 0;

        for (int removeIndex = 0, index = 0; index < nums.length; index++) {
            while (!maxOfWindow.isEmpty() && maxOfWindow.peekLast().intValue() <= nums[index]) {
                maxOfWindow.removeLast();
                maxIndices.removeLast();
            }
            maxOfWindow.add(nums[index]);
            maxIndices.add(index);

            if (windowLength < windowSize) {
                windowLength++;

                if (windowLength == windowSize) {
                    result.add(maxOfWindow.peek().intValue());
                }
            }
            else {
                if (maxIndices.peek().intValue() <= removeIndex) {
                    maxOfWindow.remove();
                    maxIndices.remove();
                }

                result.add(maxOfWindow.peek().intValue());
                removeIndex++;
            }
        }

        return result;
    }

    public static void main(String [] args) {
        System.out.println(slidingWindowMax(new int [] {1,2,3,4,3,2,1,3,5}, 4));
    }
}