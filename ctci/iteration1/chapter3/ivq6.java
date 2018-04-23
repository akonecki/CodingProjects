import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ivq6 <T extends Comparable<T>> {
    private Stack<T> mPrimary = new Stack<T>();
    private Stack<T> mBuffer = new Stack<T>();

    public void sort() {
        if (this.mPrimary.isEmpty()) {
            return;
        }

        int count = -1;
        T minElement = null;

        while (count != 0 && !this.mPrimary.isEmpty()) {
            int index = count;
            count = 0;
            minElement = null;
            while (index != 0 && !this.mPrimary.isEmpty()) {
                T value = this.mPrimary.pop();

                if (minElement == null) {
                    minElement = value;
                }
                else if (value.compareTo(minElement) < 0) {
                    this.mBuffer.push(minElement);
                    minElement = value;
                }
                else {
                    this.mBuffer.push(value);
                }

                count++;
                index--;
            }

            // Push the min element back onto the original stack.
            this.mPrimary.push(minElement);

            while (!this.mBuffer.isEmpty()) {
                T value = this.mBuffer.pop();
                this.mPrimary.push(value);
            }

            count--;
        }
    }

    public void push(T value) {
        this.mPrimary.push(value);
    }

    public List<T> toArray() {
        List<T> list = new ArrayList<T>();

        assert (this.mBuffer.isEmpty());

        while (!this.mPrimary.isEmpty()) {
            T value = this.mPrimary.pop();
            this.mBuffer.push(value);
        }

        while (!this.mBuffer.isEmpty()) {
            T value = this.mBuffer.pop();
            // Likely need to do a full copy.
            list.add(value);
            this.mPrimary.push(value);
        }

        assert (this.mBuffer.isEmpty());

        return list;
    }

    public static void main (String [] args) {
        ivq6<Integer> integers = new ivq6<Integer>();
        integers.push(-1);
        integers.push(6);
        integers.push(5);
        integers.push(7);
        integers.push(4);
        integers.push(8);
        integers.push(3);
        integers.push(9);
        integers.push(2);
        integers.push(10);
        integers.sort();

        for (Integer integer : integers.toArray()) {
            System.out.println(integer);
        }
    }
}