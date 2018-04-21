public class ivq2 <T extends Comparable <T>> {
    private static int DEFAULT_SIZE = 100;
    private Node [] mStack = null;
    private int mSize = 0;
    private int mCapacity = 0;

    private static class Node {
        Object mValue = null;
        Object mMin = null;

        public Node(Object value, Object min) {
            this.mValue = value;
            this.mMin = min;
        }
    }

    private void resize(int newCapacity) {
        return;
    }

    public ivq2() {
        this.mStack = new Node[ivq2.DEFAULT_SIZE];
    }

    public void push(T value) {
        if (value == null) {
            return;
        }

        if (this.mSize == this.mCapacity) {
            this.resize(this.mCapacity * 2);
        }

        Node node = null;

        if (this.mSize != 0 && value.compareTo(this.min()) > 0) {
            node = new Node(value, this.min());
        }
        else {
            node = new Node(value, value);
        }

        this.mStack[this.mSize++] = node;
    }
 
    public T pop() {
        if (this.mSize <= 0) {
            throw new java.lang.IllegalArgumentException("");
        }

        T value = (T) this.mStack[--this.mSize].mValue;

        if (this.mSize * 4 <= this.mCapacity && this.mCapacity > ivq2.DEFAULT_SIZE) {
            this.resize(this.mCapacity / 2);
        }

        return (T) value;
    }

    public T min() {
        if (this.mSize <= 0) {
            throw new java.lang.IllegalArgumentException("");
        }

        return (T) this.mStack[this.mSize - 1].mMin;
    }

    public static void main(String [] args) {
        ivq2<Integer> integers = new ivq2<Integer>();

        integers.push(2);
        assert(integers.min() == 2);
        integers.push(3);
        assert(integers.min() == 2);
        integers.push(-1);
        assert(integers.min() == -1);
        integers.pop();
        assert(integers.min() == 2);
    }
}