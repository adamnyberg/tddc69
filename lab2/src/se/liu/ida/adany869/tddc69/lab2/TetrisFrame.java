package se.liu.ida.adany869.tddc69.lab2;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TetrisFrame extends JFrame{

    private final Board board;
    private JTextArea textArea;

    public TetrisFrame(Board board) {
        super("Tetris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.board = board;
        //final Timer clockTimer = new Timer(500, doOneStep);
        //clockTimer.setCoalesce(true);
        //clockTimer.start();
        textArea = new JTextArea(board.getHeight(), board.getWidth());
        textArea.setText(TetrisTextView.convertToText(board));
        this.setLayout(new MigLayout());
        createMenus();
        //this.add(textArea, BorderLayout.CENTER);
        TetrisComponent tetrisComponent = new TetrisComponent(this.board);
        this.setContentPane(tetrisComponent);//add(tetrisComponent, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }


    public void updateFrame(Board board){
        textArea.setText(TetrisTextView.convertToText(board));
    }

    private void createMenus(){
        final JMenu file = new JMenu("File");
        file.add(new JMenuItem("Quit", 'Q'));

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        this.setJMenuBar(menuBar);
    }
}
