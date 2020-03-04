package Model;

import java.util.LinkedList;

public class Article {
    LinkedList<String> places;
    LinkedList<String> topics;
    LinkedList<String> title;
    LinkedList<String> dateline;
    LinkedList<LinkedList<String>> body;

    public Article(LinkedList<String> places, LinkedList<String> topics, LinkedList<String> title, LinkedList<String> dateline, LinkedList<LinkedList<String>> body) {
        this.places = places;
        this.topics = topics;
        this.title = title;
        this.dateline = dateline;
        this.body = body;
    }

    public LinkedList<String> getPlaces() {
        return places;
    }

    public LinkedList<String> getTopics() {
        return topics;
    }

    public LinkedList<String> getTitle() {
        return title;
    }

    public LinkedList<String> getDateline() {
        return dateline;
    }

    public LinkedList<LinkedList<String>> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "\nArticle {" +
                "\nplaces=" + places +
                ",\ntopics=" + topics +
                ",\ntitle=" + title +
                ",\ndateline=" + dateline +
                ",\nbody=" + body +
                '}';
    }
}
