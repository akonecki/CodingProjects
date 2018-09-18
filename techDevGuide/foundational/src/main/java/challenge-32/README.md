# Challenge 32 - Binary Search Tree In Order Iterator
## Description
>
Given a proper binary search tree, return a in-order iterator.

### Problem Discussion
- want a full iterator on the nodes of the trees themselves.
- will need to support a full set of tree functions (i.e. insertion) first.

### Problem Labels
- Stack
- Binary Search Tree

### Problem Considerations
#### Consideration 1
- have a stack that represents the left most or root node that has been seen.
- if a node is already on the stack then it means that it has been evaluated for its left most.
  - just then evaluate itself for its own value then its right if any exist
  - this will aid in not having to over allocate or modify the structure when building the iterator
- the run time of the iterator will be O(lgN) since when a remove is performed want to get the stack into the stable state such that the next value to be removed is the next element in the in-order traversal.