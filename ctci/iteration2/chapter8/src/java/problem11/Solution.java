import java.util.Arrays;

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

        return numberOfWaysToMakeChange(changeAmount, denominations, 0);
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

    public static void main(String [] args) {
        System.out.println(numberOfWaysToMakeChange(25, new int [] {25, 10, 5, 1}));
    }
}