package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RiskPanel extends JPanel{
    private RiskWorld risk;
    private int height;
    private int width;

    private static final int INIT_HEIGHT = 800;
    private static final int INIT_WIDTH = 1000;

    public RiskPanel(RiskWorld risk, int height, int width) {
        this.risk = risk;
        this.height = height;
        this.width = width;
        addRegions();
        Component[] components = this.getComponents();

        for (int i = 0; i < components.length; i++) {
            Rectangle bounds = components[i].getBounds();
            System.out.println(bounds);
        }
    }

    public RiskPanel(RiskWorld risk) {
        this(risk, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0,0,0,0));
        g2.fillRect(0,0, width, height);

        Component[] components = this.getComponents();
        for (int i = 0; i < components.length; i++) {
            Rectangle bounds = components[i].getBounds();
            System.out.println(bounds);
        }

        drawBounds(g2);
    }

    private void addRegions(){
        for (Region region : risk.getRegions()) {
            this.add(new RegionComponent(region), "span wrap");
        }
    }

    private void drawBounds(Graphics2D g2) {
        g2.setColor(Color.BLACK);

        for (ArrayList<RegionComponent> regionTupel : this.getRegionsRelation()) {
            RegionComponent regionComponent1 = regionTupel.get(0);
            RegionComponent regionComponent2 = regionTupel.get(1);

            g2.drawLine(regionComponent1.getX(), regionComponent1.getY(), regionComponent2.getX(), regionComponent2.getY());
        }
    }

    private ArrayList<RegionComponent> getRegionComponents() { // TODO: maby not need this
        ArrayList<RegionComponent> regionComponents = new ArrayList<RegionComponent>();

        for (Component component : this.getComponents()) {
            if (component instanceof RegionComponent) { // TODO: bad OO
                regionComponents.add( (RegionComponent) component);
            }
        }
        return regionComponents;
    }
}
