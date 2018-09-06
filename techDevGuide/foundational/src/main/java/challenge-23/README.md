# Challenge 23 - String Decompression
## Description
>
Given a compresses string with the format number[string], build the decompression.
>
Example
```
Input : 3[abc]4[ab]c
Output : abcabcabcababababc
```
>
Consider the following
- number can have more than one digit.
- one repetition can occur inside another `(2[3[a]b]) => aaabaaab`
- characters allowed as input include digits, small English letters and brackets [ ].
- digits are only to represent amount of repetitions.
- letters are just letters.
- Brackets are only part of syntax of writing repeated substring.

### Problem Discussion
- assume valid input is given

### Problem Labels
- Recursion

### Problem Design
#### Design 1
- recursion implementation to roll up the de-compressed string.
- each level is processing a string or a number of times to build a string.
- return the current index to the upper level so only iterate through the string once
- parent level provides lower child call with the string buffer to manipulate