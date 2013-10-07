package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class RegionController extends AbstractController implements MouseListener{
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
        if (risk.getActionState().equals("reinforce") && region.getPlayer().isActive()) {
            region.getPlayer().addArmyToRegion(region);
        }
        else if (risk.getActionState().equals("attack")) {
            changeFocus(regionComponent);
            if (focused != null && !region.getPlayer().isActive() && region.isNeighbour(focused)){
                Battle battle = new Battle(focused, focused.getArmies()-1, region);
                battle.runBattle();
                resetFocus();
                if (risk.checkGameOver()) {
                    gameOver();
                }
            }
        }
        else if (risk.getActionState().equals("fortify")) {
            if (focused == null) {
                changeFocus(regionComponent);
                return;
            }
            if (region == focused) {
                resetFocus();
                return;
            }
            if (region.isNeighbour(focused) && region.getPlayer() == focused.getPlayer()){
                System.out.println("fortify");
            }
        }
    }

    public void changeFocus(RegionComponent regionComponent){
        Region region = regionComponent.getRegion();
        if (region.getPlayer().isActive()){
            resetFocus();
            focused = region;
            regionComponent.setFocused(true);
        }
    }

    public void resetFocus(){
        if (focused != null){
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
