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
import java.util.*;

public class MainAlgorithm {
    ResultSet resultSet;

    public MainAlgorithm() {
        this.resultSet = new ResultSet();
    }

    public ResultSet selectBestNeighbourForAllTestArticles() {

        for (int i = 0; i < TestingArticleContainer.testingArticlesList.size(); i++) {
            TestingArticleContainer.testingArticlesList.get(i).setCalculatedCategoryValue(
                    selectBestNeighbourForArticle(TestingArticleContainer.testingArticlesList.get(i)));

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
        HashMap<Integer, Integer> categoryValuesMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            categoryValuesMap.put(i, 0);
        }
        for (int i = 0; i < Settings.k; i++) {
            int key = distances.get(i).getCategoryValue();
            if (categoryValuesMap.containsKey(distances.get(i).getCategoryValue())) {
                categoryValuesMap.put(key, categoryValuesMap.get(key) + 1);
            }
            else {
                System.out.println("error");
            }
        }

        int maxNumberOfCategoryValue = 0;
        int numberOfMaxCategoryValues = 0;
        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            if (categoryValuesMap.get(i) > maxNumberOfCategoryValue) {
                maxNumberOfCategoryValue = categoryValuesMap.get(i);
                numberOfMaxCategoryValues = 1;
            } else if (categoryValuesMap.get(i) == maxNumberOfCategoryValue) {
                numberOfMaxCategoryValues += 1;
            }
        }

        if (numberOfMaxCategoryValues == 1) {
            for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
                if (categoryValuesMap.get(i) == maxNumberOfCategoryValue) {
                    return i;
                }
            }
        } else if (numberOfMaxCategoryValues > 1) {
            LinkedList<Integer> listOfMaxCategoryValues = new LinkedList<Integer>();

            for (int i = 0; i < categoryValuesMap.size(); i++) {
                if (categoryValuesMap.get(i) == maxNumberOfCategoryValue) {
                    listOfMaxCategoryValues.add(i);
                }
            }

            return closestFromGivenCategoryValues(listOfMaxCategoryValues, distances);
        }

        return -1;
    }

    public int closestFromGivenCategoryValues(LinkedList<Integer> listOfMaxCategoryValues, LinkedList<ArticleDistance> distances) {

        for (int i = 0; i < Settings.k; i++) {
            if (listOfMaxCategoryValues.contains(distances.get(i).getCategoryValue())) {
                return distances.get(i).getCategoryValue();
            }

        }

        return -1;
    }
}
