package Model;

import java.util.LinkedList;

public class ArticleContainer {
    public static LinkedList<Article> articlesList;
    public static LinkedList<Article> articlesToTrainList;
    public static LinkedList<Article> articlesToTestList;

    public ArticleContainer() {
        this.articlesList = new LinkedList<Article>();
        this.articlesToTrainList = new LinkedList<Article>();
        this.articlesToTestList = new LinkedList<Article>();
    }

    public void createArticle(LinkedList<String> places,
                              LinkedList<String> topics,
                              LinkedList<String> title,
                              LinkedList<String> dateline,
                              LinkedList<LinkedList<String>> body) {

        Article article = new Article(places, topics, title, dateline, body);
        articlesList.add(article);
    }

    public void splitForTrainingAndTestingLists (double percentOfTraining) {
        int trainingListSize = (int) (articlesList.size()*percentOfTraining);

        for (int i=0; i<trainingListSize; i++) {
            articlesToTrainList.add(articlesList.get(i));
        }
        for (int i=trainingListSize; i<articlesList.size(); i++) {
            articlesToTestList.add(articlesList.get(i));
        }

    }
}
