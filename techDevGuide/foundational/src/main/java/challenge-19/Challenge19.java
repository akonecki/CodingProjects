import java.util.Stack;
import java.util.LinkedList;
import java.util.Random;
import java.util.Arrays;

public class Challenge19 {

    // given a sort array determine the min value of arr[i] == i
    // return -1 if does not exist

    public static int search(int [] sortedNums) {
        int linearResults = searchLinear(sortedNums);
        //int serialResults = searchSerial(sortedNums);
        int recursiveResults = searchRecursive(sortedNums, 0, sortedNums.length - 1);

        System.out.println("Result is " + linearResults);
        assert (linearResults == recursiveResults);// && linearResults == recursiveResults);
        return linearResults;//search(sortedNums, 0, sortedNums.length - 1);    
    }

    private static int searchLinear(int [] sortedNums) {
        for (int index = 0; index < sortedNums.length; index++) {
            if (sortedNums[index] == index) {
                return index;
            }
        }

        return -1;
    }

    private static int searchSerial(int [] sortedNums) {
        Stack<Integer []> lowHighToCheck = new Stack<Integer []>();    
        lowHighToCheck.push(new Integer [] {0, sortedNums.length - 1});

        int min = sortedNums.length;

        while (!lowHighToCheck.isEmpty()) {
            Integer [] lowHighIndicies = lowHighToCheck.pop();
            int low = lowHighIndicies[0].intValue();
            int high = lowHighIndicies[1].intValue();

            if (low <= high && low >= 0 && high < sortedNums.length) {
                int index = (high - low) / 2 + low;

                if (sortedNums[index] > index) {
                    // index is greater will still need to go both left and right
                    // within the sorted array.
                    
                    // push on the right first if possible
                    if (sortedNums[index] < sortedNums.length) {
                        lowHighToCheck.push(new Integer [] {sortedNums[index], sortedNums.length - 1});
                    }
                    // push on the left second, range can not be deduced.
                    lowHighToCheck.push(new Integer [] {low, index - 1});
                }
                else if (sortedNums[index] < index) {
                    // push on the right, range can not be deduced
                    lowHighToCheck.push(new Integer [] {index + 1, high});
                    // push on the left if possible
                    if (sortedNums[index] >= 0) {
                        lowHighToCheck.push(new Integer [] {0, sortedNums[index]});
                    }
                }
                else {
                    // equal
                    if (index < min) {
                        min = index;
                    }

                    // Still need to check left if possible
                    lowHighToCheck.push(new Integer [] {low, index - 1});
                }
            }
        }

        if (min == sortedNums.length) {
            return -1;
        }
        else {
            return min;
        }
    }

    private static int searchRecursive(int [] sortedNums, int leftIndex, int rightIndex) {
        if (leftIndex <= rightIndex) {
            int index = (rightIndex - leftIndex) / 2 + leftIndex;

            if (sortedNums[index] > index) {
                // index is greater will have to go left and right
                // go left first since it is favored for the problem
                int leftValue = searchRecursive(sortedNums, leftIndex, index - 1);
                // go right if left has no result
                if (leftValue == -1) {
                    if (sortedNums[index] >= sortedNums.length) {
                        return -1;
                    }
                    else {
                        return searchRecursive(sortedNums, index + 1, rightIndex);
                    }
                }
                else {
                    return leftValue;
                }
            } 
            else if (sortedNums[index] < index) {
                // explore left index if possible
                int leftValue = searchRecursive(sortedNums, 0, sortedNums[index]);

                if (leftValue == -1) {
                    return searchRecursive(sortedNums, index + 1, rightIndex);
                }
                else {
                    return leftValue;
                }
            }
            else {
                // equal case

                // still need to check if it is the left most find
                int leftValue = searchRecursive(sortedNums, 0, index - 1);

                if (leftValue == -1) {
                    return index;
                }
                else {
                    return leftValue;
                }
            }
        }
        else {
            return -1;
        }
    }

    public static void main(String [] args) {
        assert (search(new int [] {1,2,3,4}) == -1);
        assert (search(new int [] {0,1,2,3,4}) == 0);
        assert (search(new int [] {-1,1,2,3,4}) == 1);
        assert (search(new int [] {-1,0,2,3,4}) == 2);
        assert (search(new int [] {-1,0,1,3,4}) == 3);
        assert (search(new int [] {-7,-1,2,2,2}) == 2);
       
        int [] randomSet = new int [100];

        for (int index = 0; index < randomSet.length; index++) {
            randomSet[index] = (new Random()).nextInt(randomSet.length * 2) + 1;
            System.out.print(randomSet[index] + " ");
        }
        System.out.println("");
        Arrays.sort(randomSet);
        search(randomSet);
    }
}