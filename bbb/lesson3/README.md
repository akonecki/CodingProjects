# Lesson 3 - Recursion with Selection & Ordering Patterns
## Lesson Description
Implementation of actual recursion problems following one of the described recursion patterns listed below.
- Selection
- Ordering

## Problems
### Problem 1 - Word Break
#### Problem Statement
>
Given a string, that has no white spaces, a dictionary, that includes a list of valid words, find all the different ways in which the given string can be broken into a string of valid words.
>
Use the following function prototype
>
```
List<String> wordBreak(String s, Set<String> dict)
```
>
Example:
```
Input : "applepineapple", {"apple", "pine", "pineapple"}
Output : ["apple pine apple", "apple pineapple"]
```

#### Problem Discussion
- can not necessarily guarantee that all the character spaces are going to be used
- will be a lot of include / not include of a word window
- can use the detail about the longest word to set a maximum on the window
- could use a trie to aid in reducing some of the searching of invalid strings
- dont know if there can be invalid strings within the string as well and just record the valid ones
- will only record those in which the entire string can be broken into a valid string with spaces

#### Problem Design
- use the include / exclude at each word find

#### Problem Analysis
- Say given dictionary of something like {a,aa,aaa,aaaa,....}
- Max Depth Expected
  - this will cause it to work on the smallest string set first will see something like "a a a a a a....a a a a"
  - depth will be length of `s` / min{dict{element -> element.length}}
  - worse case depth is `s.length()` / 1
- Max Branching Factor Expected
  - incrementing by 1 in the outer loop
  - incrementing by 1 up to `maxLength` which is the maximum length string contained within the dict, on the inner loop
  - in the worse case above each index produces a valid string
  - worse case branching factor is expected to be `s.length() * maxLength`
- timing ignoring copying of the `StringBuilder` buffer is `O((s.length() * maxLength)^(s.length())`
  - when copy of the `StringBuilder` occurs could be `s.length()` 
  - so could see an order of `O((s.length()^2 * maxLength)^(s.length())`

#### Problem Issues

### Problem 2 - Array of Arrays
#### Problem Statement
>
Given a 2 dimensional array, find all of the different 1 dimensional arrays that you can make by selecting a single element from each array.
>
Use the following function signature 
```
List<List<Integer>> arrayOfArrays(int[][] arr)
```
>
Example
```
arrayOfArrays({{1,2}, {3}, {4,5}}) == {{1,3,4}, {1,3,5}, {2,3,4}, {2,3,5}}
```

#### Problem Discussion
- Each array present must contribute one element
- Order of the array defines the order in which elements will exist within the resulting generated array.
- iteration on which array to be used
- iteration on the individaul array itself

#### Problem Design
- recursive function will define which index of the array of arrays to use.
- the recursive level will be responsible for iterating over the elements within that level's selected array.
- expecting the overall storage memory impact to be `total # arrays * total multiplied number of elements per array`
- expecting the total depth to be the total number of arrays.
- branching factor is expected to be around the max sub array in length

#### Problem Analysis
- Depth
  - The total depth is expected to be equal to the total number of arrays.
  - thus if the total number of arrays is N the max depth / height of the recursive function is N.
- Branching Factor
  - There are M total number of elements per a given array / recursive level.  Therefore is assumming are equal to the max length of one subarray then M will be expected.
- Copying per level occurs only on the last level and is at most only N elements in length.  
  - since the copying occurs at the end of a permutation then need to just know the total number of possible permutations. 
  - total number based on the values above would be equal to M^N
- Expected worse case
  - O(M^N + M^N) where the first is the branching factor to the depth, and second is the copy operations at the end of each permutation.
  - O(2(M^N)) = O(M^N)

- Memory
  - O(M^N + N) should be the worse due to the copy contents and recursion depth.

#### Problem Issues
- none

### Problem 3 - Longest Increasing Subsequence
#### Problem Statement
>
Given a list, find the longest increasing subsequence. The subsequence need not be contiguous. There may also be multiple longest subsequences, in which case you only need to return one.
>
Use the following function signature 
```
List<Integer> longestIncreasingSubsequence(List<Integer> l)
```
>
Example
```
longestIncreasingSubsequence({9,6,1,5,3,7,55,12}) = {1,3,5,12} or {1,5,7,12} or {1,3,7,55} or others
```

#### Problem Discussion
- must be strictly increasing, thus equal does not count
- array can be out of order
- max expected depth would be the length of the array of numbers
- branching factor expected is 2, include or not include the current index

#### Problem Design
- will want to manintain an array of longest sequences
- when a new sequence is completed, compare its length to the current max length.  
  - if it is equal then add the list to the result
  - if it is larger then start a new result list with the list as the first entry
  - if it is smaller then drop the list
- could iterate on the index until a larger value is found then branch

#### Problem Analysis
- 

#### Problem Issues
- 
