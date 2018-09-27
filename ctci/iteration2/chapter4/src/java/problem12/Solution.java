import java.util.HashMap;

public class Solution {
    public static class Node {
        private int value;
        private Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int numPathsWithSum(Node root, int sum) {
        if (root == null) {
            return 0;
        }
        
        return numPathsWithSum(root, sum, 0, new HashMap<Integer, Integer>());
    }

    private static int numPathsWithSum(Node root, int targetSum, int runningSum, HashMap<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }

        runningSum += root.value;
        int sum = targetSum - runningSum;
        int count = map.getOrDefault(sum, 0);

        if (runningSum == targetSum) {
            count++;
        }

        int value = map.getOrDefault(runningSum, 0) + 1;
        map.put(runningSum, value);
        count += numPathsWithSum(root.left, targetSum, runningSum, map);
        count += numPathsWithSum(root.right, targetSum, runningSum, map);
        value = map.getOrDefault(runningSum, 0) - 1;
        if (value == 0) {
            map.remove(runningSum);
        }

        return count;
    }
}