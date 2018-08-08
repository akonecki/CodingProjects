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