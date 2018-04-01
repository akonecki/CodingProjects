public class QuickSelectionWeak {

    private static void swap(Comparable [] data, int i, int j) {
        Comparable object = data[i];
        data[i] = data[j];
        data[j] = object;
    }

    // Weak implementation that does not properly handle duplicate data entries.
    private static int partition(Comparable [] data, int startIndex, int endIndex) {
        int pivot = startIndex;
        int i = startIndex + 1;
        int j = endIndex;

        while (i <= j) {
            if (data[i].compareTo(data[pivot]) <= 0) {
                // Case 1 : data[i] < data[pivot], increment i
                i++;
            }
            else if (data[i].compareTo(data[pivot]) > 0) {
                // Case 2 : data[i] >= data[pivot], swap(data[i], data[j]), decrement j.  
                QuickSelectionWeak.swap(data, i, j);
                j--;
            }
        }

        try {
            QuickSelectionWeak.swap(data, pivot, j);
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("i " + Integer.toString(i) + " j " + Integer.toString(j));
            throw new java.lang.ArrayIndexOutOfBoundsException("Out of bounds");
        }

        try {
            assert(QuickSelectionWeak.isPartitioned(data, j));
        }
        catch (java.lang.AssertionError e) {
            System.out.println("Pivot value is " + data[j].toString() + " i " + Integer.toString(i) + " j " + Integer.toString(j));
            assert(false);
        }

        return j;
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

    // N in this context is the index we are interested in obtaining.
    private static Object selectNthMember(Comparable [] data, int n, int i, int j) {
        int partitionIndex = 0;
        if (i >= j && i == n) {
            return data[n];
        }
        else if (i >= j) {
            return null;
        }

        // Partition
        partitionIndex = QuickSelectionWeak.partition(data, i, j);

        if (partitionIndex == n) {
            return data[partitionIndex];
        }
        else if (partitionIndex > n) {
            // Operate only on the left half.
            return QuickSelectionWeak.selectNthMember(data, n, i, partitionIndex - 1);
        }
        else if (partitionIndex < n) {
            // Operate only on the right half.
            return QuickSelectionWeak.selectNthMember(data, n, partitionIndex + 1, j);
        }
        else {
            return null;
        }
    }

    public static Object selectNthMember(Comparable [] data, int n) {
        if (data == null || (n > data.length) || n <= 0) {
            return null;
        }

        return QuickSelectionWeak.selectNthMember(data, n - 1, 0, data.length - 1);
    }

    public static void main(String [] args) {
        Integer [] data = {9,8,7,6,9,5,4,9,3,2,1,9,1,1,1,1,2,3,4,5,13,3,4,5,1,3,5,6,7,8,3};
        for (int index = 0; index < data.length; index++) {
            System.out.println(Integer.toString((Integer)QuickSelectionWeak.selectNthMember(data, index + 1)));
        }
    }
}