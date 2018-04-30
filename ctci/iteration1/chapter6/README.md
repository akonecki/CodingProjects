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

### Problem 3
#### Problem Statement
>
You have a five quart jug, a three quart jug and an unlimited supply of water but no measuring cups.  How would you come up with exactly four quarts of water.  Note the jugs are oddly shaped, so any measurement except full and empty would not be able to give you exact measurements.

#### Problem Discussion
- can easily get 8 (5 + 3), 2 (5 filled thrown into 3 leaves 2 in the five)
- can fill between the 3 and 5 quart jugs
- 1. fill the 3 first, pour into the 5 (empty)
- 2. 3 is now left with 0, 5 now has 3
- 3. fill the 3 full
- 4. 3 now has 3, 5 still has 3
- 5. fill 5 to the top with 3
- 6. 3 now has 1, 5 now has 5
- 7. dump 5
- 8. 3 still has 1, 5 has 0
- 9. pour 3 into 5
- 10. 3 has 0, 5 has 1
- 11. fill 3
- 12. 3 has 3, 5 has 1
- 13. pour 3 into 5
- 14. 3 has zero, 5 has 4

#### Problem Solution
- Correct
- Note that the problem is rooted in prime numbers and that any number can be represented as a combination of prime numbers.
- so you only have to show that the size of the jugs provide can provide the prime numbers less than them, and therefore can provide up to a total of their sums.

### Problem 4
#### Problem Statement
>
A bunch of people are living on an island, when a visitor comes with a strange order.  All blue-eyed people must leave the island as soon as possible.  There will be a flight out at 8 pm every day.  Each person can see everyone else's eye color, but they do not know their own (nor is anyone allowed to tell them).  Additionally, they do not know how many people have blue eyes, although they do know that at least one person does.  How many days will it take the blue eyed people to leave?

#### Problem Discussion
- this assumes that when all blue eyed people have left the island the flights will stop.
- when a blue eyed person sees all brown eyed, they know they are blue and will leave.
- when a brown eyed person sees a blue, they know there is at least one blue person.
- when a blue eyed person sees another blued eyed they both know there is at least one blue eyed person.
- if a brown eyed person sees everyone and counts the number of blue eyes and the same happens with a blue eyed person their counts will differ by one.  
- Assuming it can be publicly known what the number of each person see of the total number of blue eyes, a blue eyed person will only ever see the (total number - 1).  thus they would then know that they are a blue eyed person.
- it would only take one day in this scheme as long as public count is allowed of the total number of blue eyes each person sees and then conveyed to everyone the max number of blue eyed individuals seen.

#### Problem Solution
- close to the solution but there is missing information.  
- the plane can leave with no people.
- the number of days the plan exists indicates the total number of blue eyed people, which means all people keep a tally.  Thus it takes n days where n is the number of blue eyed people and all leave on the same flight.
- close but not needing a public count because the plane is the counter.

### Problem 5
#### Problem Statement
>
There is a building of 100 floors.  If an egg drops from the Nth floor or above, it will break.  If it is dropped from any floor below is will not break.  You are given two eggs.  Find N, while minimizing the number of drops for the worst case.

#### Problem Discussion
- using only one egg can determine by just dropping an egg from each floor.
- could drop in increments of three.
  - drop from floor 3 (if break then drop 2nd egg from floor 2, if it breaks then N is 1 if not then 2)
  - if does not break from previous increment by 3.  if breaks then go to the floor below if surrives then it is that floor, otherwise the floor beneath the 2nd egg drop.
- if divided the floors by two then would have to go from the floor up if an egg breaks.
  - if start at 50 and the egg breaks then it can be floor 50 and below.  Would then have to perform increments of 1 up to 50 to double check.
  - if start at floor 4 and the egg breaks then it can be floor 4 and below.  would then have to perform increments of 1 up to 4 to double check, but get range increase by 4 if the first egg survivies.  

#### Problem Solution
- want to load balance such that drop(A) + drop(B) is always the same no matter how many times A and B are dropped.
- thus if doing a base increment of 10, A is droped at 10, if breaks then B must be dropped 10 - 1 times (9) for a total of 10 times.
- if using the same scheme breaks at 100, then A is dropped 10 times, B will need to be dropped 9 times (91 - 99)
- start at floor x go up by (x - 1) for egg A and (x - 2) for egg B.

