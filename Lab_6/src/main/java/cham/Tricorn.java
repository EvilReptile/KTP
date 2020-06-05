package cham;

import java.awt.geom.Rectangle2D.Double;

public class Tricorn extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Double rectangle) {
        rectangle.x = -2;
        rectangle.y = -2;
        rectangle.height = 4;
        rectangle.width = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        Complex z0 = new Complex(x,y);
        Complex z = new Complex(0, 0);
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            z.pairing();
            z = z.mul(z).add(z0);
            if(z.abs() > 4) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Tricon";
    }
}
