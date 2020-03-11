package Model;

public class ArticleDistance implements Comparable<ArticleDistance>{
    private int categoryValue;
    private Double distance;

    public ArticleDistance(int categoryValue, double distance) {
        this.categoryValue = categoryValue;
        this.distance = distance;
    }

    @Override
    public int compareTo(ArticleDistance o) {
        return this.distance.compareTo(o.getDistance());
    }

    public int getCategoryValue() {
        return categoryValue;
    }

    public double getDistance() {
        return distance;
    }
}
