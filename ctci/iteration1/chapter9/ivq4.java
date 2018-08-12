import java.util.ArrayList;

public class ivq4 {

    // [F]unctional Implementation
    public static ArrayList<ArrayList<Integer>> getSubSets(int [] set) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        getSubSets(set, result, new ArrayList<Integer>(), 0);
        
        return result;
    }

    public static void getSubSets(int [] set, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int index) {
        if (index >= set.length) {
            result.add(list);
        }
        else {

            // exclude
            getSubSets(set, result, new ArrayList<Integer>(list), index + 1);
            // include
            list.add(set[index]);
            getSubSets(set, result, list, index + 1);
        }
    }

    public static void main(String [] args) {
        int [] set = {1,2,3,4,5,6,7};

        assert (getSubSets(set).size() == 1 << (set.length));

        for (ArrayList<Integer> list : getSubSets(set)) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}