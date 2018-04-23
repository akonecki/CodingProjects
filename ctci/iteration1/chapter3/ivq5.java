public class ivq5 <T> {
    private static final int STACK_SIZE = 100;
    // Making both stacks the same size for simplicity.
    // Making both stacks static for this implementation, just tired of suppting
    // the dynamic memory.
    private Stack<T> mEnqueueStack = new Stack<T>(ivq5.STACK_SIZE);
    private Stack<T> mDequeueStack = new Stack<T>(ivq5.STACK_SIZE);

    private class Stack <T> {
        private T [] mStack = null;
        private int mSize = 0;

        public Stack(int capacity) {
            this.mStack = (T []) new Object [capacity];
        }

        public boolean push(T value) {
            if (value == null || this.mSize >= this.mStack.length) {
                return false;
            }

            this.mStack[this.mSize++] = value;
            return true;
        }

        public T pop() {
            if (this.mSize <= 0) {
                return null;
            }

            T value = this.mStack[--mSize];
            this.mStack[mSize] = null;

            return value;
        }

        public boolean isEmpty() {
            return (this.mSize == 0);
        }

        public boolean isFull() {
            return (this.mSize >= this.mStack.length);
        }

        public int size() {
            return this.mSize;
        }
    }

    public boolean enqueue(T value) {
        if (value == null || this.mEnqueueStack.isFull()) {
            return false;
        }

        assert (this.mEnqueueStack.push(value));

        return true;
    }

    public T dequeue() {
        if (this.mDequeueStack.isEmpty() && this.mEnqueueStack.isEmpty()) {
            throw new java.lang.IllegalArgumentException("Nothing to dequeue.");
        }
        else if (this.mDequeueStack.isEmpty()) {
            while (!this.mDequeueStack.isFull() && !this.mEnqueueStack.isEmpty()) {
                T value = this.mEnqueueStack.pop();
                this.mDequeueStack.push(value);
            }
        }

        return this.mDequeueStack.pop();
    }

    public static void main(String [] args) {
        ivq5<Integer> myQueue = new ivq5<Integer>();

        for (int index = 0; index < 101; index++) {
            if (!myQueue.enqueue(index)) {
                break;
            }

            assert (index < ivq5.STACK_SIZE); 
        }

        assert (myQueue.dequeue() == 0);
        assert (myQueue.dequeue() == 1);
        assert (myQueue.dequeue() == 2);
        assert (myQueue.dequeue() == 3);
        assert (myQueue.dequeue() == 4);
        assert (myQueue.enqueue(-1));
        assert (myQueue.enqueue(-1));
        assert (myQueue.enqueue(-1));
        assert (myQueue.enqueue(-1));
        assert (myQueue.enqueue(-1));
        assert (!myQueue.enqueue(-1));
    }
}