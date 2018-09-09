# Lesson 4 - Recursion with DFS Traversal Techniques
## Lesson Description
Implementation of actual recursion problems following one of the described recursion patterns listed below.
- DFS

## Problems
### Problem 1 - Greatest Product Path
#### Problem Statement
>
Given a n-by-n matrix of numbers, find the path from the upper left to the lower right that has the greatest product.
>
The only supported directions are to the right and down for traversing the matrix.
>
Provide a function to find the max product and a function to provide the path(s) of the maximum product.

#### Problem Discussion
- expecting that the max height of a recursion stack would be N + M + 1 where N is number of rows and M is the number of columns.
- two options to take per location right and down, expected branching factor expected is (2).
- the finding of the max product follows that of a dynamic problem as well in terms of design.
- will assume that max product will fit within a 32-bit integer.

#### Problem Design
##### Design Consideration #1
- will go ahead and do just the recursion implementatino first
- there will be two pieces that will maintain
  - the product
  - the current list of paths.
- this is a DFS problem since will effectively be going down an entire path to the end then will slowly back up the call stack handling the other possible traversals.

##### Design Consideration #2
- will implement the pieces to support dynamic programming technique for practice.

#### Problem Analysis
- Depending on implementation pieces there is an array of different performances.
  - Worse case for generic recursion for just max product 
    - Time - O(2^(M+N))
    - Memory - O(M+N)
  - Worse case for paths with max product
    - Time - O(2^(M+N) + ((M+N-2)!/(M-1)!*(N-1)!*(M+N)))
    - Memory - O(((M+N-2)!/(M-1)!*(N-1)!*(M+N)))
- DP
  - Time O(M*N)
  - Memory O(M*N)
- Serial
  - Time O(M*N)
  - Memory (M*N)

#### Problem Issues
- Nothing with respect to the recursion other than the base case had a slight bug in it.

### Problem 2 - Lowest Common Ancestor
#### Problem Statement
>
Given a tree, and two nodes, write a function to find the lowest common ancestor between the two nodes.

#### Problem Discussion
- Guess is that will provide the actual nodes not the value of the nodes.
- the tree is not guaranteed to have any orientation w.r.t the data contained within the nodes.
- tree will also be given to the function
- will need to add supporting code to actually develop the tree.
- can search the two nodes first in a classic DFS / BFS fashion if need be.
  - if the other node is found then the starting node is the least common ancestor of the two
  - if neither are found then will need to search from the root, but if the nodes are found, wont need to go down
- might want to just start from the root and traverse.

#### Problem Design
##### Design Consideration #1
- four possible states when searching
  - 1. Neither node found
  - 2. Node A found
  - 3. Node B found
  - 4. Node A & B found
- the first occurrence in which case (4) occurs is the least common ancestor node within the graph.
- to get around the issue of return two types can take advantage of an array of node of size (1) which will contain the LCA of the two nodes within the tree.
- if the array is emtpy then no LCA was found.
- return enumeration value of the four cases above.

#### Problem Analysis
- Worse case have to search through all the nodes in the worse case.
  - O(N)
- Memory impact is also O(N) due to the tree not guaranteed to be balanced.
  - O(N)
- Did not make use of improving the average case for this.

#### Problem Issues
- None

### Problem 3 - Knapsack
#### Problem Statement
>
Given a set of items with a set of weights, values, and quantity determine a the set(s) of items that you can carry in a knapsack up to a specified weight.

#### Problem Discussion
- Will allow for items to have multiples of, then will cause a use of iterative design for the recursion.
- ordering of population of the knapsack is not important only max values w.r.t overall weight
- want to maintain the list of items for a given maximum weight.
- will need to define the `Item` class.

#### Problem Design
##### Design Consideration #1
- will follow the approach that is similar in style to the greatest product path, but will include iterative to handle the quantity of an item.
- will maintain a current item list, if it the final value ends up being greater than max then remove previous and save to current list.
- if the final value is equal to the current maximum then want the one with the least amount of weight then

#### Problem Analysis
- The analysis is a bit difficult with this due to the data being tightly controlling branching factor.
- Branching Factor
  - the total number of branches will be 1 + max(all items with highest quantity) in the worse case.
  - the plus 1 is due to the fact that an item can be not selected to be added.
- Depth
  - the total number of different items in the list of items provided to the function + 1 for handling the base case.
  - so (N)
- Memory Copies
  - Due to the saving of list a full copy occurs only at the base case.  Although there can be instance of the buffer shrinking or enlarging due to number of elements as the recursion tree is walked up and down.
  - There can be M number of elements in a list that get copied.  There are X number of permutation / paths thus worse case can be X*M, where X is a complex term larger than a linear constant.

#### Problem Issues
- None
- Dont remember if the original problem delt with quantities in addition to value.

### Problem 4 - Permutations
#### Problem Statement
>
Find all permutations of a list using depth first search.

#### Problem Discussion
- there is no indication of duplicates or how to handle.
- will assume the desired implementation is to find all unique permutations.
- could sort to aid in handling duplicates
- could develop a unique keying based on the order for duplicate detection.
- each number will need to have occurred at each location within the newly formed array.

#### Problem Design
##### Design Consideration #1
- implementation with the assumption that the array will contain all unique integers.

##### Design Consideration #1
- implementation with the assumption that the array will not be all unique integers.

#### Problem Analysis
- 

#### Problem Issues
- 