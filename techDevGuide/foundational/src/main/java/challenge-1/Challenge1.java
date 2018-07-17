import java.util.ArrayList;

public class Challenge1 {
    public static int GreedyLongestSubSequence(String S, ArrayList<String> D) {
        int max = 0;

        if (S == null || S.isEmpty() || D == null || D.isEmpty()) {
            return max;
        }

        for (String W : D) {
            if (W != null && W.length() <= S.length()) {
                int wIndex = 0;
                for (int sIndex = 0; sIndex < S.length() && wIndex < W.length(); sIndex++) {
                    if (S.charAt(sIndex) == W.charAt(wIndex)) {
                        wIndex++;
                    }
                }

                if (wIndex == W.length() && W.length() > max) {
                    max = W.length();
                }
            }
        }

        return max;
    }

    public static void main(String [] args) {
        ArrayList<String> D = new ArrayList<String>();
        D.add("able");
        D.add("ale");
        D.add("apple");
        D.add("bale");
        D.add("kangaroo");

        assert (GreedyLongestSubSequence("abppplee", D) == 5);
    }
}