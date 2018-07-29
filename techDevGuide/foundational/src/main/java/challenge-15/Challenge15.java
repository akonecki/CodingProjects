public class Challenge15 {

    // [F]unctional implementation
    public static int operationCount(String s1, String s2) {
        if (s1.length() > s2.length()) {
            // Not supporting the delete operation only the insertion and 
            // modification operation
            return 0;
        }

        return operationCount(s1, s2, 0, 0, s1.length());
    }

    private static int operationCount(String s1, String s2, int s1Index, int s2Index, int s1ModLength) {
        if (s1Index >= s1.length() && s2Index >= s2.length()) {
            return 0;
        }

        if (s1Index >= s1.length()) {
            // ran out of characters to process, just insert until s2Index is caught up.
            return operationCount(s1, s2, s1Index, s2Index + 1, s1ModLength + 1) + 1;
        }

        // There are two operations possible for each character in a string.
        // [1] A new character can be added to the string.
        //     A character can only be added if s1 length is less than the 
        //     length of s2.
        // [2] A character can be modified to another character.
        if (s1.charAt(s1Index) != s2.charAt(s2Index)) {
            int insertCost = Integer.MAX_VALUE;
            int modifyCost = Integer.MAX_VALUE;

            if (s1ModLength < s2.length()) {
                insertCost = operationCount(s1, s2, s1Index, s2Index + 1, s1ModLength + 1) + 1;
            }

            modifyCost = operationCount(s1, s2, s1Index + 1, s2Index + 1, s1ModLength) + 1;

            if (insertCost < modifyCost) {
                return insertCost;
            }
            else {
                return modifyCost;
            }
        }
        else {
            return operationCount(s1, s2, s1Index + 1, s2Index + 1, s1ModLength);
        }
    }

    public static void main(String [] args) {
        assert (operationCount("ABCD", "ACBD") == 2);
        assert (operationCount("D", "ACBD") == 3);
    }
}