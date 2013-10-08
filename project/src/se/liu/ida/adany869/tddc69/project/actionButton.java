package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActionButton extends JButton {
    private boolean isActive;

    public ActionButton(boolean active,
                        AbstractAction abstractAction) {
        super(abstractAction);
        isActive = active;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isActive){
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            g2.setColor(new Color(255,215,0));
            g2.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    public void setActive(boolean active) {
        isActive = active;
        repaint();
    }
}
