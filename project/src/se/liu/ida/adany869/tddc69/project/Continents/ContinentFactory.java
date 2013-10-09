package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.util.ArrayList;

public class ContinentFactory {
    public Continent makeContinent(ArrayList<SubArea> subContinents, int value){
        return new Continent(subContinents, value);
    }

    public SubContinent makeSubcontinent(ArrayList<SubArea> subRegions, int value){
        return new SubContinent(subRegions, value);
    }
}
