import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;
import java.util.List;

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
        return 0;


        
    }

    private static enum Position {
        SRC { 
            public String toString() {
                return "SRC";
            }
        },
        DST {
            public String toString() {
                return "DST";
            }
        },
        AUX {
            public String toString() {
                return "AUX";
            }
        }
    }

    private static class Move {
        private int disk;
        Position src;
        Position dst;

        public Move(int disk, Position src, Position dst) {
            this.disk = disk;
            this.src = src;
            this.dst = dst;
        }

        public String toString() {
            return "" + this.disk + " from " + src + " to " + dst;
        }
    }

    public static List<Move> moves(int N) {
        return moves(N, Position.SRC, Position.DST, Position.AUX);
    }

    private static List<Move> moves(int N, Position src, Position dst, Position aux) {
        if (N == 1) {
            return Arrays.asList(new Move(N, src, dst));
        }

        List<Move> result = new ArrayList<Move>();
        // move all above desired to aux
        result.addAll(moves(N - 1, src, aux, dst));
        // move desired to destination
        result.add(new Move(N, src, dst));
        // move all above from aux to dst
        result.addAll(moves(N - 1, aux, dst, src));
        return result;
    }

    public static void main(String [] args) {
        for (Move move : moves(3)) {
            System.out.println(move);
        }
    }
}