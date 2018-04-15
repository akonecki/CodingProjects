public class ivq3 {
    public static boolean isPermutation(String s1, String s2) {
        int [] counts = new int[128];

        if (s1 == s2) {
            return true;
        }

        if (s1 == null) {
            return false;
        }
        else if (s2 == null) {
            return false;
        }

        if (s1.length() != s2.length()) {
            return false;
        }

        for (int index = 0; index < s1.length(); index++) {
            counts[s1.charAt(index)]++;
            counts[s2.charAt(index)]--;
        }
        
        for (int index = 0; index < 128; index++) {
            if (counts[index] != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        System.out.println(ivq3.isPermutation("abababab", "babababa"));
        System.out.println(ivq3.isPermutation("", ""));
        System.out.println(ivq3.isPermutation(null, null));
        System.out.println(ivq3.isPermutation("null", null));
        System.out.println(ivq3.isPermutation(null, "null"));
        System.out.println(ivq3.isPermutation("s1", "s2"));
        System.out.println(ivq3.isPermutation("S", "s"));
    }
}