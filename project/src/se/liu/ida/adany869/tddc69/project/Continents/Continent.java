package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;

import java.util.ArrayList;

public class Continent implements TopArea{
    protected ArrayList<SubArea> subAreas;
    protected Player owner = null;
    protected int continentValue;

    public Continent(ArrayList<SubArea> subContinents, int value) {
        this.subAreas = subContinents;
        this.continentValue = value;
    }

    @Override
    public void giveReserves() {
        if (hasOwner()){

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
                return false;
            }
        }
        return true;
    }
}
