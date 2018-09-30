import java.util.Stack;
import java.util.Random;

public class Solution {
    
    public static boolean waterVolume(int [] heights) {
        assert (waterVolumeScan(heights) == waterVolumeStack(heights));
        return true;
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
        Random random = new Random();
        final int count = 1000;
        final int maxHeight = 10000;

        for (int i = 0; i < 1000; i++) {
            int [] heights = new int [random.nextInt(999) + 1];
            System.out.println("New Heights:");
            for (int index = 0; index < heights.length; index++) {
                heights[index] = random.nextInt(maxHeight);
                System.out.print(heights[index] + " ");
                System.out.println("");
            }
            System.out.println("");
            waterVolume(heights);
        }
    }
}