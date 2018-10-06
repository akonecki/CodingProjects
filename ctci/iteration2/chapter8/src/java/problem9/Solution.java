import java.util.List;
import java.util.LinkedList;

public class Solution {
    // [F]unctional
    public static List<String> generateParens(int number) {
        List<String> result = new LinkedList<>();

        generateParens(number, 0, 0, new StringBuffer(number * 2), result);

        return result;
    }

    private static void generateParens(int number, int leftCount, int rightCount, StringBuffer sb, List<String> result) {
        if (leftCount == number && rightCount == number) {
            result.add(sb.toString());
            return;
        }
        
        if (leftCount < number) {
            sb.append('(');
            generateParens(number, leftCount + 1, rightCount, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (rightCount < number && rightCount < leftCount) {
            sb.append(')');
            generateParens(number, leftCount, rightCount + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return;
    }

    // [A]nalysis
    // 1. does the problem have optimal substructure?
    // yes each level of recursion is self contained.
    // 2. does the problem have reoccurring sub problems?
    // no due to the unique set of sb permutations that are being built.

    // Performance
    // Memory
    // O(2*N * (2N)! / ((N+1)! * (N!)) + N)

    // Time
    // O(2^N)

    public static void main(String [] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println(i + " " + generateParens(i).size());
        }
        /*
        for (String string : generateParens(4)) {
            System.out.println(string);
        }
        System.out.println("");
        */
    }
}