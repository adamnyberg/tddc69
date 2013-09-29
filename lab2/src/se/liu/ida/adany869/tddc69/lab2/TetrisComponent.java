package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumMap;

/**
 * The graphical object of the game. Paints all squares.
 */
public class TetrisComponent extends JComponent implements BoardListener {
    private Board board;

    private int height;
    private int width;

    private static final int INIT_HEIGHT = 800;
    private static final int INIT_WIDTH = 600;
    private static final int UPDATE_RATE = 200;

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

        final Timer clockTimer = new Timer(UPDATE_RATE, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();
        board.addBoardListener(this);
    }

    public void boardChanged(){
        repaint();
    }
    @Override
    public Dimension getPreferredSize() {
	super.getPreferredSize();
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawSquares(g2);
    }

    public void drawSquares(Graphics g2){
        int sizeX = this.width/board.getWidth();
        int sizeY= this.height/board.getHeight();

        for (int y = Board.getStartAreaSize();  y < board.getFullHeight(); y++) {
            for (int x = Board.getBorderWidth(); x < board.getFullWidth(); x++) {
                SquareType squareType = board.getBoardOrFallingSquare(y, x);
                int posX = (x-Board.getBorderWidth())*sizeX;
                int posY = (y-Board.getStartAreaSize())*sizeY;
                g2.setColor(colors.get(squareType));
                g2.fillRect(posX, posY, sizeX, sizeY);
            }
        }
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
