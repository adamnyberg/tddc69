package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener {
    private Board board;

    private int height;
    private int width;

    private static final int INIT_HEIGHT = 800;
    private static final int INIT_WIDTH = 600;

    private EnumMap<SquareType, Color> colors= new EnumMap<SquareType, Color>(SquareType.class);

    public TetrisComponent(Board board) {
        this(board, INIT_HEIGHT, INIT_WIDTH);
        colors.put(SquareType.EMPTY, new Color(0,0,0,0));
        colors.put(SquareType.OUTSIDE, Color.gray);
        colors.put(SquareType.I, Color.red);
        colors.put(SquareType.J, Color.pink);
        colors.put(SquareType.L, Color.green);
        colors.put(SquareType.O, Color.blue);
        colors.put(SquareType.S, Color.cyan);
        colors.put(SquareType.T, Color.orange);
        colors.put(SquareType.Z, Color.yellow);
    }

    public TetrisComponent(Board board, int height, int width) {
        this.board = board;
        this.height = height;
        this.width = width;
        //this.add(new SquareComponent(SquareType.S, 10));
    }

    public void boardChanged(){

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
        int sizeX = this.width/board.getWidth();
        int sizeY= this.height/board.getHeight();
        int posX;
        int posY;
        for (int height = 0;  height < board.getHeight(); height++) {
            for (int width = 0; width < board.getWidth(); width++) {
                SquareType squareType = board.getSquare(height, width);
                posX = width*600/15-2;
                posY = height*800/15-2;
                g2.setColor(colors.get(squareType));
                g2.fillRect(posX, posY, sizeX, sizeY);
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

