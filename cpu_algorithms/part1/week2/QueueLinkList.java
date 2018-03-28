public class QueueLinkList<T> {
    private Node<T> mRoot = null;
    private int mSize = 0;

    public void enqueue(T data) {
        if (data == null) {
            return;
        }

        Node<T> node = new Node<T>();
        node.data = data;

        if (mSize == 0) {
            mRoot = node;
        }
        else {
            // iterate to the end of the linked list to enqueue.
            // Could maintain a tail pointer but will leave that 
            // for another time such that don't have to manage the logic for
            // overlapping case of head and tail.
            Node <T> cNode = mRoot;
            while (cNode.next != null) {
                cNode = cNode.next;
            }
            cNode.next = node;
        }
        mSize++;
    }

    public T dequeue() {
        Node <T> node = null;
        T data = null;

        // Ensure that the pop operation can be exercised on the stack.
        if (mRoot == null || mSize == 0) {
            return data;
        }

        node = mRoot;
        mRoot = mRoot.next;
        mSize--;
        
        data = node.data;
        node.next = null;
        node.data = null;

        return data;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    private class Node<T> {
        T data;
        Node<T> next;
    }

    public static void main(String[] args) {
        QueueLinkList<String> stringStack = new QueueLinkList<String>();

        stringStack.enqueue("hello");
        System.out.println(stringStack.dequeue() + " " + Integer.toString(stringStack.size()));
        System.out.println(stringStack.dequeue() + " " + Integer.toString(stringStack.size()));
        stringStack.enqueue("hello");
        stringStack.enqueue("there");
        stringStack.enqueue("bob");
        System.out.println(stringStack.dequeue() + " " + Integer.toString(stringStack.size()));
        System.out.println(stringStack.dequeue() + " " + Integer.toString(stringStack.size()));
        System.out.println(stringStack.dequeue() + " " + Integer.toString(stringStack.size()));
        System.out.println(stringStack.dequeue() + " " + Integer.toString(stringStack.size()));
    }
}