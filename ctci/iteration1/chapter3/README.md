# Chapter 3 - Stacks & Queues
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 3.

## Problems
### Problem 1
#### Problem Statement
>
Describe how you could use a single array to implement three stacks.

#### Problem Discussion
- arrays are the general memory structure to begin with and programs can have multiple regions that are stacks (heap, function stack etc).
- need to make sure that the stacks are exclusive (i.e. not over-lapping each other).
- if exclusive then each will have a start and end index within the allocated memory region.
- for simplicity that can grow in the same direction, but will need to handle resizing.
- since these would be stacks, each have a max capacity and a warning capacity.
- when the size of any of the three stacks reach the warning capacity can allocate a shadow array and start copying X number of entries starting from the bottom of each stack over when the largest stack grows toward the max capacity.  Would have to handle the pop operation of the original with the shadow as well.  Works great if the pops occur in bluk or less frequently than pushing.
- This would provide a dynamic set of stacks within a defined memory region.
- Operting on any one stack would need to be indicated by the target stack.

#### Problem Design

#### Implementation Issues
- not implemented since it more design and api definition.

#### Problem Solution
- Same as above but does not go into re-sizing.
- Also provides a flexible implementation when the previous stacks when they grow big takes over its neighbors, and the neighbor values are shifted over to a new memory.

### Problem 2
#### Problem Statement
>
How would you design a stack which, in addition to push and pop also has a function min, which returns the minimum element?  All should operate in O(1) time.

#### Problem Discussion
- since have access to the pop and push can support min function.
- must consider the maintaining of the min element as pops occur since the min could be pop'd off, then would need the minimum to then be resolved in O(1) time.
- Priority queues would all be lgN time but would provide this.

#### Problem Design
- Stack class agumentation is necessary.
- when a value is being pushed onto the stack the current minimum of the top of stack gets compared to the current value, if it is still the minimum then the mimumium for the new value being push on the stack will be the same.
- Values themselves will need to be wrapped.

#### Problem Issues
- Orignally only tried accomplishing this using algorithms, and not actually looking at class constructs.

### Problem 3
#### Problem Statement
>
Imagine s stack of plates, if the stack gets too high it might topple.  Thus when a stack gets to a specific height we would like to start a new stack when the previous stack exceeds some threshold.  Implement a data structure `SetOfStacks` that mimics this.  `SetOfStacks` should be composed of several stacks and should create a new stack once the previous one exceeds capacity.  `SetOfStacks.push()` and `SetOfStacks.pop()` should behave identically to a sinlge stack.  Also implement a function `popAt(int index)` which performs a pop operation on a specific sub-stack.

#### Problem Discussion
- two parts to this problem that needs to be considered.
- first there is the managing of an individual stack
- second is the handling of all the stacks together.
- the stacks individuall can be viewed like arrays and as a new stack gets added progresses to a 2D array where the stack is the row.
- this will allow traversal to be a bit more easy as well.

#### Problem Design
- need a dynamic array for holding the `SetOfStacks` where the current index indicates the stack that is being worked on.
- stacks could be pre-allocated to their maximum amount and just manage it to the desired capacity.
- very similar to individual array resizing but instead the array just holds references to the new array that is a stack.
- when a stack gets created or removed depends follows similar fashion to resizing, but will be not as forgiving.
- for individual popping operation would likely need to determine what the strategy for pushing is to handle holes in earlier stacks, as in fill earliest stack first.

#### Problem Issues
- None

#### Problem Solution
- The book uses ArrayList data types which requires a bit more work and more complex methods since not owning the underlying data structure.
- Valid solution but requires a large number of communication points about assumptions for the implmentation.

### Problem 4
#### Problem Statement
>
In the classic `Towers of Hanoi`, you have 3 towers and N disks of different sizes which can slide onto any tower.  The puzzle starts with disks sorted in ascending order of size from top to bottom.  The constraints are as follows.
1. Only one disk can be moved at a time.
2. A dusk is slid off the top of one tower onto the next tower.
3. A disk can only be placed on top of a larger disk.
Write a program to move the disks from the first tower to the last using stacks.

#### Problem Discussion
- make sure to ask about the meaning of slid off the top of one tower onto the next tower.
- is the next tower of the end only the middle or can it also assume wrap around.

#### Problem Design
- will just use numbers to indicate the size of each disk.
- will have an array of fixed size 2D matrix where each row represents a column.
- will need to implement the push and pop constructs that indicate the target row.
- target row can only ever be +-1.
- can run a background checker for each operation to make sure that the rules are not violated.

#### Problem Issues
- I knew that the towers of hanoi are a recursive problem. 
- couldn't actually break it down to base case
- did only serial to try to see the steps but couldnt

#### Problem Solution
- implemented a copy of the book solution.
- used my own targets.
- need to practice breaking down the problem more.

### Problem 5
#### Problem Statement
>
Implement a MyQueue class which implements a queue using two stacks.

#### Problem Discussion
- traditional queue is first in first out.
- traditional stack is last in frist out.
- don't really know the constraints of the two stacks as in two separate memory regions or can it be over-lapping memory with different points (at this point would just be different in names).
- beleive it is two separate stacks with no overlapping memory or references.

#### Problem Design
- two operations for a queue
- 1. enqueue (push to the back)
- 2. dequeue (remove from the front)
- when an enqueue happens push to an enqueue stack
- when a dequeue happens (first check to see if the dequeue stack is empty).
- if the dequeue stack is empty, pop off all the elements from the enqueue stack and push them onto the dequeue stack.
- one the dequeue stack is full again or is not empty perform the pop operation.
- For simplicity will do static size structures, (don't want to implement the resize dynamic memory).

#### Problem Issues
- Need to make sure to either use provided APIs for the stack implementation, or make a class for it.

#### Problem Solution
- Same approach as the book solution.

### Problem 6
#### Problem Statement
>
Write a program to sort a stack in ascending order (with biggest items on top).  You may use at most one additional stack to hold items but you may not copy the elements into any other data structures (such as an array).  The stack supports the following operations: `push`, `pop`, `peek`, `isEmpty`.

#### Problem Discussion
- the second buffer is the only other major storage.
- the time complexity will be O(N^2) but due to the nature of the problem.
- don't really know how to make best use of the `peek` method.
- it is not indicated if all the values are unique or not.

#### Problem Design
- push all values off the first stack onto the second stack, acting as a buffer.
- save the number of elements that are popped off.
- save the current min found.  When a new minimum is discovered push the old one onto the buffer stack.
- when the stack isEmpty push the min value back on
- push the remainder of the values on the second stack back onto the first stack
- decrement the number of acceptable pops by one.
- repeat.

#### Problem Issues

#### Problem Solution