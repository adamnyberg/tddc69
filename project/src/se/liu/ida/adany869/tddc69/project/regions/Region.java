package se.liu.ida.adany869.tddc69.project.regions;

import se.liu.ida.adany869.tddc69.project.Dice;
import se.liu.ida.adany869.tddc69.project.Player;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

/**
 * The game consists of regions. Each region has an owner and atleast an army size of one. Each region is
 * visually represented by a RegionComponent, which automatically updates when the region alters. Region can
 * be a part of one or several continents. The region holds information about which regions it is adjacent to,
 * its neighbours. The neighbours can be attacked or fortified, depending on the ownership of the regions.
 */
public class Region extends Observable implements Serializable{
    private int armySize;
    private Player player = new Player("none", Color.DARK_GRAY);
    private String name;
    private Collection<Region> neighbours = new ArrayList<>();
    private boolean isFocused;
    private static final int INIT_ARMY_SIZE = 3;

    public Region(String name) {
        this.name = name;
        this.armySize = INIT_ARMY_SIZE;
    }

    public String getName() {
        return name;
    }

    public Collection<Region> getNeighbours() {
        return neighbours;
    }

    public int getArmySize() {
        return armySize;
    }

    public void setArmySize(int armySize) {
        this.armySize = armySize;
        setChanged();
        notifyObservers();
    }

    public void addArmy(int armies){
        this.armySize += armies;
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

    //The region is focused if the player has clicked it's RegionComponent.
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
        for (int i = 0; i < (attacked.armySize >= 2 ? 2 : 1); i++){
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

    //Consumes and returns the dice with the highest value. Used to decide losses in a battle.
    private int popMax(ArrayList<Dice> dices){
        Dice max = dices.get(0);
        for (Dice dice : dices) {
            if (max.getValue() < dice.getValue()) max = dice;
        }
        dices.remove(max);
        return max.getValue();
    }

}
