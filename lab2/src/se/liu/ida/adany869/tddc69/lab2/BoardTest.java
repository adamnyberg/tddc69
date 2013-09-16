package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class BoardTest {

    public static void main(String[] args) {
        final Board b = new Board();
        TetrisFrame frame = new TetrisFrame(b);
        class Action2 extends AbstractAction {
            private Board b;
            private TetrisFrame frame;
            private Action2(Board board, TetrisFrame frame) {
                this.b = board;
                this.frame = frame;
            }

            public void actionPerformed(ActionEvent e) {
                Random randGen = new Random();
                b.emptyBoard();
                b.setSquare(randGen.nextInt(b.getWidth()),randGen.nextInt(b.getHeight()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getWidth()),randGen.nextInt(b.getHeight()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getWidth()),randGen.nextInt(b.getHeight()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getWidth()),randGen.nextInt(b.getHeight()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getWidth()),randGen.nextInt(b.getHeight()), b.randomSquareType());
                frame.updateFrame(b);
            }
        }
        final Action2 doOneStep = new Action2(b, frame);
        final Timer clockTimer = new Timer(500, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();

    }
}