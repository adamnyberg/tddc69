package se.liu.ida.adany869.tddc69.lab2;

public class TetrisTextView {
    public static String convertToText(Board board) {

        StringBuilder textBoard = new StringBuilder();
        textBoard.append("Board:\n\n");

        for (int height = 0;  height < board.getHeight(); height++) {
            for (int width = 0; width < board.getWidth(); width++) {
                SquareType square = board.getBoardOrFallingSquare(height, width);
                SquareType fallingSquare = board.getFallingSquare(height, width);

                if (fallingSquare != SquareType.EMPTY) {
                    square = fallingSquare;
                }

                if (square != SquareType.EMPTY) {
		    // All cases except OUTSIDE does the same thing.
                    switch (square) {

                        case OUTSIDE :
                            textBoard.append('|');
                            break;
                        default:
                            textBoard.append(square);
                            break;
                    }
                } else {
                    textBoard.append("  ");
                }
            }
            textBoard.append("\n");
        }

        return textBoard.toString();
    }
}
