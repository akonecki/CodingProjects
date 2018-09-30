import java.util.Stack;
import java.util.Random;

public class Solution {
    
    public static int waterVolume(int [] heights) {

    }

    private static int waterVolumeScan(int [] heights) {
        int [] leftToRight = new int [heights.length];
        int [] rightToLeft = new int [heights.length];
        int volume = 0;

        // build up max from left to right.
        leftToRight[0] = heights[0];
        for (int index = 1; index < heights.length; index++) {
            leftToRight[index] = Math.max(leftToRight[index - 1], heights[index]);
        }

        // build up max from right to left
        rightToLeft[heights.length - 1] = heights[heights.length - 1];
        for (int index = heights.length - 2; index >= 0; index--) {
            rightToLeft[index] = Math.max(rightToLeft[index + 1], heights[index]);
        }

        // compute the total volume.
        for (int index = 0; index < heights.length; index++) {
            volume += Math.abs(Math.min(rightToLeft[index], leftToRight[index]) - heights[index]);
        }

        return volume;
    }

    private static int waterVolumeStack(int [] heights) {
        Stack<Integer> heightStack = new Stack<Integer>();
        Stack<Integer> indexStack = new Stack<Integer>();
        int volume = 0;

        for (int index = 0; index < heights.length; index++) {
            int height = heights[index];

            if (heightStack.isEmpty() || heightStack.peek().intValue() >= height) {
                // just push onto the stack
                heightStack.push(height);
                indexStack.push(index);
            }
            else {
                int prevHeight = height;
                int prevIndex = index;

                while (!heightStack.isEmpty() && heightStack.peek().intValue() < height) {
                    prevHeight = heightStack.pop().intValue();
                    prevIndex = indexStack.pop().intValue();

                    if (heightStack.isEmpty()) {
                        break;
                    }

                    volume += Math.abs(prevHeight - Math.min(height, heightStack.peek().intValue())) * (index - prevIndex);
                }

                heightStack.push(height);
                indexStack.push(prevIndex);
            }
        }

        return volume;
    }

    public static void main(String [] args) {
        /*
        Random random = new Random();
        final int count = 1000;

        for (int i = 0; i < 1000; i++) {
            int rows = random.nextInt(24) + 1;
            int cols = random.nextInt(24) + 1;

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
        */
    }
}