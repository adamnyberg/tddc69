package se.liu.ida.adany869.tddc69.lab2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * The class board is the main class. Instanciatied it holds the structure of a tetris game. It has two different coordinate
 * systems, one for the playable and visible area including a starting area where tetrominos are spawned,
 * and one for the whole area, including borders, playable area and starting area.
 */
public class Board {
    private SquareType[][] boardArray;
    //fallingPoly is initialized in the constructor via generateNewFallingPoly method.
    private Poly fallingPoly;
    private int height;
    private int width;
    private Collection<BoardListener> boardListeners = new ArrayList<BoardListener>();
    private static final int INIT_HEIGHT = 20;
    private static final int INIT_WIDTH = 15;
    private static final int BORDER_HEIGHT = 2;
    private static final int BORDER_WIDTH = 2;
    private static final int START_AREA_HEIGHT = 4;
    final Random randGen = new Random();

    public Board(){
        this(INIT_HEIGHT, INIT_WIDTH);
    }
	/*
	* Creates a board with h squares playable height and w squares playable width
	*/
    public Board(int h, int w) {
        height = h;
        width = w;
        boardArray = new SquareType[height + BORDER_HEIGHT + START_AREA_HEIGHT][width + BORDER_WIDTH*2];

        for (int i = 0; i < height + BORDER_HEIGHT + START_AREA_HEIGHT; i++) {
            for (int j = 0; j<width+BORDER_WIDTH*2; j++) {
                if (i >= height+ START_AREA_HEIGHT || j < BORDER_WIDTH || j >= width+BORDER_WIDTH) setSquare(i, j, SquareType.OUTSIDE);
                else setSquare(i, j, SquareType.EMPTY);
            }
        }
	generateNewFallingPoly();
    }

    public static int getBorderWidth() {
        return BORDER_WIDTH;
    }

    public static int getStartAreaSize() {
        return START_AREA_HEIGHT;
    }

    public int getFullHeight(){
        return height + BORDER_HEIGHT + START_AREA_HEIGHT;
    }

    public int getFullWidth(){
        return width + BORDER_WIDTH*2;
    }

    private boolean doesPolyExist(){
	return fallingPoly != null;
    }

    private void setSquare(int height, int width, SquareType value){
        boardArray[height][width] = value;
        notifyListeners();
    }

    private void setBoardSquare(int y, int x, SquareType value){
        boardArray[y][x+BORDER_WIDTH] = value;
        notifyListeners();
    }

    public SquareType getBoardOrFallingSquare(int y, int x){
        SquareType squareType = boardArray[y][x];
        if (squareType == SquareType.EMPTY){
            squareType = getFallingSquare(y, x-BORDER_WIDTH);
        }
        return squareType;
    }

    public SquareType getBoardSquare(int y, int x){
        return boardArray[y][x+BORDER_WIDTH];
    }

    public SquareType getFallingSquare(int y, int x){
	if (isInsideFallingPoly(x, y)) {
	    return fallingPoly.getSquare(y - fallingPoly.getPosY(), x - fallingPoly.getPosX());
	}

	else return SquareType.EMPTY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void emptyBoard(){
        for (int i = 0; i < START_AREA_HEIGHT + height; i++) {
            for (int j = 0; j < width; j++) {
                setBoardSquare(i,j, SquareType.EMPTY) ;
            }

        }
    }

    private boolean isInsideFallingPoly(int x, int y){
        return (fallingPoly != null &&
                y >= fallingPoly.getPosY() &&
                y - fallingPoly.getPosY() < fallingPoly.getDimension() &&
                x >= fallingPoly.getPosX() &&
                x - fallingPoly.getPosX() < fallingPoly.getDimension());
    }

    public void addBoardListener(BoardListener bl){
        boardListeners.add(bl);
    }

    public void notifyListeners(){
        for (BoardListener boardListener : boardListeners) {
            boardListener.boardChanged();
        }
    }

    public void generateNewFallingPoly() {
	TetrominoMaker tetrominoMaker = new TetrominoMaker(this);
        this.fallingPoly = tetrominoMaker.getPoly(randGen.nextInt(tetrominoMaker.getNumberOfTypes()));
        notifyListeners();
    }

    public void moveLeft(){
	if (doesPolyExist()) fallingPoly.moveLeft();
    }

    public void moveRight(){
       if (doesPolyExist()) fallingPoly.moveRight();
    }

    public void rotateRight(){
	if (doesPolyExist()){
	    fallingPoly.rotate(true);
	    notifyListeners();
	}
    }

    public void rotateLeft(){
	if (doesPolyExist()){
	    fallingPoly.rotate(false);
	    notifyListeners();
	}
    }

    public int absolutePosXForFallingSquare(int x){
        if (fallingPoly == null || x>=fallingPoly.getDimension()) return -1;
        else return x + fallingPoly.getPosX();
    }

    public int absolutePosYForFallingSquare(int y){
        if (fallingPoly == null || y>=fallingPoly.getDimension()) return -1;
        else return y + fallingPoly.getPosY();
    }

    public void fixPolyToBoard(Poly poly){
        for(int y = 0; y < poly.getDimension(); y++){
            for (int x = 0; x < poly.getDimension(); x++) {
                int absX = absolutePosXForFallingSquare(x);
                int absY = absolutePosYForFallingSquare(y);
                SquareType squareType = poly.getSquare(y,x);
                if (squareType != SquareType.EMPTY) {
                    this.setBoardSquare(absY, absX, squareType);
                }
            }

        }
        checkRowRemoval();
        checkGameOver();
        fallingPoly = null;
    }

    private boolean isGameOver(){
        for(int y = 0; y < START_AREA_HEIGHT; y++){
            for (int x = 0; x < width; x++) {
                if(getBoardSquare(y, x) != SquareType.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }
    private void checkGameOver(){
        if (isGameOver()){
            emptyBoard();
        }
    }



    private void checkRowRemoval(){
        for (int i = START_AREA_HEIGHT; i < height+ START_AREA_HEIGHT; i++) {
            if (isFullRow(boardArray[i])){
                emptyRow(i);
            }
        }
    }

    private boolean isFullRow(SquareType[] row){
        for (SquareType squareType : row) {
            if (squareType == SquareType.EMPTY){
                return false;
            }
        }
        return true;
    }

    private void emptyRow(int rowIndex){
        for (int i = rowIndex; i >= START_AREA_HEIGHT; i--) {
            for (int j = 0; j < width; j++) {
                setBoardSquare(i, j, getBoardSquare(i-1, j));
            }
        }
    }

    public void tick(){
        if (doesPolyExist()) {
	    fallingPoly.moveDown();
        }
        else {
            generateNewFallingPoly();
        }

    }


}
