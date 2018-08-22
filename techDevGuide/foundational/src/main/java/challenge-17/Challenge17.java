import java.io.*;
import java.util.*;

public class Challenge17 {
    
    public static ArrayList<Integer> getNMostFreq(int [] nums, int N) {
        HashMap<Integer, Integer> numToCount = new HashMap<Integer, Integer>();
        ArrayList<Integer> orderOfOccurrence = new ArrayList<Integer>();
        HashMap<Integer, ArrayList<Integer>> countToNums = new HashMap<Integer, ArrayList<Integer>>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>(new PriorityQueueOrder());

        // Step 1 - Populate the num counting mapping along with ordered.
        for (int num : nums) {
            if (numToCount.containsKey(num)) {
                numToCount.put(num, numToCount.get(num).intValue() + 1);
            }
            else {
                numToCount.put(num, 1);
                orderOfOccurrence.add(num);
            }
        }

        // Step 2 - Population of the count bins, along with the priority queue.
        for (int orderNum : orderOfOccurrence) {
            int count = numToCount.get(orderNum);
            ArrayList<Integer> list = countToNums.get(count);

            if (list == null) {
                maxQueue.add(count);
                list = new ArrayList<Integer>();
            }

            list.add(orderNum);
            countToNums.put(count, list);
        }

        // Step 3 - Work with the priority queue to populate the result upto N
        // values.
        ArrayList<Integer> result = new ArrayList<Integer>(N);
        
        while (N > 0 && !maxQueue.isEmpty()) {
            ArrayList<Integer> list = countToNums.get(maxQueue.poll().intValue());

            for (int index = 0; index < list.size() && N > 0; index++, N--) {
                result.add(list.get(index));
            }
        }

        return result;
    }
    
    private static class PriorityQueueOrder implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            if (a.intValue() < b.intValue()) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

    public static void main(String [] args) {
        int [] nums = {1, 6, 8, 9, 7, 6, 4, 3, 2, 1, 7, 7, 7, 3, 1, 0, 9};

        assert (getNMostFreq(nums, 9).size() == 9);

        for (int num : getNMostFreq(nums, 3)) {
            System.out.print(num + " ");
        }
        System.out.println("");
    }
}