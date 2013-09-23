package se.liu.ida.adany869.tddc69.lab2;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisFrame extends JFrame{

    private final Board board;
    private JTextArea textArea;

    public TetrisFrame(Board board) {
        super("Tetris");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.board = board;
        //final Timer clockTimer = new Timer(500, doOneStep);
        //clockTimer.setCoalesce(true);
        //clockTimer.start();
        //textArea = new JTextArea(board.getHeight(), board.getWidth());
        //textArea.setText(TetrisTextView.convertToText(board));
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
        final JMenuBar menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem quit = new JMenuItem("Quit", 'Q');

        fileMenu.add(quit);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ObjButtons[] = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to exit?", "Tetris",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, ObjButtons[1]);
                if (PromptResult == 0) {
                    System.exit(0);
                }
            }
        });
    }
}
