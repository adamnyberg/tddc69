package se.liu.ida.adany869.tddc69.project;

import java.awt.*;

/**
 * An utility class which holds the methods for quiting and restarting the game.
 */
public final class GameController {
    private GameController() {
    }

    public static void restart(){
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
        System.out.println("restart");
        Run.main(null);
    }

    public static void quit(){
        System.exit(0);
    }
}
