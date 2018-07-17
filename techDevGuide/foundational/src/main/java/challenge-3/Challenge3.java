import java.io.*;
import java.util.*;

class Solution {
 
  static class Node {
      
    int cost;
    Node[] children;
    Node parent;

    Node(int cost) {
      this.cost = cost;
      children = null;
      parent = null;
    }
  }

  public static class SalesPath {
        
    public static int getCheapestCost(Node rootNode) {
      // your code goes here
      int minPath = -1;
      Stack<TreeNode> branches = new Stack<TreeNode>();
      
      if (rootNode == null) {
        return 0;
      }
      
      branches.push(new TreeNode(rootNode, 0));
      
      while (!branches.isEmpty()) {
        TreeNode branch = branches.pop();
        
        if (branch != null) {
          // cost of previous and the actual node.
          int cost = branch.cost + branch.node.cost;
          
          if (branch.node.children == null || branch.node.children.length == 0) {
            // leaf node of the branch.
            if (minPath == -1 || cost < minPath) {
              // only update the overall minPath cost if less at the leaf
              minPath = cost;
            }
            
            // branch = null;
          }
          else {
            // not a leaf node, see more than one child, need to handle more branches.
            // if (branch.node.children.length > 1) {
              // iterate through the list of children after the first
            for (int index = 0; index < branch.node.children.length; index++) {
              branches.push(new TreeNode(branch.node.children[index], cost));
            }
            // }
            
            // branch = branch.node.children[0];
            // branch.cost = cost;
          }
          
        }
      }
      
      return minPath;
    }
  }
    
  private static class TreeNode {
    private Node node;
    private int cost;
    private int index;
    
    public TreeNode(Node node, int cost) {
      this.node = node;
      this.cost = cost;
    }
  }
  
  
  /*********************************************
   * Driver program to test above method     *
   *********************************************/

  public static void main(String[] args) {
    assert (SalesPath.getCheapestCost(null) == 0);
  }
}