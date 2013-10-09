package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.util.ArrayList;

public class ContinentFactory {
    public static Continent makeContinent(int value, String name, int[] subValues, String[] subNames, SubArea[] ... subContinents){
        ArrayList<SubArea> subAreas = new ArrayList<>();
        int i = 0;
        for (SubArea[] subContinent : subContinents) {
            if (subContinent.length == 1){
                subAreas.add(subContinent[0]);
            }
            else {
                subAreas.add(new SubContinent(subContinent, subValues[i], subNames[i]));
                i++;
            }
        }
        return new Continent((SubArea[])subAreas.toArray(), value, name);
    }
}
