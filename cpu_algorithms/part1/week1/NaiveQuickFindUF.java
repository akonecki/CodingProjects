public class NaiveQuickFindUF {
    private int[] mN;

    // Each integer shall be first initialized to their respective index.
    // Index 0 has value 0, 1 has 1 etc.
    public NaiveQuickFindUF(int N) {
        mN = new int [N];
        for (int index = 0; index < mN.length; index++) {
            mN[index] = index;
        }
    }

    void union(int p, int q) {
        this.checkRange(p);
        this.checkRange(q);

        // Union occurs that if p and q are not equal then one must be converted to the other.
        // The problem is that any number of additional entries in the array could also be part
        // of the connected component of q, thus union is O(N) is this naive implementation.
        if (mN[p] != mN[q]) {
            int valueOfQ = mN[q];
            for (int index = 0; index < mN.length; index++) {
                if (mN[index] == valueOfQ) {
                    mN[index] = mN[p];
                }
            }
        }
    }

    boolean isConnected(int p, int q) {
        this.checkRange(p);
        this.checkRange(q);

        return mN[p] == mN[q];
    }

    void checkRange(int value) {
        if (value > mN.length - 1 || value < 0) {
            throw new UnsupportedOperationException("p or q is greater than size of array.");
        }
    }

    void printArray() {
        for (int index = 0; index < mN.length; index++) {
            System.out.println("Index " 
                + Integer.toString(index) 
                + " has value " 
                + Integer.toString(mN[index]));
        }
    }

    void printIsConnected(int p, int q) {
        boolean value = this.isConnected(p, q);

        System.out.println("Is p " 
            + Integer.toString(p) 
            + " connected to q " 
            + Integer.toString(q)
            + " " 
            + Boolean.toString(value));
    }

    public static void main(String[] args) {
        NaiveQuickFindUF myUF = new NaiveQuickFindUF(10);
        myUF.printArray();
        myUF.printIsConnected(0, 9);
        myUF.union(0, 9);
        myUF.printIsConnected(0, 9);
        myUF.printArray();
        return;
    }    
}

