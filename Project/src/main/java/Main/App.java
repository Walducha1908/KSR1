package Main;

import Data.DataReader;
import Features.KeyWordsCounter;
import Model.ArticleContainer;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Started reading data..." );
        DataReader dataReader = new DataReader(Settings.numberOfFiles, Settings.pathToData, Settings.category);
        System.out.println(ArticleContainer.articlesList.size() + " articles has been read and created!");

        KeyWordsCounter keyWordsCounter = new KeyWordsCounter(Settings.category, "usa");
    }
}
