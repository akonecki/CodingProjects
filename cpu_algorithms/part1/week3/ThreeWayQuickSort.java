public class ThreeWayQuickSort {
    
    private static void shuffle(Comparable [] data) {
        // Perform the definition of the shuffle to obtain the pivot.  In the 
        // ideal case this will result in roughly the median of the data set
        // that needs to be sorted.

        // For the weak implementation this will be ignored for the time being.
        return;
    }

    private static void swap(Comparable [] data, int i, int j) {
        Comparable object = data[i];
        data[i] = data[j];
        data[j] = object;
    }

    private static boolean isPartitioned(Comparable [] data, int lt, int gt) {
        boolean flag = true;
        
        // Check all values less than the pivot index.
        for (int index = 0; index < lt; index++) {
            flag = flag & (data[index].compareTo(data[lt]) < 0);
        }

        // Check all values equal to the pivot.
        for (int index = lt; index <= gt; index++) {
            flag = flag & (data[index].compareTo(data[lt]) == 0);
        }

        // Check all values greater than the pivot index.
        for (int index = gt + 1; index < data.length; index++) {
            flag = flag & (data[index].compareTo(data[lt]) > 0);
        }

        return flag;
    }

    // Implementation of the sort is to address handling of duplicates to 
    // prevent O(N^2) operational time.
    private static void sort(Comparable [] data, int startIndex, int endIndex) {
        int lt = startIndex;
        int i = startIndex;
        int gt = endIndex;

        if (lt >= gt) {
            return;
        }

        while (i <= gt) {
            if (data[i].compareTo(data[lt]) < 0) {
                // Case 1 : data[i] < data[lt], swap(data[lt], data[i]), increment i, lt
                ThreeWayQuickSort.swap(data, lt, i);
                i++;
                lt++;
            }
            else if (data[i].compareTo(data[lt]) > 0) {
                // Case 2 : data[i] > data[lt], swap(data[i], data[gt]), decrement j.  
                ThreeWayQuickSort.swap(data, i, gt);
                gt--;
            }
            else {
                // Case 3 : data[i] == data[lt], increment i
                i++;
            }
        }

        try {
            assert(ThreeWayQuickSort.isPartitioned(data, lt, gt));
        }
        catch (java.lang.AssertionError e) {
            // System.out.println("Pivot value is " + data[gt].toString() + " i " + Integer.toString(i) + " gt " + Integer.toString(gt));
            assert(false);
        }

        sort(data, startIndex, lt - 1);
        sort(data, gt + 1, endIndex);
    }

    public static void sort(Comparable [] data) {
        // Ensure not operating on null data.
        if (data == null) {
            return;
        }

        // Perform the shuffle on the data.
        ThreeWayQuickSort.shuffle(data);

        // Now sort.
        ThreeWayQuickSort.sort(data, 0, data.length - 1);
    }

    public static void main(String [] args) {
        Integer [] data = {9,8,7,6,9,5,4,9,3,2,1,9,1,1,1,1,2,3,4,5,13,3,4,5,1,3,5,6,7,8,3};
        ThreeWayQuickSort.sort(data);

        for(Integer integer : data) {
            System.out.print(Integer.toString(integer) + " ");
        }
        System.out.println("");
    }
}