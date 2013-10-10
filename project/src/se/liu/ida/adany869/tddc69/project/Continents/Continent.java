package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;
import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.awt.*;
import java.util.ArrayList;

public class Continent implements TopArea{
    protected SubArea[] subAreas;
    protected Player owner = null;
    protected int continentValue;
    protected String name;
    protected Color color;

    public Continent(SubArea[] subContinents, int value, String name, Color color) {
        this.subAreas = subContinents;
        this.continentValue = value;
        this.name = name;
        this.color = color;
    }

    public boolean containsRegion(Region region) {
        for (SubArea subArea : subAreas) {
            if (subArea.containsRegion(region)) {
                return true;
            }
        }
        return false;
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
        return owner != null;
    }
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Continent> allSubContinents() {
        ArrayList<Continent> subContinents = new ArrayList<>();
        for (SubArea subArea : subAreas) {
            subContinents.addAll(subArea.allSubContinents());
        }
        return subContinents;
    }

    public ArrayList<Continent> getContinentsWhichContains(Region region){
        ArrayList<Continent> containsRegion = new ArrayList<>();
        for (SubArea subArea : subAreas) {
            if (subArea.containsRegion(region)){
                containsRegion.addAll(subArea.getContinentsWhichContains(region));
                containsRegion.add(this);
            }
        }
        return containsRegion;
    }

}
