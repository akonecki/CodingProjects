public class Challenge9 {

    public static int recursiveFib(int n) {
        if (n == 1) {
            return 1;
        }
        else if (n == 2) {
            return 1;
        }
        else {
            return recursiveFib(n - 1) + recursiveFib(n - 2);
        }
    }

    public static void main(String [] args) {
        assert (recursiveFib(1) == 1);
        assert (recursiveFib(2) == 1);
        assert (recursiveFib(3) == 2);
        assert (recursiveFib(10) == 55);

    }
}