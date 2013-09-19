package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;

public class SquareComponent extends JComponent{
    private SquareType square;
    private int cubeSize;
    private static int border = 1;

    public SquareComponent(SquareType square, int cubeSize) {
        this.square = square;
        this.cubeSize = cubeSize;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fillRect(0,0, cubeSize, cubeSize);
    }
}
