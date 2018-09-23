public class Solution {
    public static boolean smallEditDistance(String str1, String str2) {
        if (str1 == null || str2 == null || Math.abs(str1.length() - str2.length()) > 1) {
            return false;
        }

        int dp[][] = new int [str1.length() + 1][str2.length() + 1];

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                if (row != 0 && col != 0) {
                    if (str1.charAt(row - 1) == str2.charAt(col - 1)) {
                        dp[row][col] = Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1]));
                    }
                    else {
                        dp[row][col] = Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1])) + 1;    
                    }
                }
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);

        return dp[str1.length()][str2.length()] < 2;
    }
    
    public static void main(String [] args) {
        assert (smallEditDistance("pale", "ple"));
        assert (smallEditDistance("ple", "pale"));
        assert (smallEditDistance("pale", "pales"));
        assert (smallEditDistance("bale", "pale"));
        assert (!smallEditDistance("bale", "pales"));
        assert (smallEditDistance("pale", "pale"));
    }
}