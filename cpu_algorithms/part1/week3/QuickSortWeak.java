public class QuickSortWeak {
    
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

    private static int partition(Comparable [] data, int startIndex, int endIndex) {
        // Assumption that i is the index that points to the pivot element.
        int pivot = startIndex;
        int i = startIndex + 1;
        int j = endIndex;

        while (i <= j) {
            // System.out.println("I " + Integer.toString(i) + " J " + Integer.toString(j));
            if (data[i].compareTo(data[pivot]) <= 0) {
                // Case 1 : data[i] < data[pivot], increment i
                i++;
            }
            else if (data[i].compareTo(data[pivot]) > 0) {
                // Case 2 : data[i] >= data[pivot], swap(data[i], data[j]), decrement j.  
                QuickSortWeak.swap(data, i, j);
                j--;
            }
        }

        QuickSortWeak.swap(data, pivot, j);
        try {
            assert(QuickSortWeak.isPartitioned(data, j));
        }
        catch (java.lang.AssertionError e) {
            System.out.println("Pivot value is " + data[j].toString() + " i " + Integer.toString(i) + " j " + Integer.toString(j));
            QuickSortWeak.printData(data, 0, data.length - 1);
            assert(false);
        }

        return j;
    }

    private static void printData(Comparable [] data, int i, int j) {
        System.out.println("i " + Integer.toString(i) + " j " + Integer.toString(j));
        for (int index = i; index <= j; index++) {
            System.out.print(data[index].toString() + " ");
        }
        System.out.println("");
    }

    private static boolean isPartitioned(Comparable [] data, int pivotIndex) {
        boolean flag = true;
        
        // Check all values less than the pivot index.
        for (int index = 0; index < pivotIndex; index++) {
            flag = flag & (data[index].compareTo(data[pivotIndex]) <= 0);
        }

        // Check all values greater than the pivot index.
        for (int index = pivotIndex + 1; index < data.length; index++) {
            flag = flag & (data[index].compareTo(data[pivotIndex]) >= 0);
        }

        return flag;
    }

    // This weak version of quick sort does not handle duplicate data entries
    // O(NlogN) sorting time but instead in O(N^2).
    private static void sort(Comparable [] data, int i, int j) {
        int partitionIndex = 0;
        if (j <= i) {
            return;
        }
        
        // partition data
        partitionIndex = partition(data, i, j);
        // sort left
        sort(data, i, partitionIndex - 1);
        // sort right
        sort(data, partitionIndex + 1, j);
    }

    public static void sort(Comparable [] data) {
        // Ensure not operating on null data.
        if (data == null) {
            return;
        }

        // Perform the shuffle on the data.
        QuickSortWeak.shuffle(data);

        // Now sort.
        QuickSortWeak.sort(data, 0, data.length - 1);
    }

    public static void main(String [] args) {
        Integer [] data = {9,8,7,6,9,5,4,9,3,2,1,9,1,1,1,1,2,3,4,5,13,3,4,5,1,3,5,6,7,8,3};
        QuickSortWeak.sort(data);

        for(Integer integer : data) {
            System.out.print(Integer.toString(integer) + " ");
        }
        System.out.println("");
    }
}