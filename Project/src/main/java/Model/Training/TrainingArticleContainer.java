package Model.Training;

import Main.Settings;
import Model.Article;
import Model.Testing.TestingArticle;

import java.util.LinkedList;

public class TrainingArticleContainer {
    public static LinkedList<TrainingArticle> trainingArticlesList;

    public TrainingArticleContainer() {
        this.trainingArticlesList = new LinkedList<TrainingArticle>();
    }

    public void createTrainingArticle(Article article, LinkedList<Double> featuresList, LinkedList<String> textFeaturesList) {
        if (!Settings.ngram) {
            TrainingArticle trainingArticle = new TrainingArticle(article, featuresList);
            trainingArticlesList.add(trainingArticle);
        } else {
            TrainingArticle trainingArticle = new TrainingArticle(article, textFeaturesList, true);
            trainingArticlesList.add(trainingArticle);
        }
    }
}
