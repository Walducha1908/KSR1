package Calculations.Features;

import Features.*;
import Main.Settings;
import Model.Testing.TestingArticleContainer;
import Model.Training.TrainingArticle;
import Model.Training.TrainingArticleContainer;

import java.util.Collections;
import java.util.LinkedList;

public class FeaturesNormaliser {
    LinkedList<Double> featureDivisors;

    public FeaturesNormaliser() {
        this.featureDivisors = new LinkedList<Double>();
    }

    public void normalizeAllFeatures() {

        prepareAllFeaturesDivisors();

        for (int i = 0; i < TrainingArticleContainer.trainingArticlesList.size(); i++) {
            LinkedList<Double> normalisedFeatures = new LinkedList<Double>();
            for (int j = 0; j < TrainingArticleContainer.trainingArticlesList.get(i).getFeatures().size(); j++) {
                normalisedFeatures.add(TrainingArticleContainer.trainingArticlesList.get(i).getFeatures().get(j)
                        / featureDivisors.get(j));
            }
            TrainingArticleContainer.trainingArticlesList.get(i).setFeatures(normalisedFeatures);
        }

        for (int i = 0; i < TestingArticleContainer.testingArticlesList.size(); i++) {
            LinkedList<Double> normalisedFeatures = new LinkedList<Double>();
            for (int j = 0; j < TestingArticleContainer.testingArticlesList.get(i).getFeatures().size(); j++) {
                normalisedFeatures.add(TestingArticleContainer.testingArticlesList.get(i).getFeatures().get(j)
                        / featureDivisors.get(j));
            }
            TestingArticleContainer.testingArticlesList.get(i).setFeatures(normalisedFeatures);
        }

    }

    public void prepareAllFeaturesDivisors() {
        for (int i = 0; i < Settings.featuresOrder.size(); i++) {
            String currentFeatureName = Settings.featuresOrder.get(i);
            if (Settings.featuresUsedMap.get(currentFeatureName)) {
                featureDivisors.addAll(prepareFeatureDivisors(currentFeatureName));
            }
        }
    }

    public LinkedList<Double> prepareFeatureDivisors(String featureName) {
        LinkedList<Double> divisorsList = new LinkedList<Double>();
        int featureOffset = calculateFeatureOffset(featureName);

        for (int j = 0; j < Settings.categoryItemsList.size(); j++) {
            LinkedList<Double> featureValuesList = new LinkedList<Double>();
            for (int i = 0; i < TrainingArticleContainer.trainingArticlesList.size(); i++) {
                featureValuesList.add(TrainingArticleContainer.trainingArticlesList.get(i).getFeatures().get(featureOffset + j));
            }
            for (int i = 0; i < TestingArticleContainer.testingArticlesList.size(); i++) {
                featureValuesList.add(TestingArticleContainer.testingArticlesList.get(i).getFeatures().get(featureOffset + j));
            }
            divisorsList.add(Collections.max(featureValuesList));
        }

        return divisorsList;
    }

    public int calculateFeatureOffset(String featureName) {

        int featureOffset = 0;
        int categoryValuesSize = Settings.categoryItemsList.size();

        for (int i = 0; i < Settings.featuresOrder.size(); i++) {
            String currentSettingName = Settings.featuresOrder.get(i);
            if (currentSettingName == featureName) {
                break;
            }
            if (Settings.featuresUsedMap.get(currentSettingName)) {
                featureOffset += categoryValuesSize;
            }
        }
        return featureOffset;
    }
}
