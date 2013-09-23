package se.liu.ida.adany869.tddc69.project;

import java.awt.*;

public class RiskComponent {
    private RiskWorld risk;
    private int height;
    private int width;

    private static final int INIT_HEIGHT = 800;
    private static final int INIT_WIDTH = 1000;

    public RiskComponent(RiskWorld risk, int height, int width) {
        this.risk = risk;
        this.height = height;
        this.width = width;
    }

    public RiskComponent(RiskWorld risk) {
        this(risk, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fillRect(0,0, height, width);
    }
}
