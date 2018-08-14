import java.util.TreeMap;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ivq2 {
    public static String [] anagramOrder(String [] strings) {
        String [] result = new String [strings.length];
        HashMap<String, String> anagramToFirst = new HashMap<>();
        HashMap<String, TreeMap<String, Integer>> anagramMap = new HashMap<>();

        for (String string : strings) {
            String key = buildAnagramKey(string);
            TreeMap<String, Integer> anagramEntry = anagramMap.get(key);

            if (anagramEntry == null) {
                anagramEntry = new TreeMap<String, Integer>();
                anagramToFirst.put(key, string);
            }
            else {
                if (anagramToFirst.get(key).compareTo(string) > 0) {
                    anagramToFirst.put(key, string);
                }
            }

            int count = 1;
            if (anagramEntry.containsKey(string)) {
                count += anagramEntry.get(string).intValue();
            }

            anagramEntry.put(string, count);
            anagramMap.put(key, anagramEntry);
        }

        ArrayList<String> firstStrings = new ArrayList<String>(anagramToFirst.size());
        for (String key : anagramToFirst.keySet()) {
            firstStrings.add(anagramToFirst.get(key));
        }

        Collections.sort(firstStrings);
        int index = 0;

        for (String key : firstStrings) {
            TreeMap<String, Integer> anagramEntry = anagramMap.get(buildAnagramKey(key));

            while (anagramEntry.size() > 0) {
                Map.Entry<String, Integer> entry = anagramEntry.pollFirstEntry();
                for (int count = 0; count < entry.getValue().intValue(); count++) {
                    result[index] = entry.getKey();
                    index++;
                }
            }
        }

        return result;
    }
    
    private static String buildAnagramKey(String string) {
        StringBuffer sb = new StringBuffer(string.length());
        TreeMap<Character, Integer> charCounts = new TreeMap<>();

        for (int index = 0; index < string.length(); index++) {
            int count = 1;
            if (charCounts.containsKey(string.charAt(index))) {
                count += charCounts.get(string.charAt(index)).intValue();
            }

            charCounts.put(string.charAt(index), count);
        }

        while (charCounts.size() > 0) {
            Map.Entry<Character, Integer> entry = charCounts.pollFirstEntry();
            for (int count = 0; count < entry.getValue(); count++) {
                sb.append(entry.getKey());
            }
        }

        return sb.toString();
    }

    public static void main(String [] args) {
        for (String string : anagramOrder(new String [] {"p", "a", "cd", "ca"})) {
            System.out.println(string + " ");
        }
    }
}