public class ivq8 <T> {

    public class Node<T> {
        private T value = null;
        private Node<T> left = null;
        private Node<T> right = null;

        public Node(T value) {
            this.value = value;
        }
    }

    public boolean isIdentical(Node<T> root1, Node<T> root2) {
        if (root1 == roo2) {
            // Address comparison null case, or same node itself.
            return true;
        }
        else if (root1 == null) {
            return false;
        }
        else if (root2 == null) {
            return false;
        }

        if (root1.value.equals(root2.value)) {
            return this.isIdentical(root1.left, root2.left) && 
                this.isIdentical(root1.right, root2.right);
        }
        else {
            return false;
        }
    }

    public boolean isSubTree(Node<T> root1, Node<T> root2) {
        // this assumes that root1 is > root2 otherwise root1 will iterate 
        // through its nodes and could provide in-correct answers.
        // This API has no way to actually check this which is not ideal.
        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.value.equals(root2.value)) {
            if (this.isIdentical(roo1, root2)) {
                // done
                return true;
            }
        }

        return this.isSubTree(root1.left, root2) || this.isSubTree(root1.right, root2);
    }
}