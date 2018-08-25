# Lesson 2 - Recursion with Iteration, SubProblems, Divide & Conquer Patterns
## Lesson Description
Implementation of actual recursion problems following one of the described recursion patterns listed below.
- iteration
- subproblems
- divide & conquer

## Problems
### Problem 1
#### Problem Statement
>
Print out the odd indicies of an integer array.
>
```
Input : [1,2,3,4,5,6,7,8,9,10]
Output : 
2 // occurs on index 1
4 // occurs on index 3
6 // occurs on index 5
8 // occurs on index 7
10 // occurs on index 9
```

#### Problem Discussion
- an iterative implementation would be quite trival
  - just iterate through the array two at a time starting at index 1
  - print at each index that is less than the overall array length
- expected operational time will be O(N) since will hit every other element within the array which is N/2 == N for complexity.
- expected memory impact will also be O(N) due to linear relationship to the number of elements within the array.

#### Problem Design
- take the serial approach and just convert into a recursive iteration problem.
- have each layer of the recursive function be responsible for the printing out of the current index and then call on the next index with the increment by two.
- if the passed in index is greater than that of the array total length, then just return (base case).

#### Problem Issues
- None

