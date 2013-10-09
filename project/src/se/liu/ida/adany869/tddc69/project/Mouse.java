package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.RegionComponent;
import se.liu.ida.adany869.tddc69.project.regions.RegionController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
    private RegionController regionController;

    public void setRegionController(RegionController regionController){
        this.regionController = regionController;
    }

    public void mouseClicked(MouseEvent e){
        ((RegionComponent) e.getSource()).switchFocused();
        ((RegionComponent) e.getSource()).getRegion().getOwner().
                addArmyToRegion(((RegionComponent) e.getSource()).getRegion());
        //((RegionComponent) e.getSource()).getRegion().attack(
        //        ((RegionComponent) e.getSource()).getRegion().getNeighbours().get(0));
    }

    public void mouseReleased(MouseEvent e){
        //System.out.println(e);
    }

    public void mousePressed(MouseEvent e){
        //System.out.println(e);
    }

    public void mouseEntered(MouseEvent e){
        //System.out.println(e);
    }

    public void mouseExited(MouseEvent e){
        //System.out.println(e);
    }
}
