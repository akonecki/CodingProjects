# Lesson 1 - Recursion
## Lesson Description
Looking into the actual inspection of several different recursion method implementations.  Each implementation will break down the recursion tree depth to determine the actual expected result.

## Problems
### Problem 1
>
```java
public static int f1(int N) {
    if (N < 0) return 0;
    if (N == 0) return 1;
    return f1(N-1) + f1(N-2) + f1(N-3);
}
```

#### Desired Outputs
##### f1(3)
Call Tree
>
```
f1(3)
    -> f1(2)
        -> f1(1)
            -> f1(0)
                <- 1
            -> f1(-1)
                <- 0
            -> f1(-2)
                <- 0
            <- 1 + 0 + 0
        -> f1(0)
            <- 1
        -> f1(-1)
            <- 0 
        <-  (1 + 0 + 0) + 1 + 0
    -> f1(1)
        -> f1(0)
            <- 1
        -> f1(-1)
            <- 0
        -> f1(-2)
            <- 0
        <- 1 + 0 + 0
    -> f1(0)
        <- 1
    <- ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + (1) = 4
```

##### f1(5)
Call Tree
>
```
f1(5)
    -> f1(4)
        -> f1(3)
            -> f1(2)
                -> f1(1)
                    -> f1(0)
                        <- 1
                    -> f1(-1)
                        <- 0
                    -> f1(-2)
                        <- 0
                    <- 1 + 0 + 0
                -> f1(0)
                    <- 1
                -> f1(-1)
                    <- 0
                <- (1 + 0 + 0) + 1 + 0
            -> f1(1)
                -> f1(0)
                    <- 1
                -> f1(-1)
                    <- 0
                -> f1(-2)
                    <- 0
                <- 1 + 0 + 0
            -> f1(0)
                <- 1
            <- ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + (1)
        -> f1(2)
            -> f1(1)
                -> f1(0)
                    <- 1
                -> f1(-1)
                    <- 0
                -> f1(-2)
                    <- 0
                <- 1 + 0 + 0
            -> f1(0)
                <- 1
            -> f1(-1)
                <- 0
            <- (1 + 0 + 0) + 1 + 0
        -> f1(1)
            -> f1(0)
                <- 1
            -> f1(-1)
                <- 0
            -> f1(-2)
                <- 0
            <- 1 + 0 + 0
        <- (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + (1)) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)
    -> f1(3)
        -> f1(2)
            -> f1(1)
                -> f1(0)
                    <- 1
                -> f1(-1)
                    <- 0
                -> f1(-2)
                    <- 0
                <- 1 + 0 + 0
            -> f1(0)
                <- 1
            -> f1(-1)
                <- 0
            <- (1 + 0 + 0) + 1 + 0
        -> f1(1)
            -> f1(0)
                <- 1
            -> f1(-1)
                <- 0
            -> f1(-2)
                <- 0
            <- 1 + 0 + 0
        -> f1(0)
            <- 1
        <- ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + (1)
    -> f1(2)
        -> f1(1)
            -> f1(0)
                <- 1
            -> f1(-1)
                <- 0
            -> f1(-2)
                <- 0
            <- 1 + 0 + 0
        -> f1(0)
            <- 1
        -> f1(-1)
            <- 0
        <- (1 + 0 + 0) + 1 + 0
    <- ((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + (1)) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) + (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + (1)) + ((1 + 0 + 0) + 1 + 0) = 13
```

### Problem 2
Caount the number of ways to build a proper BST.
>
```java
public static int f2(int N) {
    if (N <= 1) {
        return 1;
    }

    int total = 0;
    for (int i = 0; i < N; i++) {
        total += f2(i) * f2(N-i-1);
    }        
    return total;
}
```

#### Desired Outputs
##### f2(3)
Call Tree
>
```
f2(3)
    // i = 0
    -> f2(0)
        <- 1
    -> f2(2)
        // i = 0
        -> f2(0)
            <- 1
        -> f2(1)
            <- 1
        // i = 1
        -> f2(1)
            <- 1
        -> f2(0)
            <- 1
        <- (1 * 1) + (1 * 1) = 2
    <- (1 * 2) = 2
    // i = 1
    -> f2(1)
        <- 1
    -> f2(1)
        <- 1
    <- (1 * 1)
    // i = 2
    -> f2(2)
        // i = 0
        -> f2(0)
            <- 1
        -> f2(1)
            <- 1
        // i = 1
        -> f2(1)
            <- 1
        -> f2(0)
            <- 1
        <- (1 * 1) + (1 * 1) = 2
    -> f2(0)
        <- 1
    <- (2 * 1)
<- (2) + (1) + (2) = 5
```

