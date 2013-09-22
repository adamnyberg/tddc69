package se.liu.ida.adany869.tddc69.lab2;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private SquareType[][] boardArray;
    private Poly fallingPoly;
    private int fallingPosX;
    private int fallingPosY;
    private int height;
    private int width;
    private ArrayList<BoardListener> boardListeners = new ArrayList<BoardListener>();
    private static final int INIT_HEIGHT = 20;
    private static final int INIT_WIDTH = 15;
    private static final int BORDER_HEIGHT = 1;
    //TODO: public or private? used in TetrisComponent
    public static final int BORDER_WIDTH = 1;
    public static final int START_AREA_SIZE = 4;
    final Random randGen = new Random();

    public Board(){
        this(INIT_HEIGHT, INIT_WIDTH);
    }

    public Board(int h, int w) {
        height = h;
        width = w;
        boardArray = new SquareType[height + BORDER_HEIGHT + START_AREA_SIZE][width + BORDER_WIDTH*2];

        for (int i = 0; i < height + BORDER_HEIGHT + START_AREA_SIZE; i++) {
            for (int j = 0; j<width+BORDER_WIDTH*2; j++) {
                if (i >= height+START_AREA_SIZE || j < BORDER_WIDTH || j > width) setSquare(i, j, SquareType.OUTSIDE);
                else setSquare(i, j, SquareType.EMPTY);
            }
        }

    }

    public int getFullHeight(){
        return height + BORDER_HEIGHT + START_AREA_SIZE;
    }

    public int getFullWidth(){
        return width + BORDER_WIDTH*2;
    }

    private void setSquare(int height, int width, SquareType value){
        boardArray[height][width] = value;
        notifyListeners();
    }

    private void setBoardSquare(int y, int x, SquareType value){
        boardArray[y][x+BORDER_WIDTH] = value;
        notifyListeners();
    }

    public SquareType getAllBoardSquare(int height, int width){
        SquareType squareType = boardArray[height][width];
        if (squareType == SquareType.EMPTY){
            squareType = getFallingSquare(height, width-BORDER_WIDTH);
        }
        return squareType;
    }
    public SquareType getBoardSquare(int y, int x){
        return boardArray[y][x+BORDER_WIDTH];
    }

    public SquareType getSquare(int height, int width){
        SquareType squareType = getBoardSquare(height, width);
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
        for (int i = 0; i < START_AREA_SIZE+ getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                setBoardSquare(i,j, SquareType.EMPTY) ;
            }

        }
    }

    private boolean isInsideFallingPoly(int x, int y){
        return (fallingPoly != null &&
                y >= fallingPosY &&
                y - fallingPosY < fallingPoly.getDimension() &&
                x >= fallingPosX &&
                x - fallingPosX < fallingPoly.getDimension());
    }

    public SquareType getFallingSquare(int height, int width){
        if (isInsideFallingPoly(width, height)) {
                return fallingPoly.getSquare(height - fallingPosY, width - fallingPosX);
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
        this.fallingPoly = TetrominoMaker.getPoly(randGen.nextInt(SquareType.getNumberOfTypes()));//TetrominoMaker.getPoly(7);
        fallingPosY = 0;
        fallingPosX = width/2 - fallingPoly.getDimension()/2;
        notifyListeners();
    }

    public int getFallingPosX() {
        return fallingPosX;
    }

    public int getFallingPosY() {
        return fallingPosY;
    }

    public void moveDown() {
        if (canMoveDown()) fallingPosY++;
        else fixPolyToBoard();
        notifyListeners();
    }

    public void moveRight() {
        if (canMoveRight()) fallingPosX++;
        notifyListeners();
    }

    public void moveLeft() {
        if (canMoveLeft()) fallingPosX--;
        notifyListeners();
    }

    public void rotateRight(){
        fallingPoly.rotate(true);
        notifyListeners();
    }

    public void rotateLeft(){
        fallingPoly.rotate(false);
        notifyListeners();
    }
    private int absolutePosXForFallingSquare(int x){
        if (fallingPoly == null || x>=fallingPoly.getDimension()) throw new RuntimeException("No square in that position for fallingPoly");
        else return x + fallingPosX;
    }

    private int absolutePosYForFallingSquare(int y){
        if (fallingPoly == null || y>=fallingPoly.getDimension()) throw new RuntimeException("No square in that position for fallingPoly");
        else return y + fallingPosY;
    }

    private void fixPolyToBoard(){
        for(int y = 0; y < fallingPoly.getDimension(); y++){
            for (int x = 0; x < fallingPoly.getDimension(); x++) {
                int absX = absolutePosXForFallingSquare(x);
                int absY = absolutePosYForFallingSquare(y);
                SquareType squareType = fallingPoly.getSquare(y,x);
                if (squareType != SquareType.EMPTY) {
                    this.setBoardSquare(absY, absX, squareType);
                }
            }

        }
        checkGameOver();
        fallingPoly = null;
    }

    private boolean isGameOver(){
        for(int y = 0; y < START_AREA_SIZE; y++){
            for (int x = 0; x < getWidth(); x++) {
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

    private boolean canMoveDown(){
        if (fallingPoly != null){
            for(int y = 0; y < fallingPoly.getDimension(); y++){
                for (int x = 0; x < fallingPoly.getDimension(); x++) {
                    int absX = absolutePosXForFallingSquare(x);
                    int absY = absolutePosYForFallingSquare(y);
                    int nextHypoPos = absY+1;
                    /*System.out.println();
                    System.out.println("PosY: " + fallingPosY);
                    System.out.println("PosX: " + fallingPosX);
                    System.out.println("AbsY: " + absY);
                    System.out.println("AbsX: " + absX);
                    System.out.println("boardY: " + (absY+1));
                    System.out.println("boardX: " + absX);
                    System.out.println("fallingSquare: "+ fallingPoly.getSquare(y,x));
                    System.out.println("boardSquare: "+ this.getBoardSquare(absY + 1, absX));
                    System.out.println("Board (8,2): " + this.getBoardSquare(8,2));*/
                    if (fallingPoly.getSquare(y,x) != SquareType.EMPTY &&
                            this.getBoardSquare(nextHypoPos, absX) != SquareType.EMPTY ){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean canMoveRight(){
        if (fallingPoly != null){
            for(int y = 0; y < fallingPoly.getDimension(); y++){
                for (int x = 0; x < fallingPoly.getDimension(); x++) {
                    int absX = absolutePosXForFallingSquare(x);
                    int absY = absolutePosYForFallingSquare(y);
                    int nextHypoPos = absX+1;
                    if (fallingPoly.getSquare(y,x) != SquareType.EMPTY &&
                            this.getBoardSquare(absY, nextHypoPos) != SquareType.EMPTY ){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean canMoveLeft(){
        if (fallingPoly != null){
            for(int y = 0; y < fallingPoly.getDimension(); y++){
                for (int x = 0; x < fallingPoly.getDimension(); x++) {
                    int absX = absolutePosXForFallingSquare(x);
                    int absY = absolutePosYForFallingSquare(y);
                    int nextHypoPos = absX-1;
                    if (fallingPoly.getSquare(y,x) != SquareType.EMPTY &&
                            this.getBoardSquare(absY, nextHypoPos) != SquareType.EMPTY ){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private void checkRowRemoval(){
        for (int i = START_AREA_SIZE; i < height+START_AREA_SIZE; i++) {
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
        for (int i = rowIndex; i >= START_AREA_SIZE ; i--) {
            for (int j = 0; j < width; j++) {
                setBoardSquare(i, j, getBoardSquare(i-1, j));
            }

        }
    }

    public void tick(){
        if (fallingPoly != null) {
            moveDown();
        }
        else generateNewFallingPoly();
        checkRowRemoval();
    }


}
