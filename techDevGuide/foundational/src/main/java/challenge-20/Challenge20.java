import java.util.HashMap;
import java.util.HashSet;

public class Challenge20 {

    // Supporting the definition for the first design considerations.
    public static String substringOne(char [] array, String string) {
        // Input checking.
        if (array == null || array.length < 1 || string == null || 
            string.isEmpty() || string.length() < array.length) 
        {
            return "";
        }

        String result = "";
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        HashSet<Character> approved = new HashSet<Character>();

        // populate the set
        for (char character : array) {
            assert (!approved.contains(character));
            approved.add(character);
        }

        int invalidCount = 0;
        int totalCount = 0;
        int duplicateCount = 0;

        // Build out the window and traverse the string for the substring.
        for (int index = 0, removeIndex = 0; index < string.length(); index++) {
            char characterToAdd = string.charAt(index);
            totalCount++;

            if (!approved.contains(characterToAdd)) {
                invalidCount++;
            }

            if (charCountMap.containsKey(characterToAdd)) {
                // key already exists
                charCountMap.put(characterToAdd, charCountMap.get(characterToAdd).intValue() + 1);

                if (charCountMap.get(characterToAdd) == 2) {
                    duplicateCount++;
                }
            }
            else {
                // new key
                charCountMap.put(characterToAdd, 1);
            }

            if (totalCount == array.length + 1) {
                // Need to remove
                char characterToRemove = string.charAt(removeIndex);
                totalCount--;
                removeIndex++;

                if (!approved.contains(characterToRemove)) {
                    invalidCount--;
                }

                assert (charCountMap.containsKey(characterToRemove));
                assert (charCountMap.get(characterToRemove) >= 1);
                charCountMap.put(characterToRemove, charCountMap.get(characterToRemove).intValue() - 1);

                if (charCountMap.get(characterToRemove).intValue() == 1) {
                    duplicateCount--;
                }
            }

            if (totalCount == array.length && duplicateCount == 0 && invalidCount == 0) {
                System.out.println(string.substring(removeIndex, index + 1));
                return string.substring(removeIndex, index + 1);
            }
        }

        return "";
    }

    // Supporting the definition for the first design considerations.
    public static String substringTwo(char [] array, String string) {
        // Input checking.
        if (array == null || array.length < 1 || string == null || 
            string.isEmpty() || string.length() < array.length) 
        {
            return "";
        }

        String result = "";
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        HashSet<Character> approved = new HashSet<Character>();

        // populate the set
        for (char character : array) {
            assert (!approved.contains(character));
            approved.add(character);
        }

        int totalCount = 0;
        int duplicateCount = 0;

        // Build out the window and traverse the string for the substring.
        for (int index = 0, removeIndex = 0; index < string.length(); index++) {
            char characterToAdd = string.charAt(index);
            totalCount++;

            if (!approved.contains(characterToAdd)) {
                charCountMap.clear();
                totalCount = 0;
                removeIndex = index + 1;
                duplicateCount = 0;
                continue;    
            }

            if (charCountMap.containsKey(characterToAdd)) {
                // key already exists
                charCountMap.put(characterToAdd, charCountMap.get(characterToAdd).intValue() + 1);

                if (charCountMap.get(characterToAdd) == 2) {
                    duplicateCount++;
                }
            }
            else {
                // new key
                charCountMap.put(characterToAdd, 1);
            }

            assert (approved.contains(characterToAdd));

            if (totalCount == array.length + 1) {
                // Need to remove
                char characterToRemove = string.charAt(removeIndex);
                totalCount--;
                removeIndex++;

                assert (charCountMap.containsKey(characterToRemove));
                assert (charCountMap.get(characterToRemove) >= 1);
                charCountMap.put(characterToRemove, charCountMap.get(characterToRemove).intValue() - 1);

                if (charCountMap.get(characterToRemove).intValue() == 1) {
                    duplicateCount--;
                }

                assert (approved.contains(characterToRemove));
            }

            if (totalCount == array.length && duplicateCount == 0) {
                System.out.println(string.substring(removeIndex, index + 1));
                return string.substring(removeIndex, index + 1);
            }
        }

        return "";
    }

    public static void main(String [] args) {
        assert (substringOne(new char [] {'x', 'y', 'z'}, "xyyzkzxyadkianxzydln").equals(substringTwo(new char [] {'x', 'y', 'z'}, "xyyzkzxyadkianxzydln")));
    }
}