##### f2(4)
Call Tree
>
```
f2(4)
    // i = 0
    -> f2(0)
        <- 1
    -> f2(3)
        <- 5 (from the graph of above)
    <- (1 * 5) = 5
    // i = 1
    -> f2(1)
        <- 1
    -> f2(2)
        // i = 0
        -> f2(0)
            <- 1
        -> f2(1)
            <- 1
        // i = 1
        -> f2(1)
            <- 1
        -> f2(0)
            <- 1
        <- (1 * 1) + (1 * 1) = 2
    <- (1 * 2) = 2
    // i = 2
    -> f2(2)
        // i = 0
        -> f2(0)
            <- 1
        -> f2(1)
            <- 1
        // i = 1
        -> f2(1)
            <- 1
        -> f2(0)
            <- 1
        <- (1 * 1) + (1 * 1) = 2
    -> f2(1)
        <- 1
    <- (2 * 1) = 2
    // i = 3
    -> f2(3)
        <- 5 (from the graph above)
    -> f2(0)
        <- 1
    <- (5 * 1) = 5
<- 5 + 2 + 2 + 5 = 14
```

### Problem 3
>
```java
public static List<List<Integer>> f3(int[] n) {
    List<List<Integer>> results = new LinkedList();
    f3(n, 0, results, new LinkedList<Integer>());
    return results;
}

private static void f3(int[] n, int i, List<List<Integer>> results,  
                       List<Integer> path) {
    if (i == n.length) {
        results.add(path);
        return;
    }

    List<Integer> newPath = new LinkedList(path);
    newPath.add(n[i]);

    f3(n, i+1, results, path);
    f3(n, i+1, results, newPath);
}
```

#### Desired Outputs
##### f3({1,2,3})
Call Tree
>
```
f3({1,2,3}, 0, [[]], [])
    // newPath = [1]
    -> f3({1,2,3}, 1, [[]], [])
        // newPath = [2]
        -> f3({1,2,3}, 2, [[]], [])
            // newPath = [3]
            -> f3({1,2,3}, 3, [[]], [])
                <- [] added to results
            -> f3({1,2,3}, 3, [[]], [3])
                <- [3] added to results
        -> f3({1,2,3}, 2, [[]], [2])
            // newPath = [2, 3]
            -> f3({1,2,3}, 3, [[]], [2])
                <- [2] added to results
            -> f3({1,2,3}, 3, [[]], [2, 3])
                <- [2, 3] added to results
    -> f3({1,2,3}, 1, [[]], [1])
        // newPath = [1, 2]
        -> f3({1,2,3}, 2, [[]], [1])
            // newPath = [1, 3]
            -> f3({1,2,3}, 3, [[]], [1])
                <- [1] added to results
            -> f3({1,2,3}, 3, [[]], [1, 3])
                <- [1,3] added to results
        -> f3({1,2,3}, 2, [[]], [1, 2])
            // newPath = [1, 2, 3]
            -> f3({1,2,3}, 3, [[]], [1, 2])
                <- [1, 2] added to the results
            -> f3({1,2,3}, 3, [[]], [1, 2, 3])
                <- [1, 2, 3] added to the results
<- [[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]
```

##### f3({1,2,3,4,5})
Call Tree
>
```
f3({1,2,3,4,5}, 0, [[]], [])
    // newPath = [1]
    -> f3({1,2,3,4,5}, 1, [[]], [])
        // newPath = [2]
        -> f3({1,2,3,4,5}, 2, [[]], [])
            // newPath = [3]
            -> f3({1,2,3,4,5}, 3, [[]], [])
                // newPath = [4]
                -> f3({1,2,3,4,5}, 4, [[]], [])
                    // newPath = [5]
                    -> f3({1,2,3,4,5}, 5, [[]], [])
                        <- [] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [5])
                        <- [5] added to results
                -> f3({1,2,3,4,5}, 4, [[]], [4])
                    // newPath = [4, 5]
                    -> f3({1,2,3,4,5}, 5, [[]], [4])
                        <- [4] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [4, 5])
                        <- [4, 5] added to results
            -> f3({1,2,3,4,5}, 3, [[]], [3])
                // newPath = [3, 4]
                -> f3({1,2,3,4,5}, 4, [[]], [3])
                    // newPath = [3, 5]
                    -> f3({1,2,3,4,5}, 5, [[]], [3])
                        <- [3] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [3, 5])
                        <- [3,5] added to results
                -> f3({1,2,3,4,5}, 4, [[]], [3, 4])
                    // newPath = [3,4,5]
                    -> f3({1,2,3,4,5}, 5, [[]], [3, 4])
                        <- [3, 4] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [3, 4, 5])
                        <- [3, 4, 5] added to results
        -> f3({1,2,3,4,5}, 2, [[]], [2])
            // newPath = [2, 3]
            -> f3({1,2,3,4,5}, 3, [[]], [2])
                // newPath = [2, 4]
                -> f3({1,2,3,4,5}, 4, [[]], [2])
                    // newPath = [2,5]
                    -> f3({1,2,3,4,5}, 5, [[]], [2])
                        <- [2] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [2,5])
                        <- [2,5] added to results
                -> f3({1,2,3,4,5}, 4, [[]], [2, 4])
                    // newPath = [2,4,5]
                    -> f3({1,2,3,4,5}, 5, [[]], [2,4])
                        <- [2,4] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [2,4,5])
                        <- [2,4,5] added to results
            -> f3({1,2,3,4,5}, 3, [[]], [2, 3])
                // newPath = [2,3,4]
                -> f3({1,2,3,4,5}, 4, [[]], [2, 3])
                    // newPath = [2,3,5]
                    -> f3({1,2,3,4,5}, 5, [[]], [2,3])
                        <- [2,3] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [2,3,5])
                        <- [2,3,5] added to results
                -> f3({1,2,3,4,5}, 4, [[]], [2, 3, 4])
                    // newPath = [2,3,4,5]
                    -> f3({1,2,3,4,5}, 5, [[]], [2,3,4])
                        <- [2,3,4] added to results
                    -> f3({1,2,3,4,5}, 5, [[]], [2,3,4,5])
                        <- [2,3,4,5] added to results
    .... for the [1] starting
<- [[], [5], [4], [4, 5], [3], [3, 5], [3, 4], [3, 4, 5], [2], [2, 5], [2, 4], [2, 4, 5], [2, 3], [2, 3, 5], [2, 3, 4], [2, 3, 4, 5], [1], [1, 5], [1, 4], [1, 4, 5], [1, 3], [1, 3, 5], [1, 3, 4], [1, 3, 4, 5], [1, 2], [1, 2, 5], [1, 2, 4], [1, 2, 4, 5], [1, 2, 3], [1, 2, 3, 5], [1, 2, 3, 4], [1, 2, 3, 4, 5]]
```

