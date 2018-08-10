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


#### Problem Solution