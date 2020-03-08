package Model.Training;

import Model.Article;

import java.util.LinkedList;

public class TrainingArticleContainer {
    public static LinkedList<TrainingArticle> trainingArticlesList;

    public TrainingArticleContainer() {
        this.trainingArticlesList = new LinkedList<TrainingArticle>();
    }

    public void createTrainingArticle(Article article, LinkedList<Double> featuresList) {
        TrainingArticle trainingArticle = new TrainingArticle(article, featuresList);
        trainingArticlesList.add(trainingArticle);
    }
}
