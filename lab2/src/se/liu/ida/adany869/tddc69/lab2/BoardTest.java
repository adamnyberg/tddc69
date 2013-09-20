package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class BoardTest {

    public static void main(String[] args) {
        final Board b = new Board();
        b.generateNewFallingPoly();
        final Random randGen = new Random();
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
        System.out.println("haha");
        final TetrisFrame frame = new TetrisFrame(b);
        System.out.println("hehe");
        final Action doOneStep = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b.setFallingPosDown();
                b.emptyBoard();
                b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getHeight()), randGen.nextInt(b.getWidth()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getHeight()), randGen.nextInt(b.getWidth()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());
                b.setSquare(randGen.nextInt(b.getHeight()),randGen.nextInt(b.getWidth()), b.randomSquareType());

                b.rotateFallingPolyLeft();
                //frame.updateFrame(b);
                //frame.repaint();
            }
        };
        final Timer clockTimer = new Timer(500, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();



    }
}