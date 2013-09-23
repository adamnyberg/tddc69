package se.liu.ida.adany869.tddc69.lab2;

public class Run {

    public static void main(String[] args) {
        final Board b = new Board();
        b.generateNewFallingPoly();
        final TetrisFrame frame = new TetrisFrame(b);
    }
}