# Chapter 17 - Moderate Problem Set
## Description
Set of moderate problems from CTCI.

## Problems
### Problem 3
#### Problem Statement
> 
Given a positive number `N` determine the total number of trailing zeros if you were to compute `N!`.

#### Problem Discussion
- `2 * 5` will always contribute a trailing `0`.  There will be more `2s` than `5s` due to there are more even numbers than numbers that are wholly divisible by `5`.

#### Problem Design
- As long as the number is wholly divisible by `5` should be able to perform the operation.
- can simplify to where dont even need to perform on the `!`, can just perform it on the value iteration of 1 to N.

#### Problem Issues
- None

#### Problem Solution
- Essentially the same, book is faster due to increasing in power each time
- Mine wholly loops through on `% 5` == 0, so as the multiple of 5 increases more iterations will be needed.

### Problem 6
#### Problem Statement
> 
Given an array find two indices within the array such that if all the numbers between those two indices are sorted then the entire array would then be sorted.

#### Problem Discussion
- could be that the entire array needs to be sorted, or the entire array is already sorted
- in the case that the array is already sorted then just return -1, -1 for the two indices 

#### Problem Design
- find the first occurrence in which sorted order is violated.
- record the indices, because will need to perform a binary search to find out where the violated minimum value sits within the beginning of the sorted array.
- second find the ending index of violation w.r.t the growing maximum.
- record the minimum along the way
- once reach the end of the array, perform a binary search to determine the index in which the minimum would need to exist.  This will be the lower bound of the index that needs to be sorted.

#### Problem Issues
- 

#### Problem Solution
-