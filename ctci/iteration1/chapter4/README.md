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

#### Problem Solution

