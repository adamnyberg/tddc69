package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;

public class TetrisComponent extends JComponent {
    private Board board;

    private int height;
    private int width;

    private static final int INIT_HEIGHT = 800;
    private static final int INIT_WIDTH = 600;

    public TetrisComponent(Board board) {
        this.board = board;
        this.height = INIT_HEIGHT;
        this.width = INIT_WIDTH;
    }

    public TetrisComponent(Board board, int height, int width) {
        this.board = board;
        this.height = height;
        this.width = width;
    }

    /*public Object getPreferredSize() {
        return null;
    }*/

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(height, width);
    }

    public void paintComponent() {
        drawBackground();
        //drawSquares();
    }

    public void drawBackground() {
        JTextArea text = new JTextArea(height, width);
        text.setText("hej");
        this.add(text);
    }
}
