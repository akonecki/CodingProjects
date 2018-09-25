import java.util.Stack;
import java.util.Comparator;

public class Solution {
    
    // O(N^2) Might have to perform the full push and pop logic for all values
    // that exist within the stack.
    public static <T extends Comparable<T>> void sort(Stack<T> data) {
        // data is the un-sorted stack.
        Stack<T> sorted = new Stack<T>();

        while (!data.isEmpty()) {
            if (sorted.isEmpty()) {
                sorted.push(data.pop());
            }
            else {
                // need to perform data inspection.
                T value = data.pop();

                while (!sorted.isEmpty()) {
                    if (value.compareTo(sorted.peek()) >= 0) {
                        break;
                    }
                    else {
                        data.push(sorted.pop());
                    }
                }

                sorted.push(value);
            }
        }

        while (!sorted.isEmpty()) {
            data.push(sorted.pop());
        }
    }
    
    public static void main(String [] args) {
        Stack<Integer> data = new Stack<Integer>();

        data.push(1);
        data.push(2);
        data.push(3);
        data.push(4);
        data.push(5);
        data.push(6);
        data.push(7);
        data.push(8);
        data.push(9);
        data.push(10);

        sort(data);

        // Iterator is from the bottom of the stack to the top.
        for (int num : data) {
            System.out.print(num + " ");
        }
        System.out.println("");

    }
}