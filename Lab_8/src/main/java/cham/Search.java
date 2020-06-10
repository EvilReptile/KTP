package cham;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * Класс реализации поиска ссылок на сайте
 */
public class Search {

    private URLPool list;
    private URLData data;

    public Search(URLPool list, URLData data){
        this.list = list;
        this.data = data;
    }

    public void parsing() {
        try {
            //Инициадихация подключения к сайту
            URL connect = new URL(data.getUrl());

            //Получение содержимого сайта
            BufferedReader in = new BufferedReader(new InputStreamReader(connect.openStream()));

            // Поиск и запись всех строк с ссылочным типом
            String line;
            while ((line = in.readLine()) != null)
                // Выбор стуктур ссылочного типа
                if(line.indexOf("<a href=") != -1)
                    for(String newUrl: line.split("\""))
                        // Проверка полученной ссылки по протоколу
                        if((newUrl.indexOf("http") != -1) && (newUrl.indexOf("https") == -1)){
                            list.addUrlData(new URLData(newUrl, (data.getIteration() + 1)));
                            break;

                        }else if(newUrl.indexOf("https") != -1)
                            System.out.println("Найден https протокол сайта: " + newUrl);

            in.close();
        }catch (UnknownHostException ex){
            System.out.println("Unknown Host " + ex.getMessage());
        }catch (IOException ex){
            System.out.println("IOException " + ex.getMessage());
        }
    }
}
