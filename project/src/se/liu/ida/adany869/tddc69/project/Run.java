package se.liu.ida.adany869.tddc69.project;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Run {
    public static void main(String[] args) {

        Random randGen = new Random();

        // create regions
        Region[] regions = {
            new Region("SWEDEN"),
            new Region("MOTHER RUSSIA"),
            new Region("MONGOLA"),
            new Region("SIBIRIA")
        };

        regions[0].addNeighbour(regions[1]);
        regions[1].addNeighbour(regions[0]);
        //regions[0].addNeighbour(regions[3]);
        //regions[3].addNeighbour(regions[0]);
        regions[2].addNeighbour(regions[3]);
        regions[3].addNeighbour(regions[2]);

        for (Region region : regions) {
            region.setArmies(randGen.nextInt(100));
        }

        // create players
        Player[] players = {
                new Player("Adam", Color.blue),
                new Player("Harald", Color.green)
        };
        ArrayList<Region> playerRegions = new ArrayList<Region>();
        playerRegions.add(regions[0]);
        playerRegions.add(regions[1]);
        players[0].setRegions(playerRegions);

        playerRegions.remove(1);
        playerRegions.remove(0);
        playerRegions.add(regions[2]);
        playerRegions.add(regions[3]);
        players[1].setRegions(playerRegions);

        RiskWorld risk = new RiskWorld(regions, players);
        RiskFrame riskFrame = new RiskFrame(risk);
        riskFrame.setMenuObserver(new MenuController());
    }
}
