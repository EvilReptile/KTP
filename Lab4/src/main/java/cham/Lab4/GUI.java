package cham.Lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -3603744653970464775L;
	/**
	 * Класс реализации GUI
	 */

	private JImageDisplay image;
	private JButton button;
	
	public GUI(Dimension dim) {
        // Задание параметров окна
        setSize(dim);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(dim);
        
        // Задание параметров для кнопки
		button = new JButton("test button");
		button.setBackground(Color.GRAY);
		
		//Задание параметров для окна отрисвоки изображения
		int height = dim.height - button.getHeight();
		image = new JImageDisplay(height, dim.width);
		image.clearImage();
		
		// Добавление компонентов в окно
		add(image, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
