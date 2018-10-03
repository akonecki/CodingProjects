import java.util.HashMap;
import java.util.Random;

public class Solution {
    public static class Node {
        private int value;
        private Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node insert(Node root, int value) {
        Random random = new Random();
        Node node = root;
        
        if (node == null) {
            return new Node(value);
        }

        while (node != null) {
            if (random.nextBoolean()) {
                if (node.left == null) {
                    node.left = new Node(value);
                    node = null;
                }
                else {
                    node = node.left;
                }
            }
            else {
                if (node.right == null) {
                    node.right = new Node(value);
                    node = null;
                }
                else {
                    node = node.right;
                }  
            }
        }

        return root;
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

        if (!map.isEmpty()) {
            // start the root as the new root.
            count += numPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
        }

        return count;
    }

    public static void main(String [] args) {
        Node root = insert(null, 10);
        root = insert(root, 5);
        root = insert(root, 3);
        root = insert(root, 5);
        root = insert(root, 5);

        System.out.println(numPathsWithSum(root, 10));
    }
}