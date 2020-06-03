package cham;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer extends JFrame{

	/**
	 * Класс реализации GUI
	 */

	private JImageDisplay image;
	private JButton reset;
	private  JButton save;
	private final Rectangle2D.Double rectangle;
	private FractalGenerator fractal;
	private final int size;
	private JComboBox comboBox = null;

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
		// Задание параметров для кнопки обновления
		reset = new JButton("Reset Fractal");
		reset.setBackground(Color.GRAY);

		// Обработка нажали кнопки
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				fractal.getInitialRange(rectangle);
				drawFractal();
			}
		});

		// Инициализация параметров кнопки сохранения
		save = new JButton("Save Fractal");
		save.setBackground(Color.GRAY);

		// Обработка нажатия кнопки
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// Инициализация диалогового окна сохранения файла
				JFileChooser chooser = new JFileChooser();

				// Инициализация фильтра
				FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);

				// Запуск диалогового окна
				chooser.showDialog(null, "Выбрать файл");

				// Полуение пути к файлу, изображения и сохранение изображения
				File file = chooser.getSelectedFile();
				BufferedImage buf = image.getImage();
				try {
					ImageIO.write(buf, "PNG", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// Задание параметров окна
		setSize(dim.width, dim.height + reset.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Задание коллекции фракталов
		FractalGenerator[] fractals = {new Mandelbrot(), new Tricorn(), new BurningShip()};

		// Инициализация выпадающего списка
		comboBox = new JComboBox(fractals);

		// Добавление активной работы выпадающего меню
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JComboBox box = (JComboBox) actionEvent.getSource();
				fractal = (FractalGenerator) box.getSelectedItem();
				fractal.getInitialRange(rectangle);
				drawFractal();
			}
		});

		// Задание параметров для окна отрисвоки изображения
		int height = dim.height - reset.getHeight();
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

		// Создание контекстной панели
		JPanel panel = new JPanel();
		panel.add(reset);
		panel.add(save);

		// Добавление компонентов в окно
		add(image, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		add(comboBox, BorderLayout.NORTH);

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
