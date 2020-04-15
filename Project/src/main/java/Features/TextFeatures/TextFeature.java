package Features.TextFeatures;

import Model.Article;

import java.util.LinkedList;

public interface TextFeature {
    public LinkedList<String> calculateFeature(Article article);
}
