package Calculations.Measures;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

public interface Measure {
    public ArticleDistance calculateMeasure(TrainingArticle trainingArticle, TestingArticle testingArticle);
}
