package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;

public class BoardTest {
    public static void main(String[] args) {
        Board b = new Board();
        b.setSquare(4,4, b.randomSquareType());
        b.setSquare(5,4, b.randomSquareType());
        b.setSquare(4,5, b.randomSquareType());
        b.setSquare(6,5, b.randomSquareType());
        TetrisTextView view;
        view = new TetrisTextView();

        System.out.println(view.convertToText(b));
    }
}