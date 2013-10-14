package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.continent.Continent;
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

            new Player("Adam", Color.pink, true),
            new Player("Harald", Color.green)
    };

    private Continent westWest = new Continent("West Western", 5, Color.DARK_GRAY, new Region[]{regions[0], regions[1], regions[4]});
    private Continent eastWest = new Continent("East Western", 5, Color.cyan, new Region[]{regions[2], regions[8], regions[9]});
    private Continent west = new Continent("Western", 7, Color.MAGENTA, new Region[]{
            regions[0], regions[1], regions[4], regions[2], regions[8], regions[9]
    });
    private Continent outerSpace = new Continent("Outer Space", 3, Color.orange, new Region[]{regions[12], regions[13]});
    private Continent southEast = new Continent("South East", 3, Color.blue, new Region[]{regions[5], regions[6], regions[11]});
    private Continent east = new Continent("East", 7, Color.red, new Region[]{regions[5], regions[6], regions[11], regions[3], regions[7]});

    private Continent[] continents = new Continent[]{
            westWest,
            eastWest,
            west,
            outerSpace,
            southEast,
            east
    };

    public Map() {
        Random randGen = new Random();
        if (randGen.nextInt(2) == 1){
            players = new Player[]{new Player("Harald", Color.pink, true),
                    new Player("Adam", Color.green)};
        }

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
