package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RiskComponent extends JComponent{
    private RiskWorld risk;
    private int height;
    private int width;
    private RegionComponentRelations relations;
    private ArrayList<int[]> regionPositions;

    private static final int INIT_HEIGHT = 830;
    private static final int INIT_WIDTH = 1400;

    public RiskComponent(RiskWorld risk, int height, int width) {
        this.setLayout(null);
        this.risk = risk;
        this.height = height;
        this.width = width;
        relations = new RegionComponentRelations();
        setRegionPositions();

        addPlayersInfo();
        addRegions();
        setupRegionComponentRelations();

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "attack");
        this.getActionMap().put("attack", attack);
    }

    public RiskComponent(RiskWorld risk) {
        this(risk, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(INIT_WIDTH, INIT_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        this.setBounds(0,0,width,height);

        drawRelations(g2);
        //drawPlayers(g2);
    }

    private void addRegions(){
        for (int i = 0; i < risk.getRegions().length; i++) {
            Region region = risk.getRegions()[i];
            int[] regionPos = regionPositions.get(i);
            RegionComponent regionComponent = new RegionComponent(region, regionPos[0], regionPos[1]);
            risk.regionController.mapRegionToComponent(region, regionComponent);
            this.add(regionComponent);
        }
    }

    private void addPlayersInfo() {
        this.add(new PlayerComponent(risk.getPlayers()[0], 30, 30));
        this.add(new PlayerComponent(risk.getPlayers()[1], 30, 1150));
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

        int[] pos3 = {300, 600};
        regionPos.add(pos3);

        int[] pos4 = {500, 20};
        regionPos.add(pos4);

        int[] pos5 = {500, 1000};
        regionPos.add(pos5);

        int[] pos6 = {550, 800};
        regionPos.add(pos6);

        int[] pos7 = {50, 900};
        regionPos.add(pos7);

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

    private final void updateComponent(){
        this.repaint();
    }

    final private Action attack = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("asd");
            Region attacker = risk.getRegions()[0];
            Region attacked = risk.getRegions()[0].getNeighbours().get(0);
            attacker.attack(attacked);
            /*getRegionComponent(attacker).repaint();
            getRegionComponent(attacked).repaint();*/
            updateComponent();
        }
    };
}
