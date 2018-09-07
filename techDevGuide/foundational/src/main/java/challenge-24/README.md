# Challenge 24 - Water Volume
## Description
>
Given an array of integers that represent height calculate the total volume of water that can exist between two heights in any given cavities present by two integers within the array.
>
Example
```
Input :  [1,3,2,4,1,3,1,4,5,2,2,1,4,2,2]
Output : 15
```

### Problem Discussion


### Problem Labels
- Stack
- Dijkstra

### Problem Design
#### Design 1
- brute force implementation in which can find the next largest max left within the array.  If the indicies are greater than one then can iterate through all indices that occur between performing the difference of the min of pivot location and the next found max.
- this would be expected to be O(N^2) for the time performance with O(1) space complexity.

#### Design 2
- use a stack to keep track of the heights
- use a stack to keep track of the min indices
- if a value is smaller than the current max then put the value and the stacks, assuming it is smaller than the height of the value at the top of the stack
- if the new value is less than or equal to max and greater than the top value then perform the following
  - volume += (current height - lower height) * (current index - lower height index)
  - update current index to the lower index
  - keep doing this until the stacks are empty or until a height value on the stack is equal or larger than the current height.