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

    public static void main(String [] args) {
        System.out.println(numberOfWaysToMakeChange(25, new int [] {25, 10, 5, 1}));
    }
}