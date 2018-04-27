# Chapter 5 - Bit Manipulation
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 5.

## Problems
### Problem 1
#### Problem Statement
>You are given two 32-bit numbers, N and M and two bit positions, i and j.  Write a method to insert M into N such that M starts at bit j and ends at bit i.  You can assume that the bits j through i have enough sapce to fit all of M.  That is, if M = 10011, you can assume that there are at least 5 bits between j and i.  You would not, for example, have j = 3 and i = 2 becasue M could not fully fit between bit 3 and bit 2.
>
Input:
- N = 10000000000, M = 1011, i = 2, j = 6
>
Output:
- N = 10000101100

#### Problem Discussion
- The whole number of M does not necessarily get to be placed in N.
- The problem accounts for N having enough sapce to accomadate it.
- Assumming the content of N at the positions provided is over-written completely.

#### Problem Design
- M will always be left shifted by i
- Need a mask to accept only certain bits of M but also can clear N.
- nMask = (-1 >> (32 - j)) | ~(-1 >> (32 - i))

#### Problem Issues

#### Problem Solution
