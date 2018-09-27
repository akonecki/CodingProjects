import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Solution {
    public static class Node {
        private int value;
        private Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node insert(Node root, int value) {
        Node node = root;

        if (root == null) {
            return new Node(value);
        }

        while (node != null) {
            if (node.value >= value) {  
                if (node.left != null) {  
                    node = node.left;
                }
                else {
                    node.left = new Node(value);
                    node = null;
                }
            }
            else {
                if (node.right != null) {  
                    node = node.right;
                }
                else {
                    node.right = new Node(value);
                    node = null;
                }
            }
        }

        return root;
    }

    //     5
    //   3   7
    //  1 4 6 8
    // (5,3,1,4,7,6,8), (5,3,4,1,7,6,8), ()
    public static List<List<Integer>> getBSTSequences(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> left = getBSTSequences(root.left);
        List<List<Integer>> right = getBSTSequences(root.right);
        return listPermutations(root.value, left, right);
    }

    private static List<List<Integer>> listPermutations(int head, List<List<Integer>> l1, List<List<Integer>> l2) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> base = new ArrayList<>();
        base.add(head);

        if (l1.isEmpty() && l2.isEmpty()) {
            result.add(base);
        }
        else if (l1.isEmpty()) {
            for (List<Integer> list2 : l2) {
                List<Integer> value = new ArrayList<>(base);
                value.addAll(list2);
                result.add(value);   
            }
        }
        else if (l2.isEmpty()) {
            for (List<Integer> list1 : l1) {
                List<Integer> value = new ArrayList<>(base);
                value.addAll(list1);
                result.add(value);   
            }
        }
        else {
            for (List<Integer> list1 : l1) {
                for (List<Integer> list2 : l2) {
                    permutation(list1, 0, list2, 0, base, result);   
                }
            }
        }
        
        return result;
    }

    public static List<List<Integer>> permutation(List<Integer> l1, List<Integer> l2) {
        List<List<Integer>> result = new ArrayList<>();
        permutation(l1, 0, l2, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private static void permutation(
        List<Integer> l1, int index1, 
        List<Integer> l2, int index2, 
        List<Integer> current, List<List<Integer>> result) 
    {
        if (index1 >= l1.size() && index2 >= l2.size()) {
            // no more processing left
            result.add (new ArrayList<Integer> (current));
            return;
        }

        if (index1 < l1.size()) {
            current.add(l1.get(index1));
            permutation(l1, index1 + 1, l2, index2, current, result);
            // remove the last one
            current.remove(current.size() - 1);
        }

        // pull from l2
        if (index2 < l2.size()) {
            current.add(l2.get(index2));
            permutation(l1, index1, l2, index2 + 1, current, result);
            // remove the last one
            current.remove(current.size() - 1);
        }

        return;
    }

    public static void main(String [] args) {
        Node root = null;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            root = insert(root, random.nextInt(1000));
        }

        for (List<Integer> list : getBSTSequences(root)) {
            System.out.print("(");
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println(")");    
        }

        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        List<List<Integer>> list1 = new ArrayList<>();
        List<List<Integer>> list2 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l2.add(10);
        l2.add(11);

        list1.add(l1);
        list2.add(l2);

        for (List<Integer> list : listPermutations(0, list1, list2)) {
            System.out.print("(");
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println(")");
        }

        for (List<Integer> list : permutation(l1, l2)) {
            System.out.print("(");
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println(")");
        }
    }
}