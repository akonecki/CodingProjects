# Challenge 38 - Water Volume
## Description
>
Given an array of heights, determine the maximum water volume that can be held within the heights.

### Problem Discussion
- assume a volume of water can only exist in-between two higher levels on the left and right.
- there must be a set of three heights to have any volume.
- the water volume with be held to the minimum of the left and right heights as long as they are higher than that of the middle.

### Problem Labels
- Stack
- two scan

### Problem Considerations
#### Consideration 1
- iterate through the list of heights and build out the left to right max heights
- iterate through the list of heights and build out the right to left max heights
- compute the Math.abs(min(leftToRight, rightToLeft) - height)

#### Consideration 2
- maintain a stack of the values.
- keep larger values at the bototm of the stack.  
- when encounter new value, put directly on the stack if it is less than that of the value at the top of the stack.
- if the new value is greater than top of the stack, pop off the top value.  Then peek at the top of the stack and compare the min calculation based off of index.
- if no more values are available to peek then push the new value onto the stack