package Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

public class DataReader {

    private int numberOfFiles;
    LinkedList<String> filesString;
    LinkedList<String> articlesString;

    public DataReader(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
        this.filesString = new LinkedList<String>();
        this.articlesString = new LinkedList<String>();

        readData();
        prepareArticles();
    }

    public void readData() {
        DataPaths dataPaths = new DataPaths(numberOfFiles);

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

        for (int i=0; i<articlesString.size(); i++) {
            LinkedList<String> places = getPlaces(articlesString.get(i));
            LinkedList<String> titleWords = getTitle(articlesString.get(i));
            LinkedList<String> dateLine = getDateline(articlesString.get(i));

            System.out.println(i);
            System.out.println(places);
            System.out.println(titleWords);
            System.out.println(dateLine);
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

    public LinkedList<String> getTitle(String currentArticleString) {
        String titleStartKey = "<TITLE>";
        String titleEndKey = "</TITLE>";

        String titleString = new String();
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

        String datelineString = new String();
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


}
