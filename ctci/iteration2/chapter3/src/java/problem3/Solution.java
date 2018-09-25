import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    public static class StackOfPlates <T> {
        private final int CAPACITY;
        private ArrayList<Stack<T>> mStackOfPlates = new ArrayList<Stack<T>>();

        private StackOfPlates() {
            this.CAPACITY = 1;
        }

        public StackOfPlates(int capacity) {
            if (capacity < 1) {
                throw new java.lang.IllegalArgumentException("Illegal Capacity Provided.");
            }

            this.CAPACITY = capacity;
        }

        // not going to support load balanced push in this push.
        public void push(T data) {
            Stack<T> stack = null;

            if (mStackOfPlates.isEmpty() || mStackOfPlates.get(mStackOfPlates.size() - 1).size() >= CAPACITY) {
                stack = new Stack<T>();
                stack.push(data);
                mStackOfPlates.add(stack);    
            }
            else {
                stack = mStackOfPlates.get(mStackOfPlates.size() - 1);
                stack.push(data);
                mStackOfPlates.set(mStackOfPlates.size() - 1, stack);
            }
        }

        public T pop() {
            if (mStackOfPlates.isEmpty()) {
                return null;
            }
            else {
                T value = null;
                
                if (!mStackOfPlates.get(mStackOfPlates.size() - 1).isEmpty()) {
                    value = mStackOfPlates.get(mStackOfPlates.size() - 1).pop();
                }
                
                if (mStackOfPlates.get(mStackOfPlates.size() - 1).isEmpty()) {
                    mStackOfPlates.remove(mStackOfPlates.size() - 1);
                }

                return value;
            }
        }

        public T popAt(int index) {
            if (mStackOfPlates.isEmpty() || index >= mStackOfPlates.size()) {
                return null;
            }
            else {
                T value = null;

                if (!mStackOfPlates.get(index).isEmpty()) {
                    value = mStackOfPlates.get(index).pop();
                }
                
                if (mStackOfPlates.get(mStackOfPlates.size() - 1).isEmpty()) {
                    mStackOfPlates.remove(mStackOfPlates.size() - 1);
                }

                return value;
            }
        }

        public void print() {
            int stackIndex = 0;

            for (Stack<T> stack : mStackOfPlates) {
                System.out.print("Stack " + stackIndex + " : ");
                stackIndex++;
                for (T value : stack) {
                    System.out.print(value + " ");
                }
                System.out.println("");
            }
        }
    }

    public static void main(String [] args) {
        Stack<Integer> normalStack = new Stack<Integer>();
        StackOfPlates<Integer> plateStack = new StackOfPlates<Integer>(5);

        for (int i = 0; i < 10; i++) {
            normalStack.push(i);
            plateStack.push(i);
        }

        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());

        for (int i = 0; i < 10; i++) {
            normalStack.push(i);
            plateStack.push(i);
        }

        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());

        for (int i = 0; i < 10; i++) {
            normalStack.push(i);
            plateStack.push(i);
        }  
        
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());
        assert (normalStack.pop().intValue() == plateStack.pop().intValue());

        plateStack.print();
        assert (plateStack.popAt(0).intValue() == 4);
        assert (plateStack.popAt(0).intValue() == 3);
        assert (plateStack.popAt(0).intValue() == 2);
        plateStack.print();
        assert (plateStack.popAt(1).intValue() == 2);
        assert (plateStack.popAt(1).intValue() == 1);
        assert (plateStack.popAt(1).intValue() == 0);
        plateStack.print();
        assert (plateStack.popAt(3).intValue() == 7);
        assert (plateStack.popAt(3).intValue() == 6);
        assert (plateStack.popAt(3).intValue() == 5);
        assert (plateStack.popAt(3).intValue() == 4);
        plateStack.print();
    }
}