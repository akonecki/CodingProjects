public class Solution {

    public static class Node {
        private int value;
        private Node left, right;
        private int height = 1;

        public Node(int value) {
            this.value = value;
        }

        public int getHeight() {
            return this.height;
        }
    }

    public static Node createMinimalTree(int [] data) {
        return createMinimalTree(data, 0, data.length - 1);
    }

    // O(N) for run time complexity.  Must visit each node once.
    // In terms of stack space height will be O(lgN), since the creation will
    // be a balanced tree since always going from the middle index for creation
    // of a new element.
    private static Node createMinimalTree(int [] data, int low, int high) {
        Node root = null;

        if (low <= high && low >= 0 && high < data.length) {    
            int index = (high - low) / 2 + low;

            root = new Node(data[index]);
            root.left = createMinimalTree(data, low, index - 1);
            root.right = createMinimalTree(data, index + 1, high);
            root.height += Math.max(root.left != null ? root.left.height : 0, root.right != null ? root.right.height : 0);
        }

        return root;
    }

    public static void main(String [] args) {
        assert (createMinimalTree(new int [] {1}).getHeight() == 1);
        assert (createMinimalTree(new int [] {1,2}).getHeight() == 2);
        assert (createMinimalTree(new int [] {1,2,3}).getHeight() == 2);
        assert (createMinimalTree(new int [] {1,2,3,4}).getHeight() == 3);
        assert (createMinimalTree(new int [] {1,2,3,4,5}).getHeight() == 3);
        assert (createMinimalTree(new int [] {1,2,3,4,5,6}).getHeight() == 3);
        assert (createMinimalTree(new int [] {1,2,3,4,5,6,7}).getHeight() == 3);
        assert (createMinimalTree(new int [] {1,2,3,4,5,6,7,8}).getHeight() == 4);
    }
}