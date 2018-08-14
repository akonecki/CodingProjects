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
- 

#### Problem Solution
- 
