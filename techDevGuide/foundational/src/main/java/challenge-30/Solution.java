import java.util.*;

public class Solution {
    
    public static int eval(String expression) {
        Stack<String> left = new Stack<String>();
        Stack<String> control = new Stack<String>();

        for (int pivot = 0; pivot < expression.length(); ) {
            if (expression.charAt(pivot) == ' ') {
                pivot++;
            }
            else if (expression.charAt(pivot) == '+' || 
                     expression.charAt(pivot) == '-' || 
                     expression.charAt(pivot) == '*' || 
                     expression.charAt(pivot) == '/') 
            {
                while (!control.isEmpty() && 
                        getPrioity(control.peek()) >= getPrioity(expression.substring(pivot, pivot + 1))) 
                {
                    int num1 = Integer.parseInt(left.pop());
                    int num2 = Integer.parseInt(left.pop());

                    String operation = control.pop();

                    if (operation.equals("*")) {
                        left.push(Integer.toString(num1 * num2));
                    }
                    else if (operation.equals("+")) {
                        left.push(Integer.toString(num1 + num2));
                    }
                    else if (operation.equals("-")) {
                        left.push(Integer.toString(num2 - num1));
                    }
                    else {
                        left.push(Integer.toString(num2 / num1));
                    }
                }

                control.push(expression.substring(pivot, pivot + 1));
                pivot++;
            }
            else {
                int tail = pivot + 1;

                for (; tail < expression.length(); tail++) {
                    if (expression.charAt(tail) < '0' || expression.charAt(tail) > '9') {
                        break;
                    }
                }

                left.push(expression.substring(pivot, tail));
                pivot = tail;
            }
        }

        while (!control.isEmpty()) {
            int num1 = Integer.parseInt(left.pop());
            int num2 = Integer.parseInt(left.pop());

            String operation = control.pop();

            if (operation.equals("*")) {
                left.push(Integer.toString(num1 * num2));
            }
            else if (operation.equals("+")) {
                left.push(Integer.toString(num1 + num2));
            }
            else if (operation.equals("-")) {
                left.push(Integer.toString(num2 - num1));
            }
            else {
                left.push(Integer.toString(num2 / num1));
            }
        }

        return Integer.parseInt(left.pop());
    }

    private static int getPrioity(String str) {
        return str.equals("+") || str.equals("-") ? 1 : 2;
    }
    
    public static void main(String [] args) {
        assert (eval("1") == 1);
        assert (eval("1+1") == 2);
        assert (eval("1 + 1") == 2);
        assert (eval("1 +1") == 2);
        assert (eval("1 - 1") == 0);
        assert (eval("1*1") == 1);
        assert (eval("1/1") == 1);
        assert (eval("1+1+1") == 3);
        assert (eval("1+1/1") == 2);
        assert (eval("1+1*1") == 2);
        assert (eval("1+1-1") == 1);
        assert (eval("1/1+1") == 2);
        assert (eval("1*1+1") == 2);
        assert (eval("1/1-1") == 0);
        assert (eval("1*1-1") == 0);
    }
}