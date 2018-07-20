import java.util.ArrayList;
import java.util.HashMap;

public class Challenge4 {

    public static ArrayList<String> spellCheckTrie(String word, ArrayList<String> dict) {



        return null;
    }

    public static ArrayList<String> spellCheckHashMap(String word, ArrayList<String> dict) {
        HashMap<String, ArrayList<String>> spellDict = new HashMap<String, ArrayList<String>>();
        
        if (word == null || word.isEmpty() || dict == null || dict.isEmpty()) {
            return new ArrayList<String>();
        }

        for (String dictWord : dict) {
            String key = buildKey(dictWord);

            ArrayList<String> list = null;

            if (spellDict.containsKey(key)) {
                list = spellDict.get(key);
            }
            else {
                list = new ArrayList<String>();
            }

            list.add(dictWord);
            spellDict.put(key, list);
        }
        
        if (spellDict.containsKey(buildKey(word))) {
            return spellDict.get(buildKey(word));
        }
        else {
            return new ArrayList<String>();
        }
    }

    private static String buildKey(String word) {
        StringBuilder sb = new StringBuilder(word.length());

        for (int index = 0; index < word.length(); index++) {
            char character = word.charAt(index);
            if (index >= 'A' && index <= 'Z') {
                character = (char)(character - 'A' + 'a');
            }

            switch (character) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    character = '*';
                    break;
                default:
                    break;
            }

            sb.append(character);
        }

        return sb.toString();
    }

    public static void main(String [] args) {
        ArrayList<String> words = new ArrayList<String>();
        words.add("red");
        words.add("rad");
        words.add("GreeN");
        words.add("bLuE");

        System.out.print("rud : [");
        for (String word : Challenge4.spellCheckHashMap("rud", words)) {
            System.out.print(word + " ");
        }
        System.out.println("]");
    }
}