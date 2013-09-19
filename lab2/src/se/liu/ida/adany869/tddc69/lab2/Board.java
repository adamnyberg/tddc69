package se.liu.ida.adany869.tddc69.lab2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class Board {
    private SquareType[][] BoardArray;
    public Poly fallingPoly;
    public int[] FallingPos = new int[2];
    private int height;
    private int width;
    private static final int INIT_HEIGHT = 15;
    private static final int INIT_WIDTH = 15;
    private static final int BORDER_HEIGHT = 1;
    private static final int BORDER_WIDTH = 1;

    public Board(){
        this(INIT_HEIGHT, INIT_WIDTH);
    }

    public Board(int h, int w) {
        height = h;
        width = w;
        BoardArray = new SquareType[height + BORDER_HEIGHT][width + BORDER_WIDTH*2];
        for (int i = 0; i < height; i++) {
            for (int j = BORDER_WIDTH; j<width+BORDER_WIDTH*2; j++) {
                BoardArray[i][j] = SquareType.EMPTY;
            }
        }

    }

    public void setSquare(int height, int width, SquareType value){
        BoardArray[height][width] = value;
    }

    public SquareType getSquare(int height, int width){
        return BoardArray[height][width+BORDER_WIDTH];
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
                squareTypes[i] = SquareType.EMPTY;
            }

        }
    }

    public SquareType getFallingSquare(int height, int width){
        if (fallingPoly != null &&
            height >= FallingPos[0] &&
            height - FallingPos[0] < fallingPoly.getDimension() &&
            width >= FallingPos[1] &&
            width - FallingPos[1] < fallingPoly.getDimension()) {
                return fallingPoly.getSquare(height - FallingPos[0], width - FallingPos[1]);
        }

        else return SquareType.EMPTY;
    }

}

