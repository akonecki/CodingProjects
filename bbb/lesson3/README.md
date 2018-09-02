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