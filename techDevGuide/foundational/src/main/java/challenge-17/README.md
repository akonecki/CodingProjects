# Challenge 17 - Most N Frequent Occurrences
## Description
>
Given an array of integers, find the N most frequent numbers.

## Considerations
- The returned list should be first in highest frequency first, then order of occurrence within the array.
- N can split between multiple occurrence counts.

## Design
- first want to map the number to their count.
- while mapping the number to their count, if it is the first time that the number exists, then add to an ordering array.
- once the mapping of the number to their respected count of occurrences then iterate through the ordered array and populate maps of count to list of numbers.
- when populating the maps of count to list of numbers, if it is the first number to populate at a specific count then add that count to a priority queue (max priority queue).
- dequeue from the priority queue and copy the number from the count map to list up to N numbers.  If N is not reached pop off the next count from the priority queue.  Do this until the queue is empty or N is reached.