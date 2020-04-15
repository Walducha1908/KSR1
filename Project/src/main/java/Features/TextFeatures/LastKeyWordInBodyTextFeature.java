package Features.TextFeatures;

import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class LastKeyWordInBodyTextFeature implements TextFeature {
    public LinkedList<String> calculateFeature(Article article) {
        LinkedList<String> featureValues = new LinkedList<String>();
        for (int i = 0; i< Settings.categoryItemsList.size(); i++) {
            String featureValue = "";
            for (int j = article.getBody().size() - 1; j >= 0; j--) {
                for (int k = article.getBody().get(j).size() - 1; k >= 0; k--) {
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
