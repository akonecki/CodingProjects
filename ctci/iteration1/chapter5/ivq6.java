public class ivq6 {
    public static int flipEvenOddBits(int value) {
        int oddMask = 0x55555555, evenMask = 0xAAAAAAAA;

        return ((value & oddMask) << 1) | ((value & evenMask) >>> 1);
    }

    public static void main (String [] args) {
        assert (ivq6.flipEvenOddBits(9) == 6);
        assert (ivq6.flipEvenOddBits(-1) == -1);
        assert (ivq6.flipEvenOddBits(1) == 2);
        assert (ivq6.flipEvenOddBits(0x80000000) == 0x40000000);
    }
}