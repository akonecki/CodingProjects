import java.util.Stack;

public class Solution {
    
    // [F]unctional Implementation
    public static int numberOfMoves(int N) {
        // source aux destination
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> destination = new Stack<Integer>();
        for (int i = N; i > 0; i--) {
            stack.push(i);
        }

        int count = numberOfMoves(N, stack, new Stack<Integer>(), destination);

        for (int num : destination) {
            System.out.print(num + " ");
        }
        System.out.println("");

        System.out.println(count);

        return count;
    }

    private static int numberOfMoves(int N, Stack<Integer> source, Stack<Integer> aux, Stack<Integer> destination) {
        // N - 1 has to be moved to aux first
        // once N - 1 has been move to aux then Nth disk can be moved to 
        // destination.
        if (N <= 0) {
            return 0;
        }
        int count = 0;

        // move top n - 1 disks using destination as aux
        count = numberOfMoves(N - 1, source, destination, aux);

        // move top of source to destination
        if (!destination.isEmpty()) {
            assert (destination.peek().intValue() > source.peek().intValue());
        }
        count++;
        destination.push(source.pop());

        // move top N - 1 from aux to destination
        count += numberOfMoves(N - 1, aux, source, destination);
        return count;
    }
    
    public static void main(String [] args) {
        for (int i = 1; i < 32; i++) {
            assert (numberOfMoves(i) == (1 << i) - 1);
        }
    }
}