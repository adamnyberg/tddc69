package se.liu.ida.adany869.tddc69.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Region {
    private int armies;
    private Player player = new Player("none", Color.DARK_GRAY);
    private String name;
    private ArrayList<Region> neighbours = new ArrayList<Region>();
    private ArrayList<Observer> regionObservers = new ArrayList<Observer>();

    public Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Region> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList neighbours) {
        this.neighbours = neighbours;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
        this.armies = armies;
        notifyObservers();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addNeighbour(Region region){
        System.out.println("Region: " + region);
        System.out.println("Regionname: " + region.name);
        neighbours.add(region);
    }

    public void attack(Region attacked){
        this.setArmies(this.getArmies()-attacked.getArmies());
        attacked.setArmies(attacked.getArmies()-this.getArmies());
    }

    public void addObserver(Observer o){
        regionObservers.add(o);
    }

    public void notifyObservers(){
        for (Observer observer : regionObservers) {
            observer.update(new ActionEvent(this, 0, "updateArmy"));
        }
    }
}
