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
- assumption that the probability of making any one shot is independent from another, meaning that one does not impact the other.  You either make it or you dont.
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
- decision of each ant for their direction is independent from each other.
- a line segment a ant only has two directions say left or right.
- all three ant selecting left is 1/2 and 1/2 and 1/2 or all ants selecting right is 1/2 and 1/2 and 1/2.
- for three ants this is 1/8 + 1/8 = 1/4
- generally speaking it is [2 * 1/(2^n)] for n number of ants for a collision to not occur.
- so a collision will occur 1 - [2 * 1/(2^n)] for any number of N ants and 3/4 for three ants.

#### Problem Solution
- Same as discussion above.

### Problem 3
#### Problem Statement
>
Given two lines on a Cartesian plane, determine whether the two lines would intersect.

#### Problem Discussion
- in an infinitely large cartesian plane two lines will always intersect as long as their slopes are not parallel and they do not overlap each other unless end-points are considered.
- guessing this is endpoint driven
- supported x values for a given line would be x1 to x2 
- supported y values for a given line would be y1 to y2 
- need to know the range of x and y values for a given line that are valid
- if the points are outside of each others ranges then will never intersect.
- only have to examine if there is overlap in both x and y coordinates
- should be able to just setup a system of equations and calculate the x y position needed for two lines to intersect then perform a range validation from these determined values

#### Problem Design
- line has two points
- line will be ordered such that x1 <= x2 (major ordering element)
- slope will also be determined from the end points
- y1 = ax1 + b
- y2 = ax2 + b
- y1 == y2, x1 == x2 :: a1x1 + b1 = a2x2 + b2 
- a1, a2, b1, b2 can be calculated from the two given points (these are for the infinite plane lines).
- a1x1 = b2 - b1 + a2x2, a1x1 - a2x2 = b2 - b1, a1x - a2x = b2 - b1, x(a1 - a2) = (b2 - b1), x = (b2 - b1) / (a1 - a2)
- plug-in for y the will give the (x,y) coordinate where the two lines whould possible intersect.  if (x,y) lives in both lines then an intersection occurs else no intersection.

#### Problem Issues
- made the problem way harder than needed due to not wanting to handle the double's

#### Problem Solution
- difference between values to determine equality uses an epsilon to provide a level of accuracy.  
- equations were the same which is good, basic geometry.

### Problem 4
#### Problem Statement
>
Write the methods to implement the multiply, subtract and divide operations for integers.  Use only the add operator.

#### Problem Discussion
- multiplication should just be a loop on the addition operation of n times, can also left shift then add based on how much multiplication can not be done with left shifting.
- subtract could just add the two's complement but don't know if that is what is desired.
- division perform the implemented subtraction in loop if desired.
- sounds like shifting really isnt allowed due to operation limitation.

#### Problem Issues
- Scope of the problem led me down trying to do more bit manipulation.

#### Problem Solution
- Really just consider addition.
- Need to handle negative correctly.

### Problem 5
#### Problem Statement
>
Given two squares in a two dimensional plane, find a line that divides both squares in half.  Assume the top and bottom sides run parallel to the x-axis.

#### Problem Discussion
- is no solution a valid solution.
- if a solution exists then it must exist for one square as well.
- iterating through all the lines that intersect one square and comparing with the other is not feasible.
- have to use some percision factor for calculating the area to ensure that both squares 
- at most 4 points above and 4 points below

#### Problem Issues
- Don't really know where to start.
- line must always connect the two middles, this is quite critical since it provides the slope.

#### Problem Solution
- start with the middle since the line must always go through both centers.
- have to consider the squares being vertical thus resulting in a divide by zero problem.

### Problem 6
#### Problem Statement
>
Given a two dimensional graph with points on it, find a line which passes the most number of points.

#### Problem Discussion
- brute force method can be to just iterate through all the points and connect to each one, recording the slope that occurs the most.
- Similar to the colinear problem which performs sorting based on slope.  
- Sort between three points, pivot point to the other two reference points and place in order

#### Problem Issues
- None, implemented a solution based on sorting, might be abit slower.

#### Problem Solution
- Calls out recording the slope and end point in a hashtable and iterating through all to determine if the points already exist within the defined line segments with the consideration for eplsion values.
- Epslion values in my solution would be taken care of in just comparing the slope values.

### Problem 7
#### Problem Statement
>
Design an algorithm to find the kth number such that the only prime factors are 3, 5, and 7.

