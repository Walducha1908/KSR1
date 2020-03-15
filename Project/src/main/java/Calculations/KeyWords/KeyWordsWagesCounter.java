package Calculations.KeyWords;

import Main.Settings;
import Model.KeyWordsContainer;

import java.util.HashMap;

public class KeyWordsWagesCounter {
    public void calculateWages() {
        for (int i = 0; i < KeyWordsContainer.keyWordsMap.size(); i++) {
            for (int j = 0; j < KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).size(); j++) {
                String keyWord = KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(i)).get(j);
                if (Settings.wages) {
                    Double keyWordWage = Settings.maxWage;

                    for (int k = 0; k < KeyWordsContainer.keyWordsMap.size(); k++) {
                        if (KeyWordsContainer.keyWordsMap.get(Settings.categoryItemsList.get(k)).contains(keyWord) && i != k) {
                            keyWordWage -= ((Settings.maxWage - Settings.minWage) / (Settings.categoryItemsList.size() - 1));
                        }
                    }
                    keyWordWage = Math.pow(keyWordWage, 2);
                    KeyWordsContainer.keyWordsWagesMap.put(keyWord, keyWordWage);
                    System.out.println(keyWord + " wage: " + KeyWordsContainer.keyWordsWagesMap.get(keyWord));
                } else {
                    KeyWordsContainer.keyWordsWagesMap.put(keyWord, 1.0);
                }
            }
        }
    }
}
