import java.util.Comparator;
import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;

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
        ArrayList<T> data = new ArrayList<>();
        inOrderTraversal(root, data);
     
        // will check the list for correctness
        // O(N) Memory with O(N) time complexity
        for (int index = 1; index < data.size(); index++) {
            if (data.get(index).compareTo(data.get(index - 1)) < 0) {
                return false;
            }
        }

        // 
        boolean result = inOrderTraversalWithCompare(root, new ArrayList<T>(1));

        // serial solution
        // O(H) for memory O(N) for runtime
        Stack<Node<T>> branchStack = new Stack<>();
        Node<T> node = root;
        ArrayList<T> list = new ArrayList<T>(1);

        while (!branchStack.isEmpty() || node != null) {
            if (node != null) {
                // go left as much as possible
                branchStack.push(node);
                node = node.left;
            }
            else {
                // |-----------------------|
                // |     Node(10)          |
                // |-----------------------|
                // |     Node(5)           |
                // |-----------------------|
                // |     Node(1)           |
                // |-----------------------|

                // |-----------------------|
                // |     Node(10)          |
                // |-----------------------|
                // |     Node(7)           |
                // |-----------------------|

                // |-----------------------|
                // |     Node(12)          |
                // |-----------------------|

                // pop off parent
                node = branchStack.pop();

                // compare
                if (list.isEmpty()) {
                    // just add it
                    list.add(node.data);
                }
                else {
                    if (node.data.compareTo(list.get(0)) < 0) {
                        return false;
                    }
                    else {
                        // constant memory storage  
                        // below it was result.set(0, root.data);
                        list.set(0, node.data);
                    }
                }

                // then go right
                node = node.right;
            }
        }

        return true;
    }

    // is just the in order traversal
    private static <T extends Comparable<T>> boolean inOrderTraversal(Node<T> root, ArrayList<T> result) {
        // Base case if null cant really say anything about so just return true;
        if (root == null) {
            return true;
        }

        // go left
        inOrderTraversal(root.left, result);

        // process root
        result.add(root.data);

        // go right
        inOrderTraversal(root.right, result);

        return true;
    }

    // O(H + C) for memory O(N) for run time

    //          Stack
    // |-----------------------|
    // |     Node(10)          |
    // |     result            |
    // |-----------------------|
    // |     Node(5)           |
    // |     result            |
    // |-----------------------|
    // |     Node(1)           |
    // |     result            |
    // |-----------------------|

    // null case / base case return true, & pop off the stack & restore
    // |-----------------------|
    // |     Node(10)          |
    // |     result            |
    // |-----------------------|
    // |     Node(5)           |
    // |     result            |
    // |-----------------------|

    // do compare
    // go right
    // |-----------------------|
    // |     Node(10)          |
    // |     result            |
    // |-----------------------|
    // |     Node(5)           |
    // |     result            |
    // |-----------------------|
    // |     Node(1)           |
    // |     result            |
    // |-----------------------|
    
    // null case / base case return true & pop off the stack & restore
    // |-----------------------|
    // |     Node(10)          |
    // |     result            |
    // |-----------------------|
    // |     Node(5)           |
    // |     result            |
    // |-----------------------|

    // return of 1 is true & restore 5
    // |-----------------------|
    // |     Node(10)          |
    // |     result            |
    // |-----------------------|

    // do comparison if true then go right
    // go right on node 5
    // |-----------------------|
    // |     Node(10)          |
    // |     result            |
    // |-----------------------|
    // |     Node(5)           |
    // |     result            |
    // |-----------------------|

    // |-----------------------|
    // |     Node(10)          |
    // |     result            |
    // |-----------------------|
    // |     Node(5)           |
    // |     result            |
    // |-----------------------|
    // |     Node(7)           |
    // |     result            |
    // |-----------------------|

    // Node(10), {}
    //    -> Node(5), {}
    //         -> Node(1), {}
    //              -> Node(null), {}
    //              <- true
    //         {1}
    //              -> Node(null), {1}
    //              <- true
    //          <- true with {1}
    //    {5}
    //         -> Node(7), {5}
    //              -> Node(null), {}
    //              <- true
    //         {7}
    //              -> Node(null), {}
    //              <- true
    //    <- true
    // {10}
    //    -> Node(12), {10}
    //         -> Node(null), {10}
    //         <- true
    //    {12}
    //         -> Node(null), {12}
    //         <- true
    // <- true
    private static <T extends Comparable<T>> boolean inOrderTraversalWithCompare(Node<T> root, ArrayList<T> result) {
        // Base case if null cant really say anything about so just return true;
        if (root == null) {
            return true;
        }

        // go left
        if (!inOrderTraversalWithCompare(root.left, result)) {
            return false;
        }

        // process root
        if (result.isEmpty()) {
            // nothing to compare to, just add it to the list.
            result.add(root.data);
        }
        else {
            // result has an entry, need to compare and overwrite

            // compare
            if (root.data.compareTo(result.get(0)) < 0) {
                return false;
            }
            else {
                // overwrite on false, it is still a valid BST.
                result.set(0, root.data);
            }
        }

        // go right and return its result
        return inOrderTraversalWithCompare(root.right, result);
    }

    private static <T extends Comparable<T>> boolean inOrderTraversal(Node<T> root, T min, T max) {
        
    }

    public static void main(String [] args) {
        
        
    }
}

/*

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
*/