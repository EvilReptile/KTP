package cham.Lab4;

import java.awt.geom.Rectangle2D;

/**
 * This class provides the common interface and operations for fractal
 * generators that can be viewed in the Fractal Explorer.
 */
public abstract class FractalGenerator {

    /**
     * Эта статическая вспомогательная функция принимает целочисленную координату и преобразует ее
     * в значение двойной точности, соответствующее определенному диапазону. это
     * используется для преобразования координат пикселей в значения двойной точности для
     * вычислительные фракталы и др.
     *
     * @param rangeMin минимальное значение диапазона с плавающей запятой
     * @param rangeMax максимальное значение диапазона с плавающей запятой
     *
     * @param size размер измерения, из которого берется координата пикселя.
     * Например, это может быть ширина изображения или высота изображения.
     *
     * @param coord координату, чтобы вычислить значение двойной точности для.
     * Координата должна находиться в диапазоне [0, размер].
     */
    public static double getCoord(double rangeMin, double rangeMax,
        int size, int coord) {

        assert size > 0;
        assert coord >= 0 && coord < size;

        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /**
     * Устанавливает указанный прямоугольник, чтобы содержать начальный диапазон, подходящий для
     * генерируемый фрактал.
     */
    public abstract void getInitialRange(Rectangle2D.Double range);


    /**
     * Обновляет текущий диапазон для центрирования по указанным координатам,
     * и увеличивать или уменьшать с помощью указанного коэффициента масштабирования.
     */
    public void recenterAndZoomRange(Rectangle2D.Double range,
        double centerX, double centerY, double scale) {

        double newWidth = range.width * scale;
        double newHeight = range.height * scale;

        range.x = centerX - newWidth / 2;
        range.y = centerY - newHeight / 2;
        range.width = newWidth;
        range.height = newHeight;
    }


    /**
     * Учитывая координату <em> x </ em> + <em> y </ em> в комплексной плоскости,
     * вычисляет и возвращает количество итераций до фрактала
     * функция выходит за пределы этой точки. Дело в том, что
     * не сбегает до достижения лимита итерации
     * с результатом -1.
     */
    public abstract int numIterations(double x, double y);
}