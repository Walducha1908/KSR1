package Main;

import Calculations.Features.FeaturesExtractor;
import Calculations.Features.FeaturesNormaliser;
import Calculations.KNN.MainAlgorithm;
import Calculations.KeyWords.KeyWordsCounter;
import Calculations.KeyWords.KeyWordsWagesCounter;
import Data.DataReader;
import Data.DataWriter;
import Model.ArticleContainer;
import Model.KeyWordsContainer;
import Model.ResultSet;
import Model.Testing.TestingArticleContainer;
import Model.Training.TrainingArticleContainer;

import java.util.Set;

public class Manager {

    public static void start() {
        readAndPrepareData();

        for (int j = 0; j < 2; j++) {
            if (j == 1) {
                Settings.metrics = "Manhattan";
            }
            for (int i = 0; i < 10; i++) {
                if (j == 0 && i < 7) {
                    continue;
                }
                if (i == 1) {
                    Settings.k = 3;
                } else if (i == 2) {
                    Settings.k = 4;
                } else if (i == 3) {
                    Settings.k = 6;
                } else if (i == 4) {
                    Settings.k = 8;
                } else if (i == 5) {
                    Settings.k = 10;
                } else if (i == 6) {
                    Settings.k = 12;
                } else if (i == 7) {
                    Settings.k = 14;
                } else if (i == 8) {
                    Settings.k = 17;
                } else if (i == 9) {
                    Settings.k = 20;
                }
                System.out.println(Settings.k);
                countKeyWords();
                extractFeatures();
                normaliseFeatures();
                runKNN();
            }
        }
    }

    public static void readAndPrepareData() {
        System.out.println("");
        System.out.println( "Started reading data..." );
        DataReader dataReader = new DataReader(Settings.numberOfFiles, Settings.pathToData, Settings.category);
        System.out.println(ArticleContainer.articlesList.size() + " articles have been created!");
        System.out.println(ArticleContainer.articlesToTrainList.size() + " training articles have been created!");
        System.out.println(ArticleContainer.articlesToTestList.size() + " testing articles have been created!");
        System.out.println("");
    }

    public static void countKeyWords() {
        KeyWordsContainer keyWordsContainer = new KeyWordsContainer();
        for (int i = 0; i<Settings.categoryItemsList.size(); i++) {
            KeyWordsCounter keyWordsCounter = new KeyWordsCounter(Settings.category, Settings.categoryItemsList.get(i));
            keyWordsContainer.keyWordsMap.put(Settings.categoryItemsList.get(i), keyWordsCounter.getFinalKeyWordsList());

            System.out.println(Settings.categoryItemsList.get(i) + " key words list: " +
                    KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)));
        }
        KeyWordsWagesCounter keyWordsWagesCounter = new KeyWordsWagesCounter();
        keyWordsWagesCounter.calculateWages();
    }

    public static void extractFeatures() {
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
    }

    public static void normaliseFeatures() {
        FeaturesNormaliser normaliser = new FeaturesNormaliser();
        normaliser.normalizeAllFeatures();

        System.out.println("");
        System.out.println("All features of " + TrainingArticleContainer.trainingArticlesList.size() + " training articles " +
                "have been normalised!");
        System.out.println("All features of " + TestingArticleContainer.testingArticlesList.size() + " testing articles " +
                "have been normalised!");
    }

    public static void runKNN() {
        System.out.println("");
        System.out.println("Classification started...");
        MainAlgorithm mainAlgorithm = new MainAlgorithm();
        ResultSet resultSet = mainAlgorithm.selectBestNeighbourForAllTestArticles();

        resultSet.calculatePrecision();
        resultSet.calculateRecall();
        resultSet.calculateAccuracy();

        System.out.println("");
        System.out.println("Classification ended!");
        System.out.println("Correct classification number: " + resultSet.numberOfCorrectSelections);
        System.out.println("Incorrect classification number: " + resultSet.numberOfIncorrectSelections);
        System.out.println("Accuracy: " + resultSet.getAccuracy());
        System.out.println("");

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            System.out.println("Recall for " + Settings.categoryItemsList.get(i) + ": " +
                    resultSet.getRecallRatioList().get(Settings.categoryItemsList.get(i)));
            System.out.println("Precision for " + Settings.categoryItemsList.get(i) + ": " +
                    resultSet.getPrecisionRatioList().get(Settings.categoryItemsList.get(i)));
        }

        DataWriter.writeData(resultSet);
    }


}
