# Challenge 19 - Find minimum in Sorted Array
## Description
>
Given a sorted array of integers, find the minimum such that `array[index] == index`.

## Considerations
### Consideration 1
- This is a fairly trival linear search problem.
- Can just iterate from the front of the sorted array until `array[index] == index`.
- Once found just return the index in which the find occurs.
- If no values meet this requirement return -1.
- The worse case run-time is O(N) due to the linear traversal.

### Consideration 2
- if want to minimize the worse case impact then can perform a search function.
- For this consideration will focus on the recursive implementation.
- can start at the mid.  
  - if the value found at the calculated index is less than 0, then only capable values are those to the right of the index
  - if the value found at the calculated index is less than the index but greater or equal to 0, then go left first.  If nothing is found then go right.
  - if the value found at the calculated index is greater than the index and less than the array length, then go left first on the [left, index - 1] range.  If nothing found then go right on the [array[index], high].

### Consideration 3
- if want to get rid of the recursive stack then implement it in a serial fashion.  Will need some state to control the left and right traversal history.