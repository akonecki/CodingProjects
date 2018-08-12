import java.util.ArrayList;

public class ivq6 {

    // [F]uncational Implementation
    public static ArrayList<String> getParenthesesSets(int N) {
        ArrayList<String> result = new ArrayList<String>();

        getParenthesesSets(N, 0, 0, result, new StringBuilder());

        return result;
    }

    private static void getParenthesesSets(
        int N, 
        int openCount, 
        int closeCount, 
        ArrayList<String> result, 
        StringBuilder sb) 
    {
        if (openCount == N && closeCount == N) {
            result.add(sb.toString());
        }
        else {
            if (openCount < N) {  
                StringBuilder newSB = new StringBuilder(sb.toString());
                newSB.append('(');
                getParenthesesSets(N, openCount + 1, closeCount, result, newSB);
            }

            if (closeCount < N && closeCount < openCount) {
                sb.append(')');
                getParenthesesSets(N, openCount, closeCount + 1, result, new StringBuilder(sb.toString()));
            }
        }
    }

    /*
        2 0 0
            2 1 0 (
                2 2 0 ((
                    SKIP
                    2 2 1 (()
                        SKIP
                        2 2 2 (())
                            SAVE (())
                
    */

    public static void main(String [] args) {
        for (String string : getParenthesesSets(3)) {
            System.out.println(string);
        }
    }
}