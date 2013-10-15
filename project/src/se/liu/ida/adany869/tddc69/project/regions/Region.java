package se.liu.ida.adany869.tddc69.project.regions;

import se.liu.ida.adany869.tddc69.project.Dice;
import se.liu.ida.adany869.tddc69.project.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Region extends Observable{
    private int armies;
    //private int armiesReadyToBeDeployed = 0;
    private Player player = new Player("none", Color.DARK_GRAY);
    private String name;
    private ArrayList<Region> neighbours = new ArrayList<>();
    private boolean isFocused;

    public Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Region> getNeighbours() {
        return neighbours;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
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
        ArrayList<Dice> attackerDices = new ArrayList<>();
        ArrayList<Dice> defenderDices = new ArrayList<>();
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

}
