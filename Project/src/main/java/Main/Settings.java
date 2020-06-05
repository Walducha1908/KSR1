package Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Settings {
    public static String category = "topics";
    public static String pathToData = "../Data/";
    public static String pathToReports = "../Reports/";
    public static int numberOfFiles = 22;
    public static double percentOfTraining = 0.7;
    public static int numberOfKeyWordPerCategory = 23;
    public static String keyWords = "TF";
    public static String metrics_measure = "Manhattan";
    public static boolean ngramBeforeExtraction = false;
    public static int k = 6;
    public static boolean wages = false;
    public static double minWage = 0;
    public static double maxWage = 1;
    public static boolean ngram = false;
    public static int n = 3;
//    public static LinkedList<String> categoryItemsList =
//            new LinkedList<String> (Arrays.asList (
//            "usa",
//            "west-germany",
//            "uk",
//            "japan",
//            "canada",
//            "france"
//    ));
    public static LinkedList<String> categoryItemsList =
            new LinkedList<String> (Arrays.asList (
                    "earn",
                    "trade",
                    "money-supply",
                    "acq"
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
            put("Last50Words", true);
            put("Last10PerCent", true);
            put("LastParagraph", true);
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
