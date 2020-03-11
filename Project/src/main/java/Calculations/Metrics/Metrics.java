package Calculations.Metrics;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

public interface Metrics {
    public ArticleDistance calculateDistance(TrainingArticle trainingArticle, TestingArticle testingArticle);
}
