package se.liu.ida.adany869.tddc69.project;

import java.awt.*;

public final class GameController {
    public static final void restart(){
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
        System.out.println("restart");
        Run.main(null);
    }

    public static final void quit(){
        System.exit(0);
    }
}
