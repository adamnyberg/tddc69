package se.liu.ida.adany869.tddc69.project.regions;

import se.liu.ida.adany869.tddc69.project.Continents.Continent;
import se.liu.ida.adany869.tddc69.project.Continents.SubArea;
import se.liu.ida.adany869.tddc69.project.Dice;
import se.liu.ida.adany869.tddc69.project.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Region extends Observable implements SubArea{
    private int armies;
    private int armiesReadyToBeDeployed = 0;
    private Player player = new Player("none", Color.DARK_GRAY);
    private String name;
    private ArrayList<Region> neighbours = new ArrayList<>();
    private Random randGen = new Random();
    private final ActionEvent UPDATE_ARMY_EVENT = new ActionEvent(this, 0, "updateArmy");
    private boolean isFocused;

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

    public void setArmy(int armies) {
        this.armies = armies;
        setChanged();
        notifyObservers();
    }

    public void addArmy(int armies){
        this.armies += armies;
        setChanged();
        notifyObservers();
    }

    public Player getOwner() {
        return player;
    }

    public void setOwner(Player player) {
        this.player = player;
        setChanged();
        notifyObservers();
    }

    public boolean hasOwner(){
        return player != null;
    }

    public void addNeighbour(Region region){
        neighbours.add(region);
    }

    public boolean isNeighbour(Region region){
        return neighbours.contains(region);
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
        setChanged();
        notifyObservers();
    }

    public int attack(Region attacked, int attackSize){
        ArrayList<Dice> attackerDices = new ArrayList<Dice>();
        ArrayList<Dice> defenderDices = new ArrayList<Dice>();
        for (int i = 0; i < attackSize; i++) {
            attackerDices.add(new Dice());
        }
        for (int i = 0; i < (attacked.getArmies() >= 2 ? 2 : 1); i++){
            defenderDices.add(new Dice());
        }

        int attackerLoss = 0;
        int defenderLoss = 0;
        while (!(attackerDices.isEmpty() || defenderDices.isEmpty())){
            if (popMax(attackerDices) > popMax(defenderDices)) defenderLoss++;
            else attackerLoss++;
        }
        this.addArmy(-attackerLoss);
        attacked.addArmy(-defenderLoss);
        return attackerLoss;
    }

    private int popMax(ArrayList<Dice> dices){
        Dice max = dices.get(0);
        for (Dice dice : dices) {
            if (max.getValue() < dice.getValue()) max = dice;
        }
        dices.remove(max);
        return max.getValue();
    }

    @Override
    public void addAreaReserves(Player player) {
        return;
    }

    @Override
    public boolean containsRegion(Region region) {
        return this == region;
    }

    @Override
    public ArrayList<Continent> allSubContinents() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Continent> getContinentsWhichContains(Region region) {
        return new ArrayList<>();
    }

    /*public void addObserver(Observer o){
        regionObservers.add(o);
        this.
    }*/

    /*public void notifyObservers(){
        for (Observer observer : regionObservers) {
            observer.update(new ActionEvent(this, 0, "updateArmy"));
        }
    }*/
}
