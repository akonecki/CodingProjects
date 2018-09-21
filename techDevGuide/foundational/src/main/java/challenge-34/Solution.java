public class Solution {

    public static int substringCount(String s1, String s2) {
        final int RADIX = 26;
        
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty() || s1.length() > s2.length()) {
            return 0;
        }    
        else if (s1.length() == s2.length()) {
            if (s1.equals(s2)) {
                return 1;
            }
            else {
                return 0;
            }
        }

        int leftIndex = 0, rightIndex = 0, length = 0, count = 0;
        long targetHash = 0, currentHash = 0, result = computeBase((long)RADIX, s1.length());
        long base = result;

        assert (computeBase(2, 2) == 2);
        assert (computeBase(2, 3) == 4);
        assert (computeBase(3, 3) == 9);

        for (int index = 0; index < s1.length(); index++) {
            targetHash += s1.charAt(index) * base;
            base /= RADIX;
        }
        
        base = result;

        for (rightIndex = 0; rightIndex < s2.length(); rightIndex++) {
            if (length < s1.length()) {
                currentHash += s2.charAt(rightIndex) * base;
                base /= RADIX;
                length++;
            }
            else {
                currentHash = (currentHash - s2.charAt(leftIndex) * result) * RADIX + s2.charAt(rightIndex);
                leftIndex++;
            }

            if (length == s1.length() && currentHash == targetHash) {
                count++;
            }
        }

        System.out.println(count);

        return count;
    }

    private static long computeBase(long radix, int length) {
        long result = 1;
        
        for (int count = 1; count < length; count++) {
            result *= radix;
        }

        return result;
    }

    public static void main(String [] args) {
        assert (substringCount("aab", "aababaa") == 1);
    }
}