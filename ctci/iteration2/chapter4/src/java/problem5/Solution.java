import java.util.Comparator;
import java.util.Stack;
import java.util.Random;

public class Solution {
    public final static int ORDERED = 1;
    public final static int RANDOM = 2;
    public final static int REVERSE_ORDERED = 3;

    private static class Node <T extends Comparable<T>> {
        private T data;
        private Node<T> left, right;

        public Node(T data) {
            this.data = data;
        }
    }

    public static <T extends Comparable<T>> Node<T> insert(Node<T> root, T data, final int type) {
        switch (type) {
            case ORDERED:
                return insertOrdered(root, data);
            case RANDOM:
                return insertRandom(root, data);
            case REVERSE_ORDERED:
                return insertRevOrdered(root, data);
            default:
                return root;
        }
    }

    private static <T extends Comparable<T>> Node<T> insertOrdered(Node<T> root, T data) {
        Node<T> node = root;

        if (root == null) {
            return new Node<T>(data);
        }

        while (node != null) {
            if (node.data.compareTo(data) < 0) {
                // go right
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    node.right = new Node<T>(data);
                    return root;
                }
            }
            else {
                // go left
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node.left = new Node<T>(data);
                    return root;
                }
            }
        }

        return root;
    }

    private static <T extends Comparable<T>> Node<T> insertRevOrdered(Node<T> root, T data) {
        Node<T> node = root;

        if (root == null) {
            return new Node<T>(data);
        }       
        
        while (node != null) {
            if (new Random().nextBoolean()) {
                // go right
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    node.right = new Node<T>(data);
                    return root;
                }
            }
            else {
                // go left
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node.left = new Node<T>(data);
                    return root;
                }
            }
        }

        return root;
    }

    private static <T extends Comparable<T>> Node<T> insertRandom(Node<T> root, T data) {
        Node<T> node = root;

        if (root == null) {
            return new Node<T>(data);
        }

        while (node != null) {
            if (node.data.compareTo(data) >= 0) {
                // go right
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    node.right = new Node<T>(data);
                    return root;
                }
            }
            else {
                // go left
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node.left = new Node<T>(data);
                    return root;
                }
            }
        }   

        return root;
    }

    public static <T extends Comparable<T>> boolean isValidBST(Node<T> root) {
        Stack<Node<T>> branches = new Stack<Node<T>>();
        Node<T> prev = null, node = root;

        while (!branches.isEmpty() || node != null) {
            if (node != null) {
                // go as left as possible.

                if (node.left == null) {
                    //System.out.print(node.data + " ");
                    // is the left most node
                    if (prev != null && prev.data.compareTo(node.data) > 0) {
                        return false;
                    }
                    prev = node;
                }
                branches.push(node);
                node = node.left;
            } 
            else {
                node = branches.pop();
                // do something with the root
                //System.out.print(node.data + " ");

                if (prev != null && prev.data.compareTo(node.data) > 0) {
                    return false;
                }
                prev = node;

                // then go right
                node = node.right;
            }
        }

        return true;
    }

    public static void main(String [] args) {   
        Node<Integer> orderedRoot = null;
        Node<Integer> randomRoot = null;
        Node<Integer> revOrderedRoot = null;

        for (int i = 0; i < 100; i++) {
            orderedRoot = insert(orderedRoot, i, ORDERED);
            randomRoot = insert(randomRoot, i, RANDOM);
            revOrderedRoot = insert(revOrderedRoot, i, REVERSE_ORDERED);
        }

        assert (isValidBST(orderedRoot));
        assert (!isValidBST(randomRoot));
        assert (!isValidBST(revOrderedRoot));
    } 
}