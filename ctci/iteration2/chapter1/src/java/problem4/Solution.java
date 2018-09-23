public class Solution {
    public static boolean isPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }

        int leftIndex = 0, rightIndex = str.length() -  1;

        while (leftIndex < rightIndex) {
            if (str.charAt(leftIndex) != ' ' && str.charAt(rightIndex) != ' ') {
                if (str.charAt(leftIndex) != str.charAt(rightIndex)) {
                    return false;
                }

                leftIndex++;
                rightIndex--;
            }
            else if (str.charAt(leftIndex) != ' ') {
                rightIndex--;
            }
            else if (str.charAt(rightIndex) != ' ') {
                leftIndex++;
            }
        }

        return true;
    }
    
    public static void main(String [] args) {
        assert (isPalindrome("taco cat"));
        assert (!isPalindrome("taco cats"));
    }
}