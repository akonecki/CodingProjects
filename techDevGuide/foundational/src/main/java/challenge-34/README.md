# Challenge 34 - Rabin Karp
## Description
>
Find all occurrence of a string within another given string (substring method).

### Problem Discussion
- substring method that takes into consideration a sliding window
- assumes that one string is smaller than the other
- assumes that the string that is smaller is the one being searched for in the larger string
- uses a hashing technique based on the character combination set to determine if a substring exists.

### Problem Labels
- String
- Rabin-Karp

### Problem Considerations
#### Consideration 1
- first calculate the total hash value of the smaller string first.
- then build out the window of the larger string along with the hash.
- then iterate through the remaining characters increasing the right and left indices by one each time.
- update the computed hash by removing the head value at the right value, shift the result over by the base radix, then include the new entry.