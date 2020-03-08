package Features;

import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class KeyWordsInLastParagraphFeature implements Feature {
    @Override
    public LinkedList<Double> calculateFeature(Article article) {
        LinkedList<Double> featureValues = new LinkedList<Double>();
        for (int i = 0; i< Settings.categoryItemsList.size(); i++) {
            double featureValue = 0;
            if (article.getBody().size() > 0) {
                for (int k = 0; k < article.getBody().getLast().size(); k++) {
                    String word = article.getBody().getLast().get(k);
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
