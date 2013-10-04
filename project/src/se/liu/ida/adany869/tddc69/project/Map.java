package se.liu.ida.adany869.tddc69.project;

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
                regions[randomRegionIndex].setArmies(3);
                player.addRegion(regions[randomRegionIndex]);
                listOfRegionIndexes.remove(random);
            }
        }
        for (Player player : players) {
            player.addReinforcement();
        }
        addRelations(new int[][]{{0,1},{0,2},{1,2},{1,4},{4,8},{2,7},{2,8},{3,8},{3,6},{3,7},{3,10},{8,9},
                {4,9},{9,10},{10,6},{5,6},{5,7},{11,5},{11,6},{12,7},{12,13},{13,7}});
        /*addRelation(0, 1);
        addRelation(0, 2);
        addRelation(1, 2);
        addRelation(1, 4);
        addRelation(4, 8);
        addRelation(2, 7);
        addRelation(2, 8);
        addRelation(3, 8);
        addRelation(3, 6);
        addRelation(3, 7);
        addRelation(3, 10);
        addRelation(8, 9);
        addRelation(4, 9);
        addRelation(9, 10);
        addRelation(10, 6);
        addRelation(5, 6);
        addRelation(5, 7);
        addRelation(11, 5);
        addRelation(11,6);
        addRelation(12, 7);
        addRelation(12, 13);
        addRelation(13, 7);*/
    }

    public Region[] getRegions() {
        return regions;
    }

    public Player[] getPlayers() {
        return players;
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
