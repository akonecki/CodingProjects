public class Solution {
    public static String compressBasic(String str) {
        // Assumming character by character compression only.
        StringBuilder sb = new StringBuilder(str.length());

        for (int pivotIndex = 0; pivotIndex < str.length(); ) {
            char character = str.charAt(pivotIndex);
            int count = 0;

            for (int index = pivotIndex; index < str.length(); index++, pivotIndex++) {
                if (str.charAt(index) == character) {
                    count++;
                }
                else {
                    break;
                }
            }
            sb.append(character);
            sb.append(Integer.toString(count));

            if (sb.length() >= str.length()) {
                return str;
            }
        }

        return sb.toString();
    }
    
    public static void main(String [] args) {
        assert (compressBasic("aabcccccaaa").equals("a2b1c5a3"));
        assert (compressBasic("abababab").equals("abababab"));
    }
}