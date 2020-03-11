package Model;

import Main.Settings;

import java.util.HashMap;
import java.util.LinkedList;

public class ResultSet {
    public double numberOfCorrectSelections;
    public double numberOfIncorrectSelections;
    public double[][] positiveNegativeArray;

    private double accuracy;
    private HashMap<String, Double> recallRatioList;
    private HashMap<String, Double> precisionRatioList;

    public ResultSet() {
        this.numberOfCorrectSelections = 0;
        this.numberOfIncorrectSelections = 0;
        this.positiveNegativeArray = new double[Settings.categoryItemsList.size()][Settings.categoryItemsList.size()];

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            for (int j = 0; j < Settings.categoryItemsList.size(); j++) {
                this.positiveNegativeArray[i][j] = 0;
            }
        }

        this.recallRatioList = new HashMap<String, Double>();
        this.precisionRatioList = new HashMap<String, Double>();
    }

    public double calculateAccuracy() {
        double result = ((numberOfCorrectSelections / (numberOfCorrectSelections + numberOfIncorrectSelections)) * 100);
        accuracy = result;
        return accuracy;
    }

    public void calculatePrecision() {

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            double sum = 0;
            double correct = 0;

            for (int j = 0; j < Settings.categoryItemsList.size(); j++) {
                sum += positiveNegativeArray[i][j];
                if (i == j) {
                    correct = positiveNegativeArray[i][j];
                }
            }
            double result = correct / sum;
            precisionRatioList.put(Settings.categoryItemsList.get(i), result);
        }
    }

    public void calculateRecall() {

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            double sum = 0;
            double correct = 0;

            for (int j = 0; j < Settings.categoryItemsList.size(); j++) {
                sum += positiveNegativeArray[j][i];
                if (i == j) {
                    correct = positiveNegativeArray[j][i];
                }
            }
            double result = correct / sum;
            recallRatioList.put(Settings.categoryItemsList.get(i), result);
        }
    }

    public double getAccuracy() {
        return accuracy;
    }

    public HashMap<String, Double> getRecallRatioList() {
        return recallRatioList;
    }

    public HashMap<String, Double> getPrecisionRatioList() {
        return precisionRatioList;
    }
}
