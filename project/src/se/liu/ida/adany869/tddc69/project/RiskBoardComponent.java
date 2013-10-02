package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RiskBoardComponent extends JComponent{
    private RiskWorld risk;
    private int height;
    private int width;
    private RegionComponentRelations relations;
    private ArrayList<int[]> regionPositions;

    private static final int INIT_HEIGHT = 830;
    private static final int INIT_WIDTH = 1400;

    public RiskBoardComponent(RiskWorld risk, int height, int width) {
        this.setLayout(null);
        this.risk = risk;
        this.height = height;
        this.width = width;
        relations = new RegionComponentRelations();
        setRegionPositions();
        addRegions();
        setupRegionComponentRelations();
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "attack");
        this.getActionMap().put("attack", attack);
    }

    public RiskBoardComponent(RiskWorld risk) {
        this(risk, INIT_HEIGHT, INIT_WIDTH);
    }

    public RiskWorld getRisk() {
        return risk;
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

    private void drawRelations(Graphics2D g2) {
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

    private void drawPlayers(Graphics2D g2) {
        Player player1 = risk.getPlayers()[0];

        g2.setColor(player1.getColor());
        g2.drawRect(20, 100, 200, 100);

        Player player2 = risk.getPlayers()[1];

        g2.setColor(player2.getColor());
        g2.drawRect(20, 300, 200, 100);
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
        this.relations = new RegionComponentRelations();
        for (Region region : risk.getRegions()) {
            ArrayList<Region> neighbourRegions = region.getNeighbours();
            RegionComponent regionComponentA = getRegionComponent(region);
            RegionComponent regionComponentB = null;
            for (int i = 0; i < neighbourRegions.size(); i++) {
                 this.relations.addRelation(regionComponentA, getRegionComponent(neighbourRegions.get(i)));
            }
        }
        this.add(this.relations);
    }

    private final void updateComponent(){
        this.repaint();
    }

    final private Action attack = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("asd");
            Region attacker = risk.getRegions()[0];
            Region attacked = risk.getRegions()[0].getNeighbours().get(0);
            //attacker.attack(attacked);
            /*getRegionComponent(attacker).repaint();
            getRegionComponent(attacked).repaint();*/
            updateComponent();
        }
    };
}
