import java.util.ArrayList;
import java.util.HashMap;

public class Challenge4 {

    public static ArrayList<String> spellCheckTrie(String word, ArrayList<String> dict) {

        TrieNode root = new TrieNode();

        for (String dictWord : dict) {
            // iterate through each dict word and add to the trie.
            insert(root, dictWord);
        }

        // Trie is fully built now.
        // Nasty part will be actually going down the Trie to retrieve all the words
        // that could be potential matches for the spell correction.
        ArrayList<String> result = new ArrayList<String>();
        search(root, word, 0, result, new StringBuilder());

        return result;
    }

    private static void search(TrieNode root, String word, int index, ArrayList<String> result, StringBuilder sb) {
        if (root == null) {
            return;
        }
        else if (index >= word.length()) {
            return;
        }

        // Need to handle capitial variant / vowel.
        char character = word.charAt(index);
        boolean lastChar = (index + 1 == word.length());

        switch (character) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                // handle mismatch potential and cap
                for (char letter : new char[]{'a','e','i','o','u','A','E','I','O','U'}) {
                    TrieNode nextNode = null;
                    int nextIndex = 0;

                    if (letter >= 'a' && letter <= 'z') {
                        nextIndex = letter - 'a';
                    }
                    else {
                        nextIndex = letter - 'A' + 26;
                    }

                    nextNode = root.next[nextIndex];

                    if (nextNode != null && lastChar) {
                        result.add((new StringBuilder(sb.toString())).append(letter).toString());
                    }
                    else {
                        search(nextNode, word, index + 1, result, (new StringBuilder(sb.toString())).append(letter));
                    }
                }
                break;
            default:
                // handle cap only
                TrieNode nextNode = null;
                int nextIndex = 0;

                if (character >= 'a' && character <= 'z') {
                    nextIndex = character - 'a';
                    nextNode = root.next[nextIndex];

                    if (nextNode != null && lastChar) {
                        result.add((new StringBuilder(sb.toString())).append(character).toString());
                    }
                    else {
                        search(nextNode, word, index + 1, result, (new StringBuilder(sb.toString())).append(character));
                    }
    
                    nextNode = root.next[nextIndex + 26];
    
                    if (nextNode != null && lastChar) {
                        result.add((new StringBuilder(sb.toString())).append((char)(character - 'a' + 'A')).toString());
                    }
                    else {
                        search(nextNode, word, index + 1, result, (new StringBuilder(sb.toString())).append((char)(character - 'a' + 'A')));
                    }
                }
                else {
                    nextIndex = character - 'A';
                    nextNode = root.next[nextIndex];

                    if (nextNode != null && lastChar) {
                        result.add((new StringBuilder(sb.toString())).append(character).toString());
                    }
                    else {
                        search(nextNode, word, index + 1, result, (new StringBuilder(sb.toString())).append(character));
                    }
    
                    nextNode = root.next[nextIndex - 26];
    
                    if (nextNode != null && lastChar) {
                        result.add((new StringBuilder(sb.toString())).append((char)(character - 'A' + 'a')).toString());
                    }
                    else {
                        search(nextNode, word, index + 1, result, (new StringBuilder(sb.toString())).append((char)(character - 'A' + 'a')));
                    }
                }

                break;
        }
    }

    private static void insert(TrieNode root, String word) {
        TrieNode node = root;

        for (int index = 0; index < word.length(); index++) {
            char character = word.charAt(index);
            int nextIndex = 0;

            if (character >= 'A' && character <= 'Z') {
                nextIndex = character - 'A' + 26;
            }
            else {
                nextIndex = character - 'a';
            }

            if (node.next[nextIndex] == null) {
                node.next[nextIndex] = new TrieNode(character);
            }

            node = node.next[nextIndex];
        }

        node.last = true;
    }

    private static class TrieNode {
        private char character;
        private boolean last;
        private TrieNode[] next = new TrieNode[52];

        public TrieNode() {}

        public TrieNode(char character) {
            this.character = character;
        }
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
        System.out.print("rud : [");
        for (String word : Challenge4.spellCheckTrie("rud", words)) {
            System.out.print(word + " ");
        }
        System.out.println("]");
    }
}