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

#### Problem Issues