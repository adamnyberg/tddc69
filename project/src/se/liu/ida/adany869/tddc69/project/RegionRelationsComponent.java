package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegionRelationsComponent extends JComponent{
    private ArrayList<RegionComponent[]> relations = new ArrayList<RegionComponent[]>();

    private boolean isInArray(RegionComponent regionA, RegionComponent regionB){
        for (int i = 0; i < relations.size(); i++) {
             if (relations.get(i).equals(new RegionComponent[]{regionA, regionB})){
                return true;
             }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));

        for (RegionComponent[] regionTupel : relations) {
            RegionComponent regionComponent1 = regionTupel[0];
            RegionComponent regionComponent2 = regionTupel[1];
            g2.drawLine(regionComponent1.getX()+regionComponent1.getWidth()/2,
                    regionComponent1.getY()+regionComponent1.getHeight()/2,
                    regionComponent2.getX()+regionComponent2.getWidth()/2,
                    regionComponent2.getY()+regionComponent2.getHeight()/2);
        }
    }

    public void addRelation(RegionComponent regionA, RegionComponent regionB){
        if (!isInArray(regionA, regionB)){
            RegionComponent[] objects = new RegionComponent[]{regionA, regionB};
            relations.add(objects);
        }
    }

    public ArrayList<RegionComponent[]> getRelations(){
        return relations;
    }

    public RegionComponent[] getRelation(int index){
        return relations.get(index);
    }
}
