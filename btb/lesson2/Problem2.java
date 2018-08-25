import java.util.List;
import java.util.ArrayList;

public class Problem2 {

    public static List<String> substrings(String string) {
        List<String> result = new ArrayList<String>();

        if (string == null || string.isEmpty()) {
            return result;
        }

        // Perform the iteration, where the iteration provides the starting 
        // index to be used within the recursive function.
        for (int index = 0; index < string.length(); index++) {
            substrings(string, index, index + 1, result);
        }

        return result;
    }

    private static void substrings(String string, int startIndex, int endIndex, List<String> result) {
        // Input assumptions of the recursive method.
        assert (startIndex != endIndex);
        assert (startIndex < endIndex);
        assert (startIndex >= 0);
        assert (string != null && !string.isEmpty());
        assert (result != null);
        
        if (endIndex > string.length()) {
            return;
        }
        else {
            result.add(string.substring(startIndex, endIndex));
            substrings(string, startIndex, endIndex + 1, result);
        }
    }

    public static void main(String [] args) {
        assert (substrings(null).size() == 0);
        assert (substrings("null").size() == ("null".length() * ("null".length() + 1)) / 2);
        assert (substrings("abcd").size() == ("abcd".length() * ("abcd".length() + 1)) / 2);
        assert (substrings("a").size() == ("a".length() * ("a".length() + 1)) / 2);
        assert (substrings("abcde").size() == ("abcde".length() * ("abcde".length() + 1)) / 2);

        for (String string : substrings("abcde")) {
            System.out.println(string);
        }
        System.out.println("");
    }
}