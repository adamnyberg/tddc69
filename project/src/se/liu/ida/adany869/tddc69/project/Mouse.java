package se.liu.ida.adany869.tddc69.project;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    private RegionController regionController;

    public void setRegionController(RegionController regionController){
        this.regionController = regionController;
    }

    public void mouseClicked(MouseEvent e){


        ((RegionComponent) e.getSource()).switchFocused();
        ((RegionComponent) e.getSource()).getRegion().getPlayer().
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