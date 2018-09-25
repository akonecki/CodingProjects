import java.util.Stack;
import java.util.Comparator;

public class Solution {
    public static class StackNode {
        private int value;
        private int min;

        public StackNode(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    public static class MyStack {
        private Stack<StackNode> stack = new Stack<>();
        
        public void push(int data) {
            if (stack.isEmpty()) {
                stack.push(new StackNode(data, data));
            }
            else {
                stack.push(new StackNode(data, Math.min(stack.peek().min, data)));
            }
        }

        public int pop() {
            if (stack.isEmpty()) {
                throw new java.lang.IllegalStateException("Can not pop");
            }
            else {
                StackNode node = stack.pop();
                return node.value;
            }
        }

        public int min() {
            if (stack.isEmpty()) {
                throw new java.lang.IllegalStateException("Can not min");
            }
            else {
                StackNode node = stack.peek();
                return node.min;
            }
        }
    }

    // More space efficient since if the minimum is not constantly changing
    // then dont have to allocate space for it.  In the previous implementation
    // allocated space for it no mater what.
    public static class SpaceStack {
        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public void push(int value) {
            if (minStack.isEmpty()) {
                minStack.push(value);
            }
            else {
                if (value <= minStack.peek().intValue()) {
                    minStack.push(value);
                }
            }

            stack.push(value);
        }

        public int min() {
            if (minStack.isEmpty()) {
                throw new java.lang.IllegalStateException("no min");
            }
            else {
                return minStack.peek().intValue();
            }
        }
    }

    public static void main(String [] args) {
        MyStack stack = new MyStack();

        stack.push(3);
        stack.push(4);
        assert (stack.min() == 3);
        stack.push(2);
        assert (stack.min() == 2);
        stack.push(1);
        assert (stack.min() == 1);
        stack.push(2);
        assert (stack.min() == 1);
        stack.pop();
        assert (stack.min() == 1);
        stack.pop();
        stack.pop();
        assert (stack.min() == 3);
    }
}