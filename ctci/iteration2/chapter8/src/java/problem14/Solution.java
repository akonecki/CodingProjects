import java.util.HashMap;

public class Solution {
    // [F]unctional
    private static int evalWays(String expression, boolean answerDesired) {
        if (expression.length() == 0) {
            return 0;
        }
        else if (expression.length() == 1) {
            return (expression.charAt(0) == '1' ? true : false) == answerDesired ? 1 : 0;
        }

        int count = 0;
        for (int index = 1; index < expression.length(); index++) {
            String leftExpression = expression.substring(0, index);
            String rightExpression = expression.substring(index + 1, expression.length());

            int leftTrueCount = evalWays(leftExpression, true);
            int leftFalseCount = evalWays(leftExpression, false);
            int rightTrueCount = evalWays(rightExpression, true);
            int rightFalseCount = evalWays(rightExpression, false);

            int trueTotal = 0;
            switch (expression.charAt(index)) {
                case '^':
                trueTotal = leftTrueCount * rightFalseCount + leftFalseCount * rightTrueCount;
                break;
                case '&':
                trueTotal = leftTrueCount * rightTrueCount;
                break;
                case '|':
                trueTotal = leftTrueCount * rightFalseCount + leftFalseCount * rightTrueCount + leftTrueCount * rightTrueCount;
                break;
            }

            count += answerDesired ? trueTotal : ((leftTrueCount + leftFalseCount) * (rightTrueCount + rightFalseCount)) - trueTotal;
        }
        
        return count;
    }
    
    // [A]nalysis
    // Does meet DP requirements

    // Performance
    // Memory
    // O(N) for the stack

    // Time
    // O((N/2)^N)

    // [S]ubproblem Memoization
    private static int evalWays(String expression, boolean answerDesired, HashMap<String, Integer> map) {
        if (expression.length() == 0) {
            return 0;
        }
        else if (expression.length() == 1) {
            return (expression.charAt(0) == '1' ? true : false) == answerDesired ? 1 : 0;
        }

        if (map.containsKey(answerDesired + expression)) {
            return map.get(answerDesired + expression);
        }

        int count = 0;
        for (int index = 1; index < expression.length(); index++) {
            String leftExpression = expression.substring(0, index);
            String rightExpression = expression.substring(index + 1, expression.length());

            int leftTrueCount = evalWays(leftExpression, true);
            int leftFalseCount = evalWays(leftExpression, false);
            int rightTrueCount = evalWays(rightExpression, true);
            int rightFalseCount = evalWays(rightExpression, false);

            int trueTotal = 0;
            switch (expression.charAt(index)) {
                case '^':
                trueTotal = leftTrueCount * rightFalseCount + leftFalseCount * rightTrueCount;
                break;
                case '&':
                trueTotal = leftTrueCount * rightTrueCount;
                break;
                case '|':
                trueTotal = leftTrueCount * rightFalseCount + leftFalseCount * rightTrueCount + leftTrueCount * rightTrueCount;
                break;
            }

            count += answerDesired ? trueTotal : ((leftTrueCount + leftFalseCount) * (rightTrueCount + rightFalseCount)) - trueTotal;
        }
        
        map.put(answerDesired + expression, count);

        return count;
    }

    public static int numberOfWays(String expression, boolean answerDesired) {
        assert (evalWays(expression, answerDesired) == evalWays(expression, answerDesired, new HashMap<String, Integer>()));
        return evalWays(expression, answerDesired);
    }    

    public static void main(String [] args) {
        assert (numberOfWays("1^0|0|1", false) == 2);
        assert (numberOfWays("0&0&0&1^1|0", true) == 10);
        assert (numberOfWays("0", false) == 1);
        assert (numberOfWays("1", true) == 1);
    }
}