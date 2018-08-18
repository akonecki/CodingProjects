import java.io.*;
import java.util.*;

public class Challenge16 {
    private static class Node {
        
        int key;
        Node left;
        Node right;
        Node parent;
        
        Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }
  
    // Node findMinKeyWithinTree(Node input) 
    Node findInOrderSuccessor(Node inputNode) {
        // your code goes here 
        if (inputNode == null) {
            return null;
        }
        else {
            // sucessor when the inputNode has a right child
            if (inputNode.right != null) {
            // go to the right
            // then go to the left
            Node node = inputNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
            }
            else {
            // when successor has no right child
            // go up to parent
            // check back to child just left
            // if child is the right of parent, keep going up
            // if child is to the left of parent, return parent
            Node prev = inputNode;
            Node node = inputNode.parent;
            
            while (node != null) {
                if (node.right != null && node.right.equals(prev)) {
                prev = node;
                node = node.parent;
                }
                else {
                return node;
                }
            }
            
            return null;  
            }
        }
    }
}