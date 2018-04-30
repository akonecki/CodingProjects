import java.lang.Byte;

public class ivq8 {
    public void drawHorizontal(byte [] screen, int width, int x1, int x2, int y) {
        int index = 0;

        System.out.println(ivq8.getByteIndex(width, x1, y) + " " + ivq8.getByteIndex(width, x2, y));

        for (index = ivq8.getByteIndex(width, x1, y); 
            index < ivq8.getByteIndex(width, x2, y); 
            index++) {
            // fill the blocks that contain only x1.
            screen[index] = ivq8.fillByteFromPositionToPosition(x1 % 8, 7);
            x1 += (8 - (x1 % 8));
            // System.out.println(x1);
        }

        // fill the block that contains x1 and x2.
        screen[index] = ivq8.fillByteFromPositionToPosition(x1 % 8, x2 % 8);
    }

    public static int getByteIndex(int width, int x, int y) {
        int offset = (y * width / 8) + x / 8;
        // if (x % 8 > 0) {
        //    return offset + 1;
        // }
        // else {
            return offset;
        // } 
    }

    public static byte fillByteFromPositionToPosition(int x1, int x2) {
        assert (x1 <= x2);

        int value2 = (-1 << (7 - x2));
        int value1 = ~(-1 << (8 - x1));

        // System.out.println(Integer.toBinaryString(value1) + " " + Integer.toBinaryString(value2));

        return (byte) (value1 & value2);
    }

    public static void main(String [] args) {
        ivq8 screen = new ivq8();
        byte [] display = new byte[64];
        screen.drawHorizontal(display, 8, 13, 511, 0);

        byte [] testDisplay = new byte[1];
        
        assert (screen.fillByteFromPositionToPosition(0, 7) == -1);
        assert (screen.fillByteFromPositionToPosition(1, 7) == 127);
        assert (screen.fillByteFromPositionToPosition(2, 7) == 63);
        assert (screen.fillByteFromPositionToPosition(3, 7) == 31);
        assert (screen.fillByteFromPositionToPosition(4, 7) == 15);
        assert (screen.fillByteFromPositionToPosition(5, 7) == 7);
        assert (screen.fillByteFromPositionToPosition(6, 7) == 3);
        assert (screen.fillByteFromPositionToPosition(7, 7) == 1);

        assert (screen.fillByteFromPositionToPosition(0, 6) == -2);
        assert (screen.fillByteFromPositionToPosition(1, 6) == 126);
        assert (screen.fillByteFromPositionToPosition(2, 6) == 62);
        assert (screen.fillByteFromPositionToPosition(3, 6) == 30);
        assert (screen.fillByteFromPositionToPosition(4, 6) == 14);
        assert (screen.fillByteFromPositionToPosition(5, 6) == 6);
        assert (screen.fillByteFromPositionToPosition(6, 6) == 2);

        int count = 0;
        for (int index = 0; index < display.length; index++) {
            Byte newByte = new Byte(display[index]);
            if (index % 8 == 0) {
                System.out.println("");
            }
            System.out.println(Integer.toBinaryString(newByte.intValue() & 0xFF ));
        }
        System.out.println("");
    }
}