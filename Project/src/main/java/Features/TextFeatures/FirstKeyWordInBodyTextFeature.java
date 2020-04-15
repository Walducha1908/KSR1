package Features.TextFeatures;

import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class FirstKeyWordInBodyTextFeature implements TextFeature {
    public LinkedList<String> calculateFeature(Article article) {
        LinkedList<String> featureValues = new LinkedList<String>();
        for (int i = 0; i< Settings.categoryItemsList.size(); i++) {
            String featureValue = "";
            for (int j = 0; j < article.getBody().size(); j++) {
                for (int k = 0; k < article.getBody().get(j).size(); k++) {
                    String word = article.getBody().get(j).get(k);
                    if (KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).contains(word)) {
                        featureValue = word;
                        break;
                    }
                }
                if (!featureValue.isEmpty()) {
                    break;
                }
            }
            featureValues.add(featureValue);
        }
        return featureValues;
    }
}
