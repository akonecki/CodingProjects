public class StackStaticArray<T> {
    private T[] mDataArray;
    // this is not really necessary since java would provide these as benefits 
    // of java arrays, but say in C this would need to be provided.
    private int mCapacity = 0;
    private int mSize = 0;

    public StackStaticArray(int numOfElements) {
        // Generally frowned upon, but this is a simple exercise.
        mDataArray = (T[]) new Object[numOfElements];
        mCapacity = numOfElements;
    }

    public void push(T data) {
        // Prevent buffer overflow.
        if (mSize < mCapacity && data != null) {
            mDataArray[mSize] = data;
            mSize++;
        }    
    }

    public T pop() {
        T data = null;

        if (mSize > 0) {
            mSize--;
            data = mDataArray[mSize];
            mDataArray[mSize] = null;
        }

        return data;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public static void main(String[] args) {
        StackStaticArray<String> stringStack = new StackStaticArray<String>(10);

        stringStack.push("hello");
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
    }
}