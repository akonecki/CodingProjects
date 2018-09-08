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
- 

#### Problem Issues

