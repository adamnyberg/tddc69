package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.Region;
import se.liu.ida.adany869.tddc69.project.regions.RegionComponent;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class RiskBoardComponent extends JComponent implements Observer {
    private RiskWorld risk;
    private int height;
    private int width;
    private RegionRelationsComponent relations;
    private Collection<RegionComponent> regionComponents = new ArrayList<>();
    private List<int[]> regionPositions;
    private static final int INIT_HEIGHT = 830;
    private static final int INIT_WIDTH = 1400;
    private static final int[][] REGION_POSITIONS = {{100, 30}, {300, 50}, {50, 300}, {250, 800}, {500, 20},
            {300, 1030}, {550, 800}, {50, 900}, {300, 350}, {550, 300}, {550, 460},
            {480, 1280}, {190, 1230}, {10, 1250}};

    public RiskBoardComponent(RiskWorld risk, int height, int width) {
        this.setLayout(null);
        this.risk = risk;
        this.height = height;
        this.width = width;
        this.relations = new RegionRelationsComponent(risk);
        setRegionPositions();
        addRegions();
        setupRegionComponentRelations();
        this.risk.addObserver(this);
    }

    public RiskBoardComponent(RiskWorld risk) {
        this(risk, INIT_HEIGHT, INIT_WIDTH);
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    //Override to make it as we want it.
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //TODO: relations shouldn't have to call update itself
        this.relations.update(g, this.getRegionComponent(risk.getFocused()));
    }

    private void addRegions(){
        for (int i = 0; i < risk.getRegions().length; i++) {
            Region region = risk.getRegions()[i];
            int[] regionPos = regionPositions.get(i);
            RegionComponent regionComponent = new RegionComponent(region,
                    regionPos[0], regionPos[1],
                    risk.getRegionController(),
                    risk.getContinentsWhichContains(region)
            );
            //risk.getRegionController().mapRegionToComponent(region, regionComponent);

            this.regionComponents.add(regionComponent);
            this.add(regionComponent);
        }
    }

    public RegionComponent getRegionComponent(Region region){
        for (RegionComponent regionComponent : regionComponents) {
            if (regionComponent.getRegion().equals(region)){
                return regionComponent;
            }
        }
        return null;
    }

    public void setupRegionComponentRelations(){
        this.relations = new RegionRelationsComponent(risk);
        for (Region region : risk.getRegions()) {
            Iterable<Region> neighbourRegions = region.getNeighbours();
            RegionComponent regionComponentA = getRegionComponent(region);
            for (Region neighbourRegion : neighbourRegions) {
                this.relations.addRelation(regionComponentA, getRegionComponent(neighbourRegion));
            }
        }
        this.add(this.relations);
    }

    private void setRegionPositions() {
        this.regionPositions = Arrays.asList(REGION_POSITIONS);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
