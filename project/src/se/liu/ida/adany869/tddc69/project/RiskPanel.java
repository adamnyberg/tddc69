package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;

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

    }

    private void addRegions(){
        for (Region region : risk.getRegions()) {
            this.add(new RegionComponent(region), "span wrap");
        }
    }
}
