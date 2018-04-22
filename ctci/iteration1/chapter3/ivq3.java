public class ivq3 <T> {
    private Stack [] mStacks = new Stack[0];
    private int mPushIndex = 0;
    private static final int CAPACITY = 10;

    private static class Stack {
        private Object [] mStack = null;
        private int mSize = 0;

        public Stack() {
            this.mStack = new Object[ivq3.CAPACITY];
        }
    }

    private void resize(int numberOfStacks) {
        Stack [] stacks = new Stack[numberOfStacks];
        int count = this.mStacks.length;

        // Copy the references to the stacks of those stacks that have at least
        // one element.
        for (int index = 0; index < this.mStacks.length; index++) {
            if (this.mStacks[index].mSize > 0) {
                stacks[index] = this.mStacks[index];
                count--;
            }
        }

        // assert (count == 0);

        // Update the instance array.
        this.mStacks = stacks;
    }

    public void push(T value) {
        if (value == null) {
            return;
        }

        int pushIndex = 0;

        if (this.mStacks == null || mPushIndex == -1) {
            pushIndex = 0;
        }
        else {
            for (pushIndex = this.mPushIndex; pushIndex < this.mStacks.length; ) {
                if (this.mStacks[pushIndex].mSize >= ivq3.CAPACITY) {
                    pushIndex++;
                }
                else {
                    break;
                }
            }

            this.mPushIndex = pushIndex;
        }

        if (pushIndex == this.mStacks.length) {
            this.resize(pushIndex + 1);
            this.mStacks[pushIndex] = new Stack();
        }

        Stack stack = this.mStacks[pushIndex];
        stack.mStack[stack.mSize++] = (Object) value;        
    }

    public T pop() {
        if (this.mStacks.length <= 0) {
            return null;
        }

        return this.pop(this.mStacks.length - 1);
    }

    public T popAt(int index) {
        if (index < 0 || index >= this.mStacks.length) {
            return null;
        }

        return this.pop(index);
    }

    private T pop(int index) {
        T value;
        Stack stack = this.mStacks[index];
        value = (T) stack.mStack[--stack.mSize];

        // Determine if need to adjust the stacks.
        if (stack.mSize == 0) {
            // No longer need this stack
            this.resize(this.mStacks.length - 1);
        }

        // Now need to handle the instance push index.
        if (index < this.mPushIndex) {
            // want earlier stacks to be filled.
            this.mPushIndex = index;
        }
        
        return value;
    }

    public int size() {
        int size = 0;

        for (int index = 0; index < this.mStacks.length; index++) {
            size += this.size(index);
        }

        return size;
    }

    public int size(int index) {
        if (index < 0 || index >= this.mStacks.length || this.mStacks[index] == null) {
            return 0;
        }

        return this.mStacks[index].mSize;
    }

    public int stackCount() {
        return this.mStacks.length;
    }

    public static void main(String [] args) {
        ivq3<Integer> SetOfStacks = new ivq3<Integer>();
        
        for (int index = 0; index < ivq3.CAPACITY; index++) {
            SetOfStacks.push(index);
        }

        assert (SetOfStacks.size() == ivq3.CAPACITY);
        assert (SetOfStacks.stackCount() == 1);
        assert (SetOfStacks.size(SetOfStacks.stackCount() - 1) == ivq3.CAPACITY);

        for (int index = 0; index < ivq3.CAPACITY; index++) {
            SetOfStacks.push(index);
        }

        assert (SetOfStacks.size() == (ivq3.CAPACITY * 2));
        assert (SetOfStacks.stackCount() == 2);
        assert (SetOfStacks.size(SetOfStacks.stackCount() - 1) == ivq3.CAPACITY);

        Integer value = SetOfStacks.pop();
        assert (value.equals(9));
        value = SetOfStacks.pop();
        assert (value.equals(8));
        value = SetOfStacks.pop();
        assert (value.equals(7));
        value = SetOfStacks.pop();
        assert (value.equals(6));
        value = SetOfStacks.pop();
        assert (value.equals(5));

        assert (SetOfStacks.size() == 15);
        assert (SetOfStacks.stackCount() == 2);
        assert (SetOfStacks.size(SetOfStacks.stackCount() - 1) == 5); 
        value = SetOfStacks.pop();
        value = SetOfStacks.pop();
        value = SetOfStacks.pop();
        value = SetOfStacks.pop();
        value = SetOfStacks.pop();       
        assert (SetOfStacks.size() == 10);
        assert (SetOfStacks.stackCount() == 1);
        assert (SetOfStacks.size(SetOfStacks.stackCount() - 1) == 10); 

        for (int index = 0; index < ivq3.CAPACITY; index++) {
            SetOfStacks.push(index);
        }

        assert (SetOfStacks.size() == (ivq3.CAPACITY * 2));
        assert (SetOfStacks.stackCount() == 2);
        assert (SetOfStacks.size(SetOfStacks.stackCount() - 1) == ivq3.CAPACITY);

        value = SetOfStacks.popAt(0);  
        assert (value.equals(9));
        value = SetOfStacks.pop(0);
        assert (value.equals(8));
        value = SetOfStacks.pop(0);
        assert (value.equals(7));
        value = SetOfStacks.pop(0);
        assert (value.equals(6));
        value = SetOfStacks.pop(0);
        assert (value.equals(5)); 

        assert (SetOfStacks.size() == 15);
        assert (SetOfStacks.stackCount() == 2);
        assert (SetOfStacks.size(0) == 5);
        assert (SetOfStacks.size(1) == 10);

        SetOfStacks.push(-1);

        assert (SetOfStacks.size() == 16);
        assert (SetOfStacks.stackCount() == 2);
        assert (SetOfStacks.size(0) == 6);
        assert (SetOfStacks.size(1) == 10);

        SetOfStacks.push(-1);
        SetOfStacks.push(-1);
        SetOfStacks.push(-1);
        
        assert (SetOfStacks.size() == 19);
        assert (SetOfStacks.stackCount() == 2);
        assert (SetOfStacks.size(0) == 9);
        assert (SetOfStacks.size(1) == 10);

        SetOfStacks.push(-1);

        assert (SetOfStacks.size() == 20);
        assert (SetOfStacks.stackCount() == 2);
        assert (SetOfStacks.size(0) == 10);
        assert (SetOfStacks.size(1) == 10);

        for (int index = 0; index < ivq3.CAPACITY; index++) {
            SetOfStacks.push((index + 1) * -1);
        }

        assert (SetOfStacks.size() == 30);
        assert (SetOfStacks.stackCount() == 3);
        assert (SetOfStacks.size(0) == 10);
        assert (SetOfStacks.size(1) == 10);
        assert (SetOfStacks.size(2) == 10);
    }
}