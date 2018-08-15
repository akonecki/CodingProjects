# Chapter 11 - Sorting
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 11.

## Problems
### Problem 1
#### Problem Statement
> 
Given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B, write a method to merge B into A in sorted order.

#### Problem Discussion
- would want to determine if the non-filled part is addressable or not.
- if not can always fill in and null out in the case of say an array list.

#### Problem Design
- will implement under the guise of two arrays being provided with the first array containing the sufficient size of addressable indices.

#### Problem Issues
- None

#### Problem Solution
- Matches solution.

### Problem 2
#### Problem Statement
> 
Sort an array of strings so all anagrams are next to each other.

#### Problem Discussion
- would need to know what is the ordering of the first anagram
- will assume it is the first in lexiographic order
- the strings that share the same anagram will then be sorted lexiographically
- this is essentially bin sorting based on the anagram.

#### Problem Design
- will need to be able to generate an anagram key that will be used to store the bin of strings that match a specific anagram.
- given two words that are anagram, they must generate the same key to be used within a HashMap, with the value being a balanced BST Red/Black so they are lexiographcially correct.
- once complete in populating, iterate through all anagram keys and get the lowest lexiographic value, sort these into an array.
- iterate through the array and generate the anagram key for each and then iterate through the entire value pair and populate an overall in-order list of strings.
- do this until the end of the sorted lexiographic first anagrams is complete.

#### Problem Issues
- None

#### Problem Solution
- The solution is fairly simple and does not require or implement the overall ordering of the words.
- My implementation is more complex with the requirement of being lexiographically correct w.r.t the anagram words and the overall list as well.

### Problem 3
#### Problem Statement
> 
Given a sorted array of integers that has been rotated an unknown amount of times, write code to find an element within the array.  The array was originally sorted.
>
```
{33, 41, 52, -23, 3, 12, 18, 21, 28, 31}
12

5
```

#### Problem Discussion
- can double the array to get the middle to be completely sorted.
>
```
{33, 41, 52, -23, 3, 12, 18, 21, 28, 31} => {33, 41, 52, -23, 3, 12, 18, 21, 28, 31, 33, 41, 52, -23, 3, 12, 18, 21, 28, 31}
```
- can be done in a single linear inspection of the dta once the dip from high to low occurs. the counted offset then signifies then beginning and end index in which to perform a search.
- if no rotation exist, can just perform the search 
- no rotation can be inspected by just examining if the first number is larger than the last number
- if rotation exists then will likely want to iterate from the front looking to see when the number goes from high to low.  

#### Problem Design
- determine if rotation has occurred.
- no rotation just search based on a start index and end index
- if rotation has occurred need to find the start and end indices.
- same binary search algorithm can be used in both cases
- this is likely worse than just doing a linear search.
- should still be able to implement a binary search.  Instead of blindly going to one half or another will need to look at the low and high index w.r.t the current inspected index.

#### Problem Issues
- can be quite tricky to get the logic correct due to minor mistakes.

#### Problem Solution
- the solution actually considers the case of duplicates, which my solution only considers discrete.
- in the even of duplicate values when the low == current index value and current index value == high then actually need to search both halves due to a break can occur on either side.
- this case is more easily handled in a recursive search than in a serial implementation due to being able to traverse both from a top down implementation.
