import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class ivq4 <T extends Comparable<T>> {

    private Node<T> mRoot;

    private class Node<T extends Comparable<T>> {
        private T value = null;
        private Node<T> left;
        private Node<T> right;
        private int count = 1;

        public Node(T value) {
            this.value = value;
        }
    }

    public void insert(T value) {
        if (value == null) {
            return;
        }

        this.mRoot = this.insert(this.mRoot, value);
    }

    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<T>(value);
        }

        if (root.value.compareTo(value) < 0) {
            root.right = this.insert(root.right, value);
        }
        else if (root.value.compareTo(value) > 0) {
            root.left = this.insert(root.left, value);
        }

        return root;
    }

    public int size() {
        return 0;
    }

    public List<LinkedList<T>> valuesAtDepths() {
        List<LinkedList<Node<T>>> nodeList = null;
        List<LinkedList<T>> valueList = new ArrayList<LinkedList<T>>(); 

        nodeList = this.nodesAtDepths(this.mRoot);

        for (LinkedList<Node<T>> listOfNodes : nodeList) {
            LinkedList<T> listOfValues = new LinkedList<T>();

            for (Node<T> node : listOfNodes) {
                listOfValues.add(node.value);
            }

            valueList.add(listOfValues);
        }

        return valueList;
    }

    private List<LinkedList<Node<T>>> nodesAtDepths(Node<T> root) {
        List<LinkedList<Node<T>>> listOfLinkedNodes = new ArrayList<LinkedList<Node<T>>>();        

        if (root != null) {
            LinkedList<Node<T>> rootList = new LinkedList<Node<T>>();
            rootList.add(root);
            listOfLinkedNodes.add(rootList);
        }

        int length = listOfLinkedNodes.size();
        for (int index = 0; index < length; index++) {
            LinkedList<Node<T>> nodeList = listOfLinkedNodes.get(index);

            for (Node<T> node : nodeList) {
                if (node.left != null || node.right != null) {
                    if ((length - 1) == index) {
                        listOfLinkedNodes.add(new LinkedList<Node<T>>());
                        length = listOfLinkedNodes.size();
                    }

                    if (node.left != null) {
                        listOfLinkedNodes.get(index + 1).add(node.left);
                    }
                    if (node.right != null) {
                        listOfLinkedNodes.get(index + 1).add(node.right);
                    }
                }
            }
        }

        return listOfLinkedNodes;
    }

    public static void main(String [] args) {
        ivq4<Integer> bstTree = new ivq4<Integer>();

        bstTree.insert(5);
        bstTree.insert(6);
        bstTree.insert(1);
        bstTree.insert(2);
        bstTree.insert(0);
        bstTree.insert(14);
        bstTree.insert(-1);
        bstTree.insert(3);
        bstTree.insert(9);
        bstTree.insert(7);

        for (LinkedList<Integer> linkListOfNums : bstTree.valuesAtDepths()) {
            for (Integer integer : linkListOfNums) {
                System.out.print(integer + " ");
            }
            System.out.println("");
        }   
    }
}