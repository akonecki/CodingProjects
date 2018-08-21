public class ivq8 {

    private Node root = null;

    public class Node {
        private int leftCount = 0;
        private int rightCount = 0;
        private int value = 0;
        private Node left = null;
        private Node right = null;
    
        public Node(int value) {
            this.value = value;
        }

        public int rank() {
            // obtain the rank for a specific node
            return leftCount;
        }
    }

    public void insert(int value) {
        this.root = insert(this.root, value);
    }

    public int getNodeCount() {
        if (this.root == null) {
            return 0;
        }
        else {
            return this.root.leftCount + this.root.rightCount + 1;
        }
    }

    private Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        else {
            if (value <= root.value) {
                // strictly less
                root.leftCount++;
                root.left = insert(root.left, value);
            }
            else {
                // greater or equal
                root.rightCount++;
                root.right = insert(root.right, value);
            }

            return root;
        }
    }

    private static int countSmallerThan(Node root, int value) {
        if (root == null) {
            return 0;
        }
        else {
            if (root.value > value) {
                // going left
                return countSmallerThan(root.left, value);
            }
            else if (root.value < value) {
                // going right
                return countSmallerThan(root.right, value) + root.leftCount + 1;
            }
            else {
                return root.leftCount;
            }
        }
    }

    public int rank(int value) {
        return countSmallerThan(this.root, value);
    }

    public static void main(String [] args) {
        ivq8 mSolution = new ivq8();

        for (int num : new int [] {5, 1, 4, 4, 5, 9, 7, 13, 3}) {
            mSolution.insert(num);
        }

        System.out.println(mSolution.getNodeCount());

        System.out.println(mSolution.rank(1));
        assert (mSolution.rank(1) == 0);
        System.out.println(mSolution.rank(3));
        assert (mSolution.rank(3) == 1);
        System.out.println(mSolution.rank(4));
        assert (mSolution.rank(4) == 3);
        assert (mSolution.rank(12) == 8);
    }
}