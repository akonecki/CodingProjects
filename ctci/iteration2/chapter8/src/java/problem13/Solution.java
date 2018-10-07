import java.util.Comparator;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            return this.height - box.height;    
        }
    }

    // [F]unctional
    private static int maxHeight(Box [] boxes, int boxIndex, Box lastBox) {
        if (boxIndex >= boxes.length) {
            return 0;
        }
        
        // not included
        int notIncludedHeight = maxHeight(boxes, boxIndex + 1, lastBox);
        int includedHeight = 0;
        // try to include
        if (lastBox == null || boxes[boxIndex].compareTo(lastBox) > 0) {
            includedHeight = maxHeight(boxes, boxIndex + 1, boxes[boxIndex]) + boxes[boxIndex].height;
        }

        return Math.max(notIncludedHeight, includedHeight);
    }

    private static class BoxOrder implements Comparator<Box> {
        public int compare(Box boxA, Box boxB) {
            return boxB.height - boxA.height;
        }
    }

    // [A]nalysis
    // 1. Does the problem have optimal substructure?
    // Yes
    // 2. Does the problem have reoccurring problems?
    // Yes it is possible.

    // Performance
    // Memory
    // Stack height based O(N)

    // Time
    // O(2^N)

    // [S]ubproblem & Memoization
    private static int maxHeight(Box [] boxes, int boxIndex, Box lastBox, int [] stackMap) {
        if (boxIndex >= boxes.length) {
            return 0;
        }
        
        // not included
        int notIncludedHeight = maxHeight(boxes, boxIndex + 1, lastBox, stackMap);
        int includedHieght = 0;
        // try to include
        if (lastBox == null || boxes[boxIndex].compareTo(lastBox) > 0) {
            if (stackMap[boxIndex] == 0) {
                stackMap[boxIndex] = maxHeight(boxes, boxIndex + 1, boxes[boxIndex], stackMap) + boxes[boxIndex].height;
            }

            includedHieght = stackMap[boxIndex];
        }

        return Math.max(notIncludedHeight, includedHieght);
    }

    public static int maxHeight(int [][] boxes) {
        Box [] sortedBoxes = new Box [boxes.length];

        int index = 0;
        for (int [] box : boxes) {
            sortedBoxes[index] = new Box(box[0], box[1], box[2]);
            index++;
        }

        //Arrays.sort(sortedBoxes, new BoxOrder());
        System.out.println(maxHeight(sortedBoxes, 0, null));
        System.out.println(maxHeight(sortedBoxes, 0, null, new int [sortedBoxes.length]));
        //assert (maxHeight(sortedBoxes, 0, null) == maxHeight(sortedBoxes, 0, null, new int [sortedBoxes.length]));

        return 0;
    }

    public static void main(String [] args) {
        final int numberOfCases = 100;
        Random random = new Random();

        for (int caseNumber = 0; caseNumber < numberOfCases; caseNumber++) {
            int boxCount = random.nextInt(100) + 1;
            int [][] boxes = new int [boxCount][3];

            for (int [] box : boxes) {
                box[0] = random.nextInt(20) + 1;
                box[1] = random.nextInt(20) + 1;
                box[2] = random.nextInt(20) + 1;
            }

            maxHeight(boxes);
        }
        
        System.out.println(maxHeight(new int [][] {
            {1,2,3},
            {2,3,4},
            {4,5,1},
            {5,1,2}
        }));   
    }
}