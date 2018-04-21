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