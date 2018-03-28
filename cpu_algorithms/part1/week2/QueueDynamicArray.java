public class QueueDynamicArray<T> {
    private T[] mDataArray;
    // this is not really necessary since java would provide these as benefits 
    // of java arrays, but say in C this would need to be provided.
    private int mCapacity = 0;
    private int mSize = 0;

    private int mHeadIndex = 0;
    private int mTailIndex = 0;

    private static final int STARTING_CAPACITY = 1;

    public QueueDynamicArray() {
        // Generally frowned upon, but this is a simple exercise.
        mDataArray = (T[]) new Object[STARTING_CAPACITY];
        mCapacity = STARTING_CAPACITY;
    }

    private void resizeDataArray(int newCapacity) {
        T[] tempData = (T[]) new Object[newCapacity];
        
        for (int index = 0; index < mSize; index++) {
            tempData[index] = mDataArray[(index +  mHeadIndex) % mCapacity];
        }

        mCapacity = newCapacity;

        mDataArray = tempData; 
        mHeadIndex = 0;
        mTailIndex = mSize;
    }

    public void enqueue(T data) {

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

        mDataArray[mTailIndex] = data;
        mSize++;
        mTailIndex = (mTailIndex + 1) % mCapacity;
    }

    public T dequeue() {
        T data = null;

        if (mSize > 0) {
            mSize--;
            data = mDataArray[mHeadIndex];
            mDataArray[mHeadIndex] = null;
            mHeadIndex = (mHeadIndex + 1) % mCapacity;

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

    public int getHeadIndex() {
        return mHeadIndex;
    }

    public int getTailIndex() {
        return mTailIndex;
    }

    public static void main(String[] args) {
        QueueDynamicArray<String> stringStack = new QueueDynamicArray<String>();

        stringStack.enqueue("hello");
        System.out.println(stringStack.dequeue());
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("hello");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("world");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("there");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("this");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("could");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("be");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("the");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("end");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        System.out.println(stringStack.dequeue());
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("hello");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        System.out.println(stringStack.dequeue());
        stringStack.enqueue("hello");
        System.out.println(stringStack.dequeue());
        System.out.println(stringStack.dequeue());
        stringStack.enqueue("world");
        stringStack.enqueue("there");
        System.out.println(stringStack.dequeue());
        System.out.println(stringStack.dequeue());
        stringStack.enqueue("this");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
        stringStack.enqueue("could");
        System.out.println(Integer.toString(stringStack.size()) + " " + Integer.toString(stringStack.getCapacity()) + " head " + Integer.toString(stringStack.getHeadIndex()) + " tail " + Integer.toString(stringStack.getTailIndex()));
    }
}
