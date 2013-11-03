package se.liu.ida.adany869.tddc69.project.continent;

import se.liu.ida.adany869.tddc69.project.Player;
import se.liu.ida.adany869.tddc69.project.regions.Region;
import java.awt.*;

/**
 * A continent contains of several regions. If a player owns all of these, they get extra reinforcement. This class
 * controls if a player is owner of a whole continent, and if so gives an amount of reinforcement.
 */
public class Continent{
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
             if (!this.regions[i].getOwner().equals(player)) return null;
        }
        return player;
    }

    public void giveReserves(Player player) {
        //Could be replaced with "equals()", but then there have to be a check for if this.getOwner() is null first,
        //making it less preferable.
        //noinspection ObjectEquality
        if (this.getOwner() == player){
            player.addReserve(this.continentValue);
        }
    }

    public boolean containsRegion(Region region) {
        for (Region continentRegion : this.regions) {
            if (region.equals(continentRegion)) {
                return true;
            }
        }
        return false;
    }
}