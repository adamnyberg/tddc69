package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener {
    private Board board;

    private int height;
    private int width;

    private static final int INIT_HEIGHT = 400;
    private static final int INIT_WIDTH = 300;

    private EnumMap<SquareType, Color> colors= new EnumMap<SquareType, Color>(SquareType.class);

    public TetrisComponent(Board board) {
        this(board, INIT_HEIGHT, INIT_WIDTH);
    }

    public TetrisComponent(Board board, int height, int width) {
        this.board = board;
        this.height = height;
        this.width = width;

        colors.put(SquareType.EMPTY, Color.black);
        colors.put(SquareType.OUTSIDE, Color.magenta);
        colors.put(SquareType.I, Color.red);
        colors.put(SquareType.J, Color.pink);
        colors.put(SquareType.L, Color.green);
        colors.put(SquareType.O, Color.blue);
        colors.put(SquareType.S, Color.cyan);
        colors.put(SquareType.T, Color.orange);
        colors.put(SquareType.Z, Color.yellow);
        colors.put(SquareType.A, Color.pink);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        this.getActionMap().put("moveLeft", moveLeft);
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        this.getActionMap().put("moveRight", moveRight);
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "rotateRight");
        this.getActionMap().put("rotateRight", rotateRight);
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "rotateLeft");
        this.getActionMap().put("rotateLeft", rotateLeft);
        this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "down");
        this.getActionMap().put("down", doOneStep);

        final Timer clockTimer = new Timer(500, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();
        board.addBoardListener(this);
    }

    public void boardChanged(){
        repaint();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawSquares(g2);
    }

    public void drawSquares(Graphics g2){
        int sizeX = this.width/board.getWidth();
        int sizeY= this.height/board.getHeight();
        int posX;
        int posY;
        for (int height = Board.getStartAreaSize();  height < board.getFullHeight(); height++) {
            for (int width = Board.getBorderWidth(); width < board.getFullWidth(); width++) {
                SquareType squareType = board.getAllBoardSquare(height, width);
                posX = (width-Board.getBorderWidth())*sizeX;
                posY = (height-Board.getStartAreaSize())*sizeY;
                g2.setColor(colors.get(squareType));
                g2.fillRect(posX, posY, sizeX, sizeY);
            }
        }
    }

    public void drawBackground(Graphics g2) {
        g2.setColor(Color.blue);
        g2.fillRect(0,0, width, height);
    }

    final private Action moveLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveLeft();
        }
    };

    final private Action moveRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveRight();
        }
    };

    final private Action rotateRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.rotateRight();
        }
    };

    final private Action rotateLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.rotateLeft();
        }
    };

    final Action doOneStep = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            board.tick();
        }
    };

}

