# Challenge 21 - Minimum Distance of Two Strings
## Description
>
Given an array of strings, find the minimum distance of their indices between two given strings.  

### Problem Discussion
- there can be duplicates within the given array.
- the two strings to find the distance can be the same string
- assume only dealing with lower case characters a-z
- assume the array of strings, all the strings are in their proper form
- if the same string is asked for then two separate ones must exist within the list itself
- the minimum found distance should always be positive

### Problem Labels
- String
- HashMap
- Greedy

### Problem Design
#### Design 1
- build a hashmap of the string to an array of the indicies that of which that they occur at.
- if either of the two strings for finding the distance does not exist within the map then return -1.
- if both are the same string then use a two pointer system on the array of indices to determine the minimum between two.
- if both are separate strings then use a two pointer system across both arrays.  
  - perform the difference
  - move the index that is the lowest of the two first
  - continue until one or both arrays are exhausted

#### Design 2
- make the distance calculation be done in constant time by cachine all the strings to other string minimums within a multi-diminsion mapping.
- the mapping will need to be updated if new strings are added.