import java.util.*;

public class Solution {
    public static boolean isUnique(String str) {
        boolean firstResult = isUnique(str, new HashSet<Character>());

        assert (isUnique(str, new boolean [128]) == firstResult);
        assert (isUnique(str, new BitSet(128)) == firstResult);

        return firstResult;
    }

    // Expected run-time is O(N) where N is equal to the length of the String
    // input.  The worse case memory impact is based on the total number of 
    // characters within the accepted character set.
    private static boolean isUnique(String str, HashSet<Character> charSet) {
        for (int index = 0; index < str.length(); index++) {
            if (charSet.contains(str.charAt(index))) {
                return false;
            }
            else {
                charSet.add(str.charAt(index));
            }
        }

        return true;
    }

    // Expected run-time is O(N) where N is equal to the length of the String
    // input.  The worse case memory impact is based on the total number of 
    // characters and the total size to represent a boolean.
    private static boolean isUnique(String str, boolean [] charsPresent) {
        for (int index = 0; index < str.length(); index++) {
            if (charsPresent[str.charAt(index)]) {
                return false;
            }
            else {
                charsPresent[str.charAt(index)] = true;
            }
        }

        return true;
    }

    // Reduction of memory space further with bit set which is essentially a 
    // bit vector.
    private static boolean isUnique(String str, BitSet bitSet) {
        for (int index = 0; index < str.length(); index++) {
            if (bitSet.get(str.charAt(index))) {
                return false;
            }
            else {
                bitSet.set(str.charAt(index));
            }
        }

        return true;
    }
    
    public static void main(String [] args) {
        assert (isUnique("abc"));
        assert (!isUnique("aabc"));
    }
}