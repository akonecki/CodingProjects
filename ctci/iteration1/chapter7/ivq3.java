import java.lang.Math;

public class ivq3 {

    public static int LCM(int x1, int x2) {
        if ((x1 <= x2) && (x2 % x1 == 0)) {
            return x1;
        }
        else if ((x1 > x2) && (x1 % x2 == 0)) {
            return x2;
        }
        else { // (x1 > x2) {
            return x1 * x2;
        }
    }

    public static int GCD(int num, int den) {
        if (den != 0 && num >= den && (num % den == 0)) {
            return den;
        }
        else if (num != 0 && num < den && (den % num == 0)) {
            return num;
        }
        else {
            return 1;
        }
    }

    public static class Line {
        int x1, x2, y1, y2;
        Ratio slope, yIntercept;

        public Line(int x1, int y1, int x2, int y2) {
            // y major ordering.
            if (y1 < y2 || (y1 <= y2 && x1 <= x2)) {
                this.x1 = x1;
                this.x2 = x2;
                this.y1 = y1;
                this.y2 = y2;
            }
            else {
                this.x1 = x2;
                this.x2 = x1;
                this.y1 = y2;
                this.y2 = y1;
            }

            this.slope = new Ratio(this.y2 - this.y1, this.x2 - this.x1);
            this.yIntercept = this.slope.multiple(new Ratio(0 - this.x1, 1)).add(new Ratio(this.y1, 1));
        }

        // intersection is defined as a single point at which two lines cross (in the planar sense).
        public boolean intersects(Line line) {
            Ratio x, y;

            // double comparison is just bad.
            if ((this.slope.denomentator == 0 && line.slope.denomentator == 0) || 
                (this.slope.equals(line.slope))) {
                assert (this.x1 == this.x2);
                assert (line.x1 == line.x2);
                return false; 
            }

            System.out.println("Line 1 Slope : " + this.slope + " y-Intercept : " + this.yIntercept);
            System.out.println("Line 2 Slope : " + line.slope + " y-Intercept : " + line.yIntercept);
            System.out.println("y2 Intercept - y1 Intercept " + line.yIntercept.sub(this.yIntercept));
            System.out.println("slope1 - slope2 " + this.slope.sub(line.slope));

            x = line.yIntercept.sub(this.yIntercept).divide(this.slope.sub(line.slope));
            y = this.slope.multiple(x).add(this.yIntercept);

            System.out.println("x " + x + " y " + y);
            System.out.println("slope1 * x " + this.slope.multiple(x));
            System.out.println("(slope1 * x) + y-intercept " + this.slope.multiple(x).add(this.yIntercept));

            return this.intervalContains(x, y) && line.intervalContains(x, y);
        }

        private boolean intervalContains(Ratio x, Ratio y) {
            // will need to convert the line's x & y points to Ratios
            return y.compareTo(new Ratio(this.y1, 1)) >= 0 && y.compareTo(new Ratio(this.y2, 1)) <= 0 && 
                x.compareTo(new Ratio(this.minX(), 1)) >= 0 && x.compareTo(new Ratio(this.maxX(), 1)) <= 0;
        }

        public int minX() {
            if (this.x1 <= this.x2) {
                return this.x1;
            }
            else {
                return this.x2;
            }
        }

        public int maxX() {  
            if (this.x1 >= this.x2) {
                return this.x1;
            }
            else {
                return this.x2;
            }
        }

        private class Ratio {
            // numerator will always indicate if negative or not.
            private int numerator;
            private int denomentator;
    
            public boolean equals(Ratio ratio) {
                if (this.numerator == ratio.numerator && this.denomentator == ratio.denomentator) {
                    return true;
                }
                return false;
            }

            public String toString() {
                return new String("[" + this.numerator + "/" + this.denomentator + "]");
            }
    
            public Ratio(int num, int den) {
                int gcd = ivq3.GCD(Math.abs(num), den);
                this.numerator = num / gcd;
                this.denomentator = den / gcd;
            }
    
            public Ratio add(Ratio operand) {
                // Find least common multiple of the denomentator
                int den = 0;
                int num = 0;
                
                if (operand.numerator != 0 && this.numerator != 0 && this.denomentator != 0 && operand.denomentator != 0) {
                    den = ivq3.LCM(this.denomentator, operand.denomentator);
                    num = ((den / this.denomentator) * this.numerator) + ((den / operand.denomentator) * operand.numerator);
                }
                else if (operand.numerator != 0 && operand.denomentator != 0) {
                    den = operand.denomentator;
                    num = operand.numerator;
                }
                else if (this.numerator != 0 && this.denomentator != 0) {
                    den = this.denomentator;
                    num = this.numerator;
                }
                
                return new Ratio(num, den);
            }
    
            public Ratio sub(Ratio operand) {
                int den = 0;
                int num = 0;
                
                if (operand.numerator != 0 && this.numerator != 0 && this.denomentator != 0 && operand.denomentator != 0) {
                    den = ivq3.LCM(this.denomentator, operand.denomentator);
                    num = ((den / this.denomentator) * this.numerator) - ((den / operand.denomentator) * operand.numerator);
                }
                else if (operand.numerator != 0 && operand.denomentator != 0) {
                    den = operand.denomentator;
                    num = -1 * operand.numerator;
                }
                else if (this.numerator != 0 && this.denomentator != 0) {
                    den = this.denomentator;
                    num = this.numerator;
                }
                
                return new Ratio(num, den);    
            }
    
            public Ratio divide(Ratio operand) {
                int den = this.denomentator * operand.numerator;
                int num = this.numerator * operand.denomentator;
                return new Ratio(num, den);
            }
    
            public Ratio multiple(Ratio operand) {
                int den = this.denomentator * operand.denomentator;
                int num = this.numerator * operand.numerator;
                return new Ratio(num, den);
            }

            public int compareTo(Ratio operand) {
                // -1 : less than
                //  0 : equal
                //  1 : greater
                int lcm = ivq3.LCM(this.denomentator, operand.denomentator);
                int num1 = lcm * this.numerator;
                int num2 = lcm * operand.numerator;

                if (num1 == num2) {
                    return 0;
                }
                else if (num1 < num2) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
    }

    public static void main(String [] args) {
        ivq3.Line line1 = new ivq3.Line(0, 0, 5, 5);
        ivq3.Line line2 = new ivq3.Line(0, 0, 3, 7);
        ivq3.Line line3 = new ivq3.Line(0, 5, 5, 0);
        assert (line1.intersects(line2));
        assert (line1.intersects(line3));
    }
}