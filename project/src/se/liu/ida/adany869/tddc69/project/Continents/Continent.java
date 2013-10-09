package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;

import java.util.ArrayList;

public class Continent implements TopArea{
    protected SubArea[] subAreas;
    protected Player owner = null;
    protected int continentValue;
    protected String name;

    public Continent(SubArea[] subContinents, int value, String name) {
        this.subAreas = subContinents;
        this.continentValue = value;
        this.name = name;
    }

    @Override
    public void giveReserves(Player player) {
        if (checkAndSetOwner()){
            if (owner == player){
                owner.addReserve(continentValue);
            }
        }
        for (SubArea subArea : subAreas) {
            subArea.addAreaReserves(player);
        }
    }

    @Override
    public boolean hasOwner() {
        return owner != null;
    }

    @Override
    public boolean checkAndSetOwner() {
        owner = null;
        for (SubArea subArea : subAreas) {
            if (owner == null){
                owner = subArea.getOwner();
            }
            else if(owner != subArea.getOwner()){
                owner = null;
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }
}
