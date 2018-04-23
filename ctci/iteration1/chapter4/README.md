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

