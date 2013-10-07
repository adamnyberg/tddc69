package se.liu.ida.adany869.tddc69.project;

public class RiskWorld {
    private Region[] regions;
    private Player[] players;
    private String actionState = "reinforce";
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

    public String getActionState() {
        return actionState;
    }

    public void setActionState(String actionState) {
        this.actionState = actionState;
    }

    public void switchPlayer(){
        System.out.println("switchPlayer");
        this.actionState = "reinforce";
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.isActive()){
                System.out.println("Player " + player.getName() + " was active");
                player.setActive(false);
                if (i == players.length-1){
                    players[0].setActive(true);
                    players[0].addReinforcement();
                }
                else{
                    players[i+1].setActive(true);
                    players[i+1].addReinforcement();
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