package Features.TextFeatures;

import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.HashMap;
import java.util.LinkedList;

public class MostCommonKeyWordInBodyTextFeature implements TextFeature {
    public LinkedList<String> calculateFeature(Article article) {
        LinkedList<String> featureValues = new LinkedList<String>();
        for (int i = 0; i< Settings.categoryItemsList.size(); i++) {
            HashMap<String, Integer> keyWordsNumbers = new HashMap<String, Integer>();
            for (int j = 0; j < article.getBody().size(); j++) {
                for (int k = 0; k < article.getBody().get(j).size(); k++) {
                    String word = article.getBody().get(j).get(k);
                    if (keyWordsNumbers.containsKey(word)) {
                        keyWordsNumbers.put(word, keyWordsNumbers.get(word) + 1);
                    } else {
                        keyWordsNumbers.put(word, 1);
                    }
                }
            }

            String mostCommonWord = "";
            int mostCommonWordNumber = 0;
            for (int j = 0; j < KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).size(); j++) {
                if (keyWordsNumbers.containsKey(KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).get(j))) {
                    if (mostCommonWordNumber < keyWordsNumbers.get(KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).get(j))) {
                        mostCommonWordNumber = keyWordsNumbers.get(KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).get(j));
                        mostCommonWord = KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).get(j);
                    }
                }
            }
            featureValues.add(mostCommonWord);
        }
        return featureValues;
    }
}
