package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuController extends AbstractController implements MenuObserver {

    public MenuController() {

    }

    public void update(ActionEvent e){
        invokeMethod(e.getActionCommand(), null);
    }

    public void Quit(){
        String ObjButtons[] = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to exit?", "Tetris",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            System.exit(0);
        }
    }

    public void Restart(){
        String ObjButtons[] = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to restart game and delete current play?", "Tetris",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            Run.main(null);
        }
    }
}
