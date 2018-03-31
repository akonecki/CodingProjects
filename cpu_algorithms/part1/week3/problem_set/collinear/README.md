# Collinear Points - Week 3 Programming Assignment
## Details
This is a set of compilmentary material for **_Coursea's Princeton Algorithms_** course week 3.  The problem of this problem set deals with determining the lines that are collinear in nature where the number of points is greater than 4.

## Limitations
All code is limited to the specification specified within the assigment and this solution explicitly uses a decision tree model on the basis of comparisons.

## Design Details
This readme is only in pertains to the FastCollinearPoints solution and not 
the trival BruteCollinearPoints solution due:

1.  limited number of applicapable test cases due to O(N^4) worse case timing complexity.
2.  sanitized input; there will only ever been collinear points that have only 4, never more.
3.  did not consider single order of collinear line segments representation in the solution.

## Design Components
### Point
Point is the a supporting class that provides some of the underlying representation of a 2D point.  An x and y coordinate are associated with each point object in addition to some supported methods.

1.  `slopeTo` - Determining the slope between two points.  
2.  `compareTo` - Compare two points in y-major order.  This method will be used by default by java sorting methods due to overriding the object's base `compareTo` method.  
3.  `slopeOrder` - obtain a comparator to support comparing the slope of the current point class to two other points for collinear ordering.  This class is responsible for only the instance of the comparator that is used by a agent that can be passed custom comparator objects, e.g. Arrays.sort() in Java.  Although not mentioned in the assignment, a private class that implements the comparator needs to be implemented; providing the definition of the `compare` method.
4.  `compare` - compare the collinear order between the current object point instance and two other points.

#### Comparator Reference Sample
```java
private class ClassName implements Comparator<Type> {
        public int compare(Type objectOne, Type objectTwo) {
            // Provide your own method.
            return 0;
        }
```

### FastCollinearPoints
The problem statement provides a step by step order of operations and thus will be provided below for reference.

1.  Think of *p* as the origin.
2.  For each other point *q*, determine the slope it makes with *p*.
3.  Sort the points accordingly to the slopes they make with *p*.
4.  Check if any 3 or more adjacent points in the sorted order have equal slopes with respect to *p*.  If so, these points w.r.t *p* are collinear.

These steps provide the basic outline needed to support the implementation of a FastCollinearPoints problem that is limited to decision trees (no hashing allowed).  However there are a few steps of what is occurring that can be expanded upon for an actual implementation.

#### Additional Steps to Consider

1.  Localize a copy of the set of points provided.  The array of points is not guaranteed to be ordered and manipulation of the original supplied points is ill-advised.
2.  Remember the properities associated with sorting algorithms, specifically stability and which algorithms support this.
3.  Will want to iterate through all the points and compare to other points, more specifically at least three other points for this problem.  Remeber the available API definition of the `Point` class.  From the steps above orginizing the slopes of all other points with respect to *p* is provided via the implemented comparator.
4.  Implement the in-correct version first, where if the number of points with the same slope is greater than 3 then it is a solution i.e. record the end points.  **This implementation will result in sub-segments appearing in the solution.**
5.  Removal of sub-segments requires a bit of thinking.  You do not want to check the slope again of all saved line segments for this will impact your worse case execution time.  If you implemented a typical iteration of the points for comparison you will likely have difficultly.  

```java
    for (int indexP = 0; indexP < numberOfPoints; indexP++) {
        for (int indexQ = indexP + 1; indexQ < numberOfPoints; indexQ++) {
            // Perform work.
        }
    }
```

The above code snippet results in `N*(N-1) / 2` executions of the work and is typical of wanting to compare looking forward only from a single point.

```java
    for (int indexP = 0; indexP < numberOfPoints; indexP++) {
        for (int indexQ = 0; indexQ < numberOfPoints; indexQ++) {
            // Perform work.
        }
    }
```

The above code snippet results in `N*N` executions of the work, but includes all previous points as well.  

It is important to realize that both result in O(N^2) operations of work.

6.  Take advantage of the `compareTo` method between points to prevent the adding of sub-segments to your solution in combination with the above information.
7.  You may ignore *double* comparisons to get a functional solution.  Addressing this problem is beyond the problem set but should be mentioned that is it not ideal to perform `==` operations between *float* and *double* primitive types.

## Compilation
```
javac -cp /nebo_ws/algs4.jar:/nebo_ws/part1/week3/problem_set/.:. FastCollinearPoints.java BruteCollinearPoints.java
```

## Executing
```
java -cp /nebo_ws/algs4.jar:/nebo_ws/part1/week3/problem_set/.:. FastCollinearPoints ../collinear-testing/input1.txt
```

