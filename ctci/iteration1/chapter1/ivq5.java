public class ivq5 {
    public static char [] compress(char [] characters) {
        int compressedLength = 0;
        char [] compressedCharacters = null;

        if (characters == null || characters.length <= 0) {
            return characters;
        }

        for (int i = 0; i < characters.length;) {
            int count = ivq5.charCount(characters, i);
            // System.out.println(1 + Integer.toString(count - i).length());
            compressedLength += 1 + Integer.toString(count - i).length();
            i = count;
        }

        // System.out.println(compressedLength);

        if (compressedLength < characters.length) {
            // Compress
            compressedCharacters = new char[compressedLength];
            int j = 0;

            for (int i = 0; i < characters.length;) {
                int count = ivq5.charCount(characters, i);
                compressedCharacters[j++] = characters[i];

                // System.out.println(Integer.toString(count - i).length());

                for (int k = 0; k < Integer.toString(count - i).length(); k++) {
                    // System.out.println(Integer.toString(count - i).charAt(k));
                    compressedCharacters[j++] = Integer.toString(count - i).charAt(k);
                }

                i = count;
            }
        }
        else {
            compressedCharacters = characters;
        }

        return compressedCharacters;
    }

    private static int charCount(char [] characters, int index) {
        char character = characters[index];
        int j = index + 1;

        while (j < characters.length && character == characters[j]) {
            j++;
        }

        return j;
    }

    public static void main(String [] args) {
        String test = "aabcccccaaabababababbbbbbbbbb";


        System.out.println(new String(ivq5.compress(test.toCharArray())));
    }
}