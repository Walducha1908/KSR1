package Model;

import java.awt.*;
import java.util.LinkedList;

public class ArticleContainer {
    public static LinkedList<Article> articlesList;

    public ArticleContainer() {
        this.articlesList = new LinkedList<Article>();
    }

    public void createArticle(LinkedList<String> places,
                              LinkedList<String> topics,
                              LinkedList<String> title,
                              LinkedList<String> dateline,
                              LinkedList<LinkedList<String>> body) {

        Article article = new Article(places, topics, title, dateline, body);
        articlesList.add(article);
    }
}
