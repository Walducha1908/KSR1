package Calculations.Features;

import Features.*;
import Main.Settings;
import Model.Article;
import Model.ArticleContainer;
import Model.Training.TrainingArticle;
import Model.Training.TrainingArticleContainer;

import java.util.LinkedList;

public class FeaturesExtractor {
    TrainingArticleContainer trainingArticleContainer;

    public FeaturesExtractor() {
        this.trainingArticleContainer = new TrainingArticleContainer();
    }

    public TrainingArticleContainer extractAllFeaturesForAllTrainingArticles() {

        for(int i = 0; i < ArticleContainer.articlesToTrainList.size(); i++) {
            LinkedList<Double> featuresList = extractAllFeaturesForArticle(ArticleContainer.articlesToTrainList.get(i));
            trainingArticleContainer.createTrainingArticle(ArticleContainer.articlesToTrainList.get(i), featuresList);
        }

        return trainingArticleContainer;
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
