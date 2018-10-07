import java.util.Comparator;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static class Box {
        private int width;
        private int height;
        private int depth;

        public Box(int width, int height, int depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
        }

        public int compareTo(Box box) {
            if (this.width < box.width && this.height < box.height && this.depth < box.depth) {
                return 1;
            }
            else if (this.width > box.width && this.height > box.height && this.depth > box.depth) {
                return -1;
            }
            else {
                return 0;
            }    
        }
    }

    // [F]unctional
    private static int maxHeight(Box [] boxes, int boxIndex, int maxHeight, int currentHeight, Box lastBox) {
        if (boxIndex >= boxes.length) {
            if (currentHeight > maxHeight) {
                return currentHeight;
            }
            else {
                return maxHeight;
            }
        }
        
        // not included
        maxHeight = maxHeight(boxes, boxIndex + 1, maxHeight, currentHeight, lastBox);

        // try to include
        if (lastBox == null || boxes[boxIndex].compareTo(lastBox) > 0) {
            maxHeight = maxHeight(boxes, boxIndex + 1, maxHeight, currentHeight + boxes[boxIndex].height, boxes[boxIndex]);
        }

        return maxHeight;
    }

    private static class BoxOrder implements Comparator<Box> {
        public int compare(Box boxA, Box boxB) {
            return boxA.compareTo(boxB);
        }
    }

    public static int maxHeight(int [][] boxes) {
        Box [] sortedBoxes = new Box [boxes.length];

        int index = 0;
        for (int [] box : boxes) {
            sortedBoxes[index] = new Box(box[0], box[1], box[2]);
            index++;
        }

        Arrays.sort(sortedBoxes, new BoxOrder());
        return maxHeight(sortedBoxes, 0, 0, 0, null);
    }

    public static void main(String [] args) {
        System.out.println(maxHeight(new int [][] {
            {1,2,3},
            {2,3,4},
            {4,5,1},
            {5,1,2}
        }));   
    }
}