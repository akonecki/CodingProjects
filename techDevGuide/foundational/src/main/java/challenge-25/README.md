# Challenge 25 - Max of Sliding Window
## Description
>
Given an array of integers and a maximum window size, find the maximum for each window within the array.

### Problem Discussion
- assume that the array given will be as big or bigger than the desired window
- numbers are not ordered.

### Problem Labels
- Array
- LinkedList

### Problem Design
#### Design 1
- Brute force multiple loops. 
- Start at a given index and iterate through up to the window size.
- record the max along the way.
- Expectation in worse case is O(N^2) depending on the window size in worse case.

#### Design 2
- maintain the max within a sliding window
- use linked list structure to remove from the back as long as the new value to be added will be greater that what exists at the back
- save the index of the position that has been saved
- remove form the max window once the remove index is equal or greater than the max of the window position.