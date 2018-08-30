# Challenge 20 - Find Substring in String of Unique Characters
## Description
>
Given a string and an array of unique characters find a substring within the string that contains all the unique characters as a substring in a continuous stride.

### Problem Discussion
- the unique set of characters will be guaranteed to be uniquely given.
- if the substring does not have a character within the unique set then it is not a valid substring.
- the found substring must be the same length as the total number of unique characters.

### Problem Labels
- String
- HashMap

### Problem Design
#### Design 1
- will want a hashmap that contains all the characters mapped to their counts.
- will need to document the total number of non-unique counts there are
- will insert at the tail and delete from the start (sliding window design)
- if a non-valid character is found, just add it to the map, just keep a count of total invalid characters then

#### Design 2
- would be the same as design 1 but will encountering a non-valid character, can instead restart the window.
- would want to do a back to front population of the map.
- if the data has a large frequency of non-valid characters will actually lead to thrashing of the data structure since will be constantly rebuilding the map from scratch in a buildup fashion