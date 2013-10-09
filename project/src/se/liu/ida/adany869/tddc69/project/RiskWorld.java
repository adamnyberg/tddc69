package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.state.ActionState;
import se.liu.ida.adany869.tddc69.project.state.ReinforceState;
import se.liu.ida.adany869.tddc69.project.regions.Region;
import se.liu.ida.adany869.tddc69.project.regions.RegionController;

public class RiskWorld {
    private Region[] regions;
    private Player[] players;
    private ActionState actionState = new ReinforceState(this);
    public RegionController regionController = new RegionController(this);
    private Region focused;
    private Player activePlayer;

    public RiskWorld(Region[] regions, Player[] players) {
        this.regions = regions;
        this.players = players;
        activePlayer = this.players[0];
    }

    public Region[] getRegions() {
        return regions;
    }

    public Player[] getPlayers() {
        return players;
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
    }

    public void resetFocus(){
        if(this.hasFocused()){
            focused.setFocused(false);
            focused = null;
        }
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void switchPlayer(){
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
                activePlayer = nextPlayer;
                return;
            }
        }
    }

    public boolean checkGameOver() {
        boolean gameOver = true;
        for (int i = 0; i < regions.length - 1; i++) {
            if (regions[i].getPlayer() != regions[i + 1].getPlayer()) {
                gameOver = false;
            }
        }
        if (gameOver) {
            System.out.println("");
            System.out.println("GAME OVER");
            System.out.println(regions[0].getPlayer().getName() + " won!");
            System.out.println("");
        }
        return gameOver;
    }
}