#### Problem 4
>
Egg drop problem of N stories of M eggs.
>
```java
public static int f4(int n, int m) {
    if (m == 0) return 0;
    if (m == 1) return 1;
    if (n == 1) return m;
        
    int max = Integer.MAX_VALUE;
    for (int i = 1; i <= m; i++) {
        int case1 = f4(n - 1, i - 1);
        int case2 = f4(n, m - i);
        max = Math.min(max, Math.max(case1, case2));                   
    }
    return max + 1;
}
```

#### Desired Outputs
##### f4(2,6)
Call Tree
>
```
f4(2,6)
    // i = 1
    -> f4(1, 0)
        <- 0
    -> f4(2, 5)
        // i = 1
        -> f4(1, 0)
            <- 0
        -> f4(2, 4)
            // i = 1
            -> f4(1, 0)
                <- 0
            -> f4(2, 3)
                // i = 1
                -> f4(1, 0) 
                    <- 0
                -> f4(2, 2)
                    // i = 1
                    -> f4(1, 0)
                        <- 0
                    -> f4(2, 1)
                        <- 1
                    <- 1
                    // i = 2
                    -> f4(1, 1)
                        <- 1
                    -> f4(2, 0)
                        <- 0
                    <- 1
                    <- (1) + 1 = 2
                <- 2
                // i = 2
                -> f4(1, 1)
                    <- 1
                -> f4(2, 1)
                    <- 1
                <- 1
                // i = 3 
                -> f4(1, 2)
                    <- 2
                -> f4(2, 0)
                    <- 0
                <- 2
                <- (1) + 1 = 2
            <- 2
            // i = 2
            -> f4(1, 1)
                <- 1
            -> f4(2, 2)
                // i = 1
                -> f4(1, 0)
                    <- 0
                -> f4(2, 1)
                    <- 1
                <- 1
                // i = 2
                -> f4(1, 1)
                    <- 1
                -> f4(2, 0)
                    <- 0
                <- 1
                <- (1) + 1 = 2
            <- 2
            // i = 3
            -> f4(1, 2)
                <- 2
            -> f4(2, 1)
                <- 1
            <- 2
            // i = 4
            -> f4(1, 3)
                <- 3
            -> f4(2, 0)
                <- 0
            <- 3
            <- (2) + 1
        <- 3
        // i = 2
        -> f4(1, 1)
            <- 1
        -> f4(2, 3)
            <- 2 (from above)
        <- 2
        // i = 3
        -> f4(1, 2)
            <- 2
        -> f4(2, 2)
            <- 2 (from above)
        <- 2 
        // i = 4
        -> f4(1, 3)
            <- 3
        -> f4(2, 1)
            <- 1
        <- 3
        // i = 5
        -> f4(1, 4)
            <- 4
        -> f4(2, 0)
            <- 0
        <- 4
        <- min(3, 2, 2, 3, 4) + 1 = 3
    <- 3
    // i = 2
    -> f4(1, 1) 
        <- 1
    -> f4(2, 3)
        <- 2 (from above)
    <- 2
    // i = 3
    -> f4(1, 2)
        <- 2
    -> f4(2, 3)
        <- 2 (from above)
    <- 2
    // i = 4
    -> f4(1, 3)
        <- 3
    -> f4(2, 2)
        <- 2 
    <- 3
    // i = 5
    -> f4(1, 4)
        <- 4
    -> f4(2, 1)
        <- 1
    <- 4
    // i = 6
    -> f4(1, 5)
        <- 5
    -> f4(2, 0)
        <- 0
    <- 5
<- min(3, 2, 2, 3, 4, 5) + 1 = 3
```         