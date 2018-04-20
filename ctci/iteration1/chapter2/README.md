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

### Problem 3
#### Problem Statement
>
Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.  For example the list is a->b->c->d->e, and passed in the reference to c, after deletion the list should be a->b->d->e.

#### Problem Discussion
- the access statement is the key point.
- if access means it is provided as an argument, then would have no way to actually correct the linked list of the parent links.
- access likely means that using only one pointer to the data structure (i.e. no pairs, leading tailing references).
- with the assumption above, this fits in a recursive method such that going down allows the deletion of the middle linked node, and then going back up the recursion fixes the invariant.

#### Problem Design 
- recursion implementation that continues to cycle until the node that is to be deleted is found.  Once found need to store locally the reference to the nodes next. 
- delete the node
- return the deleted node's next node reference.
- keep returning up the stack where the `node.next = deleteNode(node.next, value);`

#### Problem Issue
- So the assumption made was incorrect.
- Only given access to the node to be deleted is the intended argument.
- This would not really be a publicly exposed API since the nodes are wrapped around the data.

#### Problem Solution
- Move data the wrapper nodes, keeps the pointer links correctness.
- Can not perform complete removal if it is the last node in the link, caller would have to perform this function.

### Problem 4
#### Problem Statement
>
Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.

#### Problem Discussion
- Very similar to how quick sort / 3 way dikstra sorting works, but have to consider linked lists.
- Need to ask if there will be duplicates possible in the linked list.  If so then must perform with 3 way.

#### Problem Design
- Will design with multiple values being possible.
- low -> low -> low -> eq -> eq -> eq -> high -> high -> high is the resulting design.
- Instead of moving pointers around, will just move the values that the link list nodes wrap around.
- First find value x
- After finding x swap it with the value contained in the head because don't know if the values up to then are all less than x or not and would not know where to put them.
- use 4 pointers to maintain the current layout
  - head : will always point to a value less than x
  - low-match : points to the first occurrence of the value x in the linked list that has been ordered
  - high-match : points to the last occurrence of the value x in the linked list that has been ordered
  - high : points to the current value that is last confirmed to be larger than x, will eventually be null.
- when `high` reference pointer is null, then done inspecting the linked list.
- jumps need to be defined.
  - initialization requires that all pointers will be equal to the head after the swap with x has occurred.
  - if next of high is greater than x, move high by `high = high.next`
  - if next of high is equal to x, then swap value of `match_high.next.value` with `high.next.value`, move high by `high = high.next`
  - if next of high is less than x, then swap value of `match_high.next.value` with `high.next.value`, move high by `high = high.next`, swap value of `match_low.value` with `match_high.next.value`, move match_low by `match_low = match_low.next`, move match_high by `match_high = match_high.next`

#### Problem Issues
- none just a bit long
- note that this is not a stable sort.

#### Problem Solution
- stable sort can be obtained by maintaining separate memory for lists greater and less than x.
- solution re-arranges the nodes.  
- I doubt the solution handles multiples do to the fact that it only handles two cases and for values equal to x also fits into the less than category, which would mean it would get intertwined.
- there solution also is based on the posisble notion that the sorting value can be virtual (the key does not exist within the list).  This makes the problem statement quite difficult due to the assertion of to the left and to the right of in terms of key value.  if x does not exist in the linked list there is no order possible due to no motion of left or right.
- the stable solution can be used to manage 3 independent linked lists that combines the head / tails of each to each other and return the head of the low.

### Problem 5
#### Problem Statement
>
You have two numbers represented as linked lists where each node contains a single digit.  The digits are stored in **reverse** order such that the 1s digit is at the head of the list.  Write a function that adds the two numbers and returns the sum as a linked list.

#### Problem Discussion
- the reverse order of the digits in linked list fashion is very beneficial.  Allows you to start the necessary carries to the next pair of digits within the two numbers.
- will need to dynamically create a new linked list structure that also follows the same reverse order.

#### Problem Design
- Will need a carry to add to the two digits of the numbers.
- For each iteration will perform an add of new digit to the new Linklist which is the sum.
- sum scope is the entire problem.
- Did not say anything about negatives, will assume they are outside the scope of the problem.

#### Problem Issues
- None

#### Problem Solution
- Solution describes two solutions, one with the orientation of the data as given (reverse order) and the other with the order flipped.
- The flipped uses more recursion but pads the one that is shorter.  This is fine but actually un-necessary.