package se.liu.ida.adany869.tddc69.lab2;

public class BoardTest {
    public static void main(String[] args) {
        Board b = new Board();

        TetrisTextView view;
        view = new TetrisTextView();

        System.out.println(view.convertToText(b));
    }
}