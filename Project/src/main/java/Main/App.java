package Main;

import Calculations.Features.FeaturesExtractor;
import Data.DataReader;
import Calculations.KeyWords.KeyWordsCounter;
import Features.KeyWordsInBodyFeature;
import Model.ArticleContainer;
import Model.KeyWordsContainer;
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

    }
}
