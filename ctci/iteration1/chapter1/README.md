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
