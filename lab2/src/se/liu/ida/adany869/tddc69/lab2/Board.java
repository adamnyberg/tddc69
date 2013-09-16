package se.liu.ida.adany869.tddc69.lab2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Harald
 * Date: 2013-09-10
 * Time: 09:17
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private SquareType[][] BoardArray;
    private Poly fallingPoly;
    private int[] FallingPos = new int[2];
    private int height;
    private int width;
    private static final int INIT_HEIGHT = 15;
    private static final int INIT_WIDTH = 15;
    private static final int BORDER_HEIGHT = 1;
    private static final int BORDER_WIDTH = 2;

    public Board(){
        this(INIT_HEIGHT, INIT_WIDTH);
    }

    public Board(int h, int w) {
        height = h;
        width = w;
        BoardArray = new SquareType[height + BORDER_HEIGHT][width + BORDER_WIDTH];
    }

    public void setSquare(int height, int width, SquareType value){
        BoardArray[height][width] = value;
    }

    public SquareType getSquare(int height, int width){
        return BoardArray[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public SquareType randomSquareType() {
        Random randGen = new Random();
        return SquareType.getSquareType(randGen.nextInt(7));
        }

    public void emptyBoard(){
        for (SquareType[] squareTypes : BoardArray) {
            for (int i = 0; i<squareTypes.length; i++) {
                squareTypes[i] = null;
            }

        }
    }

    public SquareType getFallingSquare(int height, int width){
        if (height-FallingPos[0]>= 0 && height-FallingPos[0]<= fallingPoly.getDimension()
                && width-FallingPos[1]>= 0 && width-FallingPos[1]<= fallingPoly.getDimension()) {
            return fallingPoly.getSquare(height-FallingPos[0], width-FallingPos[1]);
        }
        else return SquareType.EMPTY;
    }

}

