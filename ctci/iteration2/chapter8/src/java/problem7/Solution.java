import java.util.List;
import java.util.LinkedList;

public class Solution {
    
    // [F]unctional

    // As per the problem statement the provide string argument contains no 
    // duplicates, so there exists no possibility of generating a permutation
    // in which there are multiple of.
    public static List<String> permutationsWithoutDuplicates(String string) {
        List<String> result = new LinkedList<String>();

        permutationsWithoutDuplicates(new StringBuilder(string), string.length(), new StringBuilder(string.length()), result);

        return result;
    }

    private static void permutationsWithoutDuplicates(StringBuilder string, int availableLength, StringBuilder sb, List<String> result) {
        if (availableLength <= 0) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < availableLength; i++) {
            char character = string.charAt(i);

            // Append to the string builder buffer.
            sb.append(character);

            // perform the swap
            string.setCharAt(i, string.charAt(availableLength - 1));
            
            // Recursive call now
            permutationsWithoutDuplicates(string, availableLength - 1, sb, result);

            // Restore
            string.setCharAt(i, character);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    // [A]nalysis 
    // 1. Does the problem have optimal substructure?
    // Yes - each level is independent of each other.
    // 2. Does the problem have re-occurring problems.
    // No due to the sb being populated with different string values in different
    // orders.

    // Memory Impact
    // Bounded more by the number of permtuations that the stack.
    // O(L!)

    // Performance Impact
    // O(L^L) L - length of the string

    public static void main(String [] args) {
        for (String string : permutationsWithoutDuplicates("abcde")) {
            System.out.println(string);
        }
        System.out.println("");
    }
}