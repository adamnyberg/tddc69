package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.*;

public class ButtonBorder extends JComponent {
    private int width;
    private int height;

    public ButtonBorder(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(0,0,width,height);
    }
}
