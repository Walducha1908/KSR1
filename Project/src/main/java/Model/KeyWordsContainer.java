package Model;

import java.util.HashMap;
import java.util.LinkedList;

public class KeyWordsContainer {
    public static HashMap<String, LinkedList<String>> keyWordsMap;
    public static HashMap<String, Double> keyWordsWagesMap;

    public KeyWordsContainer() {
        this.keyWordsMap = new HashMap<String, LinkedList<String>>();
        this.keyWordsWagesMap = new HashMap<String, Double>();
    }
}
