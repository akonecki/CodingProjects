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

