import java.util.Stack;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    private static class Node<T> {
        private T data;
        private Node<T> left, right;

        public Node (T data) {
            this.data = data;
        }
    }

    public static <T> Node<T> firstCommonAncestor(
        Node<T> node1, 
        Node<T> node2, 
        Node<T> root,
        int mode) 
    {
        // check case 1
        Node<T> node = inorderTraversal(node1, node2), prev = null;

        if (node == node1) {
            return node1;
        }

        assert (node == null);

        // check case 2
        node = inorderTraversal(node2, node1);

        if (node == node2) {
            return node2;
        }

        assert (node == null);

        List<Node<T>> result = new ArrayList<Node<T>>(2);
        result.add(null);
        result.add(null);

        Node<T> serial = null, recursive = null;
        System.out.println("Data " + node1.data + " " + node2.data);
        serial = firstCommonAncestor(node1, node2, root);
        firstCommonAncestor(node1, node2, root, result);
        recursive = result.get(0);
        
        assert (serial != null);
        assert (recursive != null);
        System.out.println(recursive.data);
        
        System.out.println(serial.data + " " + recursive.data);
        assert (serial == recursive);
        return serial;
    }

    private static <T> int firstCommonAncestor(
        Node<T> node1, 
        Node<T> node2, 
        Node<T> root,
        List<Node<T>> result)  
    {
        if (root == null) {
            return 0;
        }
        
        if (root == node1) {
            return 1;
        }
        if (root == node2) {
            return 1;
        }

        // go left first
        int left = firstCommonAncestor(node1, node2, root.left, result);

        if (left == 2) {
            return left;
        }

        // go right
        int right = firstCommonAncestor(node1, node2, root.right, result);

        if (right == 2) {
            return right;
        }
        
        if (left + right == 2) {
            result.set(0, root);
            return 2;
        }

        return left + right;
    }

    // A few cases that will need to be watched out for.  This is the non-recursive
    // implementation.  A bit tedious for the 3/4 case.
    // 0 - node1 is node2, it is itself
    // 1 - node1 is the ancestor, meaning that node2 is a child under node1.
    // 2 - node2 is the ancestor, meaning that node1 is a child under node2.
    // 3 - a common parent is the ancestor, meaning both are children
    // 4 - no common ancestor, node1 / node2 dont exist within the BT.
    private static <T> Node<T> firstCommonAncestor(
        Node<T> node1, 
        Node<T> node2, 
        Node<T> root) 
    {
        // check case 3 / 4
        Node<T> node = null, prev = null;
        boolean node1Found = false, node2Found = false;
        Stack<Node<T>> nodeStack = new Stack<Node<T>>();
        Stack<Node<T>> subStack = new Stack<Node<T>>();
        node = root;

        while (!nodeStack.isEmpty() || !subStack.isEmpty() || node != null) {
            if (node != null) {
                System.out.println("node data " + node.data);
                // Dont go down this path any further if found, since was 
                // already explored in the above cases.
                if (node == node1) {
                    System.out.println("found 1 " + node.data);
                    node1Found = true;
                    node = null;
                }
                else if (node == node2) {
                    System.out.println("found 2 " + node.data);
                    node2Found = true;
                    node = null;
                }
                else if (!node1Found && !node2Found) {
                    nodeStack.push(node);
                    node = node.left;
                }
                else {
                    // one is currently found.
                    subStack.push(node);
                    node = node.left;
                }
            }
            else {
                if (node1Found && node2Found) {
                    nodeStack.clear();
                    subStack.clear();
                    System.out.println("returning " + prev.data);
                    return prev;
                }
                else if ((node1Found || node2Found) && !nodeStack.isEmpty() && subStack.isEmpty()) {
                    // one has been found.  That means the current nodestack 
                    // elements one or none will be the common ancestor, since 
                    // they are the root node of the one already found.
                    prev = nodeStack.pop();
                    System.out.println("case " + prev.data);
                    node = prev.right;
                }
                else if (!node1Found && !node2Found && !nodeStack.isEmpty()) {
                    node = nodeStack.pop().right;
                }
                else if (!subStack.isEmpty()) {
                    node = subStack.pop().right;
                }
                else {
                    assert (false);
                }
            }
        }

        // assert (false);
        if (!node1Found || !node2Found) {
            return null;
        }

        return prev;
    }

    private static <T> Node<T> inorderTraversal(Node<T> root, Node<T> lookingFor) {
        Stack<Node<T>> nodeStack = new Stack<Node<T>>();
        Node<T> node = root;
        
        // O(H) memory impact where H is the height of the tree. 
        while (!nodeStack.isEmpty() || node != null) {  
            if (node != null) {
                if (node == lookingFor) {
                    return root;
                }
                nodeStack.push(node);
                node = node.left;
            }
            else {
                node = nodeStack.pop().right;
            }
        }

        return null;
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

    public static <T> Node<T> search(Node<T> root, T data) {
        if (root == null || data == null) {
            return null;
        }

        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> node = root;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                if (node.data.equals(data)) {
                    stack.clear();
                    return node;
                }
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop().right;
            }
        }

        return null;
    }

    public static void print(Node<Integer> root) {
        Queue<Node<Integer>> currentLevel = new LinkedList<Node<Integer>>();
        Queue<Node<Integer>> nextLevel = new LinkedList<Node<Integer>>();
        Node<Integer> node = null;
        nextLevel.add(root);

        while (!currentLevel.isEmpty() || !nextLevel.isEmpty()) {
            if (!currentLevel.isEmpty()) {
                node = currentLevel.remove();
                
                if (node == null) {
                    System.out.print("null ");
                }
                else {
                    System.out.print(node.data + " ");
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                }
            }
            else {
                System.out.print("\n");
                currentLevel = nextLevel;
                nextLevel = new LinkedList<Node<Integer>>();
            }
        }
        System.out.println("");
    }

    public static void main(String [] args) {
        Node<Integer> root = null;
        for (int index = 0; index < 100; index++) {
            root = insert(root, index);
        }

        print(root);

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                assert (firstCommonAncestor(search(root, i), search(root, j), root, 0) != null);   
            }
        }
    }
}