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

### Problem 2
#### Problem Statement
>
Find all substrings of a string.

#### Problem Discussion
- a substring of a string are consecutive combinations of characters within the string.
- `abcd` would result in the following substrings
  - `<empty>`
  - `a`
  - `b`
  - `c`
  - `d`
  - `ab`
  - `bc`
  - `cd`
  - `abc`
  - `bcd`
  - `abcd`
- this is an iterative pattern
- total number of strings being generated should be on the order of O(N^2) where N is the length of the original string.
- will have something along the lines of `N + (N - 1) + (N - 2) + ... 2 + 1 = N(N + 1) / 2 = O(N^2)`

#### Problem Design
- the original code provided multiple functions, 3 in total and wants to remove the unnecessary third.
- the original operates on at a specific index and then increments by one for the substring range resulting in the following substring
  - `a`
  - `ab`
  - `abc`
  - `abcd`
  - `b`
  - `bc`
  - `bcd`
  - `c`
  - `cd`
  - `d`
- the presentation of the data is position ordered
- can take the original code and move the iteration control to the first function with the substring functionality contained to the second function / the first recursive function.

#### Problem Issues
- None
