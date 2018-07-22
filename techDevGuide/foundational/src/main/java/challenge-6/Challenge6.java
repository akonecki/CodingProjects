public class Challenge6 {

    public static void neighborValues(int days, int [] values) {
        for (int day = 0; day < days; day++) {
            int prev = 0;
            int curr = 0;
            int next = 0;

            for (int index = 0; index < values.length; index++) {
                curr = values[index];
                if (index + 1 < values.length) {
                    next = values[index + 1];
                }
                else {
                    next = 0;
                }

                values[index] = next == prev ? 0 : 1;
                prev = curr;
            }
        }
    }

    public static void main(String [] args) {
        int [] cells = new int [] {0,1,0,1,0,1,0};
        neighborValues(1, cells);

        for (int cell : cells) {
            System.out.print(cell + " ");
        }

        System.out.println("");
    }
}