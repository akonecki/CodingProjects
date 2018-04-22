See the Assessment Guide for information on how to interpret this report.

ASSESSMENT SUMMARY

Compilation:  PASSED
API:          PASSED

Findbugs:     FAILED (1 warning)
PMD:          PASSED
Checkstyle:   FAILED (0 errors, 5 warnings)

Correctness:  27/36 tests passed
Memory:       16/16 tests passed
Timing:       31/42 tests passed

Aggregate score: 79.76%
[Compilation: 5%, API: 5%, Findbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
 16K Apr 22 01:12 KdTree.java
7.3K Apr 22 01:12 PointSET.java


********************************************************************************
*  COMPILING                                                                    
********************************************************************************


% javac PointSET.java
*-----------------------------------------------------------

% javac KdTree.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
PointSET:

KdTree:

================================================================


********************************************************************************
*  CHECKING STYLE AND COMMON BUG PATTERNS                                       
********************************************************************************


% findbugs *.class
*-----------------------------------------------------------
M P URF_UNREAD_FIELD UrF: The instance (or static) variable 'mMaxPoint' is never read. Consider removing it from the class.  At KdTree.java:[line 58]
Warnings generated: 1


================================================================


% pmd .
*-----------------------------------------------------------
KdTree.java:58: The private instance (or static) variable 'mMaxPoint' can be made 'final'; it is initialized only in the declaration or constructor. [ImmutableField]
PMD ends with 1 warning.


================================================================


% checkstyle *.java
*-----------------------------------------------------------
[WARN] KdTree.java:7:8: Unused import statement for 'java.util.Comparator'. [UnusedImports]
[WARN] KdTree.java:50:5: Declare static variables before instance variables, constructors, and methods. [DeclarationOrder]
Checkstyle ends with 0 errors and 2 warnings.

% custom checkstyle checks for PointSET.java
*-----------------------------------------------------------
[WARN] PointSET.java:146:52: The numeric literal '2.1' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 1 warning.

% custom checkstyle checks for KdTree.java
*-----------------------------------------------------------
[WARN] KdTree.java:1: Declaring 1 non-final static variables ('mVERTICAL') suggests poor design in this class. [StaticVariableCount]
[WARN] KdTree.java:371:34: The numeric literal '0.001' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 2 warnings.


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of PointSET
*-----------------------------------------------------------
Running 8 total tests.

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m

Test 1: inserting n random points and check size() after each insertion
        (size may be less than n because of duplicates)
  * 100000 random points in a 65536-by-65536 grid
  * 100000 random points in a 8192-by-8192 grid
  * 100000 random points in a 1024-by-1024 grid
  * 100000 random points in a 128-by-128 grid
  * 100000 random points in a 16-by-16 grid
==> passed

Test 2: check isEmpty() for n = 0, 1, and 2 points
  * 0 points
  * 1 point
  * 2 points
==> passed

Test 3: insert n random points and check contains() for n random query points
  * 100000 random points in a 65536-by-65536 grid
  * 100000 random points in a 8192-by-8192 grid
  * 100000 random points in a 1024-by-1024 grid
  * 100000 random points in a 128-by-128 grid
  * 100000 random points in a 16-by-16 grid
==> passed

Test 4: insert n random points and check nearest() for n random query points
  * 1000 random points in a 65536-by-65536 grid
  * 1000 random points in a 8192-by-8192 grid
  * 1000 random points in a 1024-by-1024 grid
  * 1000 random points in a 128-by-128 grid
  * 1000 random points in a 16-by-16 grid
==> passed

Test 5: insert n random points and check range() for n random query rectangles
  * 1000 random rectangles and points in a 65536-by-65536 grid
  * 1000 random rectangles and points in a 8192-by-8192 grid
  * 1000 random rectangles and points in a 1024-by-1024 grid
  * 1000 random rectangles and points in a 128-by-128 grid
  * 1000 random rectangles and points in a 16-by-16 grid
==> passed

Test 6: check intermixed sequence of calls to isEmpty(), size(),
        insert(), contains(), range(), and nearest() with probabilities
        p1, p2, p3, p4, p5, and p6, respectively
  * 10000 calls with random points in a 8192-by-8192 grid
    and probabilities 0.05, 0.05, 0.3, 0.2, 0.2, 0.2
  * 10000 calls with random points in a 1024-by-1024 grid
    and probabilities 0.05, 0.05, 0.3, 0.2, 0.2, 0.2
  * 10000 calls with random points in a 128-by-128 grid
    and probabilities 0.05, 0.05, 0.3, 0.2, 0.2, 0.2
  * 10000 calls with random points in a 16-by-16 grid
    and probabilities 0.05, 0.05, 0.3, 0.2, 0.2, 0.2
  * 10000 calls with random points in a 1-by-1 grid
    and probabilities 0.05, 0.05, 0.3, 0.2, 0.2, 0.2
==> passed

Test 7: check intermixed sequence of calls to isEmpty(), size(),
        insert(), contains(), range(), and nearest() with probabilities
        p1, p2, p3=0, p4, p5, and p6, respectively
        (data structure with 0 points)
  * 1000 calls with random points in a 1024-by-1024 grid
    and probabilities 0.5, 0.5, 0.0, 0.0, 0.0, 0.0
  * 1000 calls with random points in a 1024-by-1024 grid
    and probabilities 0.2, 0.2, 0.0, 0.6, 0.0, 0.0
  * 1000 calls with random points in a 1024-by-1024 grid
    and probabilities 0.2, 0.2, 0.0, 0.0, 0.6, 0.0
  * 1000 calls with random points in a 1024-by-1024 grid
    and probabilities 0.2, 0.2, 0.0, 0.0, 0.0, 0.6
  * 1000 calls with random points in a 1024-by-1024 grid
    and probabilities 0.2, 0.2, 0.0, 0.2, 0.2, 0.2
==> passed

Test 8: check whether two PointSET objects can be created at the same time
==> passed


Total: 8/8 tests passed!


================================================================
Testing correctness of KdTree
*-----------------------------------------------------------
Running 28 total tests.

In the tests below, we consider three classes of points and rectangles.

  * Non-degenerate points: no two points (or rectangles) share either an
                           x-coordinate or a y-coordinate

  * Distinct points:       no two points (or rectangles) share both an
                           x-coordinate and a y-coordinate

  * General points:        no restrictions on the x-coordinates or y-coordinates
                           of the points (or rectangles)

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m (inclusive).

Test 1a: insert points from file; check size() and isEmpty() after each insertion
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
==> passed

Test 1b: insert non-degenerate points; check size() and isEmpty() after each insertion
  * 1 random non-degenerate points in a 1-by-1 grid
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
  * 50 random non-degenerate points in a 128-by-128 grid
  * 500 random non-degenerate points in a 1024-by-1024 grid
  * 50000 random non-degenerate points in a 65536-by-65536 grid
==> passed

Test 1c: insert distinct points; check size() and isEmpty() after each insertion
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 8-by-8 grid
  * 20 random distinct points in a 16-by-16 grid
  * 10000 random distinct points in a 128-by-128 grid
  * 100000 random distinct points in a 1024-by-1024 grid
  * 100000 random distinct points in a 65536-by-65536 grid
==> passed

Test 1d: insert general points; check size() and isEmpty() after each insertion
  * 5 random general points in a 1-by-1 grid
  * 10 random general points in a 4-by-4 grid
  * 50 random general points in a 8-by-8 grid
  * 100000 random general points in a 16-by-16 grid
  * 100000 random general points in a 128-by-128 grid
  * 100000 random general points in a 1024-by-1024 grid
==> passed

Test 2a: insert points from file; check contains() with random query points
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
==> passed

Test 2b: insert non-degenerate points; check contains() with random query points
  * 1 random non-degenerate points in a 1-by-1 grid
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
  * 20 random non-degenerate points in a 32-by-32 grid
  * 500 random non-degenerate points in a 1024-by-1024 grid
  * 10000 random non-degenerate points in a 65536-by-65536 grid
==> passed

Test 2c: insert distinct points; check contains() with random query points
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 4-by-4 grid
  * 20 random distinct points in a 8-by-8 grid
  * 10000 random distinct points in a 128-by-128 grid
  * 100000 random distinct points in a 1024-by-1024 grid
  * 100000 random distinct points in a 65536-by-65536 grid
==> passed

Test 2d: insert general points; check contains() with random query points
  * 10000 random general points in a 1-by-1 grid
  * 10000 random general points in a 16-by-16 grid
  * 10000 random general points in a 128-by-128 grid
  * 10000 random general points in a 1024-by-1024 grid
==> passed

Test 3a: insert points from file; check range() with random query rectangles
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
==> passed

Test 3b: insert non-degenerate points; check range() with random query rectangles
  * 1 random non-degenerate points and random rectangles in a 2-by-2 grid
  * 5 random non-degenerate points and random rectangles in a 8-by-8 grid
  * 10 random non-degenerate points and random rectangles in a 16-by-16 grid
  * 20 random non-degenerate points and random rectangles in a 32-by-32 grid
  * 500 random non-degenerate points and random rectangles in a 1024-by-1024 grid
  * 10000 random non-degenerate points and random rectangles in a 65536-by-65536 grid
==> passed

Test 3c: insert distinct points; check range() with random query rectangles
  * 2 random distinct points and random rectangles in a 2-by-2 grid
  * 10 random distinct points and random rectangles in a 4-by-4 grid
  * 20 random distinct points and random rectangles in a 8-by-8 grid
  * 100 random distinct points and random rectangles in a 16-by-16 grid
  * 1000 random distinct points and random rectangles in a 64-by-64 grid
  * 10000 random distinct points and random rectangles in a 128-by-128 grid
==> passed

Test 3d: insert general points; check range() with random query rectangles
  * 5000 random general points and random rectangles in a 2-by-2 grid
  * 5000 random general points and random rectangles in a 16-by-16 grid
  * 5000 random general points and random rectangles in a 128-by-128 grid
  * 5000 random general points and random rectangles in a 1024-by-1024 grid
==> passed

Test 3e: insert random points; check range() with tiny rectangles
         enclosing each point
  * 5 tiny rectangles and 5 general points in a 2-by-2 grid
  * 10 tiny rectangles and 10 general points in a 4-by-4 grid
  * 20 tiny rectangles and 20 general points in a 8-by-8 grid
  * 5000 tiny rectangles and 5000 general points in a 128-by-128 grid
  * 5000 tiny rectangles and 5000 general points in a 1024-by-1024 grid
  * 5000 tiny rectangles and 5000 general points in a 65536-by-65536 grid
==> passed

Test 4a: insert points from file; check range() with random query rectangles
         and check traversal of kd-tree
  * input5.txt
  * input10.txt
==> passed

Test 4b: insert non-degenerate points; check range() with random query rectangles
         and check traversal of kd-tree
  * 3 random non-degenerate points and 1000 random rectangles in a 4-by-4 grid
  * 6 random non-degenerate points and 1000 random rectangles in a 8-by-8 grid
  * 10 random non-degenerate points and 1000 random rectangles in a 16-by-16 grid
  * 20 random non-degenerate points and 1000 random rectangles in a 32-by-32 grid
  * 30 random non-degenerate points and 1000 random rectangles in a 64-by-64 grid
==> passed

Test 5a: insert points from file; check nearest() with random query points
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
    - failed on trial 25 of 10000
    - sequence of points inserted: 
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
    - query point                   = (0.873, 0.503)
    - student   nearest()           = (0.564, 0.413)
    - reference nearest()           = (0.785, 0.725)
    - student   distanceSquaredTo() = 0.103581
    - reference distanceSquaredTo() = 0.057028

==> FAILED

Test 5b: insert non-degenerate points; check nearest() with random query points
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
    - failed on trial 4 of 10000
    - sequence of points inserted: 
      A  0.4375 0.5
      B  0.625 0.25
      C  0.375 0.375
      D  0.125 1.0
      E  0.6875 0.125
      F  0.5625 0.3125
      G  0.5 0.875
      H  0.3125 0.0
      I  0.0 0.1875
      J  0.25 0.5625
    - query point                   = (0.875, 0.75)
    - student   nearest()           = (0.4375, 0.5)
    - reference nearest()           = (0.5, 0.875)
    - student   distanceSquaredTo() = 0.25390625
    - reference distanceSquaredTo() = 0.15625

  * 20 random non-degenerate points in a 32-by-32 grid
    - failed on trial 22 of 10000
    - sequence of points inserted: 
      A  0.6875 0.90625
      B  0.34375 0.6875
      C  0.875 0.1875
      D  0.75 0.59375
      E  0.375 0.0
      F  0.53125 0.3125
      G  0.03125 1.0
      H  0.5625 0.25
      I  0.90625 0.40625
      J  0.125 0.15625
      K  0.78125 0.84375
      L  0.5 0.09375
      M  0.84375 0.65625
      N  0.71875 0.53125
      O  0.625 0.21875
      P  0.28125 0.9375
      Q  0.09375 0.34375
      R  0.96875 0.78125
      S  0.1875 0.75
      T  0.4375 0.28125
    - query point                   = (0.3125, 0.4375)
    - student   nearest()           = (0.09375, 0.34375)
    - reference nearest()           = (0.4375, 0.28125)
    - student   distanceSquaredTo() = 0.056640625
    - reference distanceSquaredTo() = 0.0400390625

  * 30 random non-degenerate points in a 64-by-64 grid
    - failed on trial 12 of 10000
    - query point                   = (0.625, 0.0)
    - student   nearest()           = (0.796875, 0.03125)
    - reference nearest()           = (0.6875, 0.078125)
    - student   distanceSquaredTo() = 0.030517578125
    - reference distanceSquaredTo() = 0.010009765625

  * 10000 random non-degenerate points in a 65536-by-65536 grid
    - failed on trial 2 of 10000
    - query point                   = (0.0258941650390625, 0.59674072265625)
    - student   nearest()           = (0.01300048828125, 0.595672607421875)
    - reference nearest()           = (0.0178680419921875, 0.5928802490234375)
    - student   distanceSquaredTo() = 0.000167387770489
    - reference distanceSquaredTo() = 0.000079321907833

==> FAILED

Test 5c: insert distinct points; check nearest() with random query points
  * 10 random distinct points in a 4-by-4 grid
    - failed on trial 13 of 10000
    - sequence of points inserted: 
      A  0.25 0.25
      B  0.5 1.0
      C  0.25 0.0
      D  1.0 0.5
      E  0.0 0.5
      F  0.25 0.5
      G  0.25 0.75
      H  1.0 1.0
      I  0.0 1.0
      J  1.0 0.75
    - query point                   = (0.75, 0.75)
    - student   nearest()           = (0.5, 1.0)
    - reference nearest()           = (1.0, 0.75)
    - student   distanceSquaredTo() = 0.125
    - reference distanceSquaredTo() = 0.0625

  * 15 random distinct points in a 8-by-8 grid
    - failed on trial 28 of 10000
    - sequence of points inserted: 
      A  1.0 0.625
      B  1.0 1.0
      C  0.125 0.375
      D  0.0 0.875
      E  0.625 0.5
      F  0.875 0.75
      G  0.5 0.75
      H  0.875 1.0
      I  0.5 0.0
      J  0.75 0.875
      K  0.25 0.0
      L  0.75 0.5
      M  0.25 0.375
      N  0.5 0.375
      O  1.0 0.25
    - query point                   = (0.875, 0.375)
    - student   nearest()           = (1.0, 0.625)
    - reference nearest()           = (0.75, 0.5)
    - student   distanceSquaredTo() = 0.078125
    - reference distanceSquaredTo() = 0.03125

  * 20 random distinct points in a 16-by-16 grid
    - failed on trial 12 of 10000
    - sequence of points inserted: 
      A  0.9375 0.1875
      B  0.5625 0.0625
      C  0.5625 0.8125
      D  0.5625 1.0
      E  0.6875 0.875
      F  0.375 0.4375
      G  0.125 0.4375
      H  0.3125 0.125
      I  0.75 1.0
      J  0.75 0.875
      K  0.4375 0.125
      L  0.625 0.5
      M  0.5625 0.375
      N  0.8125 0.75
      O  0.0 1.0
      P  0.375 1.0
      Q  0.375 0.3125
      R  0.25 0.125
      S  0.875 0.375
      T  0.6875 0.6875
    - query point                   = (0.75, 0.25)
    - student   nearest()           = (0.9375, 0.1875)
    - reference nearest()           = (0.875, 0.375)
    - student   distanceSquaredTo() = 0.0390625
    - reference distanceSquaredTo() = 0.03125

  * 100 random distinct points in a 32-by-32 grid
    - failed on trial 22 of 10000
    - query point                   = (0.21875, 0.4375)
    - student   nearest()           = (0.25, 0.53125)
    - reference nearest()           = (0.28125, 0.375)
    - student   distanceSquaredTo() = 0.009765625
    - reference distanceSquaredTo() = 0.0078125

  * 10000 random distinct points in a 65536-by-65536 grid
    - failed on trial 4 of 10000
    - query point                   = (0.688446044921875, 0.8146820068359375)
    - student   nearest()           = (0.6866455078125, 0.82086181640625)
    - reference nearest()           = (0.68524169921875, 0.8193206787109375)
    - student   distanceSquaredTo() = 0.000041431980208
    - reference distanceSquaredTo() = 0.000031785108149

==> FAILED

Test 5d: insert general points; check nearest() with random query points
  * 10000 random general points in a 16-by-16 grid
  * 10000 random general points in a 128-by-128 grid
    - failed on trial 22 of 10000
    - query point                   = (0.203125, 0.296875)
    - student   nearest()           = (0.1953125, 0.2890625)
    - reference nearest()           = (0.2109375, 0.296875)
    - student   distanceSquaredTo() = 0.0001220703125
    - reference distanceSquaredTo() = 0.00006103515625

  * 10000 random general points in a 1024-by-1024 grid
    - failed on trial 9 of 10000
    - query point                   = (0.90234375, 0.10546875)
    - student   nearest()           = (0.8974609375, 0.0947265625)
    - reference nearest()           = (0.9091796875, 0.1025390625)
    - student   distanceSquaredTo() = 0.000139236450195
    - reference distanceSquaredTo() = 0.000055313110352

==> FAILED

Test 6a: insert points from file; check nearest() with random query points
         and check traversal of kd-tree
  * input5.txt
    - failed on trial 1 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - sequence of points inserted: 
      A  0.7 0.2
      B  0.5 0.4
      C  0.2 0.3
      D  0.4 0.7
      E  0.9 0.6
    - query point                   = (0.87, 0.04)
    - student   nearest()           = (0.7, 0.2)
    - reference nearest()           = (0.7, 0.2)
    - student   distanceSquaredTo() = 0.0545
    - reference distanceSquaredTo() = 0.0545
    - student sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A B C E 
    - reference sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A E B C 

  * input10.txt
    - failed on trial 1 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - sequence of points inserted: 
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
    - query point                   = (0.87, 0.29)
    - student   nearest()           = (0.564, 0.413)
    - reference nearest()           = (0.564, 0.413)
    - student   distanceSquaredTo() = 0.108765
    - reference distanceSquaredTo() = 0.108765
    - student sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A C D B G J H 
    - reference sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A B G J H I 

==> FAILED

Test 6b: insert non-degenerate points; check nearest() with random query points
         and check traversal of kd-tree
  * 5 random non-degenerate points in a 8-by-8 grid
    - failed on trial 1 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - sequence of points inserted: 
      A  0.5 0.625
      B  0.875 0.375
      C  0.625 0.125
      D  0.0 1.0
      E  0.375 0.0
    - query point                   = (0.75, 0.5)
    - student   nearest()           = (0.875, 0.375)
    - reference nearest()           = (0.875, 0.375)
    - student   distanceSquaredTo() = 0.03125
    - reference distanceSquaredTo() = 0.03125
    - student sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A D E B C 
    - reference sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A B C 

  * 10 random non-degenerate points in a 16-by-16 grid
    - failed on trial 2 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - sequence of points inserted: 
      A  0.375 0.625
      B  0.75 0.1875
      C  0.125 0.0625
      D  0.875 1.0
      E  1.0 0.75
      F  0.4375 0.5
      G  0.8125 0.5625
      H  0.25 0.0
      I  0.5 0.9375
      J  0.5625 0.125
    - query point                   = (0.9375, 0.375)
    - student   nearest()           = (0.75, 0.1875)
    - reference nearest()           = (0.8125, 0.5625)
    - student   distanceSquaredTo() = 0.0703125
    - reference distanceSquaredTo() = 0.05078125
    - student sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A C B J D E 
    - reference sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A B D E F G I J 

  * 20 random non-degenerate points in a 32-by-32 grid
    - failed on trial 1 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - sequence of points inserted: 
      A  0.84375 0.40625
      B  0.53125 0.0625
      C  0.21875 0.25
      D  0.71875 0.0
      E  0.25 0.71875
      F  0.6875 0.4375
      G  0.28125 0.125
      H  0.8125 0.625
      I  0.4375 1.0
      J  0.375 0.53125
      K  0.0625 0.46875
      L  0.65625 0.5625
      M  0.875 0.90625
      N  0.15625 0.84375
      O  0.59375 0.78125
      P  0.90625 0.375
      Q  0.75 0.96875
      R  0.09375 0.6875
      S  0.78125 0.34375
      T  0.9375 0.28125
    - query point                   = (0.0, 0.875)
    - student   nearest()           = (0.15625, 0.84375)
    - reference nearest()           = (0.15625, 0.84375)
    - student   distanceSquaredTo() = 0.025390625
    - reference distanceSquaredTo() = 0.025390625
    - student sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A B C K N R E F G J I M P 
    - reference sequence of kd-tree nodes involved in calls to distanceSquaredTo():
      A B C K N R 

  * 30 random non-degenerate points in a 64-by-64 grid
    - failed on trial 1 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 16
    - number of reference entries = 7
    - entry 4 of the two sequences are not equal
    - student   entry 4 = (0.75, 0.25)
    - reference entry 4 = (0.546875, 0.671875)

  * 50 random non-degenerate points in a 128-by-128 grid
    - failed on trial 1 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 15
    - number of reference entries = 11
    - entry 3 of the two sequences are not equal
    - student   entry 3 = (0.15625, 0.78125)
    - reference entry 3 = (0.3984375, 0.734375)

  * 1000 random non-degenerate points in a 2048-by-2048 grid
    - failed on trial 1 of 1000
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 53
    - number of reference entries = 28
    - entry 8 of the two sequences are not equal
    - student   entry 8 = (0.3603515625, 0.2685546875)
    - reference entry 8 = (0.38818359375, 0.689453125)

==> FAILED

Test 7: insert n random points; check that repeated calls to get(),
         range(), and nearest() with the same argument yield same results
  * 10 random general points in a 4-by-4 grid
  * 20 random general points in a 8-by-8 grid
  * 100 random general points in a 128-by-128 grid
  * 1000 random general points in a 65536-by-65536 grid
==> passed

Test 8a: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with non-degenerate points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 72 of 20000
    - student   nearest()  = (0.3203125, 0.28125)
    - reference nearest()  = (0.234375, 0.3125)
    - student   distanceSquaredTo() = 0.0166015625
    - reference distanceSquaredTo() = 0.01177978515625

  * 20000 calls with non-degenerate points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 402 of 20000
    - student   nearest()  = (0.6826171875, 0.798828125)
    - reference nearest()  = (0.7373046875, 0.818359375)
    - student   distanceSquaredTo() = 0.011796951293945
    - reference distanceSquaredTo() = 0.002908706665039

  * 20000 calls with non-degenerate points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 159 of 20000
    - student   nearest()  = (0.96826171875, 0.67236328125)
    - reference nearest()  = (0.7359619140625, 0.9891357421875)
    - student   distanceSquaredTo() = 0.07692863047123
    - reference distanceSquaredTo() = 0.048066452145576

  * 20000 calls with non-degenerate points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 73 of 20000
    - student   nearest()  = (0.9171600341796875, 0.73486328125)
    - reference nearest()  = (0.8314971923828125, 0.84942626953125)
    - student   distanceSquaredTo() = 0.045646277489141
    - reference distanceSquaredTo() = 0.024598893942311

==> FAILED

Test 8b: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with distinct points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 10-by-10 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 126 of 20000
    - student   nearest()  = (0.2, 0.8)
    - reference nearest()  = (0.2, 0.9)
    - student   distanceSquaredTo() = 0.05
    - reference distanceSquaredTo() = 0.02

  * 20000 calls with distinct points in a 100-by-100 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 186 of 20000
    - student   nearest()  = (0.71, 0.23)
    - reference nearest()  = (0.6, 0.34)
    - student   distanceSquaredTo() = 0.0296
    - reference distanceSquaredTo() = 0.001

  * 20000 calls with distinct points in a 1000-by-1000 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 33 of 20000
    - student   nearest()  = (0.757, 0.633)
    - reference nearest()  = (0.785, 0.431)
    - student   distanceSquaredTo() = 0.047045
    - reference distanceSquaredTo() = 0.038581

  * 20000 calls with distinct points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 56 of 20000
    - student   nearest()  = (0.6695556640625, 0.7060546875)
    - reference nearest()  = (0.4559326171875, 0.8826904296875)
    - student   distanceSquaredTo() = 0.0757105499506
    - reference distanceSquaredTo() = 0.002963334321976

  * 20000 calls with distinct points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 26 of 20000
    - student   nearest()  = (0.586395263671875, 0.53582763671875)
    - reference nearest()  = (0.5590057373046875, 0.102081298828125)
    - student   distanceSquaredTo() = 0.257678230525926
    - reference distanceSquaredTo() = 0.15127029735595
    - sequence of operations was:
           st.insert(0.91961669921875, 0.0910186767578125)
           st.contains((0.8810882568359375, 0.6486968994140625))  ==>  false
           st.insert(0.497833251953125, 0.9405059814453125)
           st.insert(0.586395263671875, 0.53582763671875)
           st.nearest((0.7742156982421875, 0.394989013671875))   ==>  (0.586395263671875, 0.53582763671875)
           st.insert(0.061737060546875, 0.894989013671875)
           st.range([0.591644287109375, 0.6520843505859375] x [0.4742584228515625, 0.8388214111328125])  ==>  empty
           st.nearest((0.7405548095703125, 0.089630126953125))   ==>  (0.91961669921875, 0.0910186767578125)
           st.nearest((0.762847900390625, 0.3976898193359375))   ==>  (0.586395263671875, 0.53582763671875)
           st.nearest((0.5703887939453125, 0.81268310546875))   ==>  (0.497833251953125, 0.9405059814453125)
           st.insert(0.3010711669921875, 0.739837646484375)
           st.range([0.27789306640625, 0.598480224609375] x [0.5249481201171875, 0.7088775634765625])  ==>  T 
           st.range([0.559661865234375, 0.9792022705078125] x [0.796295166015625, 0.9455718994140625])  ==>  empty
           st.range([0.054412841796875, 0.3371734619140625] x [0.511016845703125, 0.7719268798828125])  ==>  Z 
           st.insert(0.354278564453125, 0.781890869140625)
           st.nearest((0.07598876953125, 0.4384918212890625))   ==>  (0.3010711669921875, 0.739837646484375)
           st.nearest((0.19000244140625, 0.5876007080078125))   ==>  (0.3010711669921875, 0.739837646484375)
           st.nearest((0.1506500244140625, 0.35052490234375))   ==>  (0.3010711669921875, 0.739837646484375)
           st.insert(0.36956787109375, 0.60809326171875)
           st.isEmpty()  ==>  false
           st.insert(0.5590057373046875, 0.102081298828125)
           st.contains((0.161651611328125, 0.0479888916015625))  ==>  false
           st.nearest((0.952850341796875, 0.1321868896484375))   ==>  (0.91961669921875, 0.0910186767578125)
           st.nearest((0.6179962158203125, 0.879425048828125))   ==>  (0.497833251953125, 0.9405059814453125)
           st.nearest((0.563201904296875, 0.1490325927734375))   ==>  (0.5590057373046875, 0.102081298828125)
           st.nearest((0.1885528564453125, 0.220550537109375))   ==>  (0.586395263671875, 0.53582763671875)

==> FAILED

Test 8c: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with general points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 10-by-10 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 117 of 20000
    - student   nearest()  = (0.6, 0.8)
    - reference nearest()  = (0.7, 0.9)
    - student   distanceSquaredTo() = 0.04
    - reference distanceSquaredTo() = 0.02

  * 20000 calls with general points in a 100-by-100 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 113 of 20000
    - student   nearest()  = (0.32, 0.73)
    - reference nearest()  = (0.07, 0.86)
    - student   distanceSquaredTo() = 0.0685
    - reference distanceSquaredTo() = 0.0101

  * 20000 calls with general points in a 1000-by-1000 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 183 of 20000
    - student   nearest()  = (0.454, 0.311)
    - reference nearest()  = (0.441, 0.558)
    - student   distanceSquaredTo() = 0.020969
    - reference distanceSquaredTo() = 0.013949

  * 20000 calls with general points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 116 of 20000
    - student   nearest()  = (0.9981689453125, 0.5670166015625)
    - reference nearest()  = (0.6905517578125, 0.53076171875)
    - student   distanceSquaredTo() = 0.043526962399483
    - reference distanceSquaredTo() = 0.012257635593414

  * 20000 calls with general points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 49 of 20000
    - student   nearest()  = (0.58282470703125, 0.4512481689453125)
    - reference nearest()  = (0.7475433349609375, 0.565948486328125)
    - student   distanceSquaredTo() = 0.023974461946636
    - reference distanceSquaredTo() = 0.0021269954741

==> FAILED

Test 9: check intermixed sequence of calls to insert(), isEmpty(),
        size(), contains(), range(), and nearest() with probabilities
        (p1=0, p2, p3, p4, p5, p6), respectively
        (data structure with 0 points)
  * 1000 calls with no points in a 1024-by-1024 grid
    and probabilities (0.0, 0.5, 0.5, 0.0, 0.0, 0.0)
  * 1000 calls with no points in a 1024-by-1024 grid
    and probabilities (0.0, 0.2, 0.2, 0.6, 0.0, 0.0)
  * 1000 calls with no points in a 1024-by-1024 grid
    and probabilities (0.0, 0.2, 0.2, 0.0, 0.6, 0.0)
  * 1000 calls with no points in a 1024-by-1024 grid
    and probabilities (0.0, 0.2, 0.2, 0.0, 0.0, 0.6)
  * 1000 calls with no points in a 1024-by-1024 grid
    and probabilities (0.0, 0.2, 0.2, 0.2, 0.2, 0.2)
==> passed

Test 10: check that two KdTree objects can be created at the same time
==> passed

Test 11: check that the specified exception is thrown with null arguments
  * argument to insert() is null
  * argument to contains() is null
  * argument to range() is null
  * argument to nearest() is null
==> passed


Total: 19/28 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Point2D
*-----------------------------------------------------------
Memory of Point2D object = 32 bytes
================================================================



Analyzing memory of RectHV
*-----------------------------------------------------------
Memory of RectHV object = 48 bytes
================================================================



Analyzing memory of PointSET
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a PointSET with n points (including Point2D and RectHV objects).
Maximum allowed memory is 96n + 200 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          240                264
=> passed        2          336                360
=> passed        5          624                648
=> passed       10         1104               1128
=> passed       25         2544               2568
=> passed      100         9744               9768
=> passed      400        38544              38568
=> passed      800        76944              76968
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 96.00 n + 144.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 96.00 n + 168.00  (R^2 = 1.000)
================================================================



Analyzing memory of KdTree
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a KdTree with n points (including Point2D and RectHV objects).
Maximum allowed memory is 312n + 192 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          120                160
=> passed        2          216                288
=> passed        5          504                672
=> passed       10          984               1312
=> passed       25         2424               3232
=> passed      100         9624              12832
=> passed      400        38424              51232
=> passed      800        76824             102432
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 96.00 n + 24.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 128.00 n + 32.00  (R^2 = 1.000)
================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing PointSET
*-----------------------------------------------------------
Running 14 total tests.


Inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1403063         
=> passed   320000    1572723         
=> passed   640000    1255923         
=> passed  1280000     961216         
==> 4/4 tests passed

Performing contains() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000     341006         
=> passed   320000     303377         
=> passed   640000     253539         
=> passed  1280000     327147         
==> 4/4 tests passed

Performing range() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       3785         
=> passed    20000       1624         
=> passed    40000        747         
==> 3/3 tests passed

Performing nearest() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       4761         
=> passed    20000       2218         
=> passed    40000        864         
==> 3/3 tests passed

Total: 14/14 tests passed!


================================================================



Timing KdTree
*-----------------------------------------------------------
Running 28 total tests.


Test 1a-d: Insert n points into a 2d tree. The table gives the average number of calls
           to methods in RectHV and Point per call to insert().

                                                                                                Point2D
               n      ops per second       RectHV()           x()               y()             equals()
----------------------------------------------------------------------------------------------------------------
=> passed   160000    1006043               0.0              44.3              42.3               0.0         
=> passed   320000    1099875               0.0              45.1              43.1               0.0         
=> passed   640000     861489               0.0              48.1              46.1               0.0         
=> passed  1280000     726861               0.0              52.3              50.3               0.0         
==> 4/4 tests passed


Test 2a-h: Perform contains() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to contains().

                                                                               Point2D
               n      ops per second       x()               y()               equals()
-----------------------------------------------------------------------------------------------
=> passed    10000     383453              37.0              35.0               0.0         
=> passed    20000     398224              39.3              37.3               0.0         
=> passed    40000     356218              43.6              41.6               0.0         
=> passed    80000     372522              44.0              42.0               0.0         
=> passed   160000     364638              46.5              44.5               0.0         
=> passed   320000     315558              50.1              48.1               0.0         
=> passed   640000     294208              51.4              49.4               0.0         
=> passed  1280000     253386              54.4              52.4               0.0         
==> 8/8 tests passed


Test 3a-h: Perform range() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to range().

               n      ops per second       intersects()      contains()        x()               y()
---------------------------------------------------------------------------------------------------------------
=> FAILED    10000     242213               0.0              31.1             192.9             153.6   (1.5x)
=> FAILED    20000     245812               0.0              32.6             203.8             164.2   (1.6x)
=> FAILED    40000     219946               0.0              39.3             247.2   (1.2x)    192.7   (1.9x)
=> FAILED    80000     205451               0.0              40.7             253.4   (1.3x)    202.2   (2.0x)
=> FAILED   160000     187276               0.0              42.5             270.9   (1.4x)    219.3   (2.2x)
=> FAILED   320000     159480               0.0              40.2             252.3   (1.3x)    201.1   (2.0x)
=> FAILED   640000     117477               0.0              43.3             269.8   (1.3x)    216.2   (2.2x)
=> FAILED  1280000     144973               0.0              47.0             294.9   (1.5x)    226.6   (2.3x)
==> 0/8 tests passed


Test 4a-h: Perform nearest() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to nearest().

                                         Point2D                 RectHV
               n      ops per second     distanceSquaredTo()     distanceSquaredTo()        x()               y()
------------------------------------------------------------------------------------------------------------------------
=> passed    10000   144319                 162.4                    0.0                   356.5             364.6         
=> passed    20000   109846                 234.3                    0.0                   572.3             543.1         
=> passed    40000    86655                 270.9                    0.0                   551.4             616.1         
=> passed    80000    76624                 276.5                    0.0                   694.1             621.2         
=> passed   160000    60831                 321.3                    0.0                   705.9             691.7         
=> FAILED   320000    35668                 507.3                    0.0                  1186.9   (1.5x)   1144.0   (1.4x)
=> FAILED   640000    27367                 563.0                    0.0                  1352.6   (1.7x)   1246.1   (1.6x)
=> FAILED  1280000    22376                 557.6                    0.0                  1292.0   (1.6x)   1240.0   (1.6x)
==> 5/8 tests passed



Total: 17/28 tests passed!


================================================================
