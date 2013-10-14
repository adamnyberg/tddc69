package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.Region;
import java.awt.*;

public class Continent {
    private String name;
    private int continentValue;
    private Color color;
    private Region[] regions;

    public Continent(String name, int continentValue, Color color, Region[] regions) {
        this.name = name;
        this.continentValue = continentValue;
        this.color = color;
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public int getContinentValue() {
        return continentValue;
    }

    public Color getColor() {
        return color;
    }

    public Player getOwner() {
        Player player = this.regions[0].getOwner();
        for (int i = 1; i < this.regions.length; i++) {
             if (this.regions[i].getOwner() != player) return null;
        }
        return player;
    }

    public void giveReserves(Player player) {
        if (this.getOwner() == player){
            player.addReserve(this.continentValue);
        }
    }

    public boolean containsRegion(Region region) {
        for (Region continentRegion : this.regions) {
            if (region == continentRegion) {
                return true;
            }
        }
        return false;
    }
}