import java.io.*;
import java.util.*;

public class Challenge18 {
    
    public static class Node {
        private int value = 0;
        private int index = -1;
        private Node left = null;
        private Node right = null;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static Node buildMaxTree(int [] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        } 

        return buildMaxTree(nums, 0, nums.length - 1);
    }

    private static Node buildMaxTree(int [] nums, int lowIndex, int highIndex) {
        if (lowIndex <= highIndex) {
            int index = findMaxIndex(nums, lowIndex, highIndex);

            if (index < lowIndex || index > highIndex) {
                return null;
            }
            else {
                Node root = new Node(nums[index], index);
                root.left = buildMaxTree(nums, lowIndex, index - 1);
                root.right = buildMaxTree(nums, index + 1, highIndex);

                assert (root.left != null ? (root.value >= root.left.value && root.index > root.left.index) : true);
                assert (root.right != null ? (root.value >= root.right.value && root.index < root.right.index) : true);
                
                return root;
            }
        }
        else {
            return null;
        }
    }

    private static int findMaxIndex(int [] nums, int lowIndex, int highIndex) {
        int max = nums[lowIndex];
        int maxIndex = lowIndex;

        if (lowIndex > highIndex) {
            return -1;
        }

        for (int index = lowIndex; index <= highIndex; index++) {
            if (nums[index] > max) {
                max = nums[index];
                maxIndex = index;
            }
        }

        assert (maxIndex >= lowIndex);
        assert (maxIndex <= highIndex);

        return maxIndex;
    }

    public static void main(String [] args) {
        int [] nums = {1, 6, 8, 9, 7, 6, 4, 3, 2, 1, 7, 7, 7, 3, 1, 0, 9};

        buildMaxTree(nums);
    }
}