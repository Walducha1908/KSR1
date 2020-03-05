package Data;

import Model.Article;

import java.util.Arrays;
import java.util.LinkedList;

public class DataCleaner {
    public static String removePunctuation(String stringToClear) {
        stringToClear = stringToClear.replaceAll("[\n]", " ");
        return stringToClear.replaceAll("[^a-zA-Z0-9/ ]", "");
    }

    public static LinkedList<String> removeStopListWords(LinkedList<String> listToClear,
                                                         LinkedList<String> stopList) {
        LinkedList<String> resultList = new LinkedList<String>();
        for (int i=0; i<listToClear.size(); i++) {
            if (!stopList.contains(listToClear.get(i))){
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
                if ((articlesToCheck.get(i).getPlaces().contains("usa") ||
                    articlesToCheck.get(i).getPlaces().contains("west-germany") ||
                    articlesToCheck.get(i).getPlaces().contains("uk") ||
                    articlesToCheck.get(i).getPlaces().contains("japan") ||
                    articlesToCheck.get(i).getPlaces().contains("canada")) &&
                    (articlesToCheck.get(i).getPlaces().size() == 1)) {
                        resultArticles.add(articlesToCheck.get(i));
                }
            }
        }

        return resultArticles;
    }
}
