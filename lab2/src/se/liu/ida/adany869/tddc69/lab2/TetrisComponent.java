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
        this(board, INIT_HEIGHT, INIT_WIDTH);
    }

    public TetrisComponent(Board board, int height, int width) {
        this.board = board;
        this.height = height;
        this.width = width;
        //this.add(new SquareComponent(SquareType.S, 10));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        drawBackground(g2);
        drawText(g2);
        drawSquares(g2);
    }

    public void drawSquares(Graphics g2){
        for (int height = 0;  height < board.getHeight(); height++) {
            for (int width = 0; width < board.getWidth(); width++) {
                
            }
        }
    }

    public void drawBackground(Graphics g2) {
        g2.setColor(Color.blue);
        g2.fillRect(0,0, width, height);
    }

    public void drawText(Graphics g2) {

    }
}

