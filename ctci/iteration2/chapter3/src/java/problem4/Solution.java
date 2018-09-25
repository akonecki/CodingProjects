import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {

    public static class MyQueue <T> {
        /* 
        // add / enqueue efficient
        private Stack<T> pushStack = new Stack<T>();
        private Stack<T> popStack = new Stack<T>();

        public void enqueue(T data) {
            pushStack.push(data);
        }

        public T dequeue() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }

            if (popStack.isEmpty()) {
                return null;
            }
            else {
                return popStack.pop();
            }
        }
        */

        // remove / dequeue efficient
        private Stack<T> activeStack = new Stack<T>();
        private Stack<T> nonActiveStack = new Stack<T>();

        public void enqueue(T data) {
            while (!activeStack.isEmpty()) {
                nonActiveStack.push(activeStack.pop());
            }

            activeStack.push(data);

            while (!nonActiveStack.isEmpty()) {
                activeStack.push(nonActiveStack.pop());
            }
        }

        public T dequeue() {
            if (!activeStack.isEmpty()) {
                return activeStack.pop();
            }
            else {
                return null;
            }
        }
    }

    public static void main(String [] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < 10; i++) {
            myQueue.enqueue(i);
            queue.add(i);
        }

        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());

        for (int i = 0; i < 10; i++) {
            myQueue.enqueue(i);
            queue.add(i);
        }

        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());

        for (int i = 0; i < 10; i++) {
            myQueue.enqueue(i);
            queue.add(i);
        }

        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
        assert (myQueue.dequeue().intValue() == queue.remove().intValue());
    }
}