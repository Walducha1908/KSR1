package Main;

import Calculations.Features.FeaturesExtractor;
import Calculations.Features.FeaturesNormaliser;
import Calculations.KNN.MainAlgorithm;
import Data.DataReader;
import Calculations.KeyWords.KeyWordsCounter;
import Features.KeyWordsInBodyFeature;
import Model.ArticleContainer;
import Model.KeyWordsContainer;
import Model.ResultSet;
import Model.Testing.TestingArticleContainer;
import Model.Training.TrainingArticle;
import Model.Training.TrainingArticleContainer;

import java.util.LinkedList;

public class App 
{
    public static void main (String[] args) {
        System.out.println("");
        System.out.println( "Started reading data..." );
        DataReader dataReader = new DataReader(Settings.numberOfFiles, Settings.pathToData, Settings.category);
        System.out.println(ArticleContainer.articlesList.size() + " articles have been created!");
        System.out.println(ArticleContainer.articlesToTrainList.size() + " training articles have been created!");
        System.out.println(ArticleContainer.articlesToTestList.size() + " testing articles have been created!");
        System.out.println("");

        KeyWordsContainer keyWordsContainer = new KeyWordsContainer();
        for (int i = 0; i<Settings.categoryItemsList.size(); i++) {
            KeyWordsCounter keyWordsCounter = new KeyWordsCounter(Settings.category, Settings.categoryItemsList.get(i));
            keyWordsContainer.keyWordsMap.put(Settings.categoryItemsList.get(i), keyWordsCounter.getFinalKeyWordsList());

            System.out.println(Settings.categoryItemsList.get(i) + " key words list: " +
                    KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)));
        }

        FeaturesExtractor featuresExtractor = new FeaturesExtractor();
        TrainingArticleContainer trainingArticleContainer = featuresExtractor.extractAllFeaturesForAllTrainingArticles();
        System.out.println("");
        System.out.println(trainingArticleContainer.trainingArticlesList.getFirst().getFeatures().size() +
                " features for all " + trainingArticleContainer.trainingArticlesList.size()
                + " training articles have been extracted!");

        TestingArticleContainer testingArticleContainer = featuresExtractor.extractAllFeaturesForAllTestingArticles();
        System.out.println(testingArticleContainer.testingArticlesList.getFirst().getFeatures().size() +
                " features for all " + testingArticleContainer.testingArticlesList.size()
                + " testing articles have been extracted!");

        FeaturesNormaliser normaliser = new FeaturesNormaliser();
        normaliser.normalizeAllFeatures();

        System.out.println("");
        System.out.println("All features of " + TrainingArticleContainer.trainingArticlesList.size() + " training articles " +
                "have been normalised!");
        System.out.println("All features of " + TestingArticleContainer.testingArticlesList.size() + " testing articles " +
                "have been normalised!");

        MainAlgorithm mainAlgorithm = new MainAlgorithm();
        ResultSet resultSet = mainAlgorithm.selectBestNeighbourForAllTestArticles();
        System.out.println("Classification ended!");
        System.out.println("Correct classification number: " + resultSet.numberOfCorrectSelections);
        System.out.println("Incorrect classification number: " + resultSet.numberOfIncorrectSelections);
    }
}
