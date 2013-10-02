package se.liu.ida.adany869.tddc69.project;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Player extends Observable{

    private String name;
    private Color color;
    private ArrayList<Region> regions;
    private int armyReserve = 30;
    private boolean isActive = false;

    public Player(String name, Color color) {
        this(name, color, false);
    }

    public Player(String name, Color color, Boolean isActive) {
        this.name = name;
        this.color = color;
        this.isActive = isActive;
        this.regions = new ArrayList<Region>();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        updateObservers();
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

    public int getArmyReserve() {
        return armyReserve;
    }

    public void setArmyReserve(int armyReserve) {
        this.armyReserve = armyReserve;
    }

    public void addReserve(int size){
        armyReserve += size;
        updateObservers();
    }

    public void addRegion(Region region) {
        region.setPlayer(this);
        this.regions.add(region);
    }

    public void removeRegion(Region region){
        this.regions.remove(region);
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        for (Region region : regions) {
            addRegion(region);
        }
    }

    public void addArmyToRegion(Region region){
        if (armyReserve > 0){
            region.addArmy(1);
            armyReserve--;
        }
        updateObservers();
    }

    public void addReinforcement(){
        for (int i = 0; i < regions.size(); i++) {
            this.addReserve(10);
        }
    }

    public void updateObservers(){
        setChanged();
        notifyObservers();
    }
}
