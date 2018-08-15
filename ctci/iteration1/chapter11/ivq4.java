public class ivq4 {
    public static int findIndex(String [] strings, String value) {
        return findIndex(strings, value, 0, strings.length - 1);
    }

    private static int findIndex(String [] strings, String value, int lowIndex, int highIndex) {
        if (lowIndex <= highIndex) {
            int index = (highIndex - lowIndex) / 2 + lowIndex;

            if (strings[index].equals(value)) {
                return index;
            }
            else if (strings[index].isEmpty()) {
                int left = findIndex(strings, value, lowIndex, index - 1);
                if (left == -1) {
                    return findIndex(strings, value, index + 1, highIndex);
                }
                else {
                    return left;
                }
            }
            else if (strings[index].compareTo(value) == 1) {
                return findIndex(strings, value, lowIndex, index - 1);
            }
            else {
                return findIndex(strings, value, index + 1, highIndex);
            }
        }
        else {
            return -1;
        }
    }
    
    public static void main(String [] args) {
        assert (findIndex(new String [] {"", "a", "b", "", "", "", "", "", "bc", "", "happy", "", "", "", "never", "","zebra"}, "a") == 1);
        assert (findIndex(new String [] {"", "a", "b", "", "", "", "", "", "bc", "", "happy", "", "", "", "never", "","zebra"}, "b") == 2);
        assert (findIndex(new String [] {"", "a", "b", "", "", "", "", "", "bc", "", "happy", "", "", "", "never", "","zebra"}, "bc") == 8);
        assert (findIndex(new String [] {"", "a", "b", "", "", "", "", "", "bc", "", "happy", "", "", "", "never", "","zebra"}, "happy") == 10);
        assert (findIndex(new String [] {"", "a", "b", "", "", "", "", "", "bc", "", "happy", "", "", "", "never", "","zebra"}, "zebra") == 16);

    }
}