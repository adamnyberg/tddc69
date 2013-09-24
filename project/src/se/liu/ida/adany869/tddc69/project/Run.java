package se.liu.ida.adany869.tddc69.project;

import java.util.Random;

public class Run {
    public static void main(String[] args) {
        // create players
        Player[] players = {};
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
        regions[0].addNeighbour(regions[3]);
        regions[3].addNeighbour(regions[0]);

        for (Region region : regions) {
            region.setArmies(randGen.nextInt(100));
        }

        RiskWorld risk = new RiskWorld(regions, players);
        RiskFrame riskFrame = new RiskFrame(risk);
        riskFrame.setMenuObserver(new MenuController());
    }
}
