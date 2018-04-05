/******************************************************************************
 * Interview Question 1.1
 * 
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 *****************************************************************************/

public class ivq1 {
    private final int NUM_UNIQUE_CHARS = 26;

    public static boolean uniqueCharacters(String string) {
        // Only handling limited character set, specifically ascii single case.
        int x = 0, mask = 0;

        for (int index = 0; index < string.length(); index++) {
            mask = 1 << (Character.toLowerCase(string.charAt(index)) - 'a');
            if ((x & mask) != 0) {
                return false;
            }
            else {
                x = x | mask;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        System.out.println(ivq1.uniqueCharacters("abljkjionnmalknub"));
        System.out.println(ivq1.uniqueCharacters(""));
        System.out.println(ivq1.uniqueCharacters("abcefghijklmnopqrstuvwxyzZ"));
    }
}