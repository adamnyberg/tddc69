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
        new Region("SIBIRIA")       // 7
    };

    private Player[] players = {
            new Player("Adam", Color.blue, true),
            new Player("Harald", Color.green)
    };

    public Map() {
        Random randGen = new Random();
        ArrayList<Integer> listOfRegionIndexes = new ArrayList<>();
        for (int i = 0; i < regions.length; i++) {
            System.out.println("i: " + i);
            listOfRegionIndexes.add(i);
        }

        while (!listOfRegionIndexes.isEmpty()) {
            for (Player player : players) {

                int random = randGen.nextInt(listOfRegionIndexes.size());
                int randomRegionIndex = listOfRegionIndexes.get(random);
                System.out.println("random: " + randomRegionIndex);
                regions[randomRegionIndex].setArmies(1);
                player.addRegion(regions[randomRegionIndex]);
                listOfRegionIndexes.remove(random);
            }
        }

        addRelation(0, 1);
        addRelation(0, 2);
        addRelation(1, 4);
        addRelation(4, 6);
        addRelation(3, 4);
        addRelation(2, 3);
        addRelation(3, 6);
        addRelation(5, 6);
        addRelation(5, 7);
        addRelation(2, 7);
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
}
