public class Lesson {
    public static int f4(int n, int m) {
        if (m == 0) return 0;
        if (m == 1) return 1;
        if (n == 1) return m;
            
        int max = Integer.MAX_VALUE;
        for (int i = 1; i <= m; i++) {
            int case1 = f4(n - 1, i - 1);
            int case2 = f4(n, m - i);
            max = Math.min(max, Math.max(case1, case2));                   
        }
        return max + 1;
    }
    
    public static void main(String [] args) {
        assert (f4(2,6) == 3);
    }
}