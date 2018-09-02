import java.util.*;

public class Problem1 {

    public static List<String> wordBreak(String s, Set<String> dict) {
        if (s == null || s.isEmpty() || dict == null || dict.isEmpty()) {
            return new ArrayList<String>();
        }

        int longestWord = 0;
        
        for (String key : dict) {
            if (key.length() > longestWord) {
                longestWord = key.length();
            }
        }

        ArrayList<String> result = new ArrayList<String>();
        wordBreak(s, dict, 0, new StringBuilder(), longestWord, result);
        return result;
    }

    private static void wordBreak(
        String s, 
        Set<String> dict, 
        int startIndex, 
        StringBuilder sb, 
        int maxLength,
        ArrayList<String> result) 
    {
        // Once the startIndex is beyond the indexable string, then place the 
        // stringbuffer within the result.
        if (startIndex >= s.length()) {
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
            return;
        }
    
        // Make sure within the proper length amount.
        for (int pivotIndex = startIndex; pivotIndex < s.length(); pivotIndex++) {
            int index = pivotIndex + 1;
            for (; index <= s.length() && (index - pivotIndex <= maxLength); index++) {
                // check to see if the startIndex to endIndex is a valid word
                if (dict.contains(s.substring(pivotIndex, index))) {
                    // System.out.println("Found " + s.substring(pivotIndex, index));

                    StringBuilder newSb = new StringBuilder(sb);

                    if (newSb.length() != 0) {
                        newSb.append(' ');
                    }

                    newSb.append(s.substring(pivotIndex, index));
                    // inclusion
                    wordBreak(s, dict, index, newSb, maxLength, result);
                }
            }
            if (index - pivotIndex > maxLength) {
                return;
            }
        }
    }

    public static void main (String [] args) {
        Set<String> dict = new HashSet<>();

        dict.add("apple");
        dict.add("pine");
        dict.add("pineapple");

        for (String string : wordBreak("applepineapplepineapple", dict)) {
            System.out.println(string);
        }
    }
}