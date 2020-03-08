package Main;

import Data.DataReader;
import Features.*;
import KeyWords.KeyWordsCounter;
import Model.ArticleContainer;
import Model.KeyWordsContainer;

public class App 
{
    public static void main (String[] args) {
        System.out.println( "Started reading data..." );
        DataReader dataReader = new DataReader(Settings.numberOfFiles, Settings.pathToData, Settings.category);
        System.out.println(ArticleContainer.articlesList.size() + " articles has been created!");
        System.out.println(ArticleContainer.trainingArticlesList.size() + " training articles has been created!");
        System.out.println(ArticleContainer.testingArticlesList.size() + " testing articles has been created!");

        System.out.println("");

        KeyWordsContainer keyWordsContainer = new KeyWordsContainer();
        for (int i = 0; i<Settings.categoryItemsList.size(); i++) {
            KeyWordsCounter keyWordsCounter = new KeyWordsCounter(Settings.category, Settings.categoryItemsList.get(i));
            keyWordsContainer.keyWordsMap.put(Settings.categoryItemsList.get(i), keyWordsCounter.getFinalKeyWordsList());

            System.out.println(Settings.categoryItemsList.get(i) + " key words list: " +
                    KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)));
        }

//        KeyWordsInLast10PerCentWordsFeature feature = new KeyWordsInLast10PerCentWordsFeature();
//        System.out.println(feature.calculateFeature(ArticleContainer.articlesList.get(ArticleContainer.articlesList.size() - 2)));
//        System.out.println(ArticleContainer.articlesList.get(ArticleContainer.articlesList.size() - 2).getBody());

    }
}
