import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class Problem4 {
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    
    public static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        else {
            if (root.value >= value) {
                root.left = insert(root.left, value);
            }
            else {
                root.right = insert(root.right, value);
            }

            return root;
        }
    }

    public static boolean equalBST(Node root1, Node root2) {
        if (root1 == root2) {
            return true;
        }
        else if (root1 == null || root2 == null) {
            return false;
        }
        else {
            if (root1.value != root2.value) {
                return false;
            }
            else {
                return equalBST(root1.left, root2.left) && equalBST(root1.right, root2.right);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> possibleInsertionOrders(Node root) {
        if (root == null) {
            return new ArrayList<ArrayList<Integer>>();
        }
        else {
            ArrayList<ArrayList<Integer>> left = possibleInsertionOrders(root.left);
            ArrayList<ArrayList<Integer>> right = possibleInsertionOrders(root.right);
            
            return permutationOfLists(left, right, root.value);
        }
    }

    private static ArrayList<ArrayList<Integer>> permutationOfLists(
        ArrayList<ArrayList<Integer>> left, 
        ArrayList<ArrayList<Integer>> right,
        int value)
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (left.isEmpty() && right.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(value);
            permutationOfLists(new ArrayList<Integer>(), new ArrayList<Integer>(), 0, 0, temp, result);    
        }
        else if (left.isEmpty()) {
            for (ArrayList<Integer> list : right) {
                ArrayList<Integer> temp = new ArrayList<Integer>(list.size() + 1);
                temp.add(value);
                for (int num : list) {
                    temp.add(num);
                }
                result.add(temp);
            }
        }
        else if (right.isEmpty()) {
            for (ArrayList<Integer> list : left) {
                ArrayList<Integer> temp = new ArrayList<Integer>(list.size() + 1);
                temp.add(value);
                for (int num : list) {
                    temp.add(num);
                }
                result.add(temp);
            }
        }
        else {
            for (int leftIndex = 0; leftIndex < left.size(); leftIndex++) {
                for (int rightIndex = 0; rightIndex < right.size(); rightIndex++) {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(value);
                    permutationOfLists(left.get(leftIndex), right.get(rightIndex), 0, 0, temp, result);
                }
            }
        }

        return result;
    }

    private static void permutationOfLists(
        ArrayList<Integer> left, 
        ArrayList<Integer> right,
        int leftIndex, int rightIndex, ArrayList<Integer> list,
        ArrayList<ArrayList<Integer>> result) 
    {
        if (leftIndex >= left.size() && rightIndex >= right.size()) {
            result.add(list);
            return;
        }
        else {
            if (leftIndex < left.size()) {
                ArrayList<Integer> temp = new ArrayList<Integer>(list);
                temp.add(left.get(leftIndex));
                // include a left
                permutationOfLists(left, right, leftIndex + 1, rightIndex, temp, result);
            }
            if (rightIndex < right.size()) {
                // include a right
                ArrayList<Integer> temp = new ArrayList<Integer>(list);
                temp.add(right.get(rightIndex));
                permutationOfLists(left, right, leftIndex, rightIndex + 1, temp, result);
            }
            return;
        }
    }

    private static Node buildTree(int [] nums) {
        Node root = null;
        for (int num : nums) {
            root = insert(root, num);
        }
        
        return root;
    }

    private static int [] toIntArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() < 1) {
            return new int [0];
        }
        else {
            int [] array = new int [nums.size()];
            for (int index = 0; index < nums.size(); index++) {
                array[index] = nums.get(index).intValue();
            }

            return array;
        }
    }

    private static String buildKey(ArrayList<Integer> nums) {
        StringBuilder sb = new StringBuilder();

        for (int num : nums) {
            sb.append(num).append(' ');
        }

        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String [] args) {
        Node root = buildTree(new int [] {4, 2, 1, 3, 6, 5, 7});
        HashSet<String> unique = new HashSet<String>();

        assert (equalBST(root, root));

        for (ArrayList<Integer> list : possibleInsertionOrders(root)) {
            assert (equalBST(root, buildTree(toIntArray(list))));
            String key = buildKey(list);
            assert (!unique.contains(key));
            
            if (!unique.contains(key)) {
                unique.add(key);
            }
            else {
                System.out.println("will fail on the " + key);
                assert (!unique.contains(key));
            }

            System.out.println("");
            for (int num : list) {
                System.out.print(num + " ");
            }
        }
        System.out.println("");
    }
}