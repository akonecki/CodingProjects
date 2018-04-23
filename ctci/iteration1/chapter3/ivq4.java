public class ivq4 {
    private static final int DEFAULT_SIZE = 3;
    private static final int TOWER_COUNT = 3;
    private Stack [] stacks = new Stack[ivq4.TOWER_COUNT];

    private static class Stack {
        int [] mValues = null;
        int mSize = 0;

        public Stack() {
            this.mValues = new int [ivq4.DEFAULT_SIZE];
        }

        public boolean push(int value) {
            if (this.mSize >= ivq4.DEFAULT_SIZE) {
                throw new IllegalArgumentException("Stack is full");
            }

            if (this.mSize > 0 && this.mValues[this.mSize - 1] <= value) {
                // Can not push a larger value on a smaller value.
                return false;
            }

            this.mValues[this.mSize++] = value;
            return true;
        }

        public int pop() {
            if (this.mSize <= 0) {
                throw new IllegalArgumentException("Stack is empty");
            }

            return this.mValues[--this.mSize];
        }

        public int size() {
            return this.mSize;
        }

        public int capacity() {
            return this.mValues.length;
        }
    }

    public ivq4() {
        for (int index = 0; index < ivq4.TOWER_COUNT; index++) {
            this.stacks[index] = new Stack();
        }

        // Populate the first.
        for (int index = 0; index < ivq4.DEFAULT_SIZE; index++) {
            this.stacks[0].push(ivq4.TOWER_COUNT - index);
        }
    }

    public void moveDisks(int n, int towerOrigin, int towerDestination, int towerBuffer) {
        // Base Case
        if (n <= 0) {
            return;
        }

        // Move top n-1 disks from origin to buffer, using destination as a buffer.
        this.moveDisks(n - 1, towerOrigin, towerBuffer, towerDestination);

        // Move top from origin to destination
        int value = this.stacks[towerOrigin].pop();
        this.stacks[towerDestination].push(value);
        
        // Move top n - 1 disks from buffer to destination using origin as buffer.
        this.moveDisks(n - 1, towerBuffer, towerDestination, towerOrigin);
    }

    public boolean isMoved() {
        for (int index = 0; index < ivq4.DEFAULT_SIZE; index++) {
            if (this.stacks[2].mValues[index] != (ivq4.TOWER_COUNT - index)) {
                return false;
            }
        }

        return true;
    }

    private void moveLeftToRight(int towerIndex) {
        if (towerIndex > 1) {
            return;
        }

        int value = this.stacks[towerIndex].pop();
        if (!this.stacks[towerIndex + 1].push(value)) {
            this.stacks[towerIndex].push(value);
        }
        this.moveLeftToRight(towerIndex + 1);
    }

    private void moveRightToLeft(int towerIndex) {
        if (towerIndex < 1) {
            return;
        }

        int value = this.stacks[towerIndex].pop();
        if (!this.stacks[towerIndex - 1].push(value)) {
            this.stacks[towerIndex].push(value);
        }
        this.moveLeftToRight(towerIndex - 1);
    }

    public void moveFromFirstToLast() {
        this.moveDisks(ivq4.DEFAULT_SIZE, 0, 1, 2);
        this.moveDisks(ivq4.DEFAULT_SIZE, 1, 2, 0);
        assert (this.isMoved());
    }

    public static void main(String [] args) {
        ivq4 towers = new ivq4();

        towers.moveFromFirstToLast();
        assert (towers.isMoved());
    }
}