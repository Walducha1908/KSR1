package Data;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class DataPaths {
    private LinkedList<String> paths;
    private int numberOfFiles;

    public DataPaths(int numberOfFiles) {
        this.paths = new LinkedList<String>();
        this.numberOfFiles = numberOfFiles;

        DecimalFormat formatter = new DecimalFormat("00");

        for(int i=0; i<numberOfFiles; i++) {
            paths.add("../Data/reut2-0" + formatter.format(i) + ".sgm");
        }
    }

    public LinkedList<String> getPaths() {
        return paths;
    }

    public void setPaths(LinkedList<String> paths) {
        this.paths = paths;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }
}
