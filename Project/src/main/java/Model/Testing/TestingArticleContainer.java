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

    public String getTrue() {
        int[] categoryNumbers = new int[Settings.categoryItemsList.size()];
        for (int i=0; i < Settings.categoryItemsList.size(); i++) {
            categoryNumbers[i] = 0;
        }
        for (int i = 0; i < testingArticlesList.size(); i++) {
            categoryNumbers[testingArticlesList.get(i).getTrueCategoryValue()] += 1;
        }
        String result = "";
        for (int i=0; i < Settings.categoryItemsList.size(); i++) {
            result += Settings.categoryItemsList.get(i) + " " + categoryNumbers[i] + "\n";
        }
        return result;
    }

    public static String getCalculated() {
        int[] categoryNumbers = new int[Settings.categoryItemsList.size()];
        for (int i=0; i < Settings.categoryItemsList.size(); i++) {
            categoryNumbers[i] = 0;
        }
        for (int i = 0; i < testingArticlesList.size(); i++) {
            categoryNumbers[testingArticlesList.get(i).getCalculatedCategoryValue()] += 1;
        }
        String result = "";
        for (int i=0; i < Settings.categoryItemsList.size(); i++) {
            result += Settings.categoryItemsList.get(i) + " " + categoryNumbers[i] + "\n";
        }
        return result;
    }
}
