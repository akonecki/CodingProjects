public class Solution {
    public static char [] urlify(char [] url, int length) {
        
        if (length < 1) {
            return url;
        }

        // first need to get to end of the character array.  Might not be given
        // in all languages.
        int rightIndex = length - 1;
        int spaceCount = 0;

        for (int index = 0; index < length; index++) {
            if (url[index] == ' ') {
                rightIndex += 2;
            }
        }

        // now use two pointer system to modify the url in place.
        int leftIndex = length - 1;

        while (leftIndex >= 0 && leftIndex < rightIndex) {
            if (url[leftIndex] != ' ') {
                url[rightIndex] = url[leftIndex];
            }
            else {
                // %20
                url[rightIndex--] = (char)(0x30);
                url[rightIndex--] = (char)(0x32);
                url[rightIndex] = (char)(0x25);   
            }
            leftIndex--;
            rightIndex--;
        }

        return url;
    }
    
    public static boolean containsSpaces(char [] letters) {
        for (char character : letters) {
            if (character == ' ') {
                return true;
            }
        }

        return false;
    }

    public static char [] generateUrl(char [] url, String str) {
        int spaceCount = 0;
        for (int index = 0; index < str.length(); index++) {
            if (str.charAt(index) == ' ') {
                spaceCount++;
            }
        }

        url = new char [str.length() + spaceCount * 2];

        for (int index = 0; index < str.length(); index++) {
            url[index] = str.charAt(index);
        }

        return url;
    }

    public static void main(String [] args) {
        assert (!containsSpaces(urlify(generateUrl(new char [0], "Mr John Smith"), "Mr John Smith".length())));
        assert (!containsSpaces(urlify(generateUrl(new char [0], "MrJohnSmith"), "MrJohnSmith".length())));
        assert (!containsSpaces(urlify(generateUrl(new char [0], " Mr John Smith "), " Mr John Smith ".length())));
        assert (!containsSpaces(urlify(generateUrl(new char [0], " Mr  John  Smith "), " Mr  John  Smith ".length())));    
    }
}