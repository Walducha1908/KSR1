package Features.NumberFeatures;

import Calculations.Measures.Trigram;
import Features.NumberFeatures.Feature;
import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class KeyWordsInLast10PerCentWordsFeature implements Feature {
    @Override
    public LinkedList<Double> calculateFeature(Article article) {
        LinkedList<Double> featureValues = new LinkedList<Double>();

        int numberOfAllWords = 0;
        for (int i = 0; i < article.getBody().size(); i++) {
            numberOfAllWords += article.getBody().get(i).size();
        }
        int maxNumberOfWords = (int) (0.1 * (double) numberOfAllWords);

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            double featureValue = 0;
            int numberOfWords = 0;

            for (int j = article.getBody().size() - 1; j >= 0 && numberOfWords < maxNumberOfWords; j--) {
                for (int k = article.getBody().get(j).size() - 1; k >= 0 && numberOfWords < maxNumberOfWords; k--) {
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
