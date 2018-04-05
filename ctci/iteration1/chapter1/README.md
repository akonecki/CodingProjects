# Chapter 1 - Arrays & Strings
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 1.

## Problems
### Problem 1
#### Problem Statement
>Implement an algorithm to determine if a string has all unique characters.  What if you cannot use additional data structures?

#### Problem Discussion
- Unique characters implies use of ASCII but should be confirmed.  Could be other data formats, however regardless a string character has a specific number of bits assoicated with each value; 8/16/32-bit representation per character in a string.
- Should determine if specialized characters are of importance.
- Uniquness requires support of being able to compare thus ordered data.

#### Problem Design
- if the target is specific to a known data set size such that the total order of words can be maintained within <= N word_size results then the characters can themselves can act as the key value into an array of bits. 
- Example:
>
ASCII only lower case letters is 26 characters in the known set.  26 characters in the ordered set would require 1 word size register that supports 32 bits.  The order of the ASCII ensures that each letter can be a unique key that represents a specific bit into the register.

- being less space friendly can use an array with index acting as a normalized ordered key values of each character.

#### Implementation Issues
- Java character iteration should be accomplished through indexing and using charAt on the String object.

#### Solution Points
- Depending on the context of the problem, constraint definition is important.  
- Could sort (but need to either implement own to enforce memory constraints)
- Could perform quadratic time by comparing each element to each other element.
- If need to support larger unique character counts then will need to allocate more memory for the problem.

### Problem 2
#### Problem Statement
>Implement a function `void reverse(char* str)` in `C` or `C++` which reverses a null terminated string.

#### Problem Discussion
- want to perform in-place reversal.
- will only need to iterate through half the string due to exchange of pairs of locations within the string.
- Java this is difficult due to `String` object being immutable data types resulting in un-desired memory implications if performed.

#### Problem Design
- Iterate through the characters of the string up to half the string length and exchange with the end index - the current index of iteration.
- Do not reverse the null termination.

#### Implementation Issues
- declaration of data determines location for C / C++.
- `char *string = "hello";` points the `string` variable to the bss section of code.  This is un-modifiable / read only memory.
- `char string[] = "hello";` points the `string` variable to read/write location of memory.

#### Solution Points
- no addition material.
