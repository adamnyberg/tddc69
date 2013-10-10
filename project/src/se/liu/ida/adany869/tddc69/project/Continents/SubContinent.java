package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;
import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.awt.*;
import java.util.ArrayList;

public class SubContinent extends Continent implements SubArea{

    public SubContinent(SubArea[] subAreas, int continentValue, String name, Color color) {
        super(subAreas, continentValue, name, color);
    }

    public Player getOwner() {
        checkAndSetOwner();
        return owner;
    }

    @Override
    public void addAreaReserves(Player player) {
        giveReserves(player);
    }

}

