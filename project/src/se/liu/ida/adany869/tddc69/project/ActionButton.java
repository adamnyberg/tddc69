package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;

public class ActionButton extends JButton {
    private boolean isActive;

    public ActionButton(boolean active, AbstractAction abstractAction) {
        super(abstractAction);
        isActive = active;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isActive){
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.blue);
            // Active border. Magic numbers for aesthetics.
            g2.drawRoundRect(3, 3, getWidth()-8, getHeight()-8, 20, 20);
        }
    }

    public void setActive(boolean active) {
        isActive = active;
        repaint();
    }
}

