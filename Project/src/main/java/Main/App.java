package Main;

import Data.DataReader;
import Model.ArticleContainer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Started reading data..." );
        DataReader dataReader = new DataReader(22, "../Data/");
        System.out.println(ArticleContainer.articlesList.size() + " articles has been read and created!");
    }
}
