package Calculations.Measures;

import Model.ArticleDistance;
import Model.Testing.TestingArticle;
import Model.Training.TrainingArticle;

public class TFM implements Measure {
    @Override
    public ArticleDistance calculateMeasure(TrainingArticle trainingArticle, TestingArticle testingArticle) {
        double distance = 0;
        double up = 0;
        double down = 0;
        double trainingPower = 0;
        double testingPower = 0;

        for (int i = 0; i < trainingArticle.getFeatures().size(); i++) {
            up += (trainingArticle.getFeatures().get(i) * testingArticle.getFeatures().get(i));
            trainingPower += Math.pow(trainingArticle.getFeatures().get(i), 2);
            testingPower += Math.pow(testingArticle.getFeatures().get(i), 2);
        }
        down = Math.sqrt(trainingPower * testingPower);
        distance = 1 - (up/down);

        ArticleDistance articleDistance = new ArticleDistance(trainingArticle.getCategoryValue(), distance);
        return articleDistance;
    }
}
