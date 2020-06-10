package cham;

public class CrawlerTask implements Runnable {

    private URLPool pool;
    private int iteration;
    private String threadName;

    public CrawlerTask(URLPool pool, int iteration, String threadName){
        this.pool = pool;
        this.iteration = iteration;
        this.threadName = threadName;
    }

    // Реализация метода поиска ссылок на странице
    @Override
    public void run() {
        URLData data = getURL();
        while(data != null){
            if(data.getIteration() != iteration){
                Search search = new Search(pool, data);
                search.parsing();
            }
            data = getURL();
        }

        // Если по результатам работы алгоритма URL адреса не найдено
        if(data == null)
            System.out.println("Превышен тайм-аут ожидания ссылки в потоке: " + threadName);
    }

    // Поиск нового URL адреса для поиска по странице с режимом ожидания
    private URLData getURL(){
        if(pool.isEmptyOpen())
            for(int i = 0; i < 20; i++){
                // Ожидание
                waiting();

                // Если в пуле появились ссылки - закончить ожидание
                if(!pool.isEmptyOpen())
                    break;
            }

        // Проверка на пустоту пула для возврата ссылки
        if(!pool.isEmptyOpen())
            return pool.getLastURLData();
        else
            return null;
    }

    // Инициализация режима ожидания
    private void waiting(){
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
