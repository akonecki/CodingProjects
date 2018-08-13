import java.util.Collections;
import java.util.LinkedList;

public class ivq10 {

    // [F]uncational Implementation
    // 0 - width 
    // 1 - height
    // 2 - depth
    public static int maxHeight(int [][] boxes) {
        LinkedList<int []> boxList = new LinkedList<int []>();

        for (int [] box : boxes) {
            boxList.add(box);
        }

        int [] firstBox = boxList.remove();
        return maxHeight(boxList, 0, firstBox);
    }

    public static int maxHeight(LinkedList<int []> boxes, int height, int [] prevBox) {
        if (boxes.isEmpty()) {
            return height + prevBox[1];
        }
        else {
            // Remove the head of the boxes
            int [] box = boxes.remove();
            int max = 0;

            if (compareBoxes(box, prevBox) == 1) {
                // current prevBox is larger
                max = maxHeight(boxes, height + prevBox[1], box);
            }
            else if (compareBoxes(box, prevBox) == -1) {
                // box is larger than prevBox
                max = maxHeight(boxes, height + box[1], prevBox);
            }
            else {
                // no box is strictly larger
                max = Math.max(maxHeight(boxes, height, prevBox), maxHeight(boxes, height, box));
            }

            return max;
        }
    }

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
            {4, 5, 6}
        };

        assert (maxHeight(boxes) == 9);
    }
}