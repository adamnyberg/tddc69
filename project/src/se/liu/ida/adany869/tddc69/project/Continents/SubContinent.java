package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;

import java.util.ArrayList;

public class SubContinent extends Continent implements  SubArea{

    public SubContinent(SubArea[] subAreas, int continentValue, String name) {
        super(subAreas, continentValue, name);
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

