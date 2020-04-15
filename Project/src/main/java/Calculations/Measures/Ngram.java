package Calculations.Measures;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

public interface Ngram {
    public ArticleDistance calculateMeasure(TrainingArticle trainingArticle, TestingArticle testingArticle);
    public double calculateNgram(String a, String b);
}
