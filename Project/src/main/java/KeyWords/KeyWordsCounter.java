package KeyWords;

import Main.Settings;
import Model.Article;
import Model.ArticleContainer;

import java.util.*;

public class KeyWordsCounter {
    private HashMap<String, Integer> keyWordMap;
    private String category;
    private String categoryValue;
    private LinkedList<Article> articlesToSearch;
    private LinkedList<String> finalKeyWordsList;

    public KeyWordsCounter(String category, String categoryValue) {
        this.category = category;
        this.categoryValue = categoryValue;
        this.keyWordMap = new HashMap<String, Integer>();
        this.articlesToSearch = new LinkedList<Article>();
        this.finalKeyWordsList = new LinkedList<String>();

        getArticlesToSearch();
        if (Settings.measure == "TF") {
            countWords_TF();
        } else if (Settings.measure == "DF") {
            countWords_DF();
        }
        sortWords();
    }

    public void getArticlesToSearch() {
        for (int i=0; i< ArticleContainer.trainingArticlesList.size(); i++) {
            if (category == "places") {
                if (ArticleContainer.trainingArticlesList.get(i).getPlaces().contains(categoryValue)) {
                    articlesToSearch.add(ArticleContainer.trainingArticlesList.get(i));
                }
            }
        }
    }

    public void countWords_TF() {
        for (int i=0; i<articlesToSearch.size(); i++) {
            for (int j=0; j<articlesToSearch.get(i).getBody().size(); j++) {
                for (int k=0; k<articlesToSearch.get(i).getBody().get(j).size(); k++) {
                    String key = articlesToSearch.get(i).getBody().get(j).get(k);
                    if (keyWordMap.containsKey(key)) {
                        keyWordMap.put(key, keyWordMap.get(key) + 1);
                    } else {
                        keyWordMap.put(key, 1);
                    }
                }
            }
        }
    }

    public void countWords_DF() {
        for (int i=0; i<articlesToSearch.size(); i++) {
            LinkedList<String> alreadyCounted = new LinkedList<String>();
            for (int j=0; j<articlesToSearch.get(i).getBody().size(); j++) {
                for (int k=0; k<articlesToSearch.get(i).getBody().get(j).size(); k++) {
                    String key = articlesToSearch.get(i).getBody().get(j).get(k).toLowerCase();
                    if (!alreadyCounted.contains(key)) {
                        if (keyWordMap.containsKey(key.toLowerCase())) {
                            keyWordMap.put(key, keyWordMap.get(key) + 1);
                        } else {
                            keyWordMap.put(key, 1);
                        }
                        alreadyCounted.add(key);
                    }
                }
            }
        }
    }

    public void sortWords() {
        Object[] mapSet = keyWordMap.entrySet().toArray();
        Arrays.sort(mapSet, new Comparator<Object>() {

            @Override
            public int compare(Object o1, Object o2) {
                Integer a = ((Map.Entry<String, Integer>) o1).getValue();
                Integer b = ((Map.Entry<String, Integer>) o2).getValue();
                return b.compareTo(a);
            }
        });

        for (int i = 0; i< Settings.numberOfKeyWordPerCategory; i++) {
            String element = mapSet[i].toString();
            finalKeyWordsList.add(element.substring(0, element.indexOf("=")));
        }

    }

    public LinkedList<String> getFinalKeyWordsList() {
        return finalKeyWordsList;
    }
}
