public class Solution {

    public static boolean isRotation(String str1, String str2) {
        return isSubstring(str1 + str1, str2);
    }

    private static boolean isSubstring(String str1, String str2) {
        return str1.contains(str2);
    }

    public static void main(String [] args) {
        assert (isRotation("waterbottle", "erbottlewat"));
        assert (!isRotation("waterbottle", "erbotlewat"));
    }
}