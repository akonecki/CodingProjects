public class Solution {

    public static boolean isPermutation(String str1, String str2) {
        int nonZeroCounts = 0;
        int [] charCountsByIndex;        

        if (str1 == str2) {
            return true;
        }
        else if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        // Assumming ASCII text.
        charCountsByIndex = new int [256];

        for (int index = 0; index < str1.length(); index++) {
            charCountsByIndex[str1.charAt(index)]++;
            charCountsByIndex[str2.charAt(index)]--; 

            if (charCountsByIndex[str2.charAt(index)] == 0) {
                nonZeroCounts--;
            }
            else if (charCountsByIndex[str2.charAt(index)] == -1) {
                nonZeroCounts++;
            }

            if (charCountsByIndex[str1.charAt(index)] == 0) {
                nonZeroCounts--;
            }
            else if (charCountsByIndex[str1.charAt(index)] == 1) {
                nonZeroCounts++;
            }
        }

        return nonZeroCounts == 0;
    }

    public static void main(String [] args) {
        assert (isPermutation("a", "a"));
        assert (!isPermutation("a", "aa"));
        assert (!isPermutation("a", " a"));
        assert (isPermutation("ba", "ab"));
    }
}