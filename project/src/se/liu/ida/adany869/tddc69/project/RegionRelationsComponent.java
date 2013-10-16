package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.RegionComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class RegionRelationsComponent extends JComponent{
    private Collection<RegionComponent[]> relations = new ArrayList<>();
    private RiskWorld risk;
    private RegionComponent focused = null;

    public RegionRelationsComponent(RiskWorld risk) {
        this.risk = risk;
    }

    private boolean hasRelation(RegionComponent regionA, RegionComponent regionB){
        for (RegionComponent[] relation : relations) {
            if (Arrays.equals(relation, new RegionComponent[]{regionA, regionB})){
                return true;
            }
        }
        return false;
    }

    public void update(Graphics g, RegionComponent regionComponent){
        focused = regionComponent;
        paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        for (RegionComponent[] regionTupel : relations) {
            g2.setColor(Color.BLACK);
            RegionComponent regionComponent1 = regionTupel[0];
            RegionComponent regionComponent2 = regionTupel[1];

            if ((this.focused == regionComponent1 || this.focused == regionComponent2) &&
                    (risk.getActionState().isRelevantNeighbour(this.focused.getRegion(), regionComponent1.getRegion()) ||
                    risk.getActionState().isRelevantNeighbour(this.focused.getRegion(), regionComponent2.getRegion())))
                g2.setColor(Color.RED);

            g2.drawLine(regionComponent1.getX()+regionComponent1.getWidth()/2,
                    regionComponent1.getY()+regionComponent1.getHeight()/2,
                    regionComponent2.getX()+regionComponent2.getWidth()/2,
                    regionComponent2.getY()+regionComponent2.getHeight()/2);
        }
    }

    public void addRelation(RegionComponent regionA, RegionComponent regionB){
        if (!hasRelation(regionA, regionB)){
            RegionComponent[] objects = new RegionComponent[]{regionA, regionB};
            relations.add(objects);
        }
    }
}
