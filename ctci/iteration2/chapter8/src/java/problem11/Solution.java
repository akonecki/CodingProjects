import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    // Functional

    // Assume change amount is in number of cents, as is the denominations.  
    // No half cents, only whole numbers.  Assumming no negative values either.
    public static int numberOfWaysToMakeChange(int changeAmount, 
        int [] denominations) 
    {
        if (changeAmount <= 0) {
            return 0;
        }

        int ways = numberOfWaysToMakeChange(changeAmount, denominations, 0, new HashMap<Integer, HashMap<Integer, Integer>>());
        assert (ways == numberOfWaysToMakeChange(changeAmount, denominations, 0));
        return ways;
    }

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

        while (count * value <= valueRemaining) {
            result += numberOfWaysToMakeChange(valueRemaining - (count * value), denominations, index + 1);
            count++;
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

    public static void main(String [] args) {
        System.out.println(numberOfWaysToMakeChange(100, new int [] {25, 10, 5, 1}));
    }
}