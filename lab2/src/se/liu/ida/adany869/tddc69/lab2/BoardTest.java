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
        final TetrisFrame frame = new TetrisFrame(b);
        final Action doOneStep = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b.tick();
            }
        };
        final Timer clockTimer = new Timer(100, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();



    }
}