package Calculations.Measures;

import java.util.LinkedList;

public class Trigram {
    public static double calculateMeasure(LinkedList<String> keyWordsList, String word) {
        double result = 0;
        for (int k = 0; k < keyWordsList.size(); k++) {
            double value = 0;
            String key = keyWordsList.get(k);
            if (Math.min(key.length(), word.length()) < 3) {
                continue;
            }
            int n = Math.max(key.length(), word.length()) - 2;
            int m = Math.min(key.length(), word.length()) - 2;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (key.length() >= word.length()) {
                        if (key.substring(i, i + 3).equals(word.substring(j, j + 3))) {
                            value++;
                        }
                    } else {
                        if (word.substring(i, i + 3).equals(key.substring(j, j + 3))) {
                            value++;
                        }
                    }
                }
            }
            value = (value / (double) n);
            result += value;
        }
//        System.out.println(result);
        return result;
    }
}
