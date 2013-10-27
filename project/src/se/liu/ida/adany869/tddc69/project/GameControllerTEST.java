package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.Networking.RiskClient;

public class GameControllerTEST {
    private RiskWorld risk;
    private boolean isLocalGame;

    public GameControllerTEST(RiskWorld risk, boolean localGame) {
        this.risk = risk;
        isLocalGame = localGame;
    }

    public GameControllerTEST(RiskWorld risk) {
        this(risk, true);
    }

    public void restartGame(){
        if (isLocalGame){
            Run.main(null);
        }
        else {
            RiskClient.sendMessage("restart", risk.getActivePlayer().getName());
        }

    }

    public void quitGame(){

    }
}
