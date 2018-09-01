public class ivq3 {

    public static int computeNumTrailingZeros(int N) {
        int trailCount = 0;

        for (long count = 1; count <= N; count++) {
            long value = count;
            while (value % 5 == 0) {
                value /= 5;
                trailCount++;
            }
        }

        System.out.println(trailCount);
        return trailCount;
    }

    public static void main(String [] args) {
        assert (computeNumTrailingZeros(5) == 1);
        assert (computeNumTrailingZeros(9) == 1);
        assert (computeNumTrailingZeros(10) == 2);
        assert (computeNumTrailingZeros(14) == 2);
        assert (computeNumTrailingZeros(15) == 3);
        assert (computeNumTrailingZeros(19) == 3);
        assert (computeNumTrailingZeros(20) == 4);
        assert (computeNumTrailingZeros(24) == 4);
        assert (computeNumTrailingZeros(25) == 6);
        assert (computeNumTrailingZeros(29) == 6);
        assert (computeNumTrailingZeros(30) == 7);
        assert (computeNumTrailingZeros(34) == 7);
    }
}