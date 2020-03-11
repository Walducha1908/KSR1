package Calculations.Metrics;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

public class ChebyshevMetrics implements Metrics {
    @Override
    public ArticleDistance calculateDistance(TrainingArticle trainingArticle, TestingArticle testingArticle) {
        double distance = 0;

        for (int i = 0; i < trainingArticle.getFeatures().size(); i++) {
            double currentDistance = Math.abs(trainingArticle.getFeatures().get(i) - testingArticle.getFeatures().get(i));
            if (distance < currentDistance) {
                distance = currentDistance;
            }
        }

        ArticleDistance articleDistance = new ArticleDistance(trainingArticle.getCategoryValue(), distance);
        return articleDistance;
    }
}
