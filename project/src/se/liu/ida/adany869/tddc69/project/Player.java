package se.liu.ida.adany869.tddc69.project;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    private String name;
    private Color color;
    private ArrayList<Region> regions;
    private int armyReserve = 10;

    public Player(String name, Color color) {
        this(name, color, null);
    }
    public Player(String name, Color color, ArrayList<Region> regions) {
        System.out.println("Added player: " + name);
        this.name = name;
        this.color = color;
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addRegion(Region region) {
        region.setPlayer(this);
        this.regions.add(region);
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = new ArrayList<Region>();
        for (Region region : regions) {
            addRegion(region);
        }
    }

    public void addArmyToRegion(Region region){
        if (armyReserve > 0){
            region.addArmy(1);
            armyReserve--;
        }
    }
}
