# Chapter 6 - Brain Teasers
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 6.

## Problems
### Problem 1
#### Problem Statement
>
You have 20 bottles of pills.  19 bottles have 1.0 gram pills, but one has pills of weight 1.1 grams.  Given a scale that provides an exact measurement, how would you find the heavy bottle.  You can only use the scale once.

#### Problem Discussion
- Modify the problem to:  
>
we can use the sacle unlimitedly.

- measure a pill from each bottle, this would tell us which bottle has the 1.1 gram pills.
- Now consider the constraint with one weight.  
- The total weight will need to indicate which bottle is actually being used.
  - so if each bottle contributes one pill the total weight would be 21.1 grams with no indication of the heavy pill.
  - so if bottle 1 contributes 1 pill, bottle 2 contributes 2 pills, etc to the 20th bottle.
  - the sum if all bottles where 1 gram would be the sum of (1 ... 20) equals 210.
  - with one bottom being .1 heavier, it would be n * .1 + 210 = total weigth
  - if the total weigth is equal to 220.1 then we know that only bottle one can contribute one .1 increment due to providing only one 1.1 gram pill.  
- (total weight - 210) / .1 = n

#### Problem Design
- NA

#### Problem Issues
- NA

#### Problem Solution
- Discussion points to the same conclusion as solution.

### Problem 2
#### Problem Statement
>
There is an 8x8 chess board in which diagonally opposite corners have been cut off.  You are given 31 dominos, and a single domino can cover exactly two squares.  Can you use the 31 dominos to cover the entire board?

#### Problem Discussion
- on the outer it will be 7 squares for the top, bottom, and sides to each other.
- chess board always has reprenting light and dark patterns that alternate.  Thus a domino can never cover two dark spaces, must always cover a light a dark square.  
>
D L D L D L D L          L D L D L D L
L D L D L D L D        L D L D L D L D
D L D L D L D L        D L D L D L D L
L D L D L D L D        L D L D L D L D
D L D L D L D L        D L D L D L D L
L D L D L D L D        L D L D L D L D
D L D L D L D L        D L D L D L D L
L D L D L D L D        L D L D L D L
- thus because a domino must cover both a light and dark square (due to adjency) there must exist 31 light and 31 dark squares
- cutting the ends of two opposite corners results in either 30 dark and 32 light or 32 dark and 30 light depending on which two corners are cut, making it impossible to cover the grid with 31 dominos if both squares removed are the same color opposite corners.
- this improper count leads to not a correct number of light and dark squares for 31 dominos.

#### Problem Solution
- Discussion points to the same conclusion as the solution.


