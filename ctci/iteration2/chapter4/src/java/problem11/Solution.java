import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public class BinaryTree <T> {
        private ArrayList<Node<T>> listOfNodes = new ArrayList<>();
        private HashMap<Node<T>, Integer> nodeToIndexMap = new HashMap<>();
        private Node<T> root = null;

        public class Node<T> {
            private T data;
            private Node<T> left, right, parent;

            public Node (T data) {
                this.data = data;
            }
        }

        public void insert(T data) {
            this.root = insert(this.root, data);
        }

        private Node<T> insert(Node<T> root, T data) {
            Node<T> node = root;
            Random random = new Random();
            Node<T> newNode = new Node<T>(data);
            this.listOfNodes.add(newNode);
            this.nodeToIndexMap.put(newNode, this.listOfNodes.size() - 1);

            if (root == null) {
                return newNode;
            }
            
            while (node != null) {
                if (node != null) {
                    if (random.nextBoolean()) {
                        // go left
                        if (node.left != null) {
                            node = node.left;
                        }
                        else {
                            node.left = newNode;
                            node.left.parent = node;
                            node = null;
                        }
                    }
                    else {
                        // go right
                        if (node.right != null) {
                            node = node.right;
                        }
                        else {
                            node.right = newNode;
                            node.right.parent = node;
                            node = null;
                        }   
                    }
                }
            }
    
            return root;
        }

        public Node<T> find(T data) {
            // Find the first occurrence.
            return find(this.root, data);
        }

        private Node<T> find(Node<T> root, T data) {
            Stack<Node<T>> stack = new Stack<>();
            Node<T> node = root;
    
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    if (node.data.equals(data)) {
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

        public void delete(Node<T> nodeToDelete) {
            this.root = delete(this.root, nodeToDelete);
        }

        private void swap(int index1, int index2) {
            Node<T> node = null;

            node = this.listOfNodes.get(index1);
            this.listOfNodes.set(index1, this.listOfNodes.get(index2));
            this.listOfNodes.set(index2, node);
        }

        private Node<T> delete(Node<T> root, Node<T> nodeToDelete) {
            Random random = new Random();
            Node<T> node = nodeToDelete;

            if (!nodeToIndexMap.containsKey(nodeToDelete)) {
                // Tree does not contain the node to delete so just return.
                return root;
            }

            // Go ahead and remove from mapping and array now.
            int index = nodeToIndexMap.get(nodeToDelete);
            swap(index, listOfNodes.size() - 1);
            listOfNodes.remove(listOfNodes.size() - 1);
            nodeToIndexMap.remove(nodeToDelete);

            // Update hash for the post swap.
            nodeToIndexMap.put(listOfNodes.get(index), index);

            if (node.left != null && node.right != null) {
                if (random.nextBoolean()) {
                    // get the right most child of the left
                    node = node.left;

                    while (node != null && node.right != null) {
                        node = node.right;
                    }

                    // node is now guaranteed to not have any right children.
                    // it might have left

                    node.parent.right = node.left;
                    if (node.left != null) {
                        // left might be null
                        node.left.parent = node.parent;
                    }

                    // null out the node's pointers since it is moving.
                    node.parent = null;
                    node.left = null;

                    if (nodeToDelete.parent != null) {
                        if (nodeToDelete.parent.right == nodeToDelete) {
                            // right
                            nodeToDelete.parent.right = node;
                        }
                        else {
                            // left
                            nodeToDelete.parent.left = node;
                        }
                    } 
                    else {
                        root = node;
                    }

                    node.parent = nodeToDelete.parent;
                    node.left = nodeToDelete.left;
                    node.right = nodeToDelete.right;
                }
                else {
                    // get the left most child of the right
                    node = node.right;

                    while (node != null && node.left != null) {
                        node = node.left;
                    }

                    // node is now guaranteed to not have any left children.
                    // it might have right

                    node.parent.left = node.right;
                    if (node.right != null) {
                        // right might be null
                        node.right.parent = node.parent;
                    }

                    // null out the node's pointers since it is moving.
                    node.parent = null;
                    node.right = null;

                    if (nodeToDelete.parent != null) {
                        if (nodeToDelete.parent.right == nodeToDelete) {
                            // right
                            nodeToDelete.parent.right = node;
                        }
                        else {
                            // left
                            nodeToDelete.parent.left = node;
                        }
                    } 
                    else {
                        root = node;
                    }

                    node.parent = nodeToDelete.parent;
                    node.left = nodeToDelete.left;
                    node.right = nodeToDelete.right;
                }
                
            }
            else if (node.left != null) {
                // get the right most child of the left
                node = node.left;

                while (node != null && node.right != null) {
                    node = node.right;
                }

                // node is now guaranteed to not have any right children.
                // it might have left

                node.parent.right = node.left;
                if (node.left != null) {
                    // left might be null
                    node.left.parent = node.parent;
                }

                // null out the node's pointers since it is moving.
                node.parent = null;
                node.left = null;

                if (nodeToDelete.parent != null) {
                    if (nodeToDelete.parent.right == nodeToDelete) {
                        // right
                        nodeToDelete.parent.right = node;
                    }
                    else {
                        // left
                        nodeToDelete.parent.left = node;
                    }
                } 
                else {
                    root = node;
                }

                node.parent = nodeToDelete.parent;
                node.left = nodeToDelete.left;
                node.right = nodeToDelete.right;
            }
            else if (node.right != null) {
                // get the left most child of the right
                node = node.right;

                while (node != null && node.left != null) {
                    node = node.left;
                }

                // node is now guaranteed to not have any left children.
                // it might have right

                node.parent.left = node.right;
                if (node.right != null) {
                    // right might be null
                    node.right.parent = node.parent;
                }

                // null out the node's pointers since it is moving.
                node.parent = null;
                node.right = null;

                if (nodeToDelete.parent != null) {
                    if (nodeToDelete.parent.right == nodeToDelete) {
                        // right
                        nodeToDelete.parent.right = node;
                    }
                    else {
                        // left
                        nodeToDelete.parent.left = node;
                    }
                } 
                else {
                    root = node;
                }

                node.parent = nodeToDelete.parent;
                node.left = nodeToDelete.left;
                node.right = nodeToDelete.right;
            }
            else {
                // both left and right are null
                if (node.parent == null) {
                    root = null;
                }
                else if (node.parent.left == nodeToDelete) {
                    node.parent.left = null;
                }
                else if (node.parent.right == nodeToDelete) {
                    node.parent.right = null;
                }
                else {
                    assert (false);
                }
            }

            return root;
        }

        public Node<T> randomNode() {
            Random random = new Random();

            if (listOfNodes.size() > 0) { 
                return listOfNodes.get(random.nextInt(listOfNodes.size()));
            }
            else {
                return null;
            }
        }
    }
}