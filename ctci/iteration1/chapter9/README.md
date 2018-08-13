# Chapter 9 - Recursion & Dynamic Programming
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 8.

## Problems
### Problem 1
#### Problem Statement
> 
A child is running up a staircase with n steps and can hop either 1, 2, or 3 steps at a time.  Implement a method to count how many possible ways the child can run up the stairs.

#### Problem Discussion
- Likely will fit into the category of a dynamic coding question.
- Plan to start with general recurisive solution, followed by analysis, momeization, then serial solution via the FAST methodology.
- Need to determine if the first step is always required or if the counts start from a base to step 1, step 2, or step 3.

#### Problem Design
- There are three possible choices at any given step (assuming there are that many steps left).
- Expected number of branch conditions will likely be three.
- Expected base case will occur when the nth step is fulfilled.

#### Problem Issues
- Realizing that I need to perform the recursion on steps N - 1 & N - 2 took a bit of time.
- Building out the sets made this easier to realize.

#### Problem Solution
- N/A

### Problem 2
#### Problem Statement
> 
Imagine a robot sitting in the upper left corner of a grid.  It can take only right and down.  How many total possible paths can the robot take to the bottom right corner of the grid.
>
Same rules but if there are spaces in which the path is blocked.

#### Problem Discussion
- likely will fit into a dp problem with a 2D matrix for the total caching of storage values or in an optimal fashion only a row of the matrix.
- there should be two branches to be considered.
  - 1. Move down (if not blocked)
  - 2. Move right (if not blocked)

#### Problem Design
- will assume that the matrix is initially given as a 2D matrix of ints.  All valid positions in which the robot can travel will be filled with 0's while those that have blocking objects will be filled with a 1.
- will implement the functional implementation first without blocking and then with blocking.  
- FAST methodology will only cover the blocking situation.

#### Problem Issues
- None. 

#### Problem Solution
- Same

### Problem 3
#### Problem Statement
>
Given an array A[0...n-1] such that an index is defined to be such that A[i] = i.  Given a sorted array of distinct integers, find the index where A[i] = i if one exists.  
>
What happens if the values are not distinct?

#### Problem Discussion
- values contained within the array can be negative.
- values are sorted, meaning that want to operate on the first non-negative number.  This can be found by a simple binary search.
- worse case all values are off by 1 of their index meaning that will have to search the whole array O(N) is expected worse case.
- Non-distinct values will force a bit more logic on the jumping.

#### Problem Design
- if A[i] == i 
  -  return true
- else if A[i] != i && A[i] >= 0 && A[i] > i 
  -  i = A[i]
- else if A[i] != i && A[i] >= 0 
  - i = binarySearch(A, A[i] + 1) // get the next non A[i] element index
else 
  - i = binarySearch(A, 0) // get the next non negative element index

#### Problem Issues
- Serial Implementation was very error prone for the search due to needing to limit the lower index for the greater value.
- Recursive implementation was trival, just forgot how to think about it.
- Not a recursive implementation due to only hitting each index only once.

#### Problem Solution
- Matches recursive implementation although there is a quicker serial implementation that also reduces the memory footprint but more error prone.

### Problem 4
#### Problem Statement
>
Write a method to return all subsets of a set.

#### Problem Discussion
- this is a full combinratics problems

#### Problem Design
- for the set will have two states
  - 1. include
  - 2. exclude
- will need to examine if the problem can support an optimal sub-structure.

#### Problem Issues
- None

#### Problem Solution
- Recursive implementation matches.
- There also exists a serial implementation as well but might implement another time.

### Problem 5
#### Problem Statement
>
Write a method to compute all permutations of a string of unique characters.

#### Problem Discussion
- this is another comb. problem, but only supporting the computation if it is a unique character.
- for now will only consider the ASCII characters 'a' - 'z' for reduction of scope.
- permutations will be limited by the set of unique characters

#### Problem Design
- for the set will have two states
  - 1. include
  - 2. exclude
- due to expanding of all the sets optimal substructure in the recursive form will be difficult due to state information from another step impacting the lowers, although it is possible.
- will need to maintain a total set of characters that currently exists for the given permutation
- ordering will differ based on letter occurrance, therefore will also need to maintain a hash of all strings that have already been seen.  Do not want duplicates within the final set.

