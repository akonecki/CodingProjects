public class WeightedQuickUnionPathCompression {
    private int[] mN;
    private int[] weights;

    // Each integer shall be first initialized to their respective index.
    // Index 0 has value 0, 1 has 1 etc.
    public WeightedQuickUnionPathCompression(int N) {
        mN = new int [N];
        weights = new int[N];
        for (int index = 0; index < mN.length; index++) {
            mN[index] = index;
        }
        for (int index = 0; index < weights.length; index++) {
            weights[index] = 1;
        }
    }

    void union(int p, int q) {
        this.checkRange(p);
        this.checkRange(q);

        // Union occurs that convertes q to the p.
        // O(N) due to the fact that a tree can be unbalanced.
        int rootOfP = rootOfNode(p);
        int rootOfQ = rootOfNode(q);
        
        // mN[rootOfQ] = mN[rootOfP];
        if (weights[rootOfP] >= weights[rootOfQ]) {
            weights[rootOfP] += weights[rootOfQ];
            mN[rootOfQ] = mN[rootOfP];
        }
        else {
            weights[rootOfQ] += weights[rootOfP];
            mN[rootOfP] = mN[rootOfQ];
        }

    }

    int rootOfNode(int node) {
        // Performance is tied directly to the height of the tree.
        // O(H) where H is the height.
        if (mN[node] != node) {
            mN[node] = mN[mN[node]];
            weights[mN[node]] -= weights[node];
            return rootOfNode(mN[node]);
        }
        return node;
    }

    boolean isConnected(int p, int q) {
        this.checkRange(p);
        this.checkRange(q);

        return rootOfNode(p) == rootOfNode(q);
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
                + Integer.toString(mN[index]) 
                + " weight of " 
                + Integer.toString(weights[index]));
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
        WeightedQuickUnionPathCompression myUF = new WeightedQuickUnionPathCompression(12);
        myUF.union(9, 11);
        myUF.union(9, 10);
        myUF.union(4, 3);
        myUF.union(3, 8);
        myUF.union(6, 5);
        myUF.union(9, 4);
        myUF.union(2, 1);
        myUF.union(5, 0);
        myUF.union(7, 2);
        myUF.union(6, 1);
        myUF.union(7, 3);

        myUF.printArray();
        return;
    }
}