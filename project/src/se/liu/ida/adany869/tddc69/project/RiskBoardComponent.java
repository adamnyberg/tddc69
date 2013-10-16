package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.Region;
import se.liu.ida.adany869.tddc69.project.regions.RegionComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class RiskBoardComponent extends JComponent implements Observer {
    private RiskWorld risk;
    private int height;
    private int width;
    private RegionRelationsComponent relations;
    private Collection<RegionComponent> regionComponents = new ArrayList<>();
    private ArrayList<int[]> regionPositions;
    private static final int INIT_HEIGHT = 830;
    private static final int INIT_WIDTH = 1400;

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

    @Override
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
        ArrayList<int[]> regionPos = new ArrayList<>();

        regionPos.add(new int[]{100, 30});
        regionPos.add(new int[]{300, 50});
        regionPos.add(new int[]{50, 300});
        regionPos.add(new int[]{250, 800});
        regionPos.add(new int[]{500, 20});
        regionPos.add(new int[]{300, 1030});
        regionPos.add(new int[]{550, 800});
        regionPos.add(new int[]{50, 900});
        regionPos.add(new int[]{300, 350});
        regionPos.add(new int[]{550, 300});
        regionPos.add(new int[]{550, 460});
        regionPos.add(new int[]{480, 1280});
        regionPos.add(new int[]{190, 1230});
        regionPos.add(new int[]{10, 1250});

        this.regionPositions = regionPos;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
