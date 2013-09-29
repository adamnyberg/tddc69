package se.liu.ida.adany869.tddc69.project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    public void mouseClicked(MouseEvent e){
        ((RegionComponent) e.getSource()).getRegion().attack(
                ((RegionComponent) e.getSource()).getRegion().getNeighbours().get(0));
        System.out.println(((RegionComponent) e.getSource()).getRegion().getName());
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
