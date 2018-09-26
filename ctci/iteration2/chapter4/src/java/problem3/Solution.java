import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;

public class Solution {
    private static class Node<T> {
        private T data;
        private Node<T> left, right;

        public Node(T data) {
            this.data = data;
        }
    }

    public static <T> Node<T> insert(Node<T> root, T data) {
        Node<T> node = root;

        if (root == null) {
            return new Node<T>(data);
        }

        while (node != null) {
            // randomly select to go left or right.
            if (new Random().nextBoolean()) {
                // go left
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node.left = new Node<T>(data);
                    node = null;
                }
            }
            else {
                // go right
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    node.right = new Node<T>(data);
                    node = null;
                }
            }
        }

        return root;
    }

    public static <T> ArrayList<Node<T>> traversal(Node<T> root) {
        ArrayList<Node<T>> result = new ArrayList<Node<T>>();
        Queue<Node<T>> branches = new LinkedList<Node<T>>();
        branches.add(root);

        while (!branches.isEmpty()) {
            Node<T> node = branches.remove();

            if (node != null) {
                System.out.print(node.data + " ");
                result.add(node);
                if (node.left != null) {
                    branches.add(node.left);
                }
                if (node.right != null) {
                    branches.add(node.right);
                }
            }
        }

        System.out.println("");
        return result;
    }

    public static <T> LinkedList<LinkedList<Node<T>>> listOfDepths(Node<T> root) {
        Queue<Node<T>> currentLevel = new LinkedList<Node<T>>();
        Queue<Node<T>> nextLevel = new LinkedList<Node<T>>();
        LinkedList<LinkedList<Node<T>>> result = new LinkedList<LinkedList<Node<T>>>();
        LinkedList<Node<T>> store = new LinkedList<Node<T>>();

        nextLevel.add(root);

        while (!currentLevel.isEmpty() || !nextLevel.isEmpty()) {
            if (!currentLevel.isEmpty()) {
                Node<T> node = currentLevel.remove();

                if (node != null) {

                }
            }
            else {
                if (!store.isEmpty()) {
                    result.add(store);
                    store = new LinkedList<Node<T>>();
                }
                
                currentLevel = nextLevel;
                nextLevel = new LinkedList<Node<T>>();
            }
        }

        return result;
    }

    public static <T> boolean check(LinkedList<LinkedList<Node<T>>> listOfLevelNodes, ArrayList<Node<T>> data) {
        int dataIndex = 0;

        for (LinkedList<Node<T>> nodesAtLevel : listOfLevelNodes) {
            for (Node<T> node : nodesAtLevel) {
                if (data.get(dataIndex) != node) {
                    return false;
                }
                dataIndex++;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        Node<Integer> root = null;

        for (int i = 0; i < 100; i++) {
            root = insert(root, i);
        }

        assert (check(listOfDepths(root), traversal(root)));
    }    
}