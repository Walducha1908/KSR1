package Model.Testing;

import Main.Settings;
import Model.Article;

import java.util.LinkedList;

public class TestingArticle {
    private Article article;
    private LinkedList<Double> features;
    private LinkedList<String> textFeatures;
    private int trueCategoryValue;
    private int calculatedCategoryValue;

    public TestingArticle(Article article, LinkedList<Double> features) {
        this.article = article;
        this.features = features;
        this.textFeatures = new LinkedList<String>();
        this.calculatedCategoryValue = -1;

        readTrueCategoryValue();
    }

    public TestingArticle(Article article, LinkedList<String> textFeatures, boolean text) {
        this.article = article;
        if (text) {
            this.textFeatures = textFeatures;
        } else {
            this.textFeatures = new LinkedList<String>();
        }
        this.features = new LinkedList<Double>();

        readTrueCategoryValue();
    }

    public void readTrueCategoryValue() {
        if (Settings.category == "places") {
            for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
                if (article.getPlaces().contains(Settings.categoryItemsList.get(i))) {
                    trueCategoryValue = i;
                    return;
                }
            }
        } else if (Settings.category == "topics") {
            for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
                if (article.getTopics().contains(Settings.categoryItemsList.get(i))) {
                    trueCategoryValue = i;
                    return;
                }
            }
        }
    }

    public boolean compareCategoryValues() {
        return (trueCategoryValue == calculatedCategoryValue);
    }

    public Article getArticle() {
        return article;
    }

    public LinkedList<Double> getFeatures() {
        return features;
    }

    public void setCalculatedCategoryValue(int calculatedCategoryValue) {
        this.calculatedCategoryValue = calculatedCategoryValue;
    }

    public int getCalculatedCategoryValue() {
        return calculatedCategoryValue;
    }

    public int getTrueCategoryValue() {
        return trueCategoryValue;
    }

    public void setFeatures(LinkedList<Double> features) {
        this.features = features;
    }

    public LinkedList<String> getTextFeatures() {
        return textFeatures;
    }

    public void setTextFeatures(LinkedList<String> textFeatures) {
        this.textFeatures = textFeatures;
    }
}
