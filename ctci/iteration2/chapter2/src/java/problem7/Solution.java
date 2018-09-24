public class Solution {
    private static class LinkedListNode <T> {
        private T data;
        private LinkedListNode<T> next = null;
    }

    // O(N) - Iterate through the list first until either a match that is not 
    // the end (same distance linked lists to intersection) or until the last
    // node.  If last nodes are not equal then return null.  If they are equal
    // compute the longer distance linked list (by keeping a counter for both).
    // Increment the longer one until its distance equals that of the smalelr
    // one.  Once they are both equal increment both by one until the 
    // intersection is found.
    public static <T> LinkedListNode<T> intersection(LinkedListNode<T> list1, LinkedListNode<T> list2) {
        int list1Count = 0, list2Count = 0;
        LinkedListNode<T> node1 = list1, node2 = list2, prev1 = null, prev2 = null;
        
        if (list1 == null || list2 == null) {
            return null;
        }
        else if (list1 == list2) {
            return list1;
        }

        while (node1 != null || node2 != null) {
            if (node1 != null && node2 != null) {
                if (node1 == node2) {
                    // equal distance match.
                    return node1;
                }
                
                prev1 = node1;
                prev2 = node2;
                list1Count++;
                list2Count++;
                node1 = node1.next;
                node2 = node2.next;
            }
            else if (node1 != null) {
                prev1 = node1;
                list1Count++;
                node1 = node1.next;
            }
            else if (node2 != null) {
                prev2 = node2;
                list2Count++;
                node2 = node2.next;
            }
        }

        // Make sure that at least hit the end node.
        if (prev1 != prev2) {
            // no match possible dont end on the same last node.
            return null;
        }

        node1 = list1;
        node2 = list2;

        // End on the same last node.
        while (list1Count >= 1 || list2Count >= 1) {
            if (list1Count > list2Count) {
                node1 = node1.next;
                list1Count--;
            }
            else if (list1Count < list2Count) {
                node2 = node2.next;
                list2Count--;
            }
            else {
                // equal counts
                if (node1 == node2) {
                    break;
                }
                list1Count--;
                list2Count--;
                node1 = node1.next;
                node2 = node2.next;
            }
        }

        assert (node1 == node2);

        return node1;
    }
}