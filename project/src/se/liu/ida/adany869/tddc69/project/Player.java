package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.card.Cards;
import se.liu.ida.adany869.tddc69.project.card.Deck;
import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

public class Player extends Observable implements Serializable{

    private String name;
    private Color color;
    private Collection<Region> regions;
    private ArrayList<Cards> cards;
    private int armyReserve;
    private boolean attackedAndWon = false;
    private boolean isActive = false;

    public Player(String name, Color color) {
        this(name, color, false);
    }

    public Player(String name, Color color, Boolean isActive) {
        this.name = name;
        this.color = color;
        this.isActive = isActive;
        this.regions = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        updateObservers();
    }

    public boolean hasAttackedAndWon() {
        return this.attackedAndWon;
    }

    public void setAttackedAndWon(boolean isAttackedAndWon) {
        this.attackedAndWon = isAttackedAndWon;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getArmyReserve() {
        return armyReserve;
    }

    public void addReserve(int size){
        armyReserve += size;
        updateObservers();
    }

    public void addRegion(Region region) {
        region.setOwner(this);
        this.regions.add(region);
    }

    public void removeRegion(Region region){
        this.regions.remove(region);
    }

    public void addArmyToRegion(Region region){
        if (armyReserve > 0){
            region.addArmy(1);
            armyReserve--;
        }
        updateObservers();
    }

    public void addCard() {
        this.cards.add(Deck.getInstance().getCard());
        this.attackedAndWon = false;
    }

    public ArrayList<Cards> getCards() {
        return this.cards;
    }

    public void removeCard(Cards card) {
        this.cards.remove(card);
        updateObservers();
    }

    public void addReinforcement(){
        int regionSize = regions.size();
        this.addReserve(regionSize > 4 ? regionSize : 4);
    }

    public void updateObservers(){
        setChanged();
        notifyObservers();
    }
}
