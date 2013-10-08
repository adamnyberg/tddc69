package se.liu.ida.adany869.tddc69.project.regions;

import se.liu.ida.adany869.tddc69.project.AbstractController;
import se.liu.ida.adany869.tddc69.project.RiskBoardComponent;
import se.liu.ida.adany869.tddc69.project.RiskWorld;
import se.liu.ida.adany869.tddc69.project.Run;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class RegionController extends AbstractController implements MouseListener {
    private RiskWorld risk;
    private HashMap<Region, RegionComponent> regionToComponentMap = new HashMap<>();
    private Region focused;
    private RiskBoardComponent riskBoardComponent;


    public RegionController(RiskWorld risk) {
        this.risk = risk;
    }

    public void mapRegionToComponent(Region region, RegionComponent regionComponent){
        regionToComponentMap.put(region, regionComponent);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        RegionComponent regionComponent = (RegionComponent) e.getSource();
        Region region = regionComponent.getRegion();
        this.riskBoardComponent = (RiskBoardComponent) regionComponent.getParent();
        risk.getActionState().doSomething(region);
        if (risk.checkGameOver()) {
            gameOver();
        }
    }

    public void changeFocus(RegionComponent regionComponent){
        Region region = regionComponent.getRegion();
        if (region.getPlayer().isActive()){
            //resetFocus();
            focused = region;
            regionComponent.setFocused(true);
        }
    }

    public void resetFocus(){
        if (focused != null){
            for (Region neighbour : focused.getNeighbours()) {
                RegionComponent neighbourComponent = regionToComponentMap.get(neighbour);
                if (focused.getPlayer() == neighbour.getPlayer()) {
                    neighbourComponent.unHighlightNeighbours();
                }
            }

            RegionComponent regionComponent = regionToComponentMap.get(focused);
            focused = null;
            regionComponent.setFocused(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void gameOver() {
        String ObjButtons[] = {"Restart", "Quit"};
        int promptResult = JOptionPane.showOptionDialog(null,
                "Game Over\n" + risk.getRegions()[0].getPlayer().getName() + " won!", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (promptResult == 0) {
            Run.main(null);
        } else if (promptResult == 1) {
            System.exit(1);
        }
    }
}
