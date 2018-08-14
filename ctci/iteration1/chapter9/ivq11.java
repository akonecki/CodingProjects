public class ivq11 {

    public static int numberOfWays(String expression, boolean desiredValue) {
        return numberOfWays(expression, 0, desiredValue, new StringBuffer(), 0, 0);
    }

    private static int numberOfWays(String expression, int index, boolean desiredValue, StringBuffer sb, int openCount, int closeCount) {
        if (index >= expression.length()) {
            while (closeCount < openCount) {
                sb.append(')');
                closeCount++;
            }

            if (evaulateExpression(sb.toString()) == desiredValue) {
                System.out.println(sb.toString());
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            char character = expression.charAt(index);

            if (character == '^' || character == '|' || character == '&') {
                sb.append(character);
                return numberOfWays(expression, index + 1, desiredValue, sb, openCount, closeCount);
            }
            else {
                int sum = 0;

                // exclude case
                StringBuffer newSB = new StringBuffer(sb.toString());
                newSB.append(character);
                sum += numberOfWays(expression, index + 1, desiredValue, newSB, openCount, closeCount);
                
                StringBuffer stringBuffer = new StringBuffer(sb.toString());
                // include case open
                for (int count = openCount; count < (expression.length() / 2) && index + 1 != expression.length(); count++) {
                    stringBuffer.append('(');
                    sum += numberOfWays(expression, 
                        index + 1, 
                        desiredValue, 
                        (new StringBuffer(stringBuffer.toString())).append(character), 
                        count + 1, 
                        closeCount);
                }

                sb.append(character);
                // include case close
                for (int count = closeCount; count < openCount; count++) {
                    sb.append(')');
                    sum += numberOfWays(expression, 
                        index + 1, 
                        desiredValue, 
                        new StringBuffer(sb.toString()), 
                        openCount, 
                        count + 1);
                }
                
                return sum;
            }
        }
    }

    private static boolean evaulateExpression(String expression) {
        return false;
    }

    public static void main (String [] args) {
        assert (numberOfWays("1^0|0|1", false) == 2);
    }
}