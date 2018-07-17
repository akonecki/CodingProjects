import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Collections;

public class Challenge2 {

    public static String[][] wordCountEngine(String document) {
        String [][] result;
        
        if (document == null || document.isEmpty()) {
            return new String [0][0];
        }
    
        HashMap<String, Node> wordToNodeMap = new HashMap<String, Node>();
        ArrayList<Node> nodes = new ArrayList<Node>();

        buildMap(wordToNodeMap, nodes, document);
        Collections.sort(nodes, new NodeOrder());

        if (nodes.isEmpty()) {
            return new String [0][0];
        }

        result = new String [nodes.size()][2];
        int resultIndex = 0;

        for (Node node : nodes) {
            result[resultIndex][0] = node.word;
            result[resultIndex][1] = Integer.toString(node.wordCount);
            resultIndex++;
        }

        return result;
    }

    private static void buildMap(HashMap<String, Node> map, ArrayList<Node> nodes, String document) {
        int wordIndex = 0;

        for (int leftIndex = 0; leftIndex < document.length(); leftIndex++) {
            char character = document.charAt(leftIndex);

            if ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z')) {
                // first character validation.
                StringBuilder sb = new StringBuilder();

                // build the window
                for (int rightIndex = leftIndex; rightIndex < document.length(); rightIndex++, leftIndex++) {
                    char rightCharacter = document.charAt(rightIndex);

                    if (rightCharacter == ' ' || rightCharacter == '\r' || rightCharacter == '\n') {
                        // handle the white characters
                        break;
                    }
                    else if ((rightCharacter >= 'a' && rightCharacter <= 'z') || (rightCharacter >= 'A' && rightCharacter <= 'Z')) {
                        // valid character
                        sb.append(rightCharacter);
                    } 
                    else {
                        // dont process anything else.
                    }
                }

                String word = sb.toString().toLowerCase();
                Node node = null;

                if (map.containsKey(word)) {
                    node = map.get(word);
                    node.wordCount++;
                }
                else {
                    node = new Node(word, wordIndex);
                    wordIndex++; 
                    nodes.add(node);   
                }

                map.put(word, node);
            }
            else {
                // dont handle it.
            }
        }
    }

    private static class Node {
        private String word = null;
        private int wordIndex = 0;
        private int wordCount = 1;

        public Node(String word, int index) {
            this.word = word;
            this.wordIndex = index;
        }
    }

    private static class NodeOrder implements Comparator<Node> {
        public int compare(Node a, Node b) {
            if (a.wordCount > b.wordCount) {
                return -1;
            }
            else if (a.wordCount < b.wordCount) {
                return 1;
            }
            else {
                if (a.wordIndex < b.wordIndex) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
    }

    public static void main(String args []) {
        String document = "perfect Practice makes . you'll only get Perfect by practice. just do it!";

        for (String [] strings : wordCountEngine(document)) {
            for (String word : strings) {
                System.out.print(word + " ");
            }
            System.out.println("");
        }
    }
}