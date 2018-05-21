# Chapter 8 - Object Oriented Design
## Description
This contains the questions that are reflected in Cracking the Coding Interview Fifth Edition specifically chapter 8.

## Problems
### Problem 1
#### Problem Statement
> Design the data structures for a generic deck of cards.  Explain how you would subclass the data structures to implement blackjack.

#### Problem Discussion
- Dont know the rules for blackjack so would likely choose another game.

#### Problem Design
- card attributes are value and class.
- there would be a hand limit / idea of a hand
- need to be able to shuffle a deck
- need to setup the type of players and how they would interact with the deck (i.e. typically the house has a very specific set of rules).
- need a way to compare hands

#### Problem Issues
- left fairly generic
- didn't know how deep to go into implementation

#### Problem Solution
- provides a full implementation.
- solution provides a template solution of the definition of `Card` which was not taken into consideration for personal implementation.

### Problem 2
#### Problem Statement
>
Imagine you have a call center with three levels of employees: respondent, manager, and director.  An incoming call must be first allocated to a respondent who is free.  If the respondent cant handle the call, he or she must escalate the call to a manager.  If the manager is not free or not able to handle it then the call should be escalated to a director.  Design the classes and the data structures for this problem.  Implement a mehtod `dispatchCall()` which assigns a call to the first available employee.

#### Problem Discussion
- appears that a call when never stay within the same level if it gets escalated.
- all would fit under the employee class.
- each type of employee would need to be unique and indicate if availble or not.
- assumming an employee is free one the call is complete or escalated.
- is there a specific order in which calls are dispatched within a level.  Will assume round robin.
- when a call gets escalated does the employee dispatch it or does the system dispatch it.
- will likely need an employee to determine if the call is completed or needs to be escalated.

#### Problem Design
- see implementation.

#### Problem Issues
- don't really know who / what is responsible for the `dispatchCall()`.  Is the call center itself via another system or does the individual bare the responsibility.

#### Problem Solution
- Solution provides implementation of list of list for each type of employee.
- Solution does not still provide logic on how to determine which list of employees to use.
- Employee logic is more detailed.
- the overall `CallHandler` class was treated as the callcenter.
- `dispatchCall` is handled by the call center instead of the employees.

### Problem 3
#### Problem Statement
>
Design a musical jukebox using OOP.

#### Problem Discussion
- basic capabilities of a juke box is that is plays a set of songs.
- is this a `need to pay` juke box or more of a juke box application?
- will assume that it is a paying juke-box / supports only the playing of specific number of songs.
- juke box will have a set of available songs.
- an individual can select individual songs.
- an individual can add songs to a queue that contains the order in which songs will be played.
- songs can be removed from the queue (either through playing or removal prior to playing).
- if a song is removed before being played this does not impact the number of available songs for the juke box session.
- a song can be paused.
- a song can be skipped (if within a specific window of time does not count toward song count allocation)
- a user will need to be able to search for songs
- the user must indicate to start playing (pressing play).

#### Problem Design
- likely need the jukebox owning class to be a singleton (only supporting one instance).
- the queue of songs will could be a doubly linked list to allow for easier insertions etc.