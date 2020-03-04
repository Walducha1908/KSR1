package Data;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class DataPaths {
    private LinkedList<String> paths;

    public DataPaths(int numberOfFiles, String pathToData) {
        this.paths = new LinkedList<String>();


        DecimalFormat formatter = new DecimalFormat("00");

        for(int i=0; i<numberOfFiles; i++) {
            paths.add(pathToData + "reut2-0" + formatter.format(i) + ".sgm");
        }
    }

    public LinkedList<String> getPaths() {
        return paths;
    }

    public void setPaths(LinkedList<String> paths) {
        this.paths = paths;
    }
}
