package Data;

import Main.Settings;
import Model.Article;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

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

        if (category == "places") {
            for(int i=0; i<articlesToCheck.size(); i++) {
                if ((!Collections.disjoint(articlesToCheck.get(i).getPlaces(), Settings.categoryItemsList)) &&
                    (articlesToCheck.get(i).getPlaces().size() == 1)) {
                        resultArticles.add(articlesToCheck.get(i));
                }
            }
        }

        return resultArticles;
    }
}
