package se.liu.ida.adany869.tddc69.lab2;

/**
 * Creates and run a tetris game.
 */
public final class Run {

    private Run() {}

    public static void main(String[] args) {
        final Board board = new Board();
        board.generateNewFallingPoly();
	// Creates a frame which is run until exited.
	new TetrisFrame(board);
    }
}