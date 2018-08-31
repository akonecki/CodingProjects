# Challenge 22 - Longest Word(s) for Given Prefix
## Description
>
Find the longest word from a set of given words, given a prefix.

### Problem Discussion
- assume that the words that are being managed are all lower case
- assume that the prefix provided will be all lower case

### Problem Labels
- Trie
- HashMap
- Dynamic Programming

### Problem Design
#### Design 1
- maintain a trie of the words that are being managed
- when going down the tire, can check if the current string being added is longer than the original max length string seen for the current character.  If so replace with the new word, else keep the old.
- when given a prefix, just traverse down the trie to the end of the prefix and use the max string for the terminating prefix node.  
- this implementation is O(N) with N representing the longest sequence of characters.

#### Design 2
- same as the first however when traversing down the trie for adding a new word, can keep a hashmap of all the prefixes with their associated longest string found.  
- if the new word is longer than the previous, then update the map for that prefix.
- when querying with a prefix, can just inspect the hashmap pre-computed prefix max lenght strings
- this implementation is O(N^2) for the fetching of the prefix.