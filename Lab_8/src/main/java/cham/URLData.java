package cham;

/**
 * DOM хранения информации о адресе и слое, на котором найден слой
 */
public class URLData {

    private String url;
    private int iteration;

    public URLData(String url, int iteration) {
        this.url = url;
        this.iteration = iteration;
    }

    public int getIteration() {
        return iteration;
    }

    public String getUrl() {
        return url;
    }
}
