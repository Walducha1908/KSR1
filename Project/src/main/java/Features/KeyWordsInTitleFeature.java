package Features;

import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class KeyWordsInTitleFeature implements Feature {
    @Override
    public LinkedList<Double> calculateFeature(Article article) {
        LinkedList<Double> featureValues = new LinkedList<Double>();
        for (int i = 0; i< Settings.categoryItemsList.size(); i++) {
            double featureValue = 0;
            for (int j = 0; j < article.getTitle().size(); j++) {
                String word = article.getTitle().get(j);
                if (KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).contains(word)) {
                    featureValue += (1 * KeyWordsContainer.keyWordsWagesMap.get(word));
                }
            }
            featureValues.add(featureValue);
        }
        return featureValues;
    }
}
