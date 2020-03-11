package Calculations.KNN;

import Calculations.Metrics.ChebyshevMetrics;
import Calculations.Metrics.EuclideanMetrics;
import Main.Settings;
import Model.ArticleDistance;
import Model.ResultSet;
import Model.Testing.TestingArticle;
import Model.Testing.TestingArticleContainer;
import Model.Training.TrainingArticle;
import Model.Training.TrainingArticleContainer;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class MainAlgorithm {
    ResultSet resultSet;

    public MainAlgorithm() {
        this.resultSet = new ResultSet();
    }

    public ResultSet selectBestNeighbourForAllTestArticles() {

        for (int i = 0; i < TestingArticleContainer.testingArticlesList.size(); i++) {
            TestingArticleContainer.testingArticlesList.get(i).setCalculatedCategoryValue(
                    selectBestNeighbourForArticle(TestingArticleContainer.testingArticlesList.get(i)));

//            System.out.println(selectBestNeighbourForArticle(TestingArticleContainer.testingArticlesList.get(i)));
            resultSet.positiveNegativeArray
                    [TestingArticleContainer.testingArticlesList.get(i).getCalculatedCategoryValue()]
                    [TestingArticleContainer.testingArticlesList.get(i).getTrueCategoryValue()] += 1;

            if (TestingArticleContainer.testingArticlesList.get(i).compareCategoryValues()) {
                resultSet.numberOfCorrectSelections += 1;
            } else {
                resultSet.numberOfIncorrectSelections += 1;
            }
            if (i % 100 == 0) {
                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println("Correct: " + resultSet.numberOfCorrectSelections +
                        " Incorrect: " + resultSet.numberOfIncorrectSelections +
                        " Accuracy " + df.format(resultSet.calculateAccuracy()) + "%");
            }
        }
        return resultSet;
    }

    public int selectBestNeighbourForArticle(TestingArticle testingArticle) {
        LinkedList<ArticleDistance> distances = new LinkedList<ArticleDistance>();

        for (int i = 0; i < TrainingArticleContainer.trainingArticlesList.size(); i++) {

            if (Settings.metrics == "Euclidean") {
                EuclideanMetrics metrics = new EuclideanMetrics();
                distances.add(metrics.calculateDistance(TrainingArticleContainer.trainingArticlesList.get(i), testingArticle));
            } else if (Settings.metrics == "Chebyshev") {
                ChebyshevMetrics metrics = new ChebyshevMetrics();
                distances.add(metrics.calculateDistance(TrainingArticleContainer.trainingArticlesList.get(i), testingArticle));
            }

        }

        return sortDistancesAndGetBestNeighbour(distances);
    }

    public int sortDistancesAndGetBestNeighbour(LinkedList<ArticleDistance> distances) {
        Collections.sort(distances);
        int[] categoryValuesArray = new int[Settings.k];
        int[] categoryValuesArrayToSort = new int[Settings.k];

        for (int i = 0; i < Settings.k; i++) {
            categoryValuesArray[i] = 0;
            categoryValuesArrayToSort[i] = 0;
        }
        for (int i = 0; i < Settings.k; i++) {
            categoryValuesArray[distances.get(i).getCategoryValue()] += 1;
            categoryValuesArrayToSort[distances.get(i).getCategoryValue()] += 1;
        }

        System.out.println(categoryValuesArray[0] + ", " + categoryValuesArray[1] + ", " +
                categoryValuesArray[2] + ", " + categoryValuesArray[3] + ", " + categoryValuesArray[4]);

        Arrays.sort(categoryValuesArrayToSort);

        if (categoryValuesArrayToSort[0] == categoryValuesArrayToSort[1]) {
            return distances.get(0).getCategoryValue();
        }
        for (int i = 0; i < Settings.k; i++) {
            if (categoryValuesArray[i] == categoryValuesArrayToSort[i]) {
                return i;
            }
        }

        return -1;
    }
}
