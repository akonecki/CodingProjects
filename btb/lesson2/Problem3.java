import java.util.ArrayList;
import java.util.Stack;

public class Problem3 {

    public static int countValidation(int N) {
        if (N == 1) {
            return 1;
        }
        return 2 * countValidation(N - 1) + 1;
    }

    public static int moveTower(int N) {
        ArrayList<Stack<Integer>> points = new ArrayList<Stack<Integer>>(3);

        for (int index = 0; index < 3; index++) {
            points.add(new Stack<Integer>());
        }

        for (int i = N; i > 0; i--) {
            assert (points.isEmpty() ? true : points.get(0).peek().intValue() > i);

            points.get(0).push(i);
        }

        // at this point the tower is populated with N disks on point 0.

        return movement(N, 0, 2);
    }

    // dont really know what the base case / individual logic is.
    private static int movement(int disksToMove, int source, int destination) {
        if (diskCount == 0) {

        }


        
    }

    private class Move {
        public ArrayList<Move> moveDisk(int diskIndex, int source, int destination) {
            return null;
        }
    }


    public static void main(String [] args) {

    }
}