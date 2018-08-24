# Challenge 18 - Build Max Root Binary Tree
## Description
>
Given an array of integers construct a binary tree such that the root is always the max of the left and right children.
>
The children should be represented as the left and right side of the index in which the root represents within the array of numbers.

## Considerations
- Duplicates within the array can occur, left and right leaning is not necessary, either works.
- a left child must have existed to the left of the root
- a right child must have existed to the right of the root

## Design
- Can implement a recursive function that will create a node of the max within the designated low and high index range.
- will have to have a function that is responsible for obtaining the max for a given range.