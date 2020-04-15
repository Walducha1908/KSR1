package Features.NumberFeatures;

import Calculations.Measures.Trigram;
import Features.NumberFeatures.Feature;
import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class KeyWordsInFirst50WordsFeature implements Feature {
    @Override
    public LinkedList<Double> calculateFeature(Article article) {
        LinkedList<Double> featureValues = new LinkedList<Double>();
        for (int i = 0; i< Settings.categoryItemsList.size(); i++) {
            double featureValue = 0;
            int numberOfWords = 0;
            for (int j = 0; j < article.getBody().size() && numberOfWords < 50; j++) {
                for (int k = 0; k < article.getBody().get(j).size() && numberOfWords < 50; k++) {
                    String word = article.getBody().get(j).get(k);
                    if (KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).contains(word)) {
                        if (!Settings.ngramBeforeExtraction) {
                            featureValue += (1 * KeyWordsContainer.keyWordsWagesMap.get(word));
                        } else {
                            featureValue += Trigram.calculateMeasure(KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)), word);
                        }
                    }
                    numberOfWords += 1;
                }
            }
            featureValues.add(featureValue);
        }
        return featureValues;
    }
}
