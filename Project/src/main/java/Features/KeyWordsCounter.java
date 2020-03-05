package Features;

import Model.Article;
import Model.ArticleContainer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class KeyWordsCounter {
    private HashMap<String, Integer> keyWordMap;
    private String category;
    private String categoryValue;
    private LinkedList<Article> articlesToSearch;

    public KeyWordsCounter(String category, String categoryValue) {
        this.category = category;
        this.categoryValue = categoryValue;
        this.keyWordMap = new HashMap<String, Integer>();
        this.articlesToSearch = new LinkedList<Article>();

        getArticlesToSearch();
        countWords();
        System.out.println(keyWordMap);
        sortWords();
    }

    public void getArticlesToSearch() {
        for (int i=0; i< ArticleContainer.articlesList.size(); i++) {
            if (category == "places") {
                if (ArticleContainer.articlesList.get(i).getPlaces().contains(categoryValue)) {
                    articlesToSearch.add(ArticleContainer.articlesList.get(i));
                }
            }
        }
    }

    public void countWords() {
        for (int i=0; i<articlesToSearch.size(); i++) {
            for (int j=0; j<articlesToSearch.get(i).getBody().size(); j++) {
                for (int k=0; k<articlesToSearch.get(i).getBody().get(j).size(); k++) {
                    String key = articlesToSearch.get(i).getBody().get(j).get(k).toLowerCase();
                    if (keyWordMap.containsKey(key.toLowerCase())) {
                        keyWordMap.put(key, keyWordMap.get(key) + 1);
                    } else {
                        keyWordMap.put(key, 1);
                    }
                }
            }
        }
    }

    public void sortWords() {
//        Object[] mapSet = keyWordMap.entrySet().toArray();
//        Arrays.sort(mapSet, new Comparator() {
//
//            @Override
//            public int compare(Object o1, Object o2) {
//                return ((keyWordMap<String, Integer> o2).getValue()
//                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());;
//            }
//        });
    }
}
