package Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Settings {
    public static String category = "places";
    public static String pathToData = "../Data/";
    public static int numberOfFiles = 22;
    public static double percentOfTraining = 0.7;
    public static int numberOfKeyWordPerCategory = 10;
    public static String measure = "TF";
    public static String metrics = "Euclidean";
    public static int k = 15;
    public static LinkedList<String> categoryItemsList =
            new LinkedList<String> (Arrays.asList (
            "usa",
            "west-germany",
            "uk",
            "japan",
            "canada",
            "france"
    ));
    public static HashMap<String, Boolean> featuresUsedMap =
            new HashMap<String, Boolean>() {{
            put("Body", true);
            put("Title", true);
            put("Dateline", true);
            put("Ratio", true);
            put("First50Words", true);
            put("First10PerCent", true);
            put("First20PerCent", true);
            put("First50PerCent", true);
            put("FirstParagraph", true);
            put("Last50Words", false);
            put("Last10PerCent", false);
            put("LastParagraph", false);
    }};
    public static LinkedList<String> featuresOrder =
            new LinkedList<String> (Arrays.asList (
            "Body",
            "Title",
            "Dateline",
            "Ratio",
            "First50Words",
            "First10PerCent",
            "First20PerCent",
            "First50PerCent",
            "FirstParagraph",
            "Last50Words",
            "Last10PerCent",
            "LastParagraph"
    ));
}
