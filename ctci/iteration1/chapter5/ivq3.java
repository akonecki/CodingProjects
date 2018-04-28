public class ivq3 {
    public static void printNextBitNums(int value) {
        if (value <= 0) {
            System.out.println("Value must be positive.");
        }
    
        ivq3.printLesserBitNum(value);
    }

    private static void printLesserBitNum(int value) {
        int temp = value;
        int final_mask = 2;

        while (temp > 0) {
            
            if (((temp & 0x3) ^ 0x2) == 0x0) {
                // pattern of bits found.
                break;
            }

            final_mask = final_mask << 1;
            temp = temp >> 1;
        }

        if (temp == 0) {
            System.out.println("No lesser value with same number of bits found for " + value + " " + Integer.toBinaryString(value));
            return;
        }

        // System.out.println(Integer.toBinaryString(final_mask) + " " + Integer.toBinaryString(~final_mask));

        temp = (value & (~final_mask)) | (final_mask >> 1);
        // System.out.println(Integer.toBinaryString(temp));
        assert (temp < value);
        assert (temp > 0);
        assert (Integer.bitCount(temp) == Integer.bitCount(value));

        System.out.println("Value " + value + " " + Integer.toBinaryString(value) + " Lesser " + temp + " " + Integer.toBinaryString(temp));
    }

    public static void main (String [] args) {
        ivq3.printNextBitNums(15);
        ivq3.printNextBitNums(1);
        ivq3.printNextBitNums(9);
        ivq3.printNextBitNums(34);
    }
}