package cham.Lab4;

import java.awt.geom.Rectangle2D.Double;

public class Mandelbrot extends FractalGenerator {

	public static final int MAX_ITERATIONS = 2000;

	@Override
	public void getInitialRange(Double rectangle) {
		rectangle.x = -2;
		rectangle.y = -1.5;
		rectangle.height = 3;
		rectangle.width = 3;

	}

	@Override
	public int numIterations(double x, double y) {
		Complex z0 = new Complex(x,y);
		Complex z = z0;
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			if(z.abs() > 4) return i;
			z = z.mul(z).add(z0);
		}
		return -1;
	}

}
