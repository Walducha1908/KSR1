package Calculations.Measures;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

public class Bigram implements Ngram {
    public ArticleDistance calculateMeasure(TrainingArticle trainingArticle, TestingArticle testingArticle) {
        double result = 0;
        for (int i = 0; i < trainingArticle.getTextFeatures().size(); i++) {
            result += calculateNgram(trainingArticle.getTextFeatures().get(i), testingArticle.getTextFeatures().get(i));
        }
        result *= -1;
        ArticleDistance articleDistance = new ArticleDistance(trainingArticle.getCategoryValue(), result);
        return articleDistance;
    }

    public double calculateNgram(String a, String b) {
        double value = 0;
        if (Math.min(a.length(), b.length()) < 2) {
            return 0;
        }
        int n = Math.max(a.length(), b.length()) - 1;
        int m = Math.min(a.length(), b.length()) - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a.length() >= b.length()) {
                    if (a.substring(i, i + 2).equals(b.substring(j, j + 2))) {
                        value++;
                    }
                } else {
                    if (b.substring(i, i + 2).equals(a.substring(j, j + 2))) {
                        value++;
                    }
                }
            }
        }
        return (value / (double) n);
    }
}
