package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;
import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.util.ArrayList;

public class GenericContinent {
    private ArrayList<SubArea> subAreas;
    private Player owner;
    private int continentValue;

    public GenericContinent(ArrayList<SubArea> areas, int continentValue) {
        this.subAreas = areas;
        this.continentValue = continentValue;
    }

    public boolean checkOwner(){
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
        return owner;
    }

    public void giveReserves(){
        if (checkOwner()){
            owner.addReserve(continentValue);
        }
    }
}
