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
    
    // [A]nalysis
    // 1. Does the problem have optimal sub structure.
    // Yes - since each iteration is self-contained.
    // 2. Does the problem have reoccurring problems?
    // No - in the current form the stack values are only ever visited once if 
    // taking into account the stack contents.

    // Memory impact O(N) - linear w.r.t the the value of N for the stack space
    // in addition to the function call stack.

    // Performance O(2^N) - the number of counts helps indicate the number of 
    // times the number of Moves is called successfully in which operations 
    // occur.

    public static void main(String [] args) {
        for (int i = 1; i < 32; i++) {
            assert (numberOfMoves(i) == (1 << i) - 1);
        }
    }
}