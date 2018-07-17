import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Collections;
import java.util.LinkedList;

public class Challenge2 {

    public static String[][] wordCountEngine(String document) {
        String [][] result;
        
        if (document == null || document.isEmpty()) {
            return new String [0][0];
        }
    
        HashMap<String, Node> wordToNodeMap = new HashMap<String, Node>();
        ArrayList<Node> nodes = new ArrayList<Node>();

        int maxCount = buildMap(wordToNodeMap, nodes, document) + 1;
        ArrayList<LinkedList<Node>> bucket = new ArrayList<LinkedList<Node>>(maxCount);

        // Build the bucket sort
        // Initialize
        for (int count = 0; count < maxCount; count++) {
            bucket.add(null);
        }

        // Iterate through the ordered list.
        for (Node node : nodes) {
            if (node != null) {
                LinkedList<Node> orderedNodes = bucket.get(node.wordCount);
                if (orderedNodes == null) {
                    // new bucket.
                    orderedNodes = new LinkedList<Node>();
                }
                
                // Insert the node into the bucket
                orderedNodes.add(wordToNodeMap.get(node.word));

                // make sure to put the bucket back
                bucket.set(node.wordCount, orderedNodes);
            }
        }

        result = new String [nodes.size()][2];
        int resultIndex = 0;

        // Now iterate from the high index backwards in the bucket list.
        for (int count = 0; count < maxCount; count++) {
            LinkedList<Node> orderedNodes = bucket.get(maxCount - 1 - count);
            
            if (orderedNodes != null) {
                // The bucket at the index is not empty.
                while (!orderedNodes.isEmpty()) {
                    Node node = orderedNodes.remove();
                    result[resultIndex][0] = node.word;
                    result[resultIndex][1] = Integer.toString(node.wordCount);
                    resultIndex++;
                }
            }    
        }

        return result;
    }

    private static int buildMap(HashMap<String, Node> map, ArrayList<Node> nodes, String document) {
        int wordIndex = 0;
        int maxCount = 0;

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

                    if (node.wordCount > maxCount) {
                        maxCount = node.wordCount;
                    }
                }
                else {
                    node = new Node(word, wordIndex);
                    wordIndex++; 
                    // Adding the node to the list will preserve order of new nodes.
                    nodes.add(node);  
                    
                    if (1 > maxCount) {
                        maxCount = 1;
                    }
                }

                map.put(word, node);
            }
            else {
                // dont handle it.
            }
        }

        return maxCount;
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
        String document = " Practice  perfect makes . you'll only get Perfect by practice. just do it!";

        for (String [] strings : wordCountEngine(document)) {
            for (String word : strings) {
                System.out.print(word + " ");
            }
            System.out.println("");
        }
    }
}