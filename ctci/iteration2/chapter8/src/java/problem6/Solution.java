import java.util.Stack;
import java.util.HashMap;

public class Solution {
    
    // [F]unctional Implementation
    public static int numberOfMoves(int N) {
        // source aux destination
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> destination = new Stack<Integer>();
        for (int i = N; i > 0; i--) {
            stack.push(i);
        }

        int count = 0; //numberOfMoves(N, stack, new Stack<Integer>(), destination);

        /*
        for (int num : destination) {
            System.out.print(num + " ");
        }
        System.out.println("");

        System.out.println(count);
        */
        count = numberOfMoves(N, N, 0, 0, new HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>());
        System.out.println(count);
        //assert (count == numberOfMoves(N, N, 0, 0));
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

    // [S]ubproblem Identification & Momeization
    // Reduction in total number of calls performed by using the multi-index 
    // hashmap scheme
    private static int numberOfMoves(int N, int source, int aux, int destination, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>> dp) {
        // N - 1 has to be moved to aux first
        // once N - 1 has been move to aux then Nth disk can be moved to 
        // destination.
        if (N <= 0) {
            return 0;
        }
        int count = 0;

        if (dp.containsKey(N) && 
            dp.get(N).containsKey(source) && 
            dp.get(N).get(source).containsKey(aux) && 
            dp.get(N).get(source).get(aux).containsKey(destination)) {
            return dp.get(N).get(source).get(aux).get(destination).intValue();
        }

        System.out.println(N + " " + source + " " + aux + " " + destination);

        // move top n - 1 disks using destination as aux
        count = numberOfMoves(N - 1, source, destination, aux, dp);

        // move top of source to destination
        count++;
        destination++;
        source--;

        // move top N - 1 from aux to destination
        count += numberOfMoves(N - 1, aux, source, destination, dp);

        HashMap<Integer, Integer> auxMap = null;
        HashMap<Integer, HashMap<Integer, Integer>> sourceMap = null;
        HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> nMap = dp.get(N);

        if (nMap == null) {
            nMap = new HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>();
            sourceMap = new HashMap<Integer, HashMap<Integer, Integer>>();
            auxMap = new HashMap<Integer, Integer>();
        }
        else {
            sourceMap = dp.get(N).get(source);

            if (sourceMap == null) {
                sourceMap = new HashMap<Integer, HashMap<Integer, Integer>>();
                auxMap = new HashMap<Integer, Integer>();
            }
            else {
                auxMap = dp.get(N).get(source).get(aux);

                if (auxMap == null) {
                    auxMap = new HashMap<Integer, Integer>();
                }
            }  
        }

        auxMap.put(destination, count);
        sourceMap.put(aux, auxMap);
        nMap.put(source, sourceMap);
        dp.put(N, nMap);
        return count;
    }

    public static void main(String [] args) {
        /*
        for (int i = 1; i < 32; i++) {
            assert (numberOfMoves(i) == (1 << i) - 1);
        }
        */
        numberOfMoves(5);
    }
}