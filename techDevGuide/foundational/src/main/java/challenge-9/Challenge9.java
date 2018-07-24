public class Challenge9 {

    // Does the recursive implementation have optimal substructure?
    // Yes - solving recursively into self contained subproblems.
    // Does this have overlapping subproblems?
    /*  
        fib (4) 
            fib (3)
                fib (2)
                    return 1
                fib (1)
                    return 1
            fib (2)
                return 1
    */
    // Yes - does have overlapping subproblems.  Multiple occurrences of
    //       fib(2).
    // TIME COMPLEXITY Calculation
    //
    // Height of tree is equal to (n - 1) due to return condition.
    // Has 2 recusive calls so branching factor of (2)
    // Number of calls is roughly 2^(4-1) or roughly 2^N
    // Space complexity is O(N) due to the minimum height of the tree.
    //
    // So this is a perfect candidate for this problem.
    public static int recursiveFib(int n) {
        if (n == 1) {
            return 1;
        }
        else if (n == 2) {
            return 1;
        }
        else {
            return recursiveFib(n - 1) + recursiveFib(n - 2);
        }
    }

    public static void main(String [] args) {
        assert (recursiveFib(1) == 1);
        assert (recursiveFib(2) == 1);
        assert (recursiveFib(3) == 2);
        assert (recursiveFib(10) == 55);

    }
}