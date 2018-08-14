import java.util.Collections;
import java.util.LinkedList;

public class ivq10 {

    // [F]uncational Implementation
    // 0 - width 
    // 1 - height
    // 2 - depth
    public static int maxHeight(int [][] boxes) {
        return maxHeight(boxes, null);
    }

    private static int maxHeight(int [][] boxes, int [] prevBox) {
        int max = 0;
        
        int index = 0;
        for (int [] box : boxes) {
            if (prevBox == null || (box != null && compareBoxes(box, prevBox) == 1)) {
                int [][] newBoxes = boxes.clone();
                newBoxes[index] = null;
                int height = maxHeight(newBoxes, box);
            
                if (height > max) {
                    max = height;
                }
            }
            index++;
        }

        if (prevBox != null) {
            return max + prevBox[1];
        }
        else {
            return max;
        }
    }

    /*
    public static int maxHeight(int [][] boxes) {
        LinkedList<int []> boxList = new LinkedList<int []>();

        for (int [] box : boxes) {
            boxList.add(box);
        }

        int [] firstBox = boxList.remove();
        return maxHeight(boxList, firstBox);
    }

    public static int maxHeight(LinkedList<int []> boxes, int [] prevBox) {
        if (boxes.isEmpty()) {
            return 0;
        }
        else {
            // Remove the head of the boxes
            int [] box = boxes.remove();
            int max = 0;

            if (compareBoxes(box, prevBox) == 1) {
                // current prevBox is larger
                max = prevBox[1] + box[1] + maxHeight(boxes, box);
            }
            else if (compareBoxes(box, prevBox) == -1) {
                // box is larger than prevBox
                max = prevBox[1] + box[1] + maxHeight(boxes, prevBox);
            }
            else {
                // no box is strictly larger
                max = Math.max(maxHeight(boxes, prevBox), maxHeight(boxes, box));
            }

            return max;
        }
    }
    */

    public static int compareBoxes(int [] box1, int [] box2) {
        if (box1[0] < box2[0] &&
            box1[1] < box2[1] &&
            box1[2] < box2[2]) 
        {
            return 1;
        }
        else if (box1[0] > box2[0] &&
            box1[1] > box2[1] &&
            box1[2] > box2[2]) 
        {
            return -1;
        }
        else {
            return 0;
        }
    }

    public static void main(String [] args) {
        int [][] boxes = {
            {3, 4, 5},
            {4, 4, 4},
            {4, 5, 6},
            {9, 6, 7},
            {1, 2, 3},
            {2, 5, 3}
        };

        assert (maxHeight(boxes) == 17);
    }
}