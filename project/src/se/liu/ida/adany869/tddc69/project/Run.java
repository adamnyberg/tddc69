package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public final class Run {
    private Run() {
    }

    public static void main(String[] args) {
        int numberOfPlayers = StartMenu.numberOfPlayersOptionPane();
        ArrayList<String> namesOfPlayers = StartMenu.nameOfPlayersOptionPane(numberOfPlayers);
        for (String s : namesOfPlayers) {
            System.out.println(s);
        }
        Map map = new Map(namesOfPlayers);
        //Doesn't have to save the CardsPane in a variable. It will handle itself.
        //noinspection ResultOfObjectAllocationIgnored
        new RiskFrame(new RiskWorld(map));
    }
}
