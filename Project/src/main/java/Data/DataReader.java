package Data;

import Model.ArticleContainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

public class DataReader {

    private int numberOfFiles;
    private String pathToData;
    LinkedList<String> filesString;
    LinkedList<String> articlesString;

    public DataReader(int numberOfFiles, String pathToData) {
        this.numberOfFiles = numberOfFiles;
        this.pathToData = pathToData;
        this.filesString = new LinkedList<String>();
        this.articlesString = new LinkedList<String>();

        readData();
        prepareArticles();
    }

    public void readData() {
        DataPaths dataPaths = new DataPaths(numberOfFiles, pathToData);

        try {
            for (int i=0; i<numberOfFiles; i++) {
                String data = new String(Files.readAllBytes(Paths.get(dataPaths.getPaths().get(i))));
                filesString.add(data);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareArticles() {
        for (int i=0; i<numberOfFiles; i++) {
            articlesString.addAll(Arrays.asList(filesString.get(i).split("</REUTERS>\n")));
        }

        ArticleContainer articleContainer = new ArticleContainer();

        for (int i=0; i<articlesString.size(); i++) {
            LinkedList<String> places = getPlaces(articlesString.get(i));
            LinkedList<String> topics = getTopics(articlesString.get(i));
            LinkedList<String> titleWords = getTitle(articlesString.get(i));
            LinkedList<String> datelineWords = getDateline(articlesString.get(i));
            LinkedList<LinkedList<String>> bodyContent = getBody(articlesString.get(i));

            articleContainer.createArticle(places, topics, titleWords, datelineWords, bodyContent);
        }
    }

    public LinkedList<String> getPlaces(String currentArticleString) {
        String placesStartKey = "<PLACES>";
        String placesEndKey = "</PLACES>";
        String onePlaceStartKey = "<D>";
        String onePlaceEndKey = "</D>";

        String placesString = currentArticleString.substring(
                currentArticleString.indexOf(placesStartKey) + placesStartKey.length(),
                currentArticleString.indexOf(placesEndKey));

        LinkedList<String> placesList = new LinkedList<String>();

        if (placesString.length() > 0) {
            placesList.addAll(Arrays.asList(placesString.substring(
                    onePlaceStartKey.length(),
                    placesString.length() - onePlaceEndKey.length())
                    .split(onePlaceEndKey + onePlaceStartKey)));
        }

        return placesList;
    }

    public LinkedList<String> getTopics(String currentArticleString) {
        String topicsStartKey = "<TOPICS>";
        String topicsEndKey = "</TOPICS>";
        String oneTopicStartKey = "<D>";
        String oneTopicEndKey = "</D>";

        String topicsString = currentArticleString.substring(
                currentArticleString.indexOf(topicsStartKey) + topicsStartKey.length(),
                currentArticleString.indexOf(topicsEndKey));

        LinkedList<String> topicsList = new LinkedList<String>();

        if (topicsString.length() > 0) {
            topicsList.addAll(Arrays.asList(topicsString.substring(
                    oneTopicStartKey.length(),
                    topicsString.length() - oneTopicEndKey.length())
                    .split(oneTopicEndKey + oneTopicStartKey)));
        }

        return topicsList;
    }

    public LinkedList<String> getTitle(String currentArticleString) {
        String titleStartKey = "<TITLE>";
        String titleEndKey = "</TITLE>";

        String titleString = "";
        if (currentArticleString.contains(titleStartKey)) {
            titleString = currentArticleString.substring(
                    currentArticleString.indexOf(titleStartKey) + titleStartKey.length(),
                    currentArticleString.indexOf(titleEndKey));
        }

        LinkedList<String> titleWordList = new LinkedList<String>();
        if (titleString.length() > 0) {
            titleWordList.addAll(Arrays.asList(titleString.split(" ")));
        }

        return titleWordList;
    }

    public LinkedList<String> getDateline(String currentArticleString) {
        String datelineStartKey = "<DATELINE>";
        String datelineEndKey = "</DATELINE>";

        String datelineString = "";
        if (currentArticleString.contains(datelineStartKey)) {
            datelineString = currentArticleString.substring(
                    currentArticleString.indexOf(datelineStartKey) + datelineStartKey.length(),
                    currentArticleString.indexOf(datelineEndKey));
        }

        LinkedList<String> dateLineWordList = new LinkedList<String>();
        if (datelineString.length() > 0) {
            dateLineWordList.addAll(Arrays.asList(datelineString.split(" ")));
        }

        return dateLineWordList;
    }

    public LinkedList<LinkedList<String>> getBody(String currentArticleString) {
        String bodyStartKey = "<BODY>";
        String bodyEndKey = "</BODY>";

        String bodyString = "";
        if (currentArticleString.contains(bodyStartKey)) {
            bodyString = currentArticleString.substring(
                    currentArticleString.indexOf(bodyStartKey) + bodyStartKey.length(),
                    currentArticleString.indexOf(bodyEndKey));
        }

        LinkedList<LinkedList<String>> bodyContent = new LinkedList<LinkedList<String>>();

        LinkedList<String> bodyParagraphList = new LinkedList<String>();
        if (bodyString.length() > 0) {
            bodyParagraphList.addAll(Arrays.asList(bodyString.split("    ")));
        }


        for (int i=0; i<bodyParagraphList.size(); i++) {
            LinkedList<String> bodyWordList = new LinkedList<String>();
            if (bodyParagraphList.get(i).length() > 0) {
                bodyWordList.addAll(Arrays.asList(bodyParagraphList.get(i).split(" ")));
                bodyContent.add(bodyWordList);
            }
        }

        return bodyContent;
    }

}
