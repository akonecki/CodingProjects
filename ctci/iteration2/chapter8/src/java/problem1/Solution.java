public class Solution {
    public static int waysToGo(int N) {

    }

    // [F]unctional - Recurisve implementation to support the general 
    // implementation.

    // Start off on step 0 then go up to step N.
    // expected branches would be branch step + 1, + 2, + 3
    // Count the number of ways in which it reaches Nth step or greater.
    private static int waysToGo(int N, int step) {
        if (step > N) {
            return 1;
        }
        return waysToGo(N, step + 1) + waysToGo(N, step + 2) + waysToGo(N, step + 3);
    }

    private static int waysToGoSerial(int N) {

    }
}