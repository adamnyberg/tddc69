package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;
import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.util.ArrayList;

public class SubContinent extends Continent implements  SubArea{

    public SubContinent(ArrayList<SubArea> subAreas, int continentValue) {
        super(subAreas, continentValue);
        this.continentValue = continentValue;
    }

    public boolean checkAndSetOwner(){
        owner = null;
        for (SubArea subArea : subAreas) {
            if (owner == null){
                owner = subArea.getOwner();
            }
            else if(owner != subArea.getOwner()){
                return false;
            }
        }
        return true;
    }

    public boolean hasOwner(){
        return owner != null;
    }

    public Player getOwner() {
        checkAndSetOwner();
        return owner;
    }

    public void giveReserves(){
        if (checkAndSetOwner()){
            owner.addReserve(continentValue);
        }
    }

    @Override
    public void addAreaReserves() {
        giveReserves();
    }
}

