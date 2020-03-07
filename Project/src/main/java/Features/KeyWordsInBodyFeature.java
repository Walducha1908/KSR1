package Features;

import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class KeyWordsInBodyFeature implements Feature {
    @Override
    public LinkedList<Double> calculateFeature(Article article) {
        LinkedList<Double> featureValues = new LinkedList<Double>();
        for (int i=0; i< Settings.categoryItemsList.size(); i++) {
            double featureValue = 0;
            for (int j = 0; j < article.getBody().size(); j++) {
                for (int k = 0; k < article.getBody().get(j).size(); k++) {
                    String word = article.getBody().get(j).get(k);
                    if (KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).contains(word)) {
                        featureValue += 1;
                    }
                }
            }
            featureValues.add(featureValue);
        }
        return featureValues;
    }
}
