package Main;

import java.util.Arrays;
import java.util.LinkedList;

public class Settings {
    public static String category = "places";
    public static String pathToData = "../Data/";
    public static int numberOfFiles = 22;
    public static double percentOfTraining = 0.8;
    public static int numberOfKeyWordPerCategory = 15;
    public static String measure = "DF";
    public static LinkedList<String> categoryItemsList =
            new LinkedList<String> (Arrays.asList (
            "usa",
            "west-germany",
            "uk",
            "japan",
            "canada"
    ));
}
