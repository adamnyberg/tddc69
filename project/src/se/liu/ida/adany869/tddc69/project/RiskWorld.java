package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.continent.Continent;
import se.liu.ida.adany869.tddc69.project.state.ActionState;
import se.liu.ida.adany869.tddc69.project.state.ReinforceState;
import se.liu.ida.adany869.tddc69.project.regions.Region;
import se.liu.ida.adany869.tddc69.project.regions.RegionController;

import java.util.ArrayList;
import java.util.Observable;

public class RiskWorld extends Observable{
    private Region[] regions;
    private Player[] players;
    private Continent[] continents;
    private ActionState actionState = new ReinforceState(this);
    public RegionController regionController = new RegionController(this);
    private Region focused;
    private Player activePlayer;

    public RiskWorld(Map map) {
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

    public ArrayList<Continent> getContinentsWhichContains(Region region){
        ArrayList<Continent> containsRegion = new ArrayList<>();
        for (Continent continent : this.continents) {
            if (continent.containsRegion(region)) containsRegion.add(continent);
        }
        return containsRegion;
    }

    public ActionState getActionState() {
        return actionState;
    }

    public void setActionState(ActionState actionState) {
        this.actionState = actionState;
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
        Player nextPlayer;
        this.resetFocus();
        this.actionState = new ReinforceState(this);
        if (activePlayer.hasAttackedAndWon()) activePlayer.addCard();
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.isActive()){
                player.setActive(false);
                if (i == players.length-1){
                    nextPlayer = players[0];
                }
                else{
                    nextPlayer = players[i+1];
                }
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
            if (regions[i].getOwner() != regions[i + 1].getOwner()) {
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