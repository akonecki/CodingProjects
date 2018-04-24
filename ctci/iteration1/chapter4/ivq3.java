import java.lang.Math;
import java.util.Arrays;
import java.lang.StackOverflowError;

public class ivq3 {

    private Node mRoot = null;

    private class Node {
        private final int value;
        private Node left = null;
        private Node right = null;

        public Node(int value) {
            this.value = value;
        }
    }

    public boolean isBST() {
        if (this.mRoot == null) {
            System.out.println("Null root");
            return false;
        }

        return this.isBST(this.mRoot, this.mRoot.value, this.mRoot.value);
    }

    private boolean isBST(Node root, int min, int max) {
        boolean result = true;
        
        if (root == null) {
            return true;
        }

        if (root.value >= min && root.value <= max) {
            if (root.left != null) {
                result = result && this.isBST(root.left, root.left.value, root.value);
            }
            if (root.right != null) {
                result = result && this.isBST(root.right, root.value, root.right.value);
            }

            return result;
        }
        else {
            return false;
        }
    }

    public void createBalanced(int [] sortedArray) {
        // Forces the array to be sorted.
        Arrays.sort(sortedArray);

        for (int number : sortedArray) {
            System.out.print(number + " ");
        }
        System.out.println("");

        this.mRoot = this.createBalancedTree(this.mRoot, sortedArray, 0, sortedArray.length - 1);
    }

    private Node createBalancedTree(Node root, int [] array, int lowIndex, int highIndex) {
        int index;
        
        if (lowIndex < 0 || lowIndex > highIndex || lowIndex >= array.length ||
            highIndex < 0 || highIndex >= array.length) {
            return null;
        }
        
        index = lowIndex + ((highIndex - lowIndex + 1) / 2);

        if (root == null) {
            root = new Node(array[index]);
        }
        else {
            return root;
        }

        if (root != null && lowIndex != highIndex) {
            // left
            root.left = this.createBalancedTree(root.left, array, lowIndex, index - 1);
            //right
            root.right = this.createBalancedTree(root.right, array, index + 1, highIndex);
        }

        return root;
    }

    public boolean isBalanced() {
        return this.isBalanced(this.mRoot);
    }

    private boolean isBalanced(Node root) {
        return (this.treeHeight(root) != -1);
    }

    public int treeHeight() {
        return this.treeHeight();
    }

    private int treeHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int left = this.treeHeight(root.left);
        if (left == -1) {
            return -1;
        }

        int right = this.treeHeight(root.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }

    public static void main(String [] args) {
        int [] numbers = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        ivq3 tree = new ivq3();
        tree.createBalanced(numbers);

        assert (tree.isBST());
        assert (tree.isBalanced());
    }
}