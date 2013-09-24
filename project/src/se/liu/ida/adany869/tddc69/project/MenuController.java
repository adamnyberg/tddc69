package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Harald
 * Date: 2013-09-24
 * Time: 09:34
 * To change this template use File | Settings | File Templates.
 */
public class MenuController extends AbstractController implements Observer {

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
}
