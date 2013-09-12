import java.util.List;

public class TetrisTextView {
    public String convertToText(Board board) {

        StringBuilder textBoard = new StringBuilder();
        textBoard.append("Board:\n\n");

        for (int height  : board.getHeight()) {
            for (int width : board.getWidth) {
                SquareType square = board.getSquare(width, height);
                switch (square) {

                    case OUTSIDE :
                        textBoard.append(' ');
                        break;
                    default:
                        textBoard.append(square);
                        break;
                }
            }
            textBoard.append("\n");
        }

        return textBoard.toString();
    }
}
