package cham;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class URLsList {

    private LinkedList<URLData> open_list = new LinkedList<>();
    private LinkedList<URLData> close_list = new LinkedList<>();

    // Добавление URL адреса в открытый список
    public void addUrlData(URLData data){
        open_list.add(data);
    }

    // Перемещение URL адреса в закрытый список
    public void closingUrl(){
        for(URLData data: open_list)
            close_list.add(data);

        open_list = new LinkedList<>();
    }

    // Получение списка открытых URL адресов
    public LinkedList<URLData> getOpen_list() {
        return open_list;
    }

    // Вывод в файл
    public void fileOutput(String path){
        try(FileOutputStream fos=new FileOutputStream(path)){
            String line = "";

            for(URLData data : close_list)
                line += data.getUrl() + " " + data.getIteration() + "\n";

            for(URLData data : open_list)
                line += data.getUrl() + " " + data.getIteration() + "\n";

            byte[] buffer = line.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    // Вывод в консоль
    public void consoleOutput(){
        for(URLData data : close_list)
            System.out.println(data.getUrl() + " " + data.getIteration());

        for(URLData data : open_list)
            System.out.println(data.getUrl() + " " + data.getIteration());

    }
}
