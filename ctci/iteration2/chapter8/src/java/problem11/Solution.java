import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    // Functional

    // Assume change amount is in number of cents, as is the denominations.  
    // No half cents, only whole numbers.  Assumming no negative values either.
    private static int numberOfWaysToMakeChange(int valueRemaining, int [] denominations, int index) {
        if (valueRemaining == 0) {
            return 1;
        }
        else if (index >= denominations.length) {
            return 0;
        }

        // First is don't include.
        int result = 0;
        int value = denominations[index];
        int count = 0;

        /*
        while (count * value <= valueRemaining) {
            result += numberOfWaysToMakeChange(valueRemaining - (count * value), denominations, index + 1);
            count++;
        }
        */

        // exclude
        result += numberOfWaysToMakeChange(valueRemaining, denominations, index + 1);

        // include
        if (value <= valueRemaining) {
            result += numberOfWaysToMakeChange(valueRemaining - value, denominations, index);
        }

        return result;
    }

    // [A]nalysis
    // 1. Does the problem contain optimal substructure?
    // yes, each stage is independent of each other.
    // 2. Does the problem have reoccurring problems?
    // yes it is possible for the value remaining and the given index to 
    // reoccur.

    // Performance
    // Memory
    // O(N) where N is the number of denominations due to the iterative recursion
    
    // Time
    // O((D)^N) where D = value / smallest denomination.

    // [S]ubproblem Identification & Momeization
    private static int numberOfWaysToMakeChange(int valueRemaining, int [] denominations, int index, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        if (valueRemaining == 0) {
            return 1;
        }
        else if (index >= denominations.length) {
            return 0;
        }

        if (dp.containsKey(index) && dp.get(index).containsKey(valueRemaining)) {
            return dp.get(index).get(valueRemaining).intValue();
        }

        // First is don't include.
        int result = 0;
        int value = denominations[index];
        int count = 0;

        while (count * value <= valueRemaining) {
            result += numberOfWaysToMakeChange(valueRemaining - (count * value), denominations, index + 1);
            count++;
        }

        HashMap<Integer, Integer> valueRemainingMap = dp.get(index);
        if (valueRemainingMap == null) {
            valueRemainingMap = new HashMap<>();
        }

        valueRemainingMap.put(valueRemaining, result);
        dp.put(index, valueRemainingMap);

        return result;
    }

    // [T]urn Problem Around
    public static int numberOfWaysToMakeChange(int changeAmount, 
        int [] denominations) 
    {
        if (changeAmount <= 0) {
            return 0;
        }

        int ways = numberOfWaysToMakeChange(changeAmount, denominations, 0, new HashMap<Integer, HashMap<Integer, Integer>>());
        assert (ways == numberOfWaysToMakeChange(changeAmount, denominations, 0));

        int [][] dp = new int [denominations.length + 1][changeAmount + 1];

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                if (row != 0 && col != 0) {
                    // col is the denomination.
                    // row is the amount
                    // can only perform an add 
                    
                    // exclude 
                    int value = dp[row - 1][col];

                    // include 
                    if (col >= denominations[row - 1] && col % denominations[row - 1] == 0) {
                        // 

                        if (denominations[row - 1] == col) {
                            value += 1;
                        } 
                        else {
                            value += dp[row][col - denominations[row - 1]]; 
                        }
                    }
                    else if (col > denominations[row - 1]) {
                        // Performing dp[row - 1][col] earlier means dont need to perform dp[row - 1][col - denominations[row - 1]]
                        value += dp[row][col - denominations[row - 1]];
                    }

                    dp[row][col] = value;
                }
                System.out.print(dp[row][col] + " ");
            }
            System.out.println("");
        }

        System.out.println(dp[denominations.length][changeAmount]);
        return ways;
    }

    public static void main(String [] args) {
        System.out.println(numberOfWaysToMakeChange(65, new int [] {25, 10, 5, 1}));
    }
}