package se.liu.ida.adany869.tddc69.lab2;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Harald
 * Date: 2013-09-12
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class TetrisFrame extends JFrame{

    private Board board;
    private JTextArea textArea;
    public TetrisFrame(Board board) {
        super("MyWindowTitle");
        this.board = board;
        textArea = new JTextArea(board.getHeight(), board.getWidth());
        textArea.setText(TetrisTextView.convertToText(board));
        this.setLayout(new BorderLayout());
        this.add(textArea, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}
