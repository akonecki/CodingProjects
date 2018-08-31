import java.util.HashMap;

public class Challenge22 {

    public static class Node {
        private String maxString = null;
        private char character;
        private HashMap<Character, Node> children = new HashMap<Character, Node>();

        public Node() {}

        public Node(char character) {
            this.character = character;
        }
    } 

    public static class PrefixLinear {
        private Node root = new Node();
        
        public void addWords(String [] words) {
            if (words != null) { 
                for (String word : words) {
                    this.addWord(word);
                }
            }
        }

        public void addWord(String word) {
            Node current = this.root, next = null;
            
            if (word != null) {
                for (int index = 0; index < word.length(); index++) {
                    char character = word.charAt(index);

                    next = current.children.get(character);
                    if (next == null) {
                        next = new Node(character);
                    }

                    if (next.maxString == null || next.maxString.length() < word.length()) {
                        next.maxString = word;
                    }

                    current.children.put(character, next);
                    current = next;
                }
            }
        }

        public String longestWordWithPrefix(String prefix) {
            Node current = this.root, next = null;

            if (prefix == null || prefix.isEmpty()) {
                return null;
            }

            for (int index = 0; index < prefix.length(); index++) {
                char character = prefix.charAt(index);
                next = current.children.get(character);
                
                if (next == null) {
                    return null;
                }

                current = next;    
            }

            return current.maxString;
        }
    }

    public static class PrefixConstant {
        private Node root = new Node();
        private HashMap<String, String> prefixToString = new HashMap<String, String>();

        public void addWords(String [] words) {
            if (words != null) { 
                for (String word : words) {
                    this.addWord(word);
                }
            }
        }

        public void addWord(String word) {
            Node current = this.root, next = null;
            
            if (word != null) {
                for (int index = 0; index < word.length(); index++) {
                    char character = word.charAt(index);

                    next = current.children.get(character);
                    if (next == null) {
                        next = new Node(character);
                    }

                    if (prefixToString.get(word.substring(0, index + 1)) == null || 
                        prefixToString.get(word.substring(0, index + 1)).length() < word.length()) 
                    {
                        prefixToString.put(word.substring(0, index + 1), word);
                    }

                    current.children.put(character, next);
                    current = next;
                }
            }
        }

        public void printPrefixMapping() {
            for (String key : this.prefixToString.keySet()) {
                System.out.println(key + " " + this.prefixToString.get(key));
            }
        }

        public String longestWordWithPrefix(String prefix) {
            return this.prefixToString.get(prefix);
        }
    }

    public static void main(String [] args) {
        String [] words = new String [] {
            "hello",
            "world",
            "ant",
            "ab",
            "ache",
            "anteater"
        };
        PrefixLinear prefixLinear = new PrefixLinear();
        prefixLinear.addWords(words);
        assert (prefixLinear.longestWordWithPrefix("a").equals("anteater"));
        assert (prefixLinear.longestWordWithPrefix("ant").equals("anteater"));
        assert (prefixLinear.longestWordWithPrefix("b") == null);
        assert (prefixLinear.longestWordWithPrefix("ac").equals("ache"));
        assert (prefixLinear.longestWordWithPrefix("hello").equals("hello"));
        assert (prefixLinear.longestWordWithPrefix("w").equals("world"));

        PrefixConstant prefixConstant = new PrefixConstant();
        prefixConstant.addWords(words);
        assert (prefixConstant.longestWordWithPrefix("a").equals("anteater"));
        assert (prefixConstant.longestWordWithPrefix("ant").equals("anteater"));
        assert (prefixConstant.longestWordWithPrefix("b") == null);
        assert (prefixConstant.longestWordWithPrefix("ac").equals("ache"));
        assert (prefixConstant.longestWordWithPrefix("hello").equals("hello"));
        assert (prefixConstant.longestWordWithPrefix("w").equals("world"));
        prefixConstant.printPrefixMapping();
    }
}