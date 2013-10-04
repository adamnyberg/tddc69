package se.liu.ida.adany869.tddc69.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Region extends Observable {
    private int armies;
    private Player player = new Player("none", Color.DARK_GRAY);
    private String name;
    private ArrayList<Region> neighbours = new ArrayList<>();
    private Random randGen = new Random();
    private final ActionEvent UPDATE_ARMY_EVENT = new ActionEvent(this, 0, "updateArmy");

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
        setChanged();
        notifyObservers();
    }

    public void addArmy(int armies){
        this.armies += armies;
        setChanged();
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

    public boolean isNeighbour(Region region){
        return neighbours.contains(region);
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

        /*System.out.println("Attackerdices:");
        for (Dice dice : attackerDices) {
            System.out.println(dice.getValue());
        }
        System.out.println("Defenderdices:");
        for (Dice dice : defenderDices) {
            System.out.println(dice.getValue());
        }*/

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
