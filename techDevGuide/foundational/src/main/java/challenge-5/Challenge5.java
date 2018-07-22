public class Challenge5 {

    public static int arrayGCD(int [] numbers) {
        
        int gcd = numbers[0];;

        for (int num : numbers) {
            if (num > 0) {
                gcd = gcd(gcd, num);
            }
        }

        return gcd;
    }

    private static int gcd(int num1, int num2) {
        if (num1 < num2) {
            return gcd(num2, num1);
        }

        int A = num1, B = num2, C = 0;

        while (B > 0 && A % B != 0) {
            C = A % B;
            A = B;
            B = C;
        }

        return B;
    }

    public static void main(String [] args) {
        assert (arrayGCD(new int [] {2,4,6,8,16}) == 2);
    }

}