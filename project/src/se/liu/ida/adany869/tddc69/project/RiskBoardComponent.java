package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.Region;
import se.liu.ida.adany869.tddc69.project.regions.RegionComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RiskBoardComponent extends JComponent{
    private RiskWorld risk;
    private int height;
    private int width;
    private RegionRelationsComponent relations;
    private ArrayList<int[]> regionPositions;

    private static final int INIT_HEIGHT = 830;
    private static final int INIT_WIDTH = 1400;

    public RiskBoardComponent(RiskWorld risk, int height, int width) {
        this.setLayout(null);
        this.risk = risk;
        this.height = height;
        this.width = width;
        relations = new RegionRelationsComponent();
        setRegionPositions();
        addRegions();
        setupRegionComponentRelations();
    }

    public RiskBoardComponent(RiskWorld risk) {
        this(risk, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //TODO: relations shouldn't have to call paintComponent itself
        this.relations.paintComponent(g);
    }

    private void addRegions(){
        for (int i = 0; i < risk.getRegions().length; i++) {
            Region region = risk.getRegions()[i];
            int[] regionPos = regionPositions.get(i);
            RegionComponent regionComponent = new RegionComponent(region, regionPos[0], regionPos[1], risk.regionController, i);
            risk.regionController.mapRegionToComponent(region, regionComponent);
            this.add(regionComponent);
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
        ArrayList<RegionComponent> components = getRegionComponents(); //TODO: cache regionComponents
        for (int i = 0; i < components.size(); i++) {
             if (components.get(i).getRegion().equals(region)){
                 return components.get(i);
             }

        }
        return null;
    }

    public void setupRegionComponentRelations(){
        this.relations = new RegionRelationsComponent();
        for (Region region : risk.getRegions()) {
            ArrayList<Region> neighbourRegions = region.getNeighbours();
            RegionComponent regionComponentA = getRegionComponent(region);
            for (int i = 0; i < neighbourRegions.size(); i++) {
                 this.relations.addRelation(regionComponentA, getRegionComponent(neighbourRegions.get(i)));
            }
        }
        this.add(this.relations);
    }

    private void setRegionPositions() {
        ArrayList<int[]> regionPos = new ArrayList<int[]>();

        int[] pos0 = {100, 30};
        regionPos.add(pos0);

        int[] pos1 = {300, 50};
        regionPos.add(pos1);

        int[] pos2 = {50, 300};
        regionPos.add(pos2);

        int[] pos3 = {250, 800};
        regionPos.add(pos3);

        int[] pos4 = {500, 20};
        regionPos.add(pos4);

        int[] pos5 = {300, 1030};
        regionPos.add(pos5);

        int[] pos6 = {550, 800};
        regionPos.add(pos6);

        int[] pos7 = {50, 900};
        regionPos.add(pos7);

        int[] pos = {300, 350}; // 8
        regionPos.add(pos);

        pos =  new int[]{550, 300}; // 9
        regionPos.add(pos);

        pos =  new int[]{550, 460}; // 10
        regionPos.add(pos);

        pos =  new int[]{480, 1280}; // 11
        regionPos.add(pos);

        pos =  new int[]{190, 1230}; // 12
        regionPos.add(pos);

        pos =  new int[]{10, 1250}; // 13
        regionPos.add(pos);

        this.regionPositions = regionPos;
    }
}
