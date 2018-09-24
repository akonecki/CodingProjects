import java.util.LinkedList;

public class Solution {
    private static class LinkedListNode {
        private int data;
        private LinkedListNode next = null;

        public LinkedListNode(int data) {
            this.data = data;
        }
    }
    
    // O(N) where N is the length of the larger number.
    // Iterate through each one until terminates and
    // perform the sum.
    public static LinkedListNode sumReverse(
        LinkedListNode num1, 
        LinkedListNode num2) 
    {
        LinkedListNode result = null, tail = null;
        int carry = 0;

        while (num1 != null || num2 != null || carry != 0) {
            int value = 0;
            LinkedListNode node = null;

            if (num1 != null && num2 != null) {
                value += num1.data + num2.data;
                num1 = num1.next;
                num2 = num2.next;               
            }
            else if (num1 != null) {
                value += num1.data;
                num1 = num1.next;
            }
            else if (num2 != null) {
                value += num2.data;
                num2 = num2.next;
            }

            value += carry;
            carry = value / 10;
            value = value % 10;

            node = new LinkedListNode(value);
            if (result == null) {
                result = node;
                tail = node;
            }
            else {
                tail.next = node;
                tail = node;
            }            
        }

        // Could remove leading zeros if data is not really a real integer.

        return result;
    }

    private static int determineSign(LinkedListNode root) {
        LinkedListNode node = root;

        while (node != null && node.next != null) {
            node = node.next;
        }
        
        if (node == null) {
            return 0;
        }
        else if (node.data < 0) {
            return -1;
        }
        else {
            return 1;
        }
    }

}