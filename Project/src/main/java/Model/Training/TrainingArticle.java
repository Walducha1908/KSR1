package Model.Training;

import Main.Settings;
import Model.Article;

import java.util.LinkedList;

public class TrainingArticle {
    private Article article;
    private LinkedList<Double> features;
    private int categoryValue;

    public TrainingArticle(Article article, LinkedList<Double> features) {
        this.article = article;
        this.features = features;

        readCategoryValue();
    }

    public void readCategoryValue() {
        if (Settings.category == "places") {
            for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
                if (article.getPlaces().contains(Settings.categoryItemsList.get(i))) {
                    categoryValue = i;
                    return;
                }
            }
        } else if (Settings.category == "topics") {
            for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
                if (article.getTopics().contains(Settings.categoryItemsList.get(i))) {
                    categoryValue = i;
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "TrainingArticle {" +
                "\ncategoryValue = " + categoryValue +
                "\nnumber of features = " + features.size() +
                "\nfeatures = " + features +
                '}';
    }

    public Article getArticle() {
        return article;
    }

    public LinkedList<Double> getFeatures() {
        return features;
    }

    public int getCategoryValue() {
        return categoryValue;
    }

    public void setFeatures(LinkedList<Double> features) {
        this.features = new LinkedList<Double>(features);
    }
}
