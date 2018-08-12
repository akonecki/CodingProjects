import java.util.ArrayList;

public class ivq6 {

    // [F]uncational Implementation
    public static ArrayList<String> getParenthesesSets(int N) {
        ArrayList<String> result = new ArrayList<String>();

        getParenthesesSets(N, 0, 0, result, new StringBuilder());

        return result;
    }

    private static void getParenthesesSets(
        int N, 
        int openCount, 
        int closeCount, 
        ArrayList<String> result, 
        StringBuilder sb) 
    {
        if (openCount == N && closeCount == N) {
            result.add(sb.toString());
        }
        else {
            if (openCount < N) {  
                StringBuilder newSB = new StringBuilder(sb.toString());
                newSB.append('(');
                getParenthesesSets(N, openCount + 1, closeCount, result, newSB);
            }

            if (closeCount < N && closeCount < openCount) {
                sb.append(')');
                getParenthesesSets(N, openCount, closeCount + 1, result, new StringBuilder(sb.toString()));
            }
        }
    }

    /*
        2 0 0
            2 1 0 (
                2 2 0 ((
                    SKIP
                    2 2 1 (()
                        SKIP
                        2 2 2 (())
                            SAVE (())
                
    */

    // [A]nalysis
    // 1. Does the problem have optimal substructure?
    // In the current form for obtaining all the actual pairs, the generation of
    // new data at each level breaks this.  So does not fit in with gaining 
    // benefits typically associated with dynamic programming.
    // 2. Does the problem have reoccurring subproblems?
    // Not in all the arguments due to the generation of the actual string sets.

    // Not ideal in terms of gaining performance with dynamic programming.  It is
    // still likely that can be implemented in a serial manner however by 
    // performing a iteraion builder from a bottom up problem

    // Run Time Performance
    // There are two possible branching factors
    // The total depth of the tree is equal to N + 1 (to handle the insertion).
    // Therefore the worse possible is O(2^N) without considering the conditional
    // checks.

    // Memory impact is going to be O(N + 1) for the call stack plus 
    // (2*N) choose 2 times 2*N for the storage of the permutations.

    public static void main(String [] args) {
        for (String string : getParenthesesSets(3)) {
            System.out.println(string);
        }
    }
}