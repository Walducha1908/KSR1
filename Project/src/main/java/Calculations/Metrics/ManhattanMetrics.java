package Calculations.Metrics;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

public class ManhattanMetrics implements Metrics {
    @Override
    public ArticleDistance calculateDistance(TrainingArticle trainingArticle, TestingArticle testingArticle) {
        double distance = 0;

        for (int i = 0; i < trainingArticle.getFeatures().size(); i++) {
            distance += Math.abs(trainingArticle.getFeatures().get(i) - testingArticle.getFeatures().get(i));
        }

        ArticleDistance articleDistance = new ArticleDistance(trainingArticle.getCategoryValue(), distance);
        return articleDistance;
    }
}
