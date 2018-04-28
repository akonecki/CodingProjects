public class ivq1 {
    public static int stuffBits(int N, int M, int i, int j) {
        if (i < 0 || i > 31 || j < 0 || j > 31) {
            return N;
        }
        int value = 0x80000000;
        int mask = (value >> (31 - j)) | (~(value >> (31 - i)));

        System.out.println(Integer.toBinaryString(mask) + " " + Integer.toBinaryString((~(value >> (31 - i)))));

        return (N & mask) | ((~mask) & (M << i));
    }

    public static void main(String [] args) {
        System.out.println(Integer.toBinaryString(ivq1.stuffBits(0x400, 0xB, 2, 6)));
    }
}