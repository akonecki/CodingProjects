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

#### Problem Issues
- None

#### Problem Solution
- Solution highlights both a BFS and DFS implementation.
- The implementation that was completed matches fairly well with the BFS implementation.
- For the DFS implementation you must just handle the current level that you are operating at and insert the nodes appropriately.

### Problem 5
#### Problem Statement
>
Implement a function to check if a binary tree is a binary search tree.

#### Problem Discussion
- if a binary tree is implemented as a binary search tree this means that an in order traversal will provide an ordered list.
- if it is an absolute binary search tree there there are not duplicate entries within the BST but this comes down to the intended implementation (i.e. if being used for symbols key values must be unique).
- BST requires that the keys have a deterministic order and in particular type must be comparable.
- A less optimal on space implementation can be coping the values assocaited with a node of the BST to a list, then performing an iteration within the list checking that the values are in ascending order.
- Another possible method is to pass the max and min values that a node must be within, to save on storage.  Going down the tree modifies the min and max depending on the traversal direction.

#### Problem Design
- Will do implement the isBST() function two ways.
  - 1. Placing the values in additional memory that will require O(N) additional space.
  - 2. Keeping the min max on the stack for the recursive requiring O(H) where H is the maximum height of the tree.
- Method 1 : straight forward just perform in-order DFS placing each value onto a list.  After list is finished being constructed then compare each value to the previous to make sure they are in order.
- Method 2 : will need to check to see if the root is null prior to going into the recursive method due to having to provide the initial min and max, since these are templated types can pass null but have to make sure this is handled appropriately for the root.
- Base case as follows :

```java
if (root == null) {
  return true;
}
```

- Checking value associated with the node.

```java
if (root.value.compareTo(low) < 0 || root.value.compareTo(high) > 0) {
  return false;
}
```

#### Problem Issues
- Minor on the insertion for handling equal.

#### Problem Solution
- Solutions provided is same as mine minor some adjustments.
- The solution for the range only discusses integer cases, which might not always be true.

### Problem 6
#### Problem Statement
>
Write an algorithm to find the `next` node (i.e. in successor order) of a given node in a binary search tree.  You may assume that each node has a link to its parent.

#### Problem Discussion
- Same as successor problem in the general since but is at the specific node level.
- At the node level there has to be some information about the parent (either due to recursion or explicit link).
- In this case there is an explicit link back to a parent thus will require some modification to the typical node.
- Success is going to be defined as the node that comes next in sorted order.
- Predecssor is going to be the node that comes before in sorted order.

#### Problem Design 
- For successor want the left most (least value) of all the nodes to the right of the current node, if none then parent if one exists, otherwise is itself.

#### Problem Issues
- None

#### Problem Solution
- The implementation takes into consideration of the parent link in the left or right node, which changes the base class and is a feature that is not typical in a BST tree node though not that difficult to add if you own the controlling node class.
- I had discussed this in the discussion but prefered to not provide this link but yet still derive the successor of a given node that exists within the tree.
- The implementation can be improved to provide the successor to any value even if the node does not exist within the tree which is more ideal.
- The book provided solution does allow better handling of duplicate key entries better however (get to select the exact node to find the successor of).  This is great in a whiteboard or individual function implementation but testing own solution requires more API (have to construct the trees etc).

### Problem 7
#### Problem Statement
>
Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.  Avoid storing additional nodes in a data structure.  This is not necessarily a BST.

#### Problem Discussion
- since not a BST will need to search both left and right nodes.
- would need to determine if the information given is only the two nodes, this would mean that opposite side of the tree would not be accessible due to lack of parent reference, or if able to search starting from the main root of the tree (my guess would be from the root).
- Due to limitation on storing nodes in data structures Queue, arrays are out of the question.  Does recursion count since you would be storing a reference on the stack?
- are the two nodes guaranteed to exist within the tree?

#### Problem Design
- will assume that recursion is allowed but queues are not thus preventing a go to implementation of BFS
- recusion implementation that will return the ancestor node.
- couple of cases to consider, when find one node the possibilities for ancestor are
  - the found node (the other node will either exist on the left or right links of the found node).
  - a parent node (the other node does not exist under the found node, thus a parent node is responsible).
- for the parent method need to know what to return.  Returning null would mean that no common parent was found, but what needs to indicate that one node was already discovered.
- it takes two nodes to find, which means at some point a left and a right must exist, unless the nodes dont exist then there is difficult logic of needing to figure out if two nodes have ever been seen.
- if both nodes dont exist (in the one case is one node its own ancestor)?
- will assume that the implementation of only one node existing that the node itself is its own ancestor.
- in the case of no nodes then null can be properly handled. 
- in the case of two the parent that sees both a left and right child populated will return itself.

#### Problem Issues
- None

#### Problem Solution
- My implementation diverges on the assumption of handling the existence of only one.
- Answer provides a new class to return, essentially a node wrapper that allows for data agumentation that indicates if two have ever been seen.
- Other than that my solution follows the more aggressive approach.

### Problem 8
#### Problem Statement
>
You have two large binary trees: T1, with millions of nodes and T2, with hundreds of nodes.  Create an algorithm to decide if T2 is a subtree of T1.
>
A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2.  That is if you cut off the tree at node n the two trees would be identical.

#### Problem Discussion
- looks to be more of a combination of the two traversal methods, start with BFS to find the root of T2 in T1.
- Once the root is found then can perform dfs or bfs to compare nodes between the two trees.
- if the tree ends up not being equal must continue with the original bfs until the either run out of nodes or until the depth that is left is less than the smaller tree.
- if insertion is allowed then can manage the data of the tree on insertion (count, depth, hash) of those below.  This would make any run time subtree operations trival to where only a BFS or DFS is required.

#### Problem Design
- if want to prevent having to traverse the entire both trees (on average) then obtaining the actual height of the tree and managaing it is important.  
- when traversing T1 if the max depth remaining is less than the depth of T2 then there can not be a subtree.
- if can agument the `Node` data structure then this could already be provided along with the count of total number of nodes under the current node.  These values should match between T1 & T2, but this requires owning the insertion.
- break-up into at least two parts.
- 1. finding the root of T2.
- 2. Iterating between T1 & T2 for comparison.
- implementation will **NOT** consider wrapper modification w.r.t insertion.