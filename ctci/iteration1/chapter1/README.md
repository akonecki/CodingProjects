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

### Problem 3
#### Problem Statement 
>Given two strings determine if one is a permutation of the other.

#### Problem Discussion
- Could be a word or letter level but in reality the approach should be roughly the same.
- I will assume that it is character composition without the correctness of white character placement for simplity.
- Strings can be arbitrary long.
- Assume that the definition of permutation is that the lengths must be the same lenght and the the same characters and number of character occurrences must both be the same but the orders may differ.
- With this assumption it can be stated that if the lengths of the two string differ, they can not be permutations of each other (non-symmetric relationship).
- Want to state that a permutation of a string is accomplished through the function of F(S) which produces S' where S' is a permutation of S.  Let there exist another function F' such that F'(S') produces S, thus applying the inverse of the permutation.

#### Problem Design
- No matter what will need to iterate through both strings for all characters.
- Will assume that ASCII characters will be used thus total is 8-bits of representation, and can be trimmed to only the 7-bit range if only want to consider the first half of the table.
- Go through one string and record the occurrence of each letter.  Use the letter as the key, the value is the number of occurrences.  Evaluation of the keys need to be quick, thus should not have to examine all other keys.  Dictionary / symbol / hash is appropriate to manage the average case access times. 
- Pre-allocate the total number of cells needed to hold the values, to not have to deal with increasing / decreasing the total memory impact (will be inefficient for strings that use only a specific portion of the bit of representation.  In a totally random this would not be the case.)

#### Implementation Issues
- Just need to remember that String length is a function call not an attribute accessible field.

#### Solution Points
- there are two solutions provided.
- Solution 1 states about performing a sort on the characters within the string and then checking if the strings are the same.  This would result in O(nlogn) time due to the sorting algorithm.
- Solution 2 follows the same approach as my solution.  My solution however has a few more checks to return early if the strings happen to already be referencing the same string.

### Problem 4
#### Problem Statement
>Write a method to replace all spaces in a string with `%20`.  You may assume that the string has sufficient space at the end of the string to hold the additional characters, and that you are given the true length of the string.  For `Java` implementations use a character array to perform this in place.

#### Problem Discussion
- Does the string have intentional spaces at the end that are part of the actual string?
- for the characters at the end are they white spaces already in bit-representation or say zero'd values.
- Performing in place from the front will be difficult, start from the end and progress until you encounter a non-whitespace or non-terminating character for the allocated size.
- Moves characters from the current index to their new index as it progresses in reverse.

#### Problem Design
- Start from the back of the array using the array's true length.
- Must have seen a non-space character previously to process a space (this assumes that there is only one space used between words if multiple would need a flag to indicate that spaces can begin to be processed).
- Real index will keep track of the position in the array of the next index to store a character.
- If the last value in the array is already populated with a non-white space character then there is no need to inspect the character array (no spaces present).
- You know that when the pivot index that indicates where the next character is saved equals to the current iteration index, then there is no more spaces, since a space always requires + 3 more characters.

#### Problem Issues
- Did not look closely enough at the definition of the true length. 
- True length indicates where to actually start accepting spaces to be converted.
- All data behind the true length can be ignored or any form.

#### Problem Solution
- Book does a double pass of the data up to the indicated true length.
- The first pass is to determine the number of qualified spaces which are thne used to determine the total size of the allocated memory.
- In C/C++ this is necessary, in Java however it is not due to the fact that the total allocated memory associated with an array is always provided.
- The calculation of total space necessary is more conserative and cross language.

### Problem 5
#### Problem Statement
>Implement a method that performs basic string compression using the counts of repeated characters.  If the compressed string is not shorter than the original then the original should be returned instead.  Assume the string only uses lowcase ASCII letters.

#### Problem Discussion
- For `Java` implementation will need to still use char array since String type is not mutuable.
- Application is limited to lower case only.
- String length is important
- Need to determine if a length of one needs to be accounted for or not.  In the example given it is needed.  So this means that if compression occurs then all character lengths need to be accounted for otherwise use the original string with no counts.
- Likely should target in place manipulation.

#### Problem Design
- An easier implementation would be to use additional storage and create a new compressed array and then return the shorter of the two.
- In place requires the measuring of the total size of characters needed.  If the total size is less than the original then can do.
- In place also has issue with if there are lot of back to back sizes of 1 then will lose the next character due to being over-written, would need to start the modification from the back, but the same problem persists.  Can only have so many 1 compressions in a row before you would start needing to have additional data structures, which would defeat the whole purpose of performing an in place compression.
  - Could replace all compressions greater than one first
  - compress the 1s but the logic for that seems awful.
- due to the compression logic of one will implement a copy of the data to a block of memory that is the needed space if the space needed is less than the original.

#### Problem Issues
- Trying to do in place was waste of time.
- Break problem up more in the written stage.

#### Problem Solution
- My design actually hit the more optimized solution that does not rely upon StringBuffers.

### Problem 6
#### Problem Statement 
>Given an image represented by NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.  Can you do this in place?

#### Problem Discussion
- A simplified matrix below  
>
1    2    3    4      13    9    5    1    1    2    3      9    4    1
5    6    7    8      14    10   6    2    4    5    6      8    5    2
9   10   11   12      15    11   7    3    7    8    9      7    6    3
13  14   15   16      16    12   8    4

>
1    2    3    4     5       21    16    11    6    1  
6    7    8    9     10      22    17    12    7    2
11   12   13   14    15      23    18    13    8    3
16   17   18   19    20      24    19    14    9    4
21   22   23   24    25      25    20    15    10   5

- Without considering the the constraint of in-place, will need to rotate the elements to their new locations within the matrix.  
- NxN constraint allows for simplification.
- Can always move in specific set of directions, left to right, top to bottom, right to left, bottom to top.
- The rows and columns that are needed to for modification decreases by two (removing the left, top, bottom, right most sides after performing N - 1 sets (this would perform all directions movements for all rows and columns on the outter most columns and rows).
- Will need to preserve the location being replaced in-between states above.

#### Problem Design
- Have a proper definition for movements of data from
  - left to right (x, y) => (x + n - j - 1, y + j)
  - top to bottom (x, y) => (x - j, y - n + j + 1)
  - right to left (x, y) => (x - n + j + 1, y - j)
  - bottom to top (x, y) => (x + j, y + n - j - 1)
- Transitions will be for (N -1) - (i * 2) where N is the matrix size, i is the ith iteration
- Will only need to perform through half (apply the movements starting from the top left will lead to being applied to the bottom as well.
  - 4x4 would be 
  - (4 - 1) - (0 * 2) (outer layer) # of repetitions of the movements above <3>
  - (4 - 1) - (1 * 2) (inner layer) # of repetitions of the movements above <1>
  - 3x3 would be
  - (3 - 1) - (0 * 2) (outer layer) # of repetitions of the movements above <2>
  - (3 - 1) - (1 * 2) (inner layer) # of repetitions of the movements above <0>
- In the end this is application of the movement of data one layer at a time, if odd the center wont move.

#### Problem Issues
- handling of correctly of the index offsets was troubling.
- it might have been easier to do local calculations instead of from the total layer, element index.

#### Problem Solution
- Solution provided a slightly more elgant way of handling the indicies in terms of variables but actually the same.
