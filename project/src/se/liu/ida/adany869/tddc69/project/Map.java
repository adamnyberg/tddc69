package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.Continents.Continent;
import se.liu.ida.adany869.tddc69.project.Continents.ContinentFactory;
import se.liu.ida.adany869.tddc69.project.Continents.SubArea;
import se.liu.ida.adany869.tddc69.project.Continents.SubContinent;
import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Map {
    private Region[] regions = {
            new Region("SWEDEN"),       // 0
            new Region("MOTHER RUSSIA"),// 1
            new Region("MONGOLA"),      // 2
            new Region("US&A"),         // 3
            new Region("NORTH KOREA"),  // 4
            new Region("CHINA"),        // 5
            new Region("EGYPT"),        // 6
            new Region("SIBIRIA"),      // 7
            new Region("ENGLAND"),      // 8
            new Region("IRAN"),         // 9
            new Region("FINLAND"),      // 10
            new Region("CYPRUS"),       // 11
            new Region("MOON"),         // 12
            new Region("MARS")          // 13
    };



    private Player[] players = {
            new Player("Adam", Color.blue, true),
            new Player("Harald", Color.green)
    };


    private SubContinent westWest = new SubContinent(new Region[]{regions[0], regions[1], regions[4]}, 5, "West Western");
    private SubContinent eastWest = new SubContinent(new Region[]{regions[2], regions[8], regions[9]}, 5, "East Western");
    private Continent west = new Continent(new SubContinent[]{westWest, eastWest}, 7, "Western");
    private Continent outerSpace = new Continent(new Region[]{regions[12], regions[13]}, 3, "Outer Space");
    private SubContinent southEast = new SubContinent(new Region[]{regions[5], regions[6], regions[11]}, 3, "southEast");
    private Continent east = new Continent(new SubArea[]{southEast, regions[3], regions[7]}, 7, "East");
    private Continent[] continents = new Continent[]{west, east, outerSpace};

    public Map() {


        Random randGen = new Random();
        ArrayList<Integer> listOfRegionIndexes = new ArrayList<>();
        for (int i = 0; i < regions.length; i++) {
            listOfRegionIndexes.add(i);
        }

        while (!listOfRegionIndexes.isEmpty()) {
            for (Player player : players) {

                int random = randGen.nextInt(listOfRegionIndexes.size());
                int randomRegionIndex = listOfRegionIndexes.get(random);
                regions[randomRegionIndex].setArmy(3);
                player.addRegion(regions[randomRegionIndex]);
                listOfRegionIndexes.remove(random);
            }
        }
        addRelations(new int[][]{
                {0,1},
                {0,2},
                {1,2},
                {1,4},
                {4,8},
                {2,7},
                {2,8},
                {3,8},
                {3,6},
                {3,7},
                {3,10},
                {8,9},
                {4,9},
                {9,10},
                {10,6},
                {5,6},
                {5,7},
                {11,5},
                {11,6},
                {12,7},
                {12,13},
                {13,7}
        });
    }

    public Region[] getRegions() {
        return regions;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Continent[] getContinents() {
        return continents;
    }

    private void addRelation(int region1, int region2) {
        regions[region1].addNeighbour(regions[region2]);
        regions[region2].addNeighbour(regions[region1]);
    }

    private void addRelations(int[][] relations){
        for (int[] relation : relations) {
            addRelation(relation[0], relation[1]);
        }
    }
}
