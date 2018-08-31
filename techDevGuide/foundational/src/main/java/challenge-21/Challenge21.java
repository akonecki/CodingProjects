import java.util.HashMap;
import java.util.ArrayList;

public class Challenge21 {

    private static class StringDistance {
        
        private HashMap<String, ArrayList<Integer>> stringToIndices = new HashMap<String, ArrayList<Integer>>();

        public StringDistance(String [] strings) {
            if (strings == null || strings.length < 1) {
                return;
            }

            for (int index = 0; index < strings.length; index++) {
                String string = strings[index];

                ArrayList<Integer> list = this.stringToIndices.get(string);
                if (list == null) {
                    list = new ArrayList<Integer>();
                }
                list.add(index);
                this.stringToIndices.put(string, list);
            }
        }  

        public int indexDistanceOfStrings(String str1, String str2) {
            // Invalid string passed in.
            if (str1 == null || str2 == null || str1.isEmpty() || str2.isEmpty()) {
                return -1;
            } 

            // either string does not exist within the mapping thus a distance 
            // operation can not be computed.
            if (!this.stringToIndices.containsKey(str1) || !this.stringToIndices.containsKey(str2)) {
                return -1;
            }

            if (str1.equals(str2)) {
                // Both strings are the same so need to have two occurrences to
                // calculate the min distance.
                ArrayList<Integer> list = this.stringToIndices.get(str1);

                // Single element when two are required
                if (list.size() < 2) {
                    return -1;
                }

                int min = Math.abs(list.get(0) - list.get(1));

                for (int index = 2; index < list.size(); index++) {
                    if (Math.abs(list.get(index - 1) - list.get(index)) < min) {
                        min = Math.abs(list.get(index - 1) - list.get(index));
                    }
                }

                return min;
            }
            else {
                // Both strings are different.
                ArrayList<Integer> list1 = this.stringToIndices.get(str1);
                ArrayList<Integer> list2 = this.stringToIndices.get(str2);

                int index1 = 0, index2 = 0;
                int min = Math.abs(list1.get(0) - list2.get(0));

                while (index1 < list1.size() && index2 < list2.size()) {

                    if (Math.abs(list1.get(index1) - list2.get(index2)) < min) {
                        min = Math.abs(list1.get(index1) - list2.get(index2));
                    } 
                    
                    if (list1.get(index1) < list2.get(index2)) {
                        index1++;
                    }
                    else if (list1.get(index1) > list2.get(index2)) {
                        index2++;
                    }
                    else {
                        assert (false);
                    }
                }

                return min;
            }
        }
    }

    public static void main(String [] args) {
        String [] single = new String [] {"a", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        StringDistance singleDistances = new StringDistance(single);
        
        assert (singleDistances.indexDistanceOfStrings(null, "fox") == -1);
        assert (singleDistances.indexDistanceOfStrings("fox", null) == -1);
        assert (singleDistances.indexDistanceOfStrings("null", "fox") == -1);
        assert (singleDistances.indexDistanceOfStrings("fox", "null") == -1);
        assert (singleDistances.indexDistanceOfStrings("null", "null") == -1);

        for (int outer = 0; outer < single.length; outer++) {
            for (int inner = outer; inner < single.length; inner++) {
                if (outer == inner) {
                    assert (singleDistances.indexDistanceOfStrings(single[outer], single[inner]) == -1);
                }
                else {
                    assert (singleDistances.indexDistanceOfStrings(single[outer], single[inner]) == (inner - outer));
                }
            }
        }

        String [] duplicates = new String [] {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        StringDistance duplicateDistances = new StringDistance(duplicates);

        assert (duplicateDistances.indexDistanceOfStrings("the", "fox") == 3);
        assert (duplicateDistances.indexDistanceOfStrings("the", "jumps") == 2);
        assert (duplicateDistances.indexDistanceOfStrings("the", "brown") == 2);
        assert (duplicateDistances.indexDistanceOfStrings("the", "the") == 6);
    }
}