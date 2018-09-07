import java.util.*;

public class Solution {

    public static int volume(int [] heights) {
        assert (volumeScanning(heights) == volumeLinear(heights));
        return volumeLinear(heights);
    }

    private static int volumeScanning(int [] heights) {
        int [] leftMax = new int [heights.length];
        int [] rightMax = new int [heights.length];
        int volume = 0;

        int max = heights[0];
        for (int index = 0; index < heights.length; index++) {
            if (heights[index] > max) {
                max = heights[index];
            }
            rightMax[index] = max;
        }
        max = heights[heights.length - 1];
        for (int index = heights.length - 1; index >= 0; index--) {
            if (heights[index] > max) {
                max = heights[index];
            }
            leftMax[index] = max;
        }

        for (int index = 0; index < heights.length; index++) {
            volume += Math.min(leftMax[index], rightMax[index]) - heights[index];
        }

        return volume;
    }

    private static int volumeLinear(int [] heights) {
        int volume = 0;
        Stack<Integer> lowerHeights = new Stack<Integer>();
        Stack<Integer> indices = new Stack<Integer>();

        for (int pivotIndex = 0; pivotIndex < heights.length; ) {
            int max = heights[pivotIndex];
            int index = pivotIndex + 1;
            
            for (; index < heights.length; index++) {
                int height = heights[index];
                
                if (height < max) {
                    if (lowerHeights.isEmpty()) {
                        lowerHeights.push(height);
                        indices.push(index);
                    }
                    else {
                        int currentIndex = index;
                        while (!lowerHeights.isEmpty() && lowerHeights.peek().intValue() < height) {
                            int lowerHeight = lowerHeights.pop();
                            int lowerIndex = indices.pop();

                            volume += (currentIndex - lowerIndex) * (height - lowerHeight);
                            currentIndex = lowerIndex;
                        }
                        lowerHeights.push(height);
                        indices.push(currentIndex);
                    }
                }
                else {
                    int currentIndex = index;
                    while (!lowerHeights.isEmpty()) {
                        int lowerHeight = lowerHeights.pop();
                        int lowerIndex = indices.pop();

                        volume += (currentIndex - lowerIndex) * (Math.min(max, height) - lowerHeight);
                        currentIndex = lowerIndex;
                    }
                    break;
                }
            }
            pivotIndex = index;
        }
        System.out.println(volume);
        return volume;
    }

    public static void main(String [] args) {
        assert (volume(new int [] {1,3,2,4,1,3,1,4,5,2,2,1,4,2,2}) == 15);
    }
}