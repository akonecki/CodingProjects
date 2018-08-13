public class ivq8 {
    // [F]unctional 
    public static int permutationsOfCoins(int cents) {
        return permutationsOfCoins(cents, new int [] {100, 50, 25, 10, 5, 1}, 0);
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
    
    // [A]nalysis
    // 1. Does the problem have optimal sub-structure?
    // Yes - Each recursive iteration is self contained.
    // 2. Does the problem have re-occurring sub-problems?
    // yes - From the investigation from below it is obvious that the lower
    // values are encountered multiple times.

    /*
        SUB-PROBLEM INVESTIGATION

        N = 10

        10, [], 0
            10, [], 1
                10, [], 2
                    10, [], 3
                        10, [], 4
                        <- 0 (exclude)
                        9, [], 4
                        <- 0 (include)
                        8, [], 4
                        <- 0 (include)
                        7, [], 4
                        <- 0 (include)
                        6, [], 4
                        <- 0 (include)
                        5, [], 4
                        <- 0 (include)
                        4, [], 4
                        <- 0 (include)
                        3, [], 4
                        <- 0 (include)
                        2, [], 4
                        <- 0 (include)
                        1, [], 4
                        <- 0 (include)
                        0, [], 4
                        <- 1 (include)
                    <- 0 + 0 + ... + 1 (exclude)
                    5, [], 3 (include 5s)
                        5, [], 4 
                        <- 0 (exclude 1s)
                        4, [], 4
                        <- 0 (include)
                        3, [], 4
                        <- 0 (include)
                        2, [], 4
                        <- 0 (include)
                        1, [], 4
                        <- 0 (include)
                        0, [], 4
                        <- 1 (include)
                    <- 0 + 0 + ... + 1 (include)
                    0, [], 3
                        0, [], 4
                        <- 1 (include 5s exclude 1)
                    <- 1
                <- 3 (exclude 10)
                0, [], 2
                    0, [], 3
                        0, [], 4
                        <- 1
                    <- 1
                <- 1
            <- 4 (exclude 25)
            SKIP include
        <- 4                
    */

    // Run Time Performance
    // The direct branching factor ignoring the loop is two.  However the loop
    // does add to the total number of branches called which is dependent on
    // the value / current denomination value + 1.  This is due to the exclude
    // of the denomination value adding at least one while the include varies 
    // based on the value.  Assumming that their is always a penny, this worse
    // case branching is 1 + N, where N is the total number of cents.

    // The maximum height of the tree is equal to the total number of 
    // denominations supported within the currency + 1.  In this specific case this
    // number is 5.

    // Total worse case run time performance is O((1 + N)^4).

    // The total memory impact can be totally impacted by the height of the 
    // recursion tree, thus is limited to a depth of 5, O(C) if the number of
    // coin denominations is realistic.  If not then would be linear.

    // [S]ubproblem Identification & Momeization
    // The original problem asked how many ways can I represent N cents.
    // With recursion this can be done by caching the value as it is built back
    // up and provided to the top down level.
    public static int permutationsOfCoinsDP(int cents) {
        int [] dp = new int [cents + 1];
        permutationsOfCoins(cents, new int [] {100, 50, 25, 10, 5, 1}, 0, dp);

        for (int value : dp) {
            System.out.print(value + " ");
        }
        System.out.println("");
        return dp[cents];
    }

    private static int permutationsOfCoins(int cents, int [] denominations, int index, int [] dp) {
        if (index >= denominations.length) {
            if (cents == 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if (dp[cents] != 0) {
            return dp[cents];
        }
        else {
            int sum = 0, save = cents;;

            // Excluded
            sum += permutationsOfCoins(cents, denominations, index + 1, dp);

            // Included
            while (denominations[index] <= cents) {
                dp[cents - denominations[index]] = permutationsOfCoins(cents - denominations[index], denominations, index + 1, dp);
                sum += dp[cents - denominations[index]];
                cents -= denominations[index];
            }
            
            dp[save] = sum;

            return sum;
        }
    }

    public static void main(String [] args) {
        //System.out.println(permutationsOfCoins(100) + " " + permutationsOfCoinsDP(100));

        //assert (permutationsOfCoins(5) == permutationsOfCoinsDP(5));
        //assert (permutationsOfCoins(10) == permutationsOfCoinsDP(10));
        assert (permutationsOfCoins(15) == permutationsOfCoinsDP(15));
    }
}