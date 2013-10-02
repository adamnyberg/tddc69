package se.liu.ida.adany869.tddc69.project;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class RegionController extends AbstractController implements MouseListener{
    private RiskWorld risk;
    private HashMap<Region, RegionComponent> regionToComponentMap = new HashMap<Region, RegionComponent>();
    private Region focused;


    public RegionController(RiskWorld risk) {
        this.risk = risk;
    }

    public void mapRegionToComponent(Region region, RegionComponent regionComponent){
        regionToComponentMap.put(region, regionComponent);
    }

    private void updateArmyRegionComponent(RegionComponent regionComponent){
        regionComponent.updateArmy();
    }

    public void update(ActionEvent e) {
        invokeMethod(e.getActionCommand(), null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        RegionComponent regionComponent = (RegionComponent) e.getSource();
        Region region = regionComponent.getRegion();
        if (risk.getActionState() == "reinforce" && region.getPlayer().isActive()) {
            System.out.println("reinforce state");
            region.getPlayer().addArmyToRegion(region);
        }
        else if (risk.getActionState() == "attack") {
            System.out.println("attack state");
            changeFocus(regionComponent);
        }

    }

    public void changeFocus(RegionComponent regionComponent){
        Region region = regionComponent.getRegion();
        if (focused == null){
            focused = region;
            System.out.println("Set focus");
            regionComponent.setFocused(true);
        }
        else if (region == focused){
            System.out.println("Reset focus");
            resetFocus();
            /*focused = null;
            regionComponent.setFocused(false);*/
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
}
