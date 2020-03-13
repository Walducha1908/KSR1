package Data;

import Main.Settings;
import Model.Article;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class DataCleaner {
    public static String removePunctuation(String stringToClear) {
        stringToClear = stringToClear.replaceAll("[\n]", " ");
        stringToClear = stringToClear.replaceAll("[^a-zA-Z ]", "");
        return stringToClear.toLowerCase();
    }

    public static LinkedList<String> removeStopListWords(LinkedList<String> listToClear,
                                                         LinkedList<String> stopList) {
        LinkedList<String> resultList = new LinkedList<String>();
        for (int i=0; i<listToClear.size(); i++) {
            if (!stopList.contains(listToClear.get(i).toLowerCase())){
                resultList.add(listToClear.get(i));
            }
        }
        return resultList;
    }

    public static LinkedList<String> removeEmptyWords(LinkedList<String> listToClear) {
        listToClear.removeAll(Arrays.asList("", null));
        return listToClear;
    }

    public static LinkedList<Article> removeUnwantedArticles(LinkedList<Article> articlesToCheck, String category) {
        LinkedList<Article> resultArticles = new LinkedList<Article>();

        if (category.equals("places")) {
            for (int i=0; i<articlesToCheck.size(); i++) {
                if ((!Collections.disjoint(articlesToCheck.get(i).getPlaces(), Settings.categoryItemsList)) &&
                    (articlesToCheck.get(i).getPlaces().size() == 1) && (articlesToCheck.get(i).getBody().size() > 0)) {
                    if (articlesToCheck.get(i).getBody().get(0).size() > 0) {
                        resultArticles.add(articlesToCheck.get(i));
                    }
                }
            }
        }

        return resultArticles;
    }

    public static LinkedList<String> stem(LinkedList<String> listToStem) {
        LinkedList<String> resultList = new LinkedList<String>();
        for (int i=0; i<listToStem.size(); i++) {
            String word = listToStem.get(i);
//            Pattern pattern = Pattern.compile(".*ing");

            if (word.contains("ing")) {
                resultList.add(word.substring(0, word.indexOf("ing")));
            }
        }
        return resultList;
    }
}
