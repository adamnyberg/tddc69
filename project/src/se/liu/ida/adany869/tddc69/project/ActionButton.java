package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;

/**
 * The three buttons that is used for setting the action state of the game. Ordinary JButtons with
 * an added border to clarify which one is active.
 */
public class ActionButton extends JButton {
    private boolean isActive;
    private static final int ARC_WIDTH = 20;
    private static final int ARC_HEIGHT = 20;
    private static final int BORDER_WIDTH = 5;

    public ActionButton(boolean active, AbstractAction abstractAction) {
        super(abstractAction);
        isActive = active;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isActive){
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(BORDER_WIDTH));
            g2.setColor(Color.blue);
            g2.drawRoundRect(BORDER_WIDTH/2, BORDER_WIDTH/2, getWidth()-BORDER_WIDTH,
                    getHeight()-BORDER_WIDTH, ARC_WIDTH, ARC_HEIGHT);
        }
    }

    public void setActive(boolean active) {
        isActive = active;
        repaint();
    }
}

