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

### Problem 4
#### Problem Statement
> 
Given a very large file, that contains one string per line.  Describe how to sort the file.

#### Problem Discussion
- will assume that a string can be very large, or even small.
- the file itself is acting as the array.
- would not be able to store in program memory, will need temporary file(s) based on sorting approach.
- could sort in discrete sections, then essentially perform a merge sort operation until reaching a final single file that is completely sorted.
- determine a specifc size and have multiple thread sort those sections.  Once adjacent sections are complete then could merge them as well.

#### Problem Design
- say each thread will process a specific size power of two 4096 for a page size, which translates into X number of lines with the last thread doing the remaining.  
- Each thread will start off just like in merge sort perform by two, then by four and so on. 
- when two adjacent sections are complete, the host application, will need to be responsible for thread to section mapping to determine adjacent criteria
- as the lower sizes are all completed then will need to handle the merging of the large sections in designated chunks.  This can be done in multiple threads in separate regions.

#### Problem Issues
- none

#### Problem Solution
- solution is an over simplification but is along the same lines.  It is technically labeled as an external sort, the system memory is being used as the sorting buffer with the result being written back to disk to preserve the modification.

### Problem 5
#### Problem Statement
> 
Given a sorted array of Strings, which can contain empty strings between any elements, write a method to find the location of a given string.

#### Problem Discussion
- unlikely to want to process the strings that are not empty to another array.
- can perform binary search with a recursive implementation fairly easily where if an empty string is encounted will have to explose both the left and right of it.
- return the index if found.

#### Problem Design
- likely will lead to a full N traversal in the end.  Would have to argue the benefit of encurring additional run-time overhead over just performing an iterative implementation.

#### Problem Issues
- None

#### Problem Solution
- The solution performs a linear traversal in both directions when an empty string is found.  This is alright, but begs the question how sparse is the data set going to be to allow this to be an efficient means.  Wose case still requires O(N/2).

### Problem 6
#### Problem Statement
> 
Given a non-square matrix with each row and column sorted, find an element.

#### Problem Discussion
- would need to determine the definition of the matrix.  
  - if matrix is if each row was taken and iterated through the columns and placed in a single array and the array is sorted then can obtain a worse case execution of the search of O(lgMlgN).
  - if the matrix is expanded into a single array one row at a time and the array is no longer sorted then the worse case execution of the search will be
  O(MlgN).

#### Problem Design
- both are fairly simple to implement so will go ahead and implement both.  One will just iterate through all the rows in order.  A bianry search is only performed if the value is greater than or equal to the first in the row and less than or equal to the last element in the row.
- the second will use the binary search first on getting the mid row.  Then if the first is than or equal and the last for the row is greater or equal binary search on the row is performed.  If not then inspect the first element is the vlaue is less than go up in rows, else go down in rows.

#### Problem Issues
- None

#### Problem Solution
- The solutions are a bit tricky.
- First that would be expected to be able to implement is a movement by row / column.  Essentially just make one movement at a time starting on the first row last column.  If the value is geater the the current then increase the row.  If the value is less than the current decrease the column.  Continue until a violation of the row or column occurs.
- Second that is more complex
  - bottom right corner for any square / subsquare in the matrix is always the largest.
  - the top left corner for any square / subsquare in the matrix is always the smallest.
  - can search the diagonal with a binary search as well.
  - break the problem into zones, one that is known that all values are less than it
  - one that is know that all values are greater than it
  - two that of which the value may exist
  - 1. perform the search on the diagonal (since not square might not have both row and column be out of bounds), until encounter first element that is larger or equal than desired value.
  - 2. if equal just return that index
  - 3. search the upper right and lower left and repeat the diagonal again.

### Problem 7
#### Problem Statement
> 
Given heights and weights determine the total number of heights that contibrute to the tallest possible if an element can only exist above another element if it is both shorter and lighter than the one below it.

#### Problem Discussion
- could implement a comparator the the entries however, dont know what really to do with the non-absolute less and non-absolute more pairs.
- resembles the box problem from the recusion version which implemented a comparator for the base implementation.
- thinking about a multi-key tree but that leads to the same issues and will still incur a performance penalty.

#### Problem Design
- 

#### Problem Issues
- didnt really know where to start / handle the case where absolute order is not indicated.

#### Problem Solution
- does provide a comparable for the sorting on the class definition `compareTo` ends up performing against the integer first on height then width.
- Longest increasing sub sequence problem
- first sort based on height
- then use a recursive `longest increasing sub-sequence`

### Problem 8
#### Problem Statement
>
Given an unknown number of values, that can repeat, provide the capability to determine the current number of values that are less than a specific value provide via a function called rank.

#### Problem Discussion
- since there is an unknown number of values and they are added one at a time, maintaining an ordered list makes little since since the insertion time plus the sorting time likely will be O(NlgN).
- a O(lgN) solution can be obtained by using a balanced binary tree for inserting new elements and to perform the rank operation.  Insertion in a binary tree is O(lgN).

#### Problem Design
- will first implement with just a BST for simpleness, then will try to support a red/black tree implementation to support inserting into a balanced BST.
- to prevent having to traverse the tree will use agumented node to maintain the count of lower and higher and total number of nodes w.r.t the parent.  This might have to change when performing any rotations for a red black tree.

#### Problem Issues
-

#### Problem Solution
-