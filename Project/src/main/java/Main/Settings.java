package Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Settings {
    public static String category = "places";
    public static String pathToData = "../Data/";
    public static int numberOfFiles = 22;
    public static double percentOfTraining = 0.8;
    public static int numberOfKeyWordPerCategory = 15;
    public static String measure = "TF";
    public static LinkedList<String> categoryItemsList =
            new LinkedList<String> (Arrays.asList (
            "usa",
            "west-germany",
            "uk",
            "japan",
            "canada"
    ));
    public static HashMap<String, Boolean> featuresUsedMap =
            new HashMap<String, Boolean>() {{
        put("Body", true);
        put("Title", true);
        put("Dateline", true);
        put("Ratio", true);
        put("First50Words", true);
        put("First10PerCent", true);
        put("FirstParagraph", true);
        put("Last50Words", true);
        put("Last10PerCent", true);
        put("LastParagraph", true);
    }};
}
