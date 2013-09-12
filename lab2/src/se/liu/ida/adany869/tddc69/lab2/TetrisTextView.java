package se.liu.ida.adany869.tddc69.lab2;

public class TetrisTextView {
    public String convertToText(Board board) {

        StringBuilder textBoard = new StringBuilder();
        textBoard.append("se.liu.ida.adany869.tddc69.lab2.Board:\n\n");

        for (int height = 0;  height < board.getHeight(); height++) {
            for (int width = 0; width < board.getWidth(); width++) {
                SquareType square = board.getSquare(width, height);
                if (square != null) {
                    switch (square) {

                        case OUTSIDE :
                            textBoard.append('|');
                            break;
                        default:
                            textBoard.append(square);
                            break;
                    }
                }
            }
            textBoard.append("\n");
        }

        return textBoard.toString();
    }
}
