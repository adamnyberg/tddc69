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
        this(INIT_HEIGHT, INIT_WIDTH);
    }

    public Board(int h, int w) {
        height = h;
        width = w;
        BoardArray = new SquareType[width + BORDER_WIDTH][height + BORDER_HEIGHT];
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
}
