package se.liu.ida.adany869.tddc69.lab2;

/**
 * Creates the fallingPoly.
 */
public class TetrominoMaker {
    private Board board;
    public TetrominoMaker(Board board) {
	this.board = board;
    }
    public int getNumberOfTypes(){
	return SquareType.getNumberOfTypes();
    }
    public Poly getPoly(int n) {
        SquareType square = SquareType.getSquareType(n);
	// OUTSIDE and EMPTY squaretypes are never handled, because they never happen.
	switch (square) {
	    case I:
		return new Poly(new SquareType[][] { { SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY } }, board);

	    case J:

		return new Poly(new SquareType[][] { { SquareType.EMPTY, SquareType.J, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.J, SquareType.EMPTY },
			{ SquareType.J, SquareType.J, SquareType.EMPTY } }, board);

	    case L:
		return new Poly(new SquareType[][] { { SquareType.EMPTY, SquareType.L, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.L, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.L, SquareType.L } }, board);

	    case O:
		return new Poly(new SquareType[][] { { SquareType.O, SquareType.O }, { SquareType.O, SquareType.O } }, board);

	    case S:
		return new Poly(new SquareType[][] { { SquareType.EMPTY, SquareType.S, SquareType.S },
			{ SquareType.S, SquareType.S, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY } }, board);

	    case T:
		return new Poly(new SquareType[][] { { SquareType.T, SquareType.T, SquareType.T },
			{ SquareType.EMPTY, SquareType.T, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY } }, board);

	    case Z:
		return new Poly(new SquareType[][] { { SquareType.Z, SquareType.Z, SquareType.EMPTY },
			{ SquareType.EMPTY, SquareType.Z, SquareType.Z },
			{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY } }, board);
	    default:
		break;
	}
        return null;
    }
}
