package Features;

import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class KeyWordsInFirst10PerCentWordsFeature implements Feature {

    @Override
    public LinkedList<Double> calculateFeature(Article article) {
        LinkedList<Double> featureValues = new LinkedList<Double>();

        int numberOfAllWords = 0;
        for (int i = 0; i < article.getBody().size(); i++) {
            numberOfAllWords += article.getBody().get(i).size();
        }
        int lastWordIndex = (int) (0.1 * (double) numberOfAllWords);

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            double featureValue = 0;
            int numberOfWords = 0;

            for (int j = 0; j < article.getBody().size() && numberOfWords < lastWordIndex; j++) {
                for (int k = 0; k < article.getBody().get(j).size() && numberOfWords < lastWordIndex; k++) {
                    String word = article.getBody().get(j).get(k);
                    if (KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).contains(word)) {
                        featureValue += 1;
                    }
                    numberOfWords += 1;
                }
            }
            featureValues.add(featureValue);
        }
        return featureValues;
    }
}
