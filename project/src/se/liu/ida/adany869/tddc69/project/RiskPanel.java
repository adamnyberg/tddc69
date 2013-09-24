package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RiskPanel extends JPanel{
    private RiskWorld risk;
    private int height;
    private int width;
    private RegionComponentRelations relations;

    private static final int INIT_HEIGHT = 800;
    private static final int INIT_WIDTH = 1000;

    public RiskPanel(RiskWorld risk, int height, int width) {
        this.risk = risk;
        this.height = height;
        this.width = width;
        relations = new RegionComponentRelations();

        addRegions();
        setupRegionComponentRelations();
    }

    public RiskPanel(RiskWorld risk) {
        this(risk, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0,0,0,0));
        g2.fillRect(0,0, width, height);ß

        drawBounds(g2);
    }

    private void addRegions(){
        for (Region region : risk.getRegions()) {
            this.add(new RegionComponent(region), "span wrap");
        }
    }

    private void drawBounds(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));


        for (RegionComponent[] regionTupel : this.relations.getRelations()) {

            RegionComponent regionComponent1 = regionTupel[0];
            RegionComponent regionComponent2 = regionTupel[1];
            g2.drawLine(regionComponent1.getX()+regionComponent1.getWidth()/2,
                    regionComponent1.getY()+regionComponent1.getHeight()/2,
                    regionComponent2.getX()+regionComponent2.getWidth()/2,
                    regionComponent2.getY()+regionComponent2.getHeight()/2);
        }
    }

    private ArrayList<RegionComponent> getRegionComponents() { // TODO: maybe not need this
        ArrayList<RegionComponent> regionComponents = new ArrayList<RegionComponent>();

        for (Component component : this.getComponents()) {
            if (component instanceof RegionComponent) { // TODO: bad OO
                regionComponents.add( (RegionComponent) component);
            }
        }
        return regionComponents;
    }

    public RegionComponent getRegionComponent(Region region){
        ArrayList<RegionComponent> components = getRegionComponents(); //TODO: chache regionComponents
        for (int i = 0; i < components.size(); i++) {
             if (components.get(i).getRegion().equals(region)){
                 return components.get(i);
             }

        }
        return null;
    }

    public void setupRegionComponentRelations(){
        this.relations = new RegionComponentRelations();
        for (Region region : risk.getRegions()) {
            ArrayList<Region> neighbourRegions = region.getNeighbours();
            RegionComponent regionComponentA = getRegionComponent(region);
            RegionComponent regionComponentB = null;
            for (int i = 0; i < neighbourRegions.size(); i++) {
                 relations.addRelation(regionComponentA, getRegionComponent(neighbourRegions.get(i)));
            }
            /*for (Component component : this.getRegionComponents()) {

                    if (((RegionComponent) component).getRegion() == neighbourRegions.get(0)){
                        regionComponentA = (RegionComponent) component;
                    }
                    else if (((RegionComponent) component).getRegion() == neighbourRegions.get(1)){
                        regionComponentB = (RegionComponent) component;
                    }
                if (regionComponentA != null && regionComponentB != null){
                    this.relations.addRelation(regionComponentA, regionComponentB);
                }

            }*/


        }
    }
}
