public class StackLinkList<T> {
    private Node<T> mRoot = null;
    private int mSize = 0;

    public void push(T data) {
        if (data == null) {
            return;
        }

        Node<T> node = new Node<T>();
        node.data = data;

        if (mSize == 0) {
            mRoot = node;
        }
        else {
            node.next = mRoot;
            mRoot = node;
        }
        mSize++;
    }

    public T pop() {
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
        StackLinkList<String> stringStack = new StackLinkList<String>();

        stringStack.push("hello");
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
    }
}