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
        System.out.print("Введите кол-во потоков: ");
        int threads = scn.nextInt();
        scn.close();

        // Создание объекта списка URL и создание нулевого слоя
        URLPool pool = new URLPool();
        pool.addUrlData(new URLData(url, 0));

        // Создание списка потоков
        Thread[] threadsList = new Thread[threads];

        // Иницаиализация потоков в списке и запуск
        for(int i = 0; i < threads; i++){
            CrawlerTask task = new CrawlerTask(pool, iteration, "Thread-" + (i + 1));
            threadsList[i] = new Thread(task);
            threadsList[i].start();
        }

        // Перебор списка с ожиданием завершения потока
        for(Thread thread: threadsList)
            thread.join();

        // Вывод в файл
        pool.fileOutput("test.txt");
    }
}
