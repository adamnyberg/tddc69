package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class RegionComponent extends JComponent{

    private Region region;
    private int height;
    private int width;
    private JLabel armyText = new JLabel();

    private static final int INIT_HEIGHT = 100;
    private static final int INIT_WIDTH = 100;

    public RegionComponent(Region region, int height, int width) {
        this.setLayout(new MigLayout());
        this.region = region;
        this.height = height;
        this.width = width;
        armyText.setText(Integer.toString(region.getArmies()));
        this.add(armyText, "span");
    }

    public RegionComponent(Region region) {
        this(region, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fillRect(0,0, height, width);
    }

    public Region getRegion(){
        return region;
    }



}
