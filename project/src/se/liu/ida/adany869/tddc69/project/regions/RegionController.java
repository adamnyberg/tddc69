package se.liu.ida.adany869.tddc69.project.regions;

import se.liu.ida.adany869.tddc69.project.RiskWorld;
import se.liu.ida.adany869.tddc69.project.Run;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegionController extends MouseAdapter {
    private RiskWorld risk;


    public RegionController(RiskWorld risk) {
        this.risk = risk;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        RegionComponent regionComponent = (RegionComponent) e.getSource();
        Region region = regionComponent.getRegion();
        risk.getActionState().doSomething(region);
        if (risk.checkGameOver()) {
            gameOver();
        }
    }

    private void gameOver() {
        String[] objButtons = {"Restart", "Quit"};
        int promptResult = JOptionPane.showOptionDialog(null,
                "Game Over\n" + risk.getRegions()[0].getOwner().getName() + " won!", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                objButtons, objButtons[1]);
        if (promptResult == 0) {
            Run.main(null);
        } else if (promptResult == 1) {
            System.exit(1);
        }
    }
}
