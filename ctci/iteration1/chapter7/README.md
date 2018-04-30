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
