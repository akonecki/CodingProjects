public class ivq3 {
    public static void printNextBitNums(int value) {
        if (value <= 0) {
            System.out.println("Value must be positive.");
        }
    
        ivq3.printLesserBitNum(value);
        ivq3.printGreaterBitNum(value);
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

    private static void printGreaterBitNum(int value) {
        int temp = value;
        int final_mask = 1;
        int worse_case = 0;

        while (temp > 0) {
            final_mask = final_mask << 1;

            if (((temp & 3) ^ 1) == 0) {
                break;
            }

            if ((temp & 1) == 1) {
                worse_case = (worse_case << 1) | 1;
            }

            temp = temp >> 1;
        }

        if (temp == 0) {
            System.out.println("No greater value with same number of bits found for " + value + " " + Integer.toBinaryString(value));
            return;
        }

        if (final_mask > value) {
            temp = final_mask | worse_case;

            if (temp < 0) {
                System.out.println("No greater value with same number of bits found for " + value + " " + Integer.toBinaryString(value));
                return;
            }
        }
        else {
            temp = (value & (~(final_mask >> 1))) | final_mask;
        }

        assert (temp > value);
        assert (temp > 0);
        assert (Integer.bitCount(temp) == Integer.bitCount(value));
        System.out.println("Value " + value + " " + Integer.toBinaryString(value) + " Greater " + temp + " " + Integer.toBinaryString(temp));
    }

    public static void main (String [] args) {
        for (int index = 1; index < 1000; index++) {
            ivq3.printNextBitNums(index);
        }
        


    }
}