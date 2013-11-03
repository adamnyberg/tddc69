package se.liu.ida.adany869.tddc69.project.regions;

import se.liu.ida.adany869.tddc69.project.GameController;
import se.liu.ida.adany869.tddc69.project.RiskWorld;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This controller listens to mouse clicks on the RegionComponents and calls the RiskWorld object, which holds
 * the current state of the game, which conducts the appropriate action. Its the communication channel between
 * the RegionComponents and the Regions.
 */
public class RegionController extends MouseAdapter{
    private RiskWorld risk;

    public RegionController(RiskWorld risk) {
        this.risk = risk;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        RegionComponent regionComponent = (RegionComponent) e.getSource();
        Region region = regionComponent.getRegion();
        risk.getAbstractState().onRegionClick(region);
        if (risk.checkGameOver()) {
            gameOver();
        }
    }

    private void gameOver() {
        String[] objButtons = {"restart", "quit"};
        int promptResult = JOptionPane.showOptionDialog(null,
                "Game Over\n" + risk.getRegions()[0].getOwner().getName() + " won!", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                objButtons, objButtons[1]);
        if (promptResult == 0) {
            GameController.restart();
        } else if (promptResult == 1) {
            GameController.quit();
        }
    }
}
