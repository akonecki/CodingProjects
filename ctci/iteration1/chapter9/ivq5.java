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

    public static void main(String [] args) {
        for (String string : getUniquePermutations("abaaabaa")) {
            System.out.println(string);
        }

        assert (getUniquePermutations("aaaaaa").size() == 2);
    }
}