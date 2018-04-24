public class ivq5 <T extends Comparable <T>> {
    private Node<T> mRoot = null;

    private class Node<T extends Comparable <T>> {
        private T value = null;
        private Node<T> left = null;
        private Node<T> right = null;

        public Node(T value) {
            this.value = value;
        }
    }

    public boolean isBST() {
        return this.isBST(this.mRoot, null, null);
    }

    private boolean isBST(Node<T> root, T min, T max) {
        boolean value = true;

        if (root == null) {
            return true;
        }

        assert(root.value != null);

        if (min == null || max == null) {
            min = root.value;
            max = root.value;
            assert (root.equals(this.mRoot));
        }

        if (root.value.compareTo(min) < 0 || root.value.compareTo(max) > 0) {
            return false;
        }

        if (root.left != null) {
            assert(root.left.value != null);

            if (root.left.value.compareTo(root.value) > 0) {
                return false;
            }
            else {
                value = value && this.isBST(root.left, root.left.value, root.value);
            }
        }

        if (root.right != null) {
            assert(root.right.value != null);

            if (root.right.value.compareTo(root.value) < 0) {
                return false;
            }
            else {
                value = value && this.isBST(root.right, root.value, root.right.value);
            }
        }

        return value;
    }
}