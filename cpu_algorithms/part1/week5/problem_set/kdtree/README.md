# KdTree - Week 5 Programming Assignment
## Details
This is a set of compilmentary material for **_Coursea's Princeton Algorithms_** course week 5.  The problem of this problem set deals with determining if a puzzle is solvable and if so determine the number of moves required to complete the puzzle.  The focus of this problem set is to take advantage of the minimum priority queue based on the weighted calculated priority for the given puzzle instance.

## Limitations
All code is limited to the specification specified within the assigment and this solution explicitly uses a decision tree model on the basis of comparisons.

## Design Details
This readme applies to the overall solution for the KdTree problem.

## Design Components
### PointSET
Basic use of `TreeSet` api which provides a balanced tree, but does not give any availability to the actual problem.  All operations for this requires brute force still thus is more along the lines of `java` provided balanced tree.

### KdTree
The KdTree is to take advantage of the class augmentation and the specifics of the problem.  The remainder of this will be with respect to this implementation.

#### Insert
The correctness of the insertion is required to be correct.  Original implementation I used the default comparator methods `compareTo` provided within the `Point2D` class.  However this led to the tree being incorrect.  The compare requires the orientation of the root to be taken into consideration.  

One aspect that I have not completed entirely is determining what information is needed to be available to the parent (like a closest neighbor, max, min) to support peaking on the left to determine if to go left before defaulting to the right for later traversals.

#### Range
Range support is more for range within a rectangular range of supported points.  The min and max are provided and this method will return an iterable class that of which indicates all points that are contained within the 2D range.

Due to the insertion the closest neighbor, max, min to reduce the complexity for traversal may not be maximal.

#### Nearest
Current implementation does not support a correct implementation.  For correctness could implement the full traversal inspection.  Incorrectness tries to determine when to go left or go right but has not taken into full consideration for losest neighbor, max, min.

Just taking time to get this working entirely.

## Compilation
```
javac -cp /algs4.jar:. KdTree.java PointSET.java
```

## Executing
```
java -ea -cp /algs4.jar:. KdTree ../kdtree-testing/input10.txt 
```

