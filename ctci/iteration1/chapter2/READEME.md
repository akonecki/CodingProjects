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

### Problem 2
#### Problem Statement
>
Implement an algorithm to find the kth to last element of a singly linked list.

#### Problem Discussion
- if was doubly linked, and tail was provided then could just do a single k - 1 iterations from the tail.
- In the case presented above best to use two pointer where the distance between the two points are k apart.
- When the leading pointer gets to be null then will be at the kth from the tail.

#### Problem Design 
- Check if the linked list is empty.
- Iterate the leading pointer k times.  If the leading pointer is null and the kth was not reached return null at this point.  There are not enough nodes in the linked list.
- Once the lead pointer is established then the trailing pointer can be set to the head and in lock step the traversal of the linked list can occur.
- once the lead pointer is null, return the trailing pointer since it will be k from the tail.
- Count of k is the number of hopes that occur from the tail to the trailing node. 

#### Problem Issues
- Just need to make sure counting hops from the tail is the correct / desired.

#### Problem Solution
- There are multiple solutions provided, however the one implemented is the suggested ideal case.
- Other solutions may take advantage of language support (access to pointers) or only provide accessing of the kth element not actually being able to return it (recursive).
- The leading / tailing pointer solution is serial in nature and independent of the language.