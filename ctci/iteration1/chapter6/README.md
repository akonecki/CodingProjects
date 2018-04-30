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
