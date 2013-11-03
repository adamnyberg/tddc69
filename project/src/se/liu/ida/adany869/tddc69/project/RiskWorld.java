package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.continent.Continent;
import se.liu.ida.adany869.tddc69.project.state.AbstractState;
import se.liu.ida.adany869.tddc69.project.state.ReinforceState;
import se.liu.ida.adany869.tddc69.project.regions.Region;
import se.liu.ida.adany869.tddc69.project.regions.RegionController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The main game class. It's main purpose is to keep track of whose turn it is, in which state the game
 * currently is in and if someone has won. It aslo contain all the regions, players and continents. This is the logical representation of
 * the game, it has nothing to do with the graphical part.
 */
public class RiskWorld extends Observable implements Serializable{
    private Region[] regions;
    private Player[] players;
    private Continent[] continents;
    private AbstractState abstractState = new ReinforceState(this);
    private RegionController regionController = new RegionController(this);
    private Region focused = null;
    private Player activePlayer;

    public RiskWorld(AbstractMap map) {
        this.regions = map.getRegions();
        this.players = map.getPlayers();
        this.continents = map.getContinents();
        this.activePlayer = this.players[0];

        //Set Player 1's initial armies
        this.activePlayer.addReinforcement();
    }

    public Region[] getRegions() {
        return regions;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Continent[] getContinents() {
        return continents;
    }

    //Returns all continents which contain a specific region.
    public ArrayList<Continent> getContinentsWhichContains(Region region){
        ArrayList<Continent> continentsContainingRegion = new ArrayList<>();
        for (Continent continent : this.continents) {
            if (continent.containsRegion(region)) continentsContainingRegion.add(continent);
        }
        return continentsContainingRegion;
    }

    public AbstractState getAbstractState() {
        return abstractState;
    }

    public void setAbstractState(AbstractState abstractState) {
        this.abstractState = abstractState;
    }

    public RegionController getRegionController() {
        return regionController;
    }

    public boolean hasFocused(){
        return focused != null;
    }

    public Region getFocused() {
        return focused;
    }

    public void setFocused(Region newFocused){
        resetFocus();
        focused = newFocused;
        focused.setFocused(true);
        this.setChanged();
        this.notifyObservers();
    }

    public void resetFocus(){
        if(this.hasFocused()){
            focused.setFocused(false);
            focused = null;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void nextPlayer(){
        this.resetFocus();
        this.abstractState = new ReinforceState(this);
        if (activePlayer.hasAttackedAndWon()) activePlayer.addCard();
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.isActive()){
                int j = i;
                player.setActive(false);
                Player nextPlayer;

                do {
                    if (j == players.length-1){
                        nextPlayer = players[0];
                        j = 0;
                    }
                    else{
                        nextPlayer = players[j+1];
                        j++;
                    }
                } while (nextPlayer.isDefeated());

                nextPlayer.setActive(true);
                nextPlayer.addReinforcement();
                for (Continent continent : continents) {
                    continent.giveReserves(nextPlayer);
                }
                activePlayer = nextPlayer;
                return;
            }
        }
    }

    public boolean checkGameOver() {
        boolean gameOver = true;
        for (int i = 0; i < regions.length - 1; i++) {
            if (!regions[i].getOwner().equals(regions[i + 1].getOwner())) {
                gameOver = false;
            }
        }
        if (gameOver) {
            System.out.println("");
            System.out.println("GAME OVER");
            System.out.println(regions[0].getOwner().getName() + " won!");
            System.out.println("");
        }
        return gameOver;
    }
}