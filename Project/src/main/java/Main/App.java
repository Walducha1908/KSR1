package Main;

import Data.DataReader;
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
//        System.out.println(ArticleContainer.articlesList.getLast());

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writeValue(new File("../Data/plik.json"), ArticleContainer.articlesList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
