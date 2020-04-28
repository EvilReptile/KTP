package cham.Lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FractalExplorer extends JFrame{

	/**
	 * Класс реализации GUI
	 */

	private JImageDisplay image;
	private JButton button;
	private final Rectangle2D.Double rectangle;
	private final FractalGenerator fractal;
	private final int size;

	public FractalExplorer(int size) {
		this.size = size;

		// Создаем объекст координат окна отрисовки фрактала
		this.rectangle = new Rectangle2D.Double(0, 0, size, size);

		// Запускаем отрисовку окна
		createAndShowGUI(new Dimension(size, size));

		//Создаем объект алгоритма отрисовки фрактала
		fractal = new Mandelbrot();
		fractal.getInitialRange(rectangle);

		// Запускаем отрисовку фрактала
		drawFractal();
	}

	// Метод отрисовки GUI изображения
	private void createAndShowGUI(Dimension dim){
		// Задание параметров для кнопки
		button = new JButton("Reset Fractal");
		button.setBackground(Color.GRAY);

		// Обработка нажали кнопки
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				fractal.getInitialRange(rectangle);
				drawFractal();
			}
		});

		// Задание параметров окна
		setSize(dim.width, dim.height + button.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Задание параметров для окна отрисвоки изображения
		int height = dim.height - button.getHeight();
		image = new JImageDisplay(height, dim.width);
		image.clearImage();

		// Обработка нажатия мыши
		image.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, size,x);
				double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, size,y);
				fractal.recenterAndZoomRange(rectangle,xCoord,yCoord,0.5);
				drawFractal();
			}
		});

		// Добавление компонентов в окно
		add(image, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);

		// Отображение рабочего окна
		pack ();
		setVisible (true);
		setResizable (false);
	}

	// Метод рассчета отрисовки фрактала
	private void drawFractal() {
		// Перебор всех координат области отображения фрактала
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {

				// Получение координат фрактала
				double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, size, x);
				double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, size, y);

				// Кол-во совершенных итераций
				int iteration = fractal.numIterations(xCoord, yCoord);

				// Проверка на превышение итераций
				// Если привысили - черный
				if (iteration == -1)
					image.drawPixel(x, y, 0);
				//Если не привысили - создаем цвет на основе итераций
				else {
					float hue = 0.5f + (float) iteration / 200f;
					int rgbColor = Color.HSBtoRGB(hue, 0.7f, 1.0f);
					image.drawPixel(x, y, rgbColor);
				}
			}
		}
		//Перерисовать фрактал
		image.repaint();
	}
}
