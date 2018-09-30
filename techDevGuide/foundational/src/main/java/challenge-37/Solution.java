import java.util.HashSet;

public class Solution {
    public static int numberOfIslands(int [][] map) {
        int [] dp = new int [map[0].length];
        HashSet<Integer> uniqueIslandIds = new HashSet<Integer>();
        int idCount = 0;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] != 0) {
                    int left = 0, up = 0, min = 0;

                    if (row - 1 >= 0 && dp[col] != 0) {
                        up = dp[col];
                    }

                    if (col - 1 >= 0 && dp[col - 1] != 0) {
                        left = dp[col - 1];
                    }

                    if (left != 0 && up != 0) {
                        if (left != up) {
                            uniqueIslandIds.remove(Math.max(left, up));
                        }
                        // merge to min value of the two
                        dp[col] = Math.min(left, up);
                    }
                    else if (left != 0) {
                        dp[col] = left;
                    }
                    else if (up != 0) {
                        dp[col] = up;
                    }
                    else {
                        // new
                        idCount++;
                        dp[col] = idCount;
                        uniqueIslandIds.add(idCount);
                    }
                }
                else {
                    dp[col] = 0;
                }
                System.out.print(dp[col] + " ");
            }
            System.out.println("");
        }
        System.out.println(uniqueIslandIds.size());
        return uniqueIslandIds.size();
    }

    public static void main(String [] args) {
        assert (numberOfIslands(new int [][] {
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        }) == 1);
        assert (numberOfIslands(new int [][] {
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0}
        }) == 5);
        assert (numberOfIslands(new int [][] {
            {1, 0, 1, 1, 1},
            {1, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 1, 1, 0}
        }) == 4);
        assert (numberOfIslands(new int [][] {
            {1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1}
        }) == 1);
    }
}