package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class Run {

    public static void main(String[] args) {
        final Board b = new Board();
        b.generateNewFallingPoly();
        final TetrisFrame frame = new TetrisFrame(b);
    }
}