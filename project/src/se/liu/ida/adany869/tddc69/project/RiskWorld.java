package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.state.ActionState;
import se.liu.ida.adany869.tddc69.project.state.ReinforceState;

public class RiskWorld {
    private Region[] regions;
    private Player[] players;
    private ActionState actionState = new ReinforceState();
    private String actionStringState = "reinforce";
    private int tradedCards = 0;
    public RegionController regionController = new RegionController(this);

    public RiskWorld(Region[] regions, Player[] players) {
        this.regions = regions;
        this.players = players;
    }

    public Region[] getRegions() {
        return regions;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getTradedCards() {
        return tradedCards;
    }

    public void increaseTradedCards() {
        this.tradedCards++;
    }

    public ActionState getActionState() {
        return actionState;
    }

    public void setActionState(ActionState actionState) {
        this.actionState = actionState;
    }

    public String getActionStringState() {
        return actionStringState;
    }

    public void setActionStringState(String actionStringState) {
        this.actionStringState = actionStringState;
    }

    public void switchPlayer(){
        this.actionStringState = "reinforce";
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.isActive()){
                player.setActive(false);
                if (i == players.length-1){
                    players[0].setActive(true);
                    players[0].addReinforcement();
                    players[0].addCard();
                }
                else{
                    players[i+1].setActive(true);
                    players[i+1].addReinforcement();
                    players[i + 1].addCard();
                }
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