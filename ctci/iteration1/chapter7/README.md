# Chapter 7 - Mathematics & Probability
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 7.

## Problems
### Problem 1
#### Problem Statement
>
You have a basket ball hoop and someone says that you can play one of two games.
- Game 1: you get one shot to make the hoop
- Game 2: You get three shots and you have to make two of three shots or all three.

If p is the probability of making a particular shot, for which values of p should you pick one game or the other.

#### Problem Discussion
- assumption that the probability of making any one shot is mutually exclusive from another, meaning that one does not impact the other.  You either make it or you dont.
- if p = .5 
  - Game 1 : .5 (only one shot)
  - Game 2 : three shots each at .5 chance of making. 1/2 * 1/2 * 1/2 for problem of all three shots at p = .5 is 1/8 and chance of missing all three shots is also 1/8.  So three quarters support the other possibility ratios. 3 * 1/8 for 1 in 3, 3 * 1/8 for 2 in 3 + 1/8 for all three.
  - so for any two out of three combination at p = .5 is 4/8 < .5  so Game 1 would be the winner.
- if p = .25
  - Game 1 : .25 (only one shot)
  - Game 2 : 3 * (1/4 * 1/4 * 3/4) + (1/4 * 1/4 * 1/4) = 9/64 + 1/64 
  - so 10 / 64 < .25 so game 1
- if p = .75
  - Game 1 : .75 (only one shot)
  - Game 2 : 3 * (3/4 * 3/4 * 1/4) + (3/4 * 3/4 * 3/4) = 27/64 + 27/64 = 54/64
  - so 54/64 > .75
- if p = 1/3
- 

#### Problem Design
- NA

#### Problem Issues
- NA

#### Problem Solution
- Practice quadratic equation (-b +- sqrt(b^2 - 4ac)) / 2a

### Problem 2
#### Problem Statement
>
There are three anys on different vertices of a triangle.  What is the probability of collision (between any two or all three) if they start walking on the sides of the triangle?  Assume that each any randomly picks a direction, with either direction being equally likely to be chosen, and that they walk at the same speed.  Similarly, find the probability of collision with n ants on a n-vertex polygon

#### Problem Discussion
- if all ants go the same direction then they will never collide
- decision of each ant for their direction is mutually exclusive from each other.
- a line segment a ant only has two directions say left or right.
- all three ant selecting left is 1/2 and 1/2 and 1/2 or all ants selecting right is 1/2 and 1/2 and 1/2.
- for three ants this is 1/8 + 1/8 = 1/4
- generally speaking it is [2 * 1/(2^n)] for n number of ants for a collision to not occur.
- so a collision will occur 1 - [2 * 1/(2^n)] for any number of N ants and 3/4 for three ants.

#### Problem Solution
- Same as discussion above.
