package Model.Testing;

import Main.Settings;
import Model.Article;
import Model.Training.TrainingArticle;

import java.util.LinkedList;

public class TestingArticleContainer {
    public static LinkedList<TestingArticle> testingArticlesList;

    public TestingArticleContainer() {
        this.testingArticlesList = new LinkedList<TestingArticle>();
    }

    public void createTestingArticle(Article article, LinkedList<Double> featuresList, LinkedList<String> textFeaturesList) {
        if (!Settings.ngram) {
            TestingArticle testingArticle = new TestingArticle(article, featuresList);
            testingArticlesList.add(testingArticle);
        } else {
            TestingArticle testingArticle = new TestingArticle(article, textFeaturesList, true);
            testingArticlesList.add(testingArticle);
        }
    }
}
