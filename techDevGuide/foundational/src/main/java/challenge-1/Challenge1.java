import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

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

    public static int MapLongestSubSequence(String S, ArrayList<String> D) {
        int max = 0;
        HashMap<Character, ArrayList<Integer>> sMapping;

        if (S == null || S.isEmpty() || D == null || D.isEmpty()) {
            return max;
        }    

        sMapping = new HashMap<Character, ArrayList<Integer>>(S.length() * 2);
        buildStringMap(S, sMapping);

        for (String W : D) {
            if (W != null && W.length() <= S.length()) {
                int wIndex = 0;
                int lastFound = -1;

                for (; wIndex < W.length(); wIndex++) {
                    char character = W.charAt(wIndex);
                    
                    if (sMapping.containsKey(character)) {
                        int position = BinarySearch(sMapping.get(character), lastFound);
                        
                        if (position == -1) {
                            break;
                        }
                        else {
                            lastFound = position;
                        }
                    }
                    else {
                        break;
                    }
                }

                if (wIndex == W.length() && W.length() > max) {
                    max = W.length();
                }
            }
        }

        return max;
    }

    private static int BinarySearch(ArrayList<Integer> positions, int lastPosition) {
        if (lastPosition == -1) {
            // return the first position in the array for the given character.
            return positions.get(0).intValue();
        }    
        else {
            // Need to perform a basic binary search.
            int leftIndex = 0, rightIndex = positions.size() - 1;
            while (leftIndex <= rightIndex) {
                int index = (rightIndex - leftIndex) / 2 + leftIndex;
                int value = positions.get(index).intValue();
                
                if (value < lastPosition) {
                    // need to go right
                    leftIndex = index + 1;
                }
                else if (value > lastPosition) {
                    // check to see if can go left
                    if (index - 1 >= 0 && positions.get(index - 1).intValue() > lastPosition) {
                        rightIndex = index - 1;
                    }
                    else {
                        // cant go left any more
                        return positions.get(index).intValue();
                    }
                }
                else {
                    // equal to lastPosition
                    if (index + 1 < positions.size()) {
                        return positions.get(index + 1).intValue();
                    }
                    else {
                        return -1;
                    }
                }
            }

            return -1;
        }
    }

    private static void buildStringMap(String S, HashMap<Character, ArrayList<Integer>> sMapping) {
        int index = 0;
        for (char character : S.toCharArray()) {
            ArrayList<Integer> positions = null;

            if (sMapping.containsKey(character)) {
                positions = sMapping.get(character);
                positions.add(index);
            }
            else {
                positions = new ArrayList<Integer>();
                positions.add(index);
            }

            index++;
            sMapping.put(character, positions);
        }
    }

    public static void main(String [] args) {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        positions.add(1);
        positions.add(2);
        positions.add(5);
        positions.add(8);
        positions.add(12);
        positions.add(13);
        positions.add(16);
        ArrayList<String> D = new ArrayList<String>();
        D.add("able");
        D.add("ale");
        D.add("apple");
        D.add("bale");
        D.add("kangaroo");

        assert (BinarySearch(positions, -1) == 1);
        assert (BinarySearch(positions, 0) == 1);
        assert (BinarySearch(positions, 1) == 2);
        assert (BinarySearch(positions, 2) == 5);
        assert (BinarySearch(positions, 3) == 5);
        assert (BinarySearch(positions, 5) == 8);
        assert (BinarySearch(positions, 13) == 16);
        assert (BinarySearch(positions, 16) == -1);
        assert (BinarySearch(positions, 20) == -1);
        assert (GreedyLongestSubSequence("abppplee", D) == 5);
        assert (MapLongestSubSequence("abppplee", D) == 5);
    }
}