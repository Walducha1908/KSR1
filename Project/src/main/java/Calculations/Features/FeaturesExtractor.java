package Calculations.Features;

import Features.*;
import Main.Settings;
import Model.Article;
import Model.ArticleContainer;
import Model.Testing.TestingArticle;
import Model.Testing.TestingArticleContainer;
import Model.Training.TrainingArticle;
import Model.Training.TrainingArticleContainer;

import java.util.LinkedList;

public class FeaturesExtractor {
    TrainingArticleContainer trainingArticleContainer;
    TestingArticleContainer testingArticleContainer;

    public FeaturesExtractor() {
        this.trainingArticleContainer = new TrainingArticleContainer();
        this.testingArticleContainer = new TestingArticleContainer();
    }

    public TrainingArticleContainer extractAllFeaturesForAllTrainingArticles() {

        for(int i = 0; i < ArticleContainer.articlesToTrainList.size(); i++) {
            LinkedList<Double> featuresList = extractAllFeaturesForArticle(ArticleContainer.articlesToTrainList.get(i));
            trainingArticleContainer.createTrainingArticle(ArticleContainer.articlesToTrainList.get(i), featuresList);
        }

        return trainingArticleContainer;
    }

    public TestingArticleContainer extractAllFeaturesForAllTestingArticles() {

        for(int i = 0; i < ArticleContainer.articlesToTestList.size(); i++) {
            LinkedList<Double> featuresList = extractAllFeaturesForArticle(ArticleContainer.articlesToTestList.get(i));
            testingArticleContainer.createTestingArticle(ArticleContainer.articlesToTestList.get(i), featuresList);
        }

        return testingArticleContainer;
    }

    public LinkedList<Double> extractAllFeaturesForArticle(Article article) {
        LinkedList<Double> featuresList = new LinkedList<Double>();

        if (Settings.featuresUsedMap.get("Body")) {
            KeyWordsInBodyFeature feature = new KeyWordsInBodyFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("Title")) {
            KeyWordsInTitleFeature feature = new KeyWordsInTitleFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("Dateline")) {
            KeyWordsInDatelineFeature feature = new KeyWordsInDatelineFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("Ratio")) {
            KeyWordsToAllWordsRatioFeature feature = new KeyWordsToAllWordsRatioFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("First50Words")) {
            KeyWordsInFirst50WordsFeature feature = new KeyWordsInFirst50WordsFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("First10PerCent")) {
            KeyWordsInFirst10PerCentWordsFeature feature = new KeyWordsInFirst10PerCentWordsFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("First20PerCent")) {
            KeyWordsInFirst20PerCentWordsFeature feature = new KeyWordsInFirst20PerCentWordsFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("First50PerCent")) {
            KeyWordsInFirst50PerCentWordsFeature feature = new KeyWordsInFirst50PerCentWordsFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("FirstParagraph")) {
            KeyWordsInFirstParagraphFeature feature = new KeyWordsInFirstParagraphFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("Last50Words")) {
            KeyWordsInLast50WordsFeature feature = new KeyWordsInLast50WordsFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("Last10PerCent")) {
            KeyWordsInLast10PerCentWordsFeature feature = new KeyWordsInLast10PerCentWordsFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        if (Settings.featuresUsedMap.get("LastParagraph")) {
            KeyWordsInLastParagraphFeature feature = new KeyWordsInLastParagraphFeature();
            featuresList.addAll(feature.calculateFeature(article));
        }

        return featuresList;
    }
}
