package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;

public class PlayerComponent extends JComponent{
    private Player player = null;

    public PlayerComponent(Player player) {
        this.player = player;
        this.add(new JLabel(player.getName()), "");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 80);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.darkGray);
        g2.fillRect(0,0, 30, 80);
    }
}
