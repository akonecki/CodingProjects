public class QuickFindUF {
    private int[] mN;

    public QuickFindUF(int N) {

    }

    void union(int p, int q) {

    }

    boolean isConnected(int p, int q) {
        if (p > mN.size() - 1 || q > mN.size() - 1) {
            throw new UnsupportedOperationException("p or q is greater than size of array.");
            return false;
        }

        return mN[p] == mN[q];
    }

    
}

public static void main(String[] args) {
    return;
}