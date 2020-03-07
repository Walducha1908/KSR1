package Features;

import Model.Article;

import java.util.LinkedList;

public interface Feature {
    public LinkedList<Double> calculateFeature(Article article);
}
