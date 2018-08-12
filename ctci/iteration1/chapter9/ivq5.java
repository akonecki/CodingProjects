import java.util.HashSet;
import java.util.ArrayList;

public class ivq5 {

    // [F]unctional Implementation
    public static ArrayList<String> getUniquePermutations(String word) {
        ArrayList<String> result = new ArrayList<String>();

        getUniquePermutations(word, 0, result, new StringBuilder(), new HashSet<Character>(), new HashSet<String>());

        return result;
    }

    private static void getUniquePermutations(
        String word, 
        int index, 
        ArrayList<String> result, 
        StringBuilder sb, 
        HashSet<Character> present,
        HashSet<String> strings) 
    {
        if (index >= word.length() || sb.length() >= 26) {
            if (!strings.contains(sb.toString())) {
                result.add(sb.toString());
                strings.add(sb.toString());
            }
            return;
        }
        else {
            char character = word.charAt(index);

            // Not included
            getUniquePermutations(word, index + 1, result, new StringBuilder(sb.toString()), present, strings);

            // Include
            // check to see if the character has already been included within 
            // the new permutation.
            if (!present.contains(character)) {
                // Not already present, thus can be included within the 
                // permutation.
                present.add(character);
                sb.append(character);
                getUniquePermutations(word, index + 1, result, sb, present, strings);
                present.remove(character);
            }
        }

    }

    // [A]nalysis
    // 1. Does the problem have optimal substructure?
    // Each level of the recursive function contributes its own state information
    // which will make optimization from dynamic programming in the traditional
    // context not as effective.
    // 2. Does the problem have repeating subproblems
    // Yes since there can be multiple instances of repeating strings going down
    // to the lower indices.

    // Run Time Analysis
    // There are two possible branching factors
    // 1. Include the character
    // 2. Exclude the character

    // The maximum depth of the recursion tree is based on the length of the 
    // word itself.  However I have capped it such that the permutation string
    // length is limited to 26, however this is not the worse case.

    // Expected worse case run time performance expected will be on the order of
    // O(2^(N)) where N is the length of the string.

    // Run time Memory Impact
    // The memory impact is quite large due to multiple stateful pieces of 
    // information to ensure that only unique permutations of the characters
    // are added to the result.

    // Therefore the worse case is 2^N * 26

    public static void main(String [] args) {
        for (String string : getUniquePermutations("abaaabaa")) {
            System.out.println(string);
        }

        assert (getUniquePermutations("aaaaaa").size() == 2);
    }
}