public class ivq4 {
    public static void convertSpaces(char [] characters, int trueLength) {
        int pivot = characters.length - 1;
        
        if (trueLength <= 0 || characters == null || characters[characters.length - 1] != ' ') {
            return;
        }

        for (int index = trueLength - 1; index >= 0 && index != pivot; index--) {
            if (characters[index] == ' ') {
                characters[pivot--] = '0';
                characters[pivot--] = '2';
                characters[pivot--] = '%';
            }
            else {
                characters[pivot--] = characters[index];
            }
        }

        return;
    }

    public static void main(String [] args) {
        String s = " Mr John Smith         ";
        char [] characters = s.toCharArray();

        convertSpaces(characters, 15);
        System.out.println(new String(characters));
    }
}