package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PlayerComponent extends JComponent implements Observer {
    private Player player = null;
    private JLabel armyReserveLabel;
    private int yPos;
    private int xPos;
    private static int HEIGHT = 60;
    private static int WIDTH = 200;
    private static int BORDER_SIZE = 5;

    public PlayerComponent(Player player) {
        this.setLayout(new MigLayout());
        this.player = player;
        this.player.addObserver(this);
        this.armyReserveLabel = new JLabel("Reserv: " + Integer.toString(player.getArmyReserve()));
        this.add(new JLabel(player.getName()), "wrap");
        this.add(this.armyReserveLabel);
        //this.yPos = yPos;
        //this.xPos = xPos;

        this.setBounds(xPos, yPos, WIDTH + 2 * BORDER_SIZE, HEIGHT + 2 * BORDER_SIZE);
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

        if (this.player.isActive()) g2.setColor(new Color(255,215,0));
        else g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(BORDER_SIZE));
        g2.drawRect(0, 0, WIDTH, HEIGHT);

    }

    @Override
    public void update(Observable o, Object arg) {
        this.armyReserveLabel.setText("Reserv: " + Integer.toString(player.getArmyReserve()));
        this.repaint();
    }

}
