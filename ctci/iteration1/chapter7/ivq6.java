import java.util.Comparator;
import java.lang.Math;

public class ivq6 {

    private class Point implements Comparable<Point> {
        private final int mX;
        private final int mY;

        public Point(int x, int y) {
            this.mX = x;
            this.mY = y;
        }

        public String toString() {
            return "Point <X,Y> :: <" + this.mX + ", " + this.mY + ">";
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
                    return Double.POSITIVE_INFINITY;
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

        public Comparator<Point> slopeOrder() {
            return new CollinearOrder();
        }

        private class CollinearOrder implements Comparator<Point> {
            public int compare(Point a, Point b) {
                // -1 : slope (this, a) < slope (this, b)
                //  0 : this === p
                //  1 : slope (this, a) > slope (this, b)
                double slopeA = Point.this.slope(a);
                double slopeB = Point.this.slope(b);

                if (Math.abs(slopeA - slopeB) < 0.000001) {
                    // Roughly equal.
                    return 0;
                }
                else if (slopeA < slopeB) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
    }

    public int getMaxCollinearPoints(Point [] points) {
        return 0;
    }
}