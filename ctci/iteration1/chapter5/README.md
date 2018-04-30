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
- Need to remember that -1 is all 1s, and not just msb 1.

#### Problem Solution
- Book solution is similar, I however take into account that M could be trimmed down.

### Problem 2
#### Problem Statement
>
Given a real number between 0 and 1 that is passed in as a double, print the binary representation.  If the number cannot be represented accurately in binary with at most 32 characters print error.

#### Problem Discussion
- Is this just calling Double.toBinaryString() and stopping at 32 bits?
- or does this just want the mantissa part?
- double on most systems is 64 bits.

#### Problem Design
- Don't really know what the problem wants.
- maybe wants to convert to where the number is like an integer?

#### Problem Issues
- completely forgot about fractional representation that is not IEEE floating / double.
- Will need to revisit.

#### Problem Solution
- Fairly trival once you remember the fractional representation outside of IEEE standard for float / doubles.
- .101 (base 2) = 1 * (2^1/2) + 0 * (2^1/4) + 1 * (2^1/8)

### Problem 3
#### Problem Statement
>
Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in their binary representation.

#### Problem Discussion
- if the number is all 1s then there will be no next smallest.
- otherwise the next smallest is an exchange of a one to the left of a zero with the zero, with the right most one-zero pair.
- if the number is all 1s then the next largest will be << 1, with all lower 1s moved to the right, but only if the result is greater than 0.
- otherwise the next largest is an exchange of a one to the right of a zero with the zero, with the right most zero-one pair.  On this point if the exchange happens with the most significant 1 and the zero afterwards then need to do the same thing as if all ones.
- larger values strategy
  - a temp exists for the worse case
  - if a one is encountered and it is not the most significant 1, then save the one to a temp such that temp = temp << 1 | 1;
  - if a zero is encountered and temp > 0 && !temp < 0 

#### Problem Design
- iterate through from the base with two masks `01` for the greater value and `10` for the lesser value.
- the greater case needs to account for possible full shift and saving all detected ones in a temporary value with the same number of ones starting from the least significant bit position.
- save the main number to a temporary.  use the temporary for manipulation i.e. shifting right by 1 for each time.
- if the value is negative you can return due to the constraint of the problem.
- in the case of lesser value if get to the end and no `10` pattern has been found then a lesser value is not possible (all ones up to the msb), print itself / error message.
- in case of the upper need to watch out for roll-over to the negative range.  Which a swap occurs need to test the swap value for being negative.  If so print itself / error message.

#### Problem Issues
- Remember when masking lower bits to use mask of all ones for the number of bits of interest.

#### Problem Solution
- Correct implementation for bit manipulation.
- There also exists an arithmetic solution as well.
- Essentially counting 0s and 1s then either adding / subtracting from n depending on greater or less than.

### Problem 4
#### Problem Statement
>
Explain what the following code does.
>
```
((n & (n - 1)) == 0)
```

#### Problem Discusison
- Looks like it is detecting if it is a power of two value.

#### Problem Design
- Power of 2 only ever have 1 bit set.
- Subtraction of 1 from any power of two (except 1) leads to all lower bits to be set to one.
- If a value is not a power of two then there will exist some over lapping between n & (n - 1).

#### Problem Issues
- No code.

#### Problem Solution
- Correct

### Problem 5
#### Problem Statement
>
Write a function to determine the number of bits you would need to flip to convert integer A to integer B.

#### Problem Discussion
- first will need to know where A & B are the same and where they are different.
- XOR does this for us
- A ^ B zeros out all the matching 0's and matching 1s, leaving only a set of ones that are in either A or B but not in both.  
- the ones form A represent the ones that exist in A and need to be flipped to B
- the ones from B represent the ones that don't exist in A but need to be flipped to equal B
- Thus count the number of 1s left after the XOR operation.

#### Problem Design
- xor the two integers
- count the number of remaining 1s.

#### Problem Issues
- Not Implemented, fairly trival.

#### Problem Solution
- Correct Implementation (however Used library provided method).
- In the non-library provided wrapper method how to count the number of 1s.
  - two solutions (logical right shift & compare against zero) & (c & (c - 1) compared against zero).

### Problem 6
#### Problem Statement
>
Write a program to swap odd and even bits in an integer with as few instructions as possible

#### Problem Discussion
- if both bits are the same value no point in swapping
- upper order pairs of zeros wouldnt even need to be inspected.
- want to have even odd masks for the integers.
- shift the odd to the left to make it even
- shift the even to the right to make it odd.

#### Problem Design
- have two masks one to pull the even and one to pull the odd bits from the provided value.
  - 0xA (1010) & 0x5 (0101)
- use the mask of the odd to pull out bits that will be used to become even and then shift
- use the mask of the even to pull out bits that will be used to become odd and then shift

#### Problem Issues
- Need to remember to use bit-masks to eliminate the need to iterate.

#### Problem Solution
- Solution exactly matches.

### Problem 7
#### Problem Statement
>
An array A contains all the integers from 0 to n, except for one number which is missing.  In this problem we cannot access an entire integer in A with a single operation.  The elements of A are represented in binary, and the only operation we can use to access them is `fetch the jth bith of A[i]` which takes constant time.  Write code to find the missing integer.  Can you do it in O(n) time?

#### Problem Discussion
- could calculate the entire sum for the range from 0 to n.
- then would just need to iterate through each number and reconstruct using the fetch command
- for each number constructed subtract it from the total sum.
- the value contained within the sum after iterating through all integers will be the value from 0 to n that is missing (including 0).

#### Problem Design
- have a temporary integer.  Will need to iterate lg N number of fetches for each number.
- the temporary integer will be essentially forward concatention of the previous bits with the new bit at the correct shifted index.
- for now will iterate through all 30 bits (don't have to iterate through to negative for simplicity).

#### Problem Issues
- implementation is O(n log n) due to operating on all 31 bits.

#### Problem Solution
- there is a more efficient means.
- counting the number of 0s and 1s for each bit position.
- when counting for the 0th position get the count
- performs recursively through counts of the zero and one, 

### Problem 8
#### Problem Statement
>
A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte.  The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows).  The height of the screen,  of course, can be derived from the length of the array and the width.  Implment a function `drawHorizontal(byte [] screen, int width, int x1, int x2, int y)` which draws a horizontal line from (x1, y) to (x2, y).

#### Problem Discussion
- the line is only horizontal.
- will need to determine the exact starting pixel in a row 
- will need to determine the exact ending pixel in a row.
- will assume that drawing a line is equal to setting the bit value to a value of 1.
- will need to calculate the height, given is the screen (array length), and the width.  
- total length of the array is like the area and is equal to length * width.
- assuming width is not zero based.
- width and height are number of pixels not number of bytes
- assuming x and y values are zero based and always >= 0
- heights are only one pixel high
- width is at least 8 pixels

#### Problem Design
- height = (screen.length * 8) / width
- assert ((height * width) == (screen.length * 8))
- pixel @ (x, y) = screen[ (y * width / 8) + x / 8 + (x % 8 > 0 ? 1 : 0) ]
- iterate from screen[ (y * width) + x1 ] to screen[ (y * width) + x2 ] setting all positions values to 1.
- screen is in bytes not pixels will need bit mask for an individual byte.
- (0x100 >> (x % 8)) && 0xFF // sets bit in poxition x in a given byte.
- iterate from x1 to x2
- this should work for individual bit setting but not block setting, needs a little bit more logic for difference between x1 and x2.
  - for block will need to acocunt for x1 and x2 in any position within their respective blocks.
  - x1 and x2 within the same block
  - x1 and x2 in adjecent blocks