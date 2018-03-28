public class StackDynamicArray<T> {
    private T[] mDataArray;
    // this is not really necessary since java would provide these as benefits 
    // of java arrays, but say in C this would need to be provided.
    private int mCapacity = 0;
    private int mSize = 0;

    private static final int STARTING_CAPACITY = 1;

    public StackDynamicArray() {
        // Generally frowned upon, but this is a simple exercise.
        mDataArray = (T[]) new Object[STARTING_CAPACITY];
        mCapacity = STARTING_CAPACITY;
    }

    private void resizeDataArray(int newCapacity) {
        T[] tempData = (T[]) new Object[newCapacity];
        mCapacity = newCapacity;

        for (int index = 0; index < mSize; index++) {
            tempData[index] = mDataArray[index];
        }

        mDataArray = tempData;    
    }

    public void push(T data) {

        if (data == null) {
            return;
        }

        // Ensure that there is enough room first.
        if (mCapacity == 0) {
            mDataArray = (T[]) new Object[STARTING_CAPACITY];
            mCapacity = STARTING_CAPACITY;
        }
        else if (mSize == mCapacity) {
            // Double the size and copy the contents over.
            resizeDataArray(mCapacity * 2);
        }

        mDataArray[mSize] = data;
        mSize++;
    }

    public T pop() {
        T data = null;

        if (mSize > 0) {
            mSize--;
            data = mDataArray[mSize];
            mDataArray[mSize] = null;

            if (mSize <= (mCapacity / 4)) {
                resizeDataArray(mCapacity / 2);   
            }
        }

        return data;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public static void main(String[] args) {
        StackDynamicArray<String> stringStack = new StackDynamicArray<String>();

        stringStack.push("hello");
        System.out.println(stringStack.pop());
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()));
        stringStack.push("hello");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()));
        stringStack.push("world");
        stringStack.push("there");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()));
        System.out.println(stringStack.pop());
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()));
    }
}
