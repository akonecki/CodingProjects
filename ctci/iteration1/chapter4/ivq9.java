import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class ivq9 {

    private Node mRoot = null;

    public class Node {
        private int value = 0;
        private Node left = null;
        private Node right = null;

        public Node(int value) {
            this.value = value;
        }
    }

    public void insert(int value) {
        this.mRoot = this.insert(this.mRoot, value);
    }

    private Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        Random random = new Random();
        if (random.ints(1, 0, 2).toArray()[0] == 0) {
            root.left = this.insert(root.left, value);
        }
        else {
            root.right = this.insert(root.right, value);
        }

        return root;
    }

    public void findSum(int sum) {
        List<Integer> levelValues = new ArrayList<Integer>();
        this.findSum(this.mRoot, levelValues, sum);
    }

    private void findSum(Node root, List<Integer> levelValues, int sum) {
        if (root == null) {
            return;
        }

        levelValues.add(root.value);
        int total = 0;
        for (int index = levelValues.size(); index > 0; index--) {
            total += levelValues.get(index - 1);
            if (total == sum) {
                this.printValues(levelValues, index - 1);
            }
        }

        this.findSum(root.left, levelValues, sum);
        this.findSum(root.right, levelValues, sum);

        // Remove the last element from the list.
        levelValues.remove(levelValues.size() - 1);
    }   

    private void printValues(List<Integer> levelValues, int toIndex) {
        for (int index = levelValues.size(); index > toIndex; index--) {
            System.out.print(levelValues.get(index - 1) + " ");
        }
        System.out.println("");
    }

    public static void main(String [] args) {
        ivq9 tree = new ivq9();

        Random random = new Random();

        for (int number : random.ints(10000, -100, 101).toArray()) {
            tree.insert(number);
        }

        tree.findSum(33);
    }
}