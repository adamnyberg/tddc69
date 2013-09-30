package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class PlayerComponent extends JComponent{
    private Player player = null;
    private int yPos;
    private int xPos;
    private static int HEIGHT = 40;
    private static int WIDTH = 200;
    private static int BORDER_SIZE = 5;

    public PlayerComponent(Player player, int yPos, int xPos) {
        this.setLayout(new MigLayout());
        this.player = player;
        this.add(new JLabel(player.getName()));
        this.yPos = yPos;
        this.xPos = xPos;

        this.setBounds(xPos, yPos, WIDTH + 2*BORDER_SIZE, HEIGHT + 2*BORDER_SIZE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(player.getColor());
        g2.fillRect(0,0, WIDTH, HEIGHT);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(BORDER_SIZE));
        g2.drawRect(0,0, WIDTH, HEIGHT);
    }
}
