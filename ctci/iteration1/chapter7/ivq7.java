import java.lang.Math;

public class ivq7 {
    
    public static long kthNumber(int k) {
        if (k < 1) {
            return 0;
        }
    
        int powerBase = k / 3, power7 = 0, power5 = 0, power3 = 0;
        int sumOfPowers = (k / 3);

        if (k % 3 != 0) {
            sumOfPowers++;
        }

        switch(k % 3) {
            case 1:
                power3++;
                break;
            case 2:
                power5++;
                break;
            case 0:
                power7++;
                break;
            default:
                break;
        }

        power3 += powerBase;
        power5 += powerBase;
        power7 += powerBase;

        System.out.println(power3 + " " + power5 + " " + power7);

        // The double back to integer rounding could cause issues
        // Could do a base conversion perform essentially shift operations and then convert back
        return Math.round(Math.pow(3, power3)) * Math.round(Math.pow(5, power5)) * Math.round(Math.pow(7, power7));
    }
    
    public static void main(String [] args) {
        assert (kthNumber(1) == 3);
        assert (kthNumber(2) == 5);
        assert (kthNumber(3) == 7);
        assert (kthNumber(4) == 9);
        assert (kthNumber(5) == 15;
    }
}