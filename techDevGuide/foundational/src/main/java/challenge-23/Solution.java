public class Solution {
    public static String decompress(String compressed) {
        StringBuilder result = new StringBuilder();
        int index = 0;

        //while (index < compressed.length()) {
        //    StringBuilder sb = new StringBuilder();
        //    System.out.println(index);
            index = decompress("1["+compressed+"]", index, result);
        //    result.append(sb.toString());
        System.out.println("");
        //}
        // System.out.println(result.toString());
        return result.toString();
    }

    private static int decompress(String compressed, int index, StringBuilder sb) {
        if (index >= compressed.length()) {
            return index;
        }
        
        char character = compressed.charAt(index);
        System.out.println(index);
        if (character >= 'a' && character <= 'z') {
            int endIndex = index;
            while (endIndex < compressed.length() && 
                compressed.charAt(endIndex) >= 'a' && 
                compressed.charAt(endIndex) <= 'z') 
            {
                endIndex++;
            }
            sb.append(compressed.substring(index, endIndex));
            return endIndex;
        }
        else {
            //while (index < compressed.length()) {
                StringBuilder levelResult = new StringBuilder();
                int value = 0;

                // 1. build the value
                while (compressed.charAt(index) != '[') {
                    value = value * 10 + compressed.charAt(index) - '0';
                    index++;
                }

                // Handle the '['
                index++;

                // System.out.println(value + " " + index);

                while (index < compressed.length() && compressed.charAt(index) != ']') {
                    StringBuilder sub = new StringBuilder();
                    index = decompress(compressed, index, sub);
                    levelResult.append(sub.toString());
                }

                String result = levelResult.toString();
                while (value > 0) {
                    sb.append(result);
                    value--;
                }

            //    index = index + 1;
            //}

            return index + 1;
        }
    }
    
    public static void main(String [] args) {
        assert (decompress("3[b2[da]a4[c]]").equals("bdadaaccccbdadaaccccbdadaacccc"));
        assert (decompress("1[a]").equals("a"));
        assert (decompress("1[a1[b]]").equals("ab"));
        assert (decompress("1[a]1[b]").equals("ab"));
        assert (decompress("a[]b").equals("ab"));
        assert (decompress("0[abc]").equals(""));
    }
}