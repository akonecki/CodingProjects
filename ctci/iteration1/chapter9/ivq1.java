import java.util.ArrayList;

public class ivq1 {

    // [F]unctional Implementation
    // Base case definition
    /*
        N = 1
        1->end
    */
    /*
        N = 2
        1->end
        1->2->end
    */
    /*
        N = 3
        1->2->3->end
        1->2->end
        1->3->end
        1->end
    */
    /*
        N = 4
        1->2->end
        1->3->end
        1->4->end
        1->2->3->end
        1->2->4->end
        1->3->4->end
        1->2->3->4->end
    */
    /*
        N = 5
        1->3->end
        1->4->end
        1->2->3->end
        1->2->4->end
        1->2->5->end
        1->3->4->end
        1->3->5->end
        1->4->5->end
        1->2->3->4->end
        1->2->3->5->end
        1->2->4->5->end
        1->3->4->5->end
        1->2->3->4->5->end
    */
    public static int getNumberOfWays(int N) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        // series that starts with step one and step N
        getNumberOfWays(N, result, new ArrayList<Integer>());
        // Series that starts with Step one and step N - 1
        getNumberOfWays(N - 1, result, new ArrayList<Integer>());
        // Series that starts with step one and step N - 2
        getNumberOfWays(N - 2, result, new ArrayList<Integer>());

        for (ArrayList<Integer> list : result) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }

        return result.size();
    }

    private static int getNumberOfWays(int N, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list) {
        int sum = 0;
        
        if (N <= 0) {
            result.add(list);
            return 0;
        }
        else if (N == 1) {
            list.add(1);
            result.add(list);
            return 1;
        }

        list.add(N);

        if (N - 3 > 0) {
            sum = getNumberOfWays(N - 3, result, new ArrayList(list));
        }
        if (N - 2 > 0) {
            sum += getNumberOfWays(N - 2, result, new ArrayList(list));
        }
        if (N - 1 >= 0) {
            sum += getNumberOfWays(N - 1, result, new ArrayList(list));
        }

        return sum;
    }

    // [A]nalysis
    // 1. Does the problem contain optimal substructure?
    // Yes each recursive iteration is self contained.
    // 2. Does the problem contain re-occurring states?
    // Yes from the above individual callout of possible state configurations 
    // it is seen that there are multiple times that the same lower values are
    // accessed.

    // Run Time Analysis
    // 1. Branching Factor is at most 3
    // 2. Height of the tree is N due to the worse case of single increments 
    // down to one.

    // Overall run-time complexity that is expected with this implementation is
    // therefore O(3^N) in the worse case.

    // Memory Analysis
    // Due to the stack traversal (ignoring the population of the sets) is bound
    // by N. Therefore is O(N).

    public static void main(String [] args) {
        System.out.println(getNumberOfWays(5));
        //assert (getNumberOfWays(3) == 4);
        //assert (getNumberOfWays(4) == 7);
        //assert (getNumberOfWays(5) == 14);
    }
}