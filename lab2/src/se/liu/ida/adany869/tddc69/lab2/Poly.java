package se.liu.ida.adany869.tddc69.lab2;

/**
 * The class of fallingPoly in the Board class.
 */
public class Poly {
    private SquareType[][] polyArray;
    private int dimension;
    private int posX;
    private int posY;
    private Board board;

    public Poly(SquareType[][] polyArray, Board board) {
        this.polyArray = polyArray;
        dimension = this.polyArray.length;
	this.board = board;
	this.posX = board.getWidth()/2 - dimension/2;
	this.posY = 0;
    }

    public int getDimension() {
        return dimension;
    }

    public int getPosX() {
	return posX;
    }

    public int getPosY() {
	return posY;
    }

    public SquareType getSquare(int height, int width){
        return polyArray[height][width];
    }

    public void moveDown() {
	if (canMoveDown()) {
	    posY++;
	}
	else board.fixPolyToBoard(this);
	board.notifyListeners();
    }

    public void moveRight() {
	if (canMoveRight()) posX++;
	board.notifyListeners();
    }

    public void moveLeft() {
	if (canMoveLeft()) posX--;
	board.notifyListeners();
    }

    private boolean canMove(int deltaY, int deltaX){
        for(int y = 0; y < dimension; y++){
            for (int x = 0; x < dimension; x++) {
                if (this.getSquare(y,x) != SquareType.EMPTY &&
                    board.getBoardSquare(board.absolutePosYForFallingSquare(y) + deltaY, board.absolutePosXForFallingSquare(x) + deltaX)
                            != SquareType.EMPTY ){
                    return false;
                }
            }
        }
    return true;
    }
    private boolean canMoveDown(){
	return canMove(1, 0);
    }

    private boolean canMoveRight(){
	return canMove(0, 1);
    }

    private boolean canMoveLeft(){
	return canMove(0, -1);
    }

    public void rotate(boolean rotateRight){
        int maxIndex = dimension-1;
        SquareType[][] newArray = new SquareType[dimension][dimension];
        if (rotateRight) {
            for (int i=0; i<dimension; i++) {
                for (int j=0; j<dimension; j++){
                    if (this.getSquare(maxIndex - j, i) != SquareType.EMPTY &&
                        board.getBoardSquare(board.absolutePosYForFallingSquare(i),
                                 board.absolutePosXForFallingSquare(j)) != SquareType.EMPTY){
                    return;
                    }
                    newArray[i][j] = this.getSquare(maxIndex - j, i);
                }
            }

        }
        else {
            for (int i=0; i<dimension; i++) {
                for (int j=0; j<dimension; j++){
                    if (this.getSquare(i, j) != SquareType.EMPTY &&
                        board.getBoardSquare(board.absolutePosYForFallingSquare(
                            maxIndex - j), board.absolutePosXForFallingSquare(i)) != SquareType.EMPTY){
                    return;
                    }
                    newArray[maxIndex-j][i] = this.getSquare(i, j);
                }
            }
        }
	this.polyArray = newArray;
    }
}
