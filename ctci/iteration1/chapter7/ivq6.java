public class ivq6 {

    private class Point extends Comparable<Point> {
        private final int mX;
        private final int mY;

        public Point(int x, int y) {
            this.mX = x;
            this.mY = y;
        }

        public int compareTo(Point p) {
            // -1 : this < p
            //  0 : this == p
            //  1 : this > p

            if (this.mY == p.mY) {
                if (this.mX == p.mX) {
                    return 0;
                }
                else if (this.mX > p.mX) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else if (this.mY > p.mY) {
                return 1;
            }
            else {
                return -1;
            }
        }

        public double slope(Point p) {
            int diffX = p.mX - this.mX;
            int diffY = p.mY - this.mY; 

            if (diffX == 0) {
                if (diffY >= 0) {
                    return Double.INFINITY;
                }
                else {
                    return Double.NEGATIVE_INFINITY;
                }
            }

            if (diffY == 0) {
                return 0.0;
            }

            return ((double)diffY) / ((double)(diffX));
        }

        private class ColinearOrder implements Comparator<Point> {
            public int compare(Point a, Point b) {
                return 0;
            }
        }
    }
}