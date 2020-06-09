package cham;

import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Класс инициализации проекта анализа ссылок на страницах
 */

public class App {

    public static void main(String[] args) throws Exception, MalformedURLException {
        // Запрос данных от клиента
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите адрес сайта: ");
        String url = scn.nextLine();
        System.out.print("Введите глубину поиска: ");
        int iteration = scn.nextInt();
        scn.close();

        // Создание объекта списка URL и создание нулевого слоя
        URLsList list = new URLsList();
        list.addUrlData(new URLData(url, 0));

        for(int i = 0; i < iteration; i++) {
            // Получение списка
            LinkedList<URLData> searchList = list.getOpen_list();
            // Закрытие списка полученных точек
            list.closingUrl();

            // Запуск потока работу с web
            Search search = new Search(list, searchList);
            search.run();
        }

        // Вывод в файл
        list.fileOutput("test.txt");
    }
}
