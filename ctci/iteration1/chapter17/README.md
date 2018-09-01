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