# Chapter 4 - Trees & Graphs
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 4.

## Problems
### Problem 1
#### Problem Statement
>
Implement a function to check if a binary tree is balanced.  For the purposes of this question a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.

#### Problem Discussion
- BFS search is likely,  want to examine if all sub-trees w.r.t a root are even withouth having to go down every path.
- need to handle differences of height by one

#### Problem Design
- BFS traversal of the tree uses a queue data structure.
- the nodes that are enqueued will need to indicate the level of the tree they are in.
- when a null is detected then that means the max height is either the current level or plus one.
- a violation of this rule will result in a false evaluation and can be returned immediately.

#### Problem Issues
- efficient bit manipulation for handling to know the limit took more time than the entire problem combined, so next time make a function call for the calculation.
- since was also pushing null nodes onto the queue logic for non-balanced tree must also check to see if the current count is above the limit and the node is not null.
- could change to not push null nodes at all but requires different logic.
- still O(N) time since might have to touch all nodes.
- Space is O(2^(minheight + 1) to 2N) thus O(N).

#### Problem Solution
- Solution implements a recursive implementation that performs a DFS implementation comparing depths of left and right.
- if the previous is not balanced then checks are performed and handled to prevent having to go through the whole tree.
- stack space is based on the height.
- Queue implementation may be more appropriate depnding on the data set (i.e. large sets shifts across the root.)

### Problem 2
#### Problem Statement
>
Given a directed graph, design an algorithm to find out whether there is a route between two nodes.

#### Problem Discussion
- sounds like a union problem, where as long as you can perform the connection information you can perform the method of isConnected between two points to know if they are actually connected.
- don't know what the data looks like however, single tree, list of elements and their relationships?
- maybe data is like this
  - Node 1 -> (Node 2, Node 3) :: Node 2 -> (Node 5) :: ...
  - So this would be that Node 1 is connected say Node 5, but Node 5 is not connected to Node 1 (no upward link)
- So will need to search for the major node, once the major node is found then perform a search from that point.
- there is no indication of there being only two nodes per one or if there are circular paths
- nodes will likely need to indcate that they have been visited

#### Problem Design
- Node definition will likely need to support the following.
  - T value
  - ListArray<Node <T>> nodes
  - boolean visited
- First need to be able to perform a search to find the major node.
- If the node is not found then no path can ever exist.
- If found then need to perform a search from the node to the minor node.

#### Problem Issues

#### Problem Solution

