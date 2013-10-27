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

    //Indexes:
    private static final int SWEDEN = 0;
    private static final int MOTHER_RUSSIA = 1;
    private static final int MONGOLA = 2;
    private static final int USA = 3;
    private static final int NORTH_KOREA = 4;
    private static final int CHINA = 5;
    private static final int EGYPT = 6;
    private static final int SIBIRIA = 7;
    private static final int ENGLAND = 8;
    private static final int IRAN = 9;
    private static final int FINLAND = 10;
    private static final int CYPRUS = 11;
    private static final int MOON = 12;
    private static final int MARS = 13;

    private static final int WEST_WEST_VALUE = 5;
    private static final int EAST_WEST_VALUE = 5;
    private static final int WEST_VALUE = 7;
    private static final int OUTER_SPACE_VALUE = 2;
    private static final int SOUTH_EAST_VALUE = 3;
    private static final int EAST_VALUE = 7;

    private Player[] players;

    private Continent westWest = new Continent("West Western", WEST_WEST_VALUE, Color.DARK_GRAY,
            new Region[]{regions[SWEDEN], regions[MOTHER_RUSSIA], regions[NORTH_KOREA]});
    private Continent eastWest = new Continent("East Western", EAST_WEST_VALUE, Color.cyan,
            new Region[]{regions[MONGOLA], regions[ENGLAND], regions[IRAN]});
    private Continent west = new Continent("Western", WEST_VALUE, Color.MAGENTA, new Region[]{
            regions[SWEDEN], regions[MOTHER_RUSSIA], regions[NORTH_KOREA],
            regions[MONGOLA], regions[ENGLAND], regions[IRAN]
    });
    private Continent outerSpace = new Continent("Outer Space", OUTER_SPACE_VALUE, Color.orange,
            new Region[]{regions[MOON], regions[MARS]});
    private Continent southEast = new Continent("South East", SOUTH_EAST_VALUE, Color.blue,
            new Region[]{regions[CHINA], regions[EGYPT], regions[CYPRUS]});
    private Continent east = new Continent("East", EAST_VALUE, Color.red,
            new Region[]{regions[CHINA], regions[EGYPT], regions[CYPRUS], regions[USA], regions[SIBIRIA]});

    private Continent[] continents = new Continent[]{
            westWest,
            eastWest,
            west,
            outerSpace,
            southEast,
            east
    };

    public Map(ArrayList<String> playerNames) {
        Random randGen = new Random();
        players = new Player[playerNames.size()];
        int i = 0;

        Color[] playerColors = new Color[]{Color.blue, Color.green,
                Color.cyan, Color.magenta, Color.orange, Color.pink};

        while (!playerNames.isEmpty()){
            int randInt = randGen.nextInt(playerNames.size());
            players[i] = new Player(playerNames.get(randInt), playerColors[i]);
            playerNames.remove(randInt);
            i++;
        }
        players[0].setActive(true); //Setting who is starting

        ArrayList<Integer> listOfRegionIndexes = new ArrayList<>();
        for (int j = 0; j < regions.length; j++) {
            listOfRegionIndexes.add(j);
        }
        while (!listOfRegionIndexes.isEmpty()) {
            for (Player player : players) {
                int random = randGen.nextInt(listOfRegionIndexes.size());
                int randomRegionIndex = listOfRegionIndexes.get(random);
                //regions[randomRegionIndex].setArmySize(3);
                player.addRegion(
                        regions[randomRegionIndex]);
                listOfRegionIndexes.remove(random);
                if (listOfRegionIndexes.isEmpty()){
                    break;
                }
            }
        }

        addRelations(new int[][]{
                {SWEDEN,MOTHER_RUSSIA},
                {SWEDEN,MONGOLA},
                {MOTHER_RUSSIA,MONGOLA},
                {MOTHER_RUSSIA,NORTH_KOREA},
                {NORTH_KOREA,ENGLAND},
                {MONGOLA,SIBIRIA},
                {MONGOLA,ENGLAND},
                {USA,ENGLAND},
                {USA,EGYPT},
                {USA,SIBIRIA},
                {USA,FINLAND},
                {ENGLAND,IRAN},
                {NORTH_KOREA,IRAN},
                {IRAN,FINLAND},
                {FINLAND,EGYPT},
                {CHINA,EGYPT},
                {CHINA,SIBIRIA},
                {CYPRUS,CHINA},
                {CYPRUS,EGYPT},
                {MOON,SIBIRIA},
                {MOON,MARS},
                {MARS,SIBIRIA}
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
