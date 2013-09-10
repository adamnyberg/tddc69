/**
 * Created with IntelliJ IDEA.
 * User: Harald
 * Date: 2013-09-10
 * Time: 09:17
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private SquareType[][] BoardArray;

    public Board(int height, int width) {
        BoardArray = new SquareType[height][width];
    }
}
