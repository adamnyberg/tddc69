package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

/**
 * The main class. Run this to start the game.
 */
public final class Run {
    private Run() {
    }

    public static void main(String[] args) {
        int numberOfPlayers = StartMenu.numberOfPlayersOptionPane();
        ArrayList<String> namesOfPlayers = StartMenu.nameOfPlayersOptionPane(numberOfPlayers);
        Map map = new Map(namesOfPlayers);
        //Doesn't have to save the RiskFrame in a variable. It will handle itself.
        //noinspection ResultOfObjectAllocationIgnored
        new RiskFrame(new RiskWorld(map));
    }
}
