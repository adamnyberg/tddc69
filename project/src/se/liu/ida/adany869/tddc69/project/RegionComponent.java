package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;

public class RegionComponent extends JComponent{

    private int height;
    private int width;

    private static final int INIT_HEIGHT = 100;
    private static final int INIT_WIDTH = 100;

    public RegionComponent(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public RegionComponent() {
        this(INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fillRect(0,0, height, width);
    }
}
