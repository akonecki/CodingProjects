public class ivq8 {
    // [F]unctional 
    public static int permutationsOfCoins(int cents) {
        return permutationsOfCoins(cents, new int [] {25, 10, 5, 1}, 0);
    }

    private static int permutationsOfCoins(int cents, int [] denominations, int index) {
        if (index >= denominations.length) {
            if (cents == 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            int sum = 0;

            // Excluded
            sum += permutationsOfCoins(cents, denominations, index + 1);

            // Included
            while (denominations[index] <= cents) {
                sum += permutationsOfCoins(cents - denominations[index], denominations, index + 1);
                cents -= denominations[index];
            }
            
            return sum;
        }
    }
    
    public static void main(String [] args) {
        assert (permutationsOfCoins(5) == 2);
        assert (permutationsOfCoins(10) == 4);
    }
}