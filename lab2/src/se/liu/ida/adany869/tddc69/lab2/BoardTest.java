package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class BoardTest {

    public static void main(String[] args) {
        final Board b = new Board();
        b.fallingPoly = TetrominoMaker.getPoly(5);
        b.FallingPos[0] = 0;
        b.FallingPos[1] = 3;
        final Random randGen = new Random();
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        final TetrisFrame frame = new TetrisFrame(b);

        final Action doOneStep = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (b.FallingPos[0]+ b.fallingPoly.getDimension() > 15){
                    b.emptyBoard();
                    b.FallingPos[0] = 0;
                    b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                    b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                    b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                    b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                    b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                    b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                }
                b.fallingPoly.rotate(false);
                b.FallingPos[0]++;
                frame.updateFrame(b);
                frame.repaint();
            }
        };
        final Timer clockTimer = new Timer(500, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();



    }
}