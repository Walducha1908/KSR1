package Data;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class DataPaths {
    private LinkedList<String> articlePaths;
    private String stopListPath;

    public DataPaths(int numberOfFiles, String pathToData) {
        this.articlePaths = new LinkedList<String>();
        this.stopListPath = pathToData + "StopList.txt";

        DecimalFormat formatter = new DecimalFormat("00");
        for(int i=0; i<numberOfFiles; i++) {
            articlePaths.add(pathToData + "reut2-0" + formatter.format(i) + ".sgm");
        }

    }

    public LinkedList<String> getArticlePaths() {
        return articlePaths;
    }

    public void setArticlePaths(LinkedList<String> articlePaths) {
        this.articlePaths = articlePaths;
    }

    public String getStopListPath() {
        return stopListPath;
    }

    public void setStopListPath(String stopListPath) {
        this.stopListPath = stopListPath;
    }
}
