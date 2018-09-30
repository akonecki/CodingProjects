# Challenge 37 - Island Count
## Description
>
Given a matrix, find the total number of unique / stand alone island that exist within the matrix.

### Problem Discussion
- assume that non-island / water is bound to the value of 0.
- provide an inplace / non-inpplace supported implementation.
- valid island connections are only in a left, right, down, up traversal.

### Problem Labels
- Array

### Problem Considerations
#### Consideration 1
- can iterate through the array one element at a time.  
- if the element within the array is equal to 0 then dont need to keep processing it, just go to the next one.
- need to handle a square in which island wraps back around, do not want to reprocess / get stuck in a infinite loop.
- if find a 1 then
  - go left, right, down up from the find
  - can perform recursively.

#### Consideration 2
- should be able to see if can perform with a form of dynamic programming.  Just maintain one dimension of the island count and then just reduce the total count.
- Example
```
1 1 1 1 1 1    1 1 1 1 1 1
0 0 0 0 0 1    0 0 0 0 0 1
0 1 0 1 0 1 => 0 2 0 3 0 1
1 0 1 0 1 1    4 0 5 0 6 1
1 1 1 1 1 1    4 4 4 4 4 1
```
- would require a current set of the current set of mergeable unique island IDs
- take min of up / left that are not zero.  
- if both the left / up are zero then increment unique count by one and add to the set
- if run into up/left that are different values then take the min.  If the number that is not taken exists in the mergable set then remove it.
- the total number of unique islands is the total number of unique IDs left in the set.
- visit each index in the matrix once.