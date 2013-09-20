package se.liu.ida.adany869.tddc69.lab2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private SquareType[][] BoardArray;
    private Poly fallingPoly;
    private int[] FallingPos = new int[2];
    private int height;
    private int width;
    private ArrayList<BoardListener> boardListeners = new ArrayList<BoardListener>();
    private static final int INIT_HEIGHT = 20;
    private static final int INIT_WIDTH = 15;
    private static final int BORDER_HEIGHT = 1;
    private static final int BORDER_WIDTH = 1;
    final Random randGen = new Random();

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
        notifyListeners();
    }

    public SquareType getSquare(int height, int width){
        SquareType squareType = BoardArray[height][width+BORDER_WIDTH];
        if (squareType == SquareType.EMPTY){
            squareType = getFallingSquare(height, width);
        }
        return squareType;
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

    public void addBoardListener(BoardListener bl){
        boardListeners.add(bl);
    }

    private void notifyListeners(){
        for (BoardListener boardListener : boardListeners) {

            boardListener.boardChanged();
        }
    }

    public Poly getFallingPoly() {
        return fallingPoly;
    }

    public void generateNewFallingPoly() {
        this.fallingPoly = TetrominoMaker.getPoly(randGen.nextInt(SquareType.getNumberOfTypes()));
        FallingPos[0] = -fallingPoly.getDimension();
        FallingPos[1] = randGen.nextInt(getWidth() - BORDER_WIDTH - fallingPoly.getDimension()) + BORDER_WIDTH;
        notifyListeners();
    }

    public int[] getFallingPos() {
        return FallingPos;
    }

    public void setFallingPosDown() {
        if (FallingPos[0] < this.height - fallingPoly.getDimension()) FallingPos[0]++;
        else FallingPos[0] = -fallingPoly.getDimension();
        notifyListeners();
    }

    public void setFallingPosRight() {
        FallingPos[1]++;
        notifyListeners();
    }

    public void setFallingPosLeft() {
        FallingPos[1]--;
        notifyListeners();
    }

    public void rotateFallingPolyRight(){
        fallingPoly.rotate(true);
        notifyListeners();
    }

    public void rotateFallingPolyLeft(){
        fallingPoly.rotate(false);
        notifyListeners();
    }
}
