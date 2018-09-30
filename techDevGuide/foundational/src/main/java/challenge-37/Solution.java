import java.util.HashSet;
import java.util.Random;

public class Solution {
    public static boolean numberOfIslands(int [][] map) {
        int serialResult = numberOfIslandsSerial(map);
        int recursiveResult = numberOfIslandsRecursive(map);
        assert (serialResult == recursiveResult);
        return true;
    }
    
    private static int numberOfIslandsRecursive(int [][] map) {
        int count = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] != -1 & map[row][col] != 0) {
                    count++;
                    numberOfIslands(map, row, col);
                }
            }
        }
        System.out.println("Recursive Size " + count);
        return count;
    }

    private static void numberOfIslands(int [][] map, int row, int col) {
        if (row >= map.length || row < 0 || col < 0 || col >= map[row].length || map[row][col] == -1 || map[row][col] == 0) {
            return;
        }

        map[row][col] = -1;
        numberOfIslands(map, row, col - 1);
        numberOfIslands(map, row, col + 1);
        numberOfIslands(map, row - 1, col);
        numberOfIslands(map, row + 1, col);
    }

    private static int numberOfIslandsSerial(int [][] map) {
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
        System.out.println("Serial Size " + uniqueIslandIds.size());
        return uniqueIslandIds.size();
    }

    public static void main(String [] args) {
        Random random = new Random();
        final int count = 1000;

        for (int i = 0; i < 1000; i++) {
            int rows = random.nextInt(9) + 1;
            int cols = random.nextInt(9) + 1;

            int [][] map = new int [rows][cols];
            System.out.println("New Map:");
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    map[row][col] = random.nextBoolean() ? 1 : 0;
                    System.out.print(map[row][col] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
            numberOfIslands(map);
        }
        
    }
}