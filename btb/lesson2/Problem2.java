import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class Problem2 {

    public static List<String> substrings(String string) {
        List<String> result = new ArrayList<String>();
        HashSet<String> unique = new HashSet<String>();

        if (string == null || string.isEmpty()) {
            return result;
        }

        // Perform the iteration, where the iteration provides the starting 
        // index to be used within the recursive function.
        //for (int index = 0; index < string.length(); index++) {
        //    substrings(string, index, index + 1, result);
        //}

        substrings(string, 0, result, unique);
        result = new ArrayList<String>();
        substrings(string, 0, 1, result);
        return result;
    }

    // This implmentation only provides uniqueness in terms of the starting 
    // vs stopping index of the substring, which the original already handled.
    private static void substrings(String string, int i, int j, List<String> result) {
        if (i >= string.length() || j > string.length()) {
            return;
        }

        if (i + 1 == j) {
            substrings(string, i + 1, i + 2, result);
        }
        substrings(string, i, j + 1, result);

        result.add(string.substring(i, j));
    }

    private static void substrings(String string, int startIndex, List<String> result, HashSet<String>unique) {
        // Input assumptions of the recursive method.
        assert (startIndex < string.length() + 1);
        assert (startIndex >= 0);
        assert (string != null && !string.isEmpty());
        assert (result != null);

        if (startIndex >= string.length()) {
            return;
        }
        else {
            for (int index = startIndex + 1; index <= string.length(); index++) {
                if (!unique.contains(string.substring(startIndex, index))) {
                    unique.add(string.substring(startIndex, index));
                    result.add(string.substring(startIndex, index));
                }
            }

            // result.add(string.substring(startIndex, endIndex));
            substrings(string, startIndex + 1, result, unique);
        }
    }

    public static void main(String [] args) {
        assert (substrings(null).size() == 0);
        //assert (substrings("null").size() == ("null".length() * ("null".length() + 1)) / 2);
        assert (substrings("abcd").size() == ("abcd".length() * ("abcd".length() + 1)) / 2);
        assert (substrings("a").size() == ("a".length() * ("a".length() + 1)) / 2);
        assert (substrings("abcde").size() == ("abcde".length() * ("abcde".length() + 1)) / 2);
        
        for (String string : substrings("abcde")) {
            System.out.println(string);
        }
        System.out.println("");

        for (String string : substrings("lolol")) {
            System.out.println(string);
        }
        System.out.println("");
    }
}