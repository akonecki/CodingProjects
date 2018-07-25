import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Challenge8 {

    public static List<List<Integer>> solution(int maximum, List<List<Integer>> forward, List<List<Integer>> backward) {
        List<List<Integer>> result = new ArrayList<>();

        Collections.sort(backward, new CostOrder());

        int max = 0;
        for (int index = 0; index < forward.size(); index++) {
            List<Integer> route = forward.get(index);
            int startIndex = bsLeftMostEqualRightMostMaxIndex(maximum, max, route, backward);

            if (startIndex != -1) {
                // now need to see if it is equal to maximum
                List<Integer> bRoute = backward.get(startIndex);

                if (bRoute.get(1).intValue() + route.get(1).intValue() == maximum) {
                    // need to be working with a new local max.
                    if (max != maximum) {
                        result = new ArrayList<>();
                    } 
                    
                    max = maximum;

                    // will need to go from left to right
                    for (int subIndex = startIndex; subIndex < backward.size(); subIndex++) {
                        if (backward.get(subIndex).get(1).intValue() + route.get(1).intValue() == maximum) {
                            List<Integer> subResult = new ArrayList<Integer>();
                            subResult.add(route.get(0).intValue());
                            subResult.add(backward.get(subIndex).get(0).intValue());
                            result.add(subResult);
                        }
                        else {
                            break;
                        }
                    }
                }
                else if (bRoute.get(1).intValue() + route.get(1).intValue() >= max) {
                    // greater than current max which means will need new local max and new result list.
                    if (bRoute.get(1).intValue() + route.get(1).intValue() > max) {
                        result = new ArrayList<>();
                    }

                    max = bRoute.get(1).intValue() + route.get(1).intValue();

                    // is at the right most value but not equal to maximum, need to go left for value equal to the max.
                    for (int subIndex = startIndex; subIndex >= 0; subIndex--) {
                        if (backward.get(subIndex).get(1).intValue() + route.get(1).intValue() == max) {
                            List<Integer> subResult = new ArrayList<Integer>();
                            subResult.add(route.get(0).intValue());
                            subResult.add(backward.get(subIndex).get(0).intValue());
                            result.add(subResult);
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    private static int bsLeftMostEqualRightMostMaxIndex(int maximum, int max, List<Integer> forward, List<List<Integer>> backwards) {
        int leftIndex = 0, rightIndex = backwards.size() - 1;
        int routeCost = forward.get(1).intValue();

        if (routeCost > maximum) {
            return -1;
        }

        while (leftIndex <= rightIndex) {
            int index = (rightIndex - leftIndex) / 2 + leftIndex;
            int cost = backwards.get(index).get(1).intValue();

            if (cost + routeCost > maximum) {
                // need to go left
                rightIndex = index - 1;
            }
            else if (cost + routeCost < max) {
                // need to go right
                leftIndex = index + 1;
            }
            else if (cost + routeCost == maximum) {
                // go left if the next left is still equal to maximum
                if (index - 1 >= 0 && backwards.get(index - 1).get(1).intValue() + routeCost == maximum) {
                    // go left since there is still a value to the left that is equal to the maximum.
                    // want to return index of the left most index that is equal to maximum
                    rightIndex = index - 1;
                }
                else {
                    // this is the left most index that is equal to maximum.
                    return index;
                }
            }
            else if (cost + routeCost > max) {
                // go right if the next right is still greater than max but less than maximum
                if (index + 1 < backwards.size() && backwards.get(index + 1).get(1).intValue() + routeCost <= maximum) {
                    leftIndex = index + 1;
                }
                else {
                    // will return the right most index that is less than max
                    return index;
                }
            }
        }

        return -1;
    }

    private static class CostOrder implements Comparator <List<Integer>> {
        public int compare(List<Integer> route1, List<Integer> route2) {
            int r1Cost = route1.get(1).intValue();
            int r2Cost = route2.get(1).intValue();
            int r1Label = route1.get(0).intValue();
            int r2Label = route2.get(0).intValue();

            if (r1Cost < r2Cost) {
                return -1;
            }
            else if (r1Cost > r2Cost) {
                return 1;
            }
            else {
                // equal
                if (r1Label < r2Label) {
                    return -1;
                }
                else if (r1Label > r2Label) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
    }


    public static void main(String [] args) {
        List<List<Integer>> fList = new ArrayList<>();
        List<List<Integer>> bList = new ArrayList<>();
        
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Integer> list3 = new ArrayList<Integer>();
        List<Integer> list4 = new ArrayList<Integer>();
        List<Integer> list5 = new ArrayList<Integer>();
        List<Integer> list6 = new ArrayList<Integer>();

        list1.add(1);
        list1.add(8);
        list2.add(2);
        list2.add(15);
        list3.add(3);
        list3.add(9);
        list4.add(1);
        list4.add(8);
        list5.add(2);
        list5.add(11);
        list6.add(3);
        list6.add(12);

        fList.add(list1);
        fList.add(list2);
        fList.add(list3);
        bList.add(list4);
        bList.add(list5);
        bList.add(list6);

        for (List<Integer> lists : solution(20, fList, bList)) {
            for (int num : lists) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }
}