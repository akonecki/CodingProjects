import java.util.*;

public class Problem4 {

    private static class Step {
        private String operation = null;
        private int index = 0;
        private String character = null;

        public Step(String operation, int index, String character) {
            this.operation = operation;
            this.character = character;
            this.index = index;
        }

        public String toString() {
            return this.operation + "  " + this.character + " at index " + index;
        }
    }

    public static List<List<Step>> editDistance(String s1, String s2) {
        // Dont process on null, or if the strings are already equal. 
        if (s1 == null || s2 == null || s1 == s2 || s1.equals(s2)) {
            return new ArrayList<List<Step>>();
        }

        List<List<Step>> result = new ArrayList<List<Step>>();
        int minimum = s1.length() > s2.length() ? s1.length() : s2.length();
        minimum = editDistance(s1, s2, 0, 0, 0, minimum, result, new ArrayList<Step>(minimum));

        return result;
    }

    private static int editDistance(
        String s1, 
        String s2, 
        int indexOne,
        int s1Length, 
        int indexTwo, 
        int minimum,
        List<List<Step>> result, 
        List<Step> steps)
    {
        if (s1Length + s1.length() == s2.length() && 
            indexOne == s1.length() && 
            indexTwo == s2.length()) 
        {
            // System.out.println(steps.size());
            // At this point have delt with both indicies of s1 & s2.
            if (steps.size() == minimum) {
                result.add(new ArrayList<Step>(steps));
                return minimum;
            }
            else if (steps.size() < minimum) {
                result.clear();
                result.add(new ArrayList<Step>(steps));
                return steps.size();
            }
            else {
                return minimum;
            }
        }

        int myMin = minimum;
        // System.out.println(indexOne + " " + indexTwo + " " + s1Length + " " + s1.length() + " " + s2.length());

        // equals
        if (indexOne < s1.length() && indexTwo < s2.length() && s1.charAt(indexOne) == s2.charAt(indexTwo)) {
            // no step added
            myMin = editDistance(s1, s2, indexOne + 1, s1Length, indexTwo + 1, minimum, result, steps);
        }

        // less number of characters insert at the current index.
        if (steps.size() < myMin) {
            if (indexOne < s1.length() && indexTwo < s2.length()) {// s1.length() + s1Length < s2.length()) {
                steps.add(new Step("INSERT", indexOne, s2.substring(indexTwo, indexTwo + 1)));
                myMin = editDistance(s1, s2, indexOne, s1Length + 1, indexTwo + 1, myMin, result, steps);
                steps.remove(steps.size() - 1);
            }

            // character is different, modify
            if (indexOne < s1.length() && indexTwo < s2.length() && s1.charAt(indexOne) != s2.charAt(indexTwo)) {
                steps.add(new Step("MODIFY", indexOne, s1.substring(indexOne, indexOne + 1) + "->" + s2.substring(indexTwo, indexTwo + 1)));
                myMin = editDistance(s1, s2, indexOne + 1, s1Length, indexTwo + 1, myMin, result, steps);
                steps.remove(steps.size() - 1);
            }

            // number of characters is greater than s2
            if (indexOne < s1.length()) { // && s1.length() + s1Length > s2.length()) {
                steps.add(new Step("DELETE", indexOne, s1.substring(indexOne, indexOne + 1)));
                myMin = editDistance(s1, s2, indexOne + 1, s1Length - 1, indexTwo, myMin, result, steps);
                steps.remove(steps.size() - 1);
            }
        }

        return myMin;
    }

    public static void main(String [] args) {
        for (List<Step> steps : editDistance("hello world", "wthorld")) {
            int index = 0;
            for (Step step : steps) {
                index++;
                System.out.println("Step " + index + " " + step);
            }
            System.out.println("");
        }
    }
}