#### Problem Issues
- None on implementation.

#### Problem Solution
- I would say that I disagree with the solution due to the fact that the problem states `permutations of a string of unique characters`.  
  - this can mean that the permutations can only have unique characters (my implementaiton revolves around this)
  - the string given has all unique characters, therefore limited to say 26 characters long in the set defined above.
- In the end it still boils down to an inclusion / exclusion problem for the branching factors. 

### Problem 6
#### Problem Statement
>
Write a method to print all valid combinations of n-pair of parentheses.

#### Problem Discussion
- In order for a closing pair to exist it must have a pre-existing opening.
- requires all pairs up to N, not all permutations of N.  Thus if N == 3 then there will be three pairs no matter what.

#### Problem Design
- There is upto only N number of open and N number of closed parentheses that can exist.
- Will have two branching factors per level of recursion.
  - 1. Include open parentheses if possible
  - 2. Include close parentheses if possible
- the possibility rules for including an open is that the open count must be less than N
- the possibility rules for including a closed is that the closed count must be less than N and be less than the open count.

#### Problem Issues
- Tried to optimize on the the string builder.  In the end this led back to repeated strings and incorrect.  
- Went ahead and just did full string builder copy for the open and close, minor issue.

#### Problem Solution
- Matches solution.

### Problem 7
#### Problem Statement
>
Implement a fill function when given a 2D array, a point within the matrix, and a new value.  Fillin the surrounding area until the value changes from the orignal to the new value.

#### Problem Discussion
- will assume that the only directions that can be filled are left, right, up down.
- need to make sure to not keep going i.e. if it is a bulls-eye, should not turn all the opposing layers to a single color.  Only a single layer should be filled.
- will only fill for those values that are the same as the original spot.
- if you fill top down / bottom up.

#### Problem Design
- could perform with a duplicate matrix (space), along with direction branches is the worse case likely brute force solution.
- likely will want to record the positions that of which have already been visited

#### Problem Issues
- Only need to remember to account for the case of the current value is equal to the desire value.
- A serial implementation can also be performed.
  - Start at indicated value
  - Switch to desired
  - if directional is the same as the `from` value push into a queue
  - loop until the queue is empty.

#### Problem Solution
- Solution does provide a note about that screen and matrix column / rows are typically switched.  In my implementation I over look this issue due to not calling it a graphical tool.  Filling a matrix can be a numerical tool as well.

### Problem 8
#### Problem Statement
>
Given an infinite number of coins of values .25, .10, .05, and .01 determine the the total number of ways to represent n cents.

#### Problem Discussion
- Permutation counting problem, where coin order does not matter, only the number of coins of each denomination.
- will assume that cents will never be given as a negative and will not need to be converted from say dollars.

#### Problem Design
- will have the coin denomiations as an array.  Will be in reverse order, but this is not a requirement for corrections of implementation.
- at each iteration can either add or not add the denomination.
- if run out of denominations and the value is not zero then return 0, else return 1.
- this should fit a dynamic programming question, so will attempt to provide all of the FAST steps.

#### Problem Issues
- Recursive implementation was trival.
- Implementing the dynamic lead to trying to only do it with an array w.r.t the cents.  This is not correct as the denomination being used along with the cents (2 variable) should be used instead.

#### Problem Solution
- Matches solution for both the recursive and dp

### Problem 9
#### Problem Statement
>
Print all the ways of arranging N queens on an N by N chess board such that none are in the same row, same column, or share a diagional in any direction.

#### Problem Discussion
- will need to expand N into an actual board
- will need function to build a board.
- will need function to remove spaces when queen is placed
- will need function to print a valid configuration of queens.
- the total queen count must equal the dimension of the board in one direction
- the difficult part is deciding what data structure want to mantain the queen positions, since there will be a large amount of copying

#### Problem Design
- will maintain an arraylist of string buffers to allow for modification without having more memory impact than what is already going to occur.
- will only print the solution if it is valid, thus will convert to a display function.

#### Problem Issues
- 

#### Problem Solution
- 