import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;

public class Solution {

    // [F]unctional
    // Just want to keep tabs on the characters used at each level.
    public static List<String> permutationsWithDups(String string) {
        List<String> result = new LinkedList<String>();
        HashSet<String> values = new HashSet<>();

        permutationsWithDups(new StringBuilder(string), string.length(), new StringBuilder(string.length()), result);
        
        for (String str : result) {
            assert (!values.contains(str));
            values.add(str);
        }
        
        return result;
    }

    private static void permutationsWithDups(StringBuilder string, int lengthAvailable, StringBuilder sb, List<String> result) {
        if (lengthAvailable <= 0) {
            result.add(sb.toString());
            return;
        }

        HashSet<Character> charactersSeen = new HashSet<>();
        for (int i = 0; i < lengthAvailable; i++) {
            char character = string.charAt(i);

            if (!charactersSeen.contains(character)) {
                // If have not seen the character for the current level then 
                // process.

                // add to the buffer
                sb.append(character);

                // swap out
                string.setCharAt(i, string.charAt(lengthAvailable - 1));

                permutationsWithDups(string, lengthAvailable - 1, sb, result);

                // reverse swap
                string.setCharAt(i, character);

                // update buffer
                sb.deleteCharAt(sb.length() - 1);

                // add the character to the set that has been seen for the level
                charactersSeen.add(character);
            }
        }
    }

    public static void main(String [] args) {
        for (String string : permutationsWithDups("hello")) {
            System.out.println(string);
        }
        System.out.println("");
    }
}