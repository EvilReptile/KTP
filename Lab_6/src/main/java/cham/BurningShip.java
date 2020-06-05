package cham;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double rectangle) {
        rectangle.x = -2;
        rectangle.y = -2.5;
        rectangle.height = 4;
        rectangle.width = 4;

    }

    @Override
    public int numIterations(double x, double y) {
        Complex z = new Complex(0, 0);
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            z = new Complex(Math.abs(z.real()*z.real() - z.imaginary()*z.imaginary() + x), Math.abs(2*z.real()*z.imaginary() + y));
            if(z.abs() > 4) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Burning Ship";
    }
}
