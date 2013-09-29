package se.liu.ida.adany869.tddc69.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class Region {
    private int armies;
    private Player player = new Player("none", Color.DARK_GRAY);
    private String name;
    private ArrayList<Region> neighbours = new ArrayList<Region>();
    private ArrayList<Observer> regionObservers = new ArrayList<Observer>();
    private Random randGen = new Random();


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

    public void addArmy(int armies){
        this.armies += armies;
        notifyObservers();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addNeighbour(Region region){
        neighbours.add(region);
    }

    public void attack(Region attacked){
        System.out.println("RegionAttack");
        int attackDmg = randGen.nextInt(this.getArmies()+1)/3;
        int defDmg =  randGen.nextInt(attacked.getArmies()+1)/3;

        int attackerArmiesAfter = this.getArmies()-defDmg;
        int defenderArmiesAfter = attacked.getArmies()-attackDmg;

        if(attackerArmiesAfter < 0) attackerArmiesAfter = 0;
        if(defenderArmiesAfter < 0) defenderArmiesAfter = 0;

        this.setArmies(attackerArmiesAfter);
        attacked.setArmies(defenderArmiesAfter);
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
