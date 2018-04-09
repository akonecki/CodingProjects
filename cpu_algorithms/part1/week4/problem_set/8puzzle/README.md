# 8Puzzle - Week 4 Programming Assignment
## Details
This is a set of compilmentary material for **_Coursea's Princeton Algorithms_** course week 4.  The problem of this problem set deals with determining if a puzzle is solvable and if so determine the number of moves required to complete the puzzle.  The focus of this problem set is to take advantage of the minimum priority queue based on the weighted calculated priority for the given puzzle instance.

## Limitations
All code is limited to the specification specified within the assigment and this solution explicitly uses a decision tree model on the basis of comparisons.

## Design Details
This readme applies to the overall solution for the 8puzzle problem.

## Design Components
### Board
Board is the base class that represents the state of the board at a specifc point within the game.  The game itself is a collection of these boards that represent valid movements of the tile values.

For simplicity, the values that a board represents are only numbers within this problem set.  Each tile is a number and the field with the value of zero is the empty space.  The idea is to the values of the puzzle into asscending order.

#### Board Iterator
The lack of a formal API definition in the problem set leads to some difficulty in understanding the problem.  We know that valid moves are only possible my moving tiles within the empty space.  Due to the constraint of the problem the only valid moves relate to ones that contain tiles that lie next to the empty space.  Thus only top, bottom, left, right movements of tiles into the empty space are permitted.

The `neighbors()` iterator returns an iterable of boards in which the tiles have been moved with respect to the supported directions mentioned above.  This allows the creation of new boards in which values are moving.  The iterator is then used by the `Solver` to determine if the boards need to be added to the priority queue.

### Solver
The solver is quite simple in the fact that it wraps the `Board` class.  The `Solver` class is responsible for owning the priority queue instances.  The determiniation of the comparable key is based on the board's computed priority value that is included in the problem set.  Due to the `Board` not being a prime data type member it is best to provide a comparator class within the `Solver` class wrapping the `Board` class instance.

#### SearchNode
To provide a comparator class that can be used by the priority queue, the following class definition was defined.

```java
private class SearchNode implements Comparator<SearchNode> {

}
```

This class wraps the necessary data attributes and indicates the move count for the current board layout, the predecessor step of the game and the current board as well.  Since this class implements the `Comparator` interface, the compare method is required to be detailed wihtin this private class.  This allows for the comparison of three instances of the `SearchNode` class, with the base being a null defined on the private members; thus the comparison that is useful is between the two `SearchNode` objects that are passed as arguments to the compare method itself.

Since boards are created at each step via the `neighbors()` method mentioned above from the `Board` class, the `SearchNode` class objects are created in addition as well.

### Implementation Hazards
In reality the most difficult part of this problem set is understanding the API definitions that exist between the `Board` and `Solver` class.  The limitation on this API requires understanding and the difficult part that was not explicitly mentioned were:  
1. The problem set does not indiciate that a Comparator class is needed.
2. The definition of the `neighbors()` method is something to be desired.

## Compilation
```
javac -cp /algs4.jar:.:../ Solver.java
```

## Executing
```
java -cp /algs4.jar:.:../ Solver ../8puzzle-testing/puzzle2x2-unsolvable1.tx
```

