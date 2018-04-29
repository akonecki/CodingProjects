

public class ivq7 {
    private Data data;
    private class Data {
        private int [] mData;
        private int j = 0;
        private int i = 0;

        public Data() {
            mData = new int [99];
            for (int index = 0; index < 99; index++) {
                mData[index] = index;
            }
        }
        
        public int fetch() throws java.lang.IllegalArgumentException {
            if (this.i < mData.length) {
                int value = ((mData[this.i] & (1 << this.j++)) >> (this.j - 1));
                if (this.j > 30) {
                    this.i++;
                    this.j = 0;
                }
                return value;
            }
            else {
                throw new java.lang.IllegalArgumentException("helo");
            }
        }
    }

    public ivq7() {
        this.data = new Data();
    }

    public int missingValue() {
        int count = 0, sum = 0;
        int temp = 0;
        // Dont know how many bits are relevant per integer (guess is fetch will iterate though all 32 per number).
        // Dont know how many numbers total
        try {
            while (true) {
                temp = 0;
                count++;
                for (int index = 0; index < 31; index++) {
                    temp = (this.data.fetch() << index) | temp;
                }
                // System.out.println(temp);
                sum = sum + count - temp;
            }
        }
        catch (java.lang.IllegalArgumentException e) {
            // do nothing exception means end of the array.
            // System.out.println(temp);
            // sum += count - temp;
        }
        return sum;
    }

    public static void main(String [] args) {
        ivq7 data = new ivq7(); 
        System.out.println(data.missingValue());
    }
}