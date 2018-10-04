import java.util.HashMap;

public class Solution {
    // [F]unctional

    // Grade school multiplication
    //      1234
    //        11
    //      ----
    //      1234
    //     12340
    //     -----
    //     13574
    //
    // Can only use addition, subtraction and bit shifting.
    // could just loop one number by the other number N number of times.
    // Number of additions is equal to the second operand
    public static int multiplicationIterative(int a, int b) {
        int product = 0;

        while (b > 0) {
            product += a;
            b--;
        }

        return product;
    }

    // lets now examin trying to minimize the number of operations.  Know that 
    // the multiplication via the addition route only should be the first base 
    // that is examine to count the total number of operations.  Then apply the
    // other operations until either the solution is found with a new less total
    // number of operations or has gone over the count.

    // should be able to perform shift of a & b as long as b is still represented.
    public static int multiplication(int a, int b) {
        //System.out.println(a + " " + b);

        if (b == 1) {
            return a;
        }
        if (a == 1) {
            return b;
        }

        if (((b >>> 1) << 1) == b) {
            //System.out.println("Shifted B");
            return multiplication(a << 1, b >> 1);
        }
        else {
            //System.out.println("Added base");
            return multiplication(a, b - 1) + a;
        }
    }

    public static int multiplication(int a, int b, HashMap<Integer, Integer> dp) {
        System.out.println(a + " " + b);
        
        if (b == 0) {
            return 0;
        }
        else if (b == 1) {
            return a;
        }

        if (dp.containsKey(b)) {
            return dp.get(b).intValue();
        }

        int result = 0;

        // see if the value is even.
        if (((b >>> 1) << 1) == b) {
            // b is even
            result = multiplication(a, b >> 1, dp);
        }
        else {
            // b is odd
            result = multiplication(a, (b - 1) >> 1, dp) + a;
        }

        result += multiplication(a, b >> 1, dp);
        
        dp.put(b, result);

        return result;
    }

    public static int multiplicationReducedCalls(int a, int b, HashMap<Integer, Integer> dp) {
        System.out.println(a + " " + b);
        
        if (b == 0) {
            return 0;
        }
        else if (b == 1) {
            return a;
        }

        if (dp.containsKey(b)) {
            return dp.get(b).intValue();
        }

        int result = 0;

        // see if the value is even.
        if (((b >>> 1) << 1) != b) {
            // b is even
            result = a;
            b--;
        }

        result += (multiplication(a, b >> 1, dp) << 1);
        
        dp.put(b, result);

        return result;
    }

    public static void main(String [] args) {
        assert (multiplicationIterative(1,1) == 1);
        assert (multiplicationIterative(5,10) == 50);
        assert (multiplication(1, 1) == 1);
        assert (multiplication(5, 10) == 50);

        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                System.out.println("New i " + i + " New j " + j);
                assert (multiplication(i, j) == i*j);
                assert (multiplication(i, j, new HashMap<Integer, Integer>()) == i*j);
                assert (multiplicationReducedCalls(i, j, new HashMap<Integer, Integer>()) == i*j);
            }
        }
    }
}