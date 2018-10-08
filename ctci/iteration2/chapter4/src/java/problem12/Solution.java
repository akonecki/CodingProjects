import java.util.HashMap;
import java.util.Stack;

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
        // some put a key/value of 0:1 into the map.  This would not be good 
        // imo if target is zero, and first is zero, will lead to double checks, or 
        // a zero in the rest of the tree as well.

        int result = numPathsWithSum(root, sum, 0, new HashMap<Integer, Integer>());
        assert (result == traverse(root, sum));

        return result;
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

    public static int traverse(Node root, int target) {
        Node node = root;
        Stack<Node> stack = new Stack<Node>();
        int result = 0;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();
                result += bruteForce(node, target, 0);
                node = node.right;
            }
        }
        return result;
    }

    private static int bruteForce(Node root, int target, int runningSum) {
        if (root == null) {
            return 0;
        }

        runningSum += root.value;
        int count = 0;

        if (target - runningSum == 0) {
            count++;
        }

        count += bruteForce(root.left, target, runningSum) + bruteForce(root.right, target, runningSum);

        return count;
    }


}