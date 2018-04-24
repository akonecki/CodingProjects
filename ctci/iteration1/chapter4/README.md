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
- Sub-class templated iterator was causing some issues.
- Did not implement the insert (since was not part of the problem).

#### Problem Solution
- Implemented a BFS implementation.
- Implementation completed was DFS

### Problem 3
#### Problem Statement
>
Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.

#### Problem Discussion
- Minimum height is the same as balanced.
- Might not be complete.
- would ideally want to all populated nodes to be left most to be full.
- Very similar to a Min/Max priority queue except the elements are sorted in the array.

#### Problem Design
- the elements are sorted, thus roots of subtrees can be broken down by the halfway points
- the primary root will be the floor (N / 2).
- will not be full since left side will not be filled at the leaves prior to right.
- can do more time by just performing insert for each element individually or can pass the list and build from the root downward since no nodes exist.

#### Problem Issues
- pay close attention to the calculation of index, was not calculating correctly for the right half.
- Added `isBalanced` and `isBST` operations to allow for checks.

#### Problem Solution
- Solution does similar

### Problem 4
#### Problem Statement
>
Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (if you have a tree with depth D, you will have D linked lsits).

#### Problem Discussion
- BFS search where each layer is built from the parent iteration which it of itself a separate linked list.
- Put all the linked lists in an array of D (which is also equal to the height + 1) in traditional sense.

#### Problem Design
- will need to check for root before putting it in the first list (for null), null tree should be considered a depth of 0.
- will deploy link lists with each index of the array list containing a reference to the linked list that holds the children of the next depth.