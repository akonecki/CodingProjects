# Chapter 2 - Linked Lists
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 2.

## Problems
### Problem 1
#### Problem Statement
>
Write code to remove duplicates from an unsorted linked list, how to perform this if temporary buffer is not allowed.

#### Problem Discussion
- the list is not sorted.
- consider if the linked list is doubly linked.
- Looks to be quadratic in most cases without temporary memory.
- with temporary memory, then can hash the existing values.

#### Problem Design
- simpleton case use two points one is the pivot and the other iterates through the remainder of the linked list.
- for each node that is encountered perform the equals method against the node itself, if equal can remove.
- For this implementation will do a serial pivot and the removal of duplicates will be the runner pointer done recursively.

#### Implementation Issues
- None

#### Solution Points
- There are two solutions provided, 
- one that operates on linear time linear space using a hash to check for duplicates
- one that operates in quadratic time constant space (this is the one that is implemented).