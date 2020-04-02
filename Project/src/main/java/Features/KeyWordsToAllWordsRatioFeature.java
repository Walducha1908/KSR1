package Features;

import Calculations.Measures.Trigram;
import Main.Settings;
import Model.Article;
import Model.KeyWordsContainer;

import java.util.LinkedList;

public class KeyWordsToAllWordsRatioFeature implements Feature {
    @Override
    public LinkedList<Double> calculateFeature(Article article) {
        LinkedList<Double> featureValues = new LinkedList<Double>();
        for (int i = 0; i< Settings.categoryItemsList.size(); i++) {
            double numberOfKeyWords = 0;
            double numberOfAllWords = 0;
            for (int j = 0; j < article.getBody().size(); j++) {
                for (int k = 0; k < article.getBody().get(j).size(); k++) {
                    String word = article.getBody().get(j).get(k);
                    if (KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).contains(word)) {
                        if (!Settings.ngram) {
                            numberOfKeyWords += (1 * KeyWordsContainer.keyWordsWagesMap.get(word));
                        } else {
                            numberOfKeyWords += Trigram.calculateMeasure(KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)), word);
                        }
                    }
                    if (Settings.wages) {
                        numberOfAllWords += Settings.maxWage;
                    } else {
                        numberOfAllWords += 1;
                    }
                }
            }
            if (numberOfAllWords > 0) {
                featureValues.add(numberOfKeyWords / numberOfAllWords);
            } else {
                featureValues.add(0.0);
            }
        }
        return featureValues;
    }
}
