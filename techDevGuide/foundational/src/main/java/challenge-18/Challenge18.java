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

        Stack<Node> stack = new Stack<Node>();

        int index = 0;
        for (int num : nums) {
            Node newNode = new Node(num, index);

            if (stack.isEmpty()) {
                stack.push(newNode);
            }
            else if (stack.peek().value >= newNode.value) {
                stack.push(newNode);
            }
            else {
                Node prev = null;

                while (!stack.isEmpty() && stack.peek().value < newNode.value) {
                    if (prev == null) {
                        prev = stack.pop();
                    }
                    else {
                        Node temp = stack.pop();
                        temp.right = prev;
                        prev = temp;
                    }
                }

                newNode.left = prev;
                stack.push(newNode);
            }
        }

        Node prev = !stack.isEmpty() ? stack.pop() : null;
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            temp.right = prev;
            prev = temp;
        }

        return prev;
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

    public static ArrayList<Integer> inorderTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        inorderTraversal(root, result);
        return result;
    }

    private static void inorderTraversal(Node root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        else {
            inorderTraversal(root.left, result);
            result.add(root.value);
            inorderTraversal(root.right, result);
        }
    }

    public static boolean isEqual(ArrayList<Integer> nums1, int [] nums2) {
        if (nums1.size() != nums2.length) {
            return false;
        }
        
        for (int index = 0; index < nums1.size(); index++) {
            if (nums1.get(index).intValue() != nums2[index]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        int [] nums = {1, 6, 8, 9, 7, 6, 4, 3, 2, 1, 7, 7, 7, 3, 1, 0, 9};

        assert (isEqual(inorderTraversal(buildMaxTree(nums)), nums));
    }
}