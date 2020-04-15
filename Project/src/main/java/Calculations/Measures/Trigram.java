package Calculations.Measures;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

import java.util.LinkedList;

public class Trigram implements Ngram{
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
        if (Math.min(a.length(), b.length()) < 3) {
            return 0;
        }
        int n = Math.max(a.length(), b.length()) - 2;
        int m = Math.min(a.length(), b.length()) - 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a.length() >= b.length()) {
                    if (a.substring(i, i + 3).equals(b.substring(j, j + 3))) {
                        value++;
                    }
                } else {
                    if (b.substring(i, i + 3).equals(a.substring(j, j + 3))) {
                        value++;
                    }
                }
            }
        }
        return (value / (double) n);
    }

    public static double calculateMeasure(LinkedList<String> keyWordsList, String word) {
        double result = 0;
        for (int k = 0; k < keyWordsList.size(); k++) {
            double value = 0;
            String key = keyWordsList.get(k);
            if (Math.min(key.length(), word.length()) < 3) {
                continue;
            }
            int n = Math.max(key.length(), word.length()) - 2;
            int m = Math.min(key.length(), word.length()) - 2;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (key.length() >= word.length()) {
                        if (key.substring(i, i + 3).equals(word.substring(j, j + 3))) {
                            value++;
                        }
                    } else {
                        if (word.substring(i, i + 3).equals(key.substring(j, j + 3))) {
                            value++;
                        }
                    }
                }
            }
            value = (value / (double) n);
            result += value;
        }
//        System.out.println(result);
        return result;
    }
}
