package se.liu.ida.adany869.tddc69.lab2;
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
    private int height;
    private int width;
    private static final int INIT_HEIGHT = 15;
    private static final int INIT_WIDTH = 15;
    private static final int BORDER_HEIGHT = 1;
    private static final int BORDER_WIDTH = 2;

    public Board(){
        this(INIT_WIDTH, INIT_HEIGHT);
    }

    public Board(int w, int h) {
        height = h;
        width = w;
        BoardArray = new SquareType[width + BORDER_WIDTH][height + BORDER_HEIGHT];
    }

    public void setSquare(int width, int height, SquareType value){
        BoardArray[width][height] = value;
    }

    public SquareType getSquare(int width, int height){
        return BoardArray[width][height];
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
}

