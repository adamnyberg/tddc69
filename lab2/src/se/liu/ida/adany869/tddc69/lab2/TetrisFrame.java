package se.liu.ida.adany869.tddc69.lab2;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates the window where the game is displayed. Is also risk to the TetrisComponent.
 */
public class TetrisFrame extends JFrame{

    private final Board board;

    public TetrisFrame(Board board) {
        super("Tetris");
	this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.board = board;
        this.setLayout(new MigLayout());
        createMenus();
        TetrisComponent tetrisComponent = new TetrisComponent(this.board);
        this.setContentPane(tetrisComponent);
        this.pack();
        this.setVisible(true);
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
                String[] objButtons = {"Yes", "No"};
                int promptResult = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to exit?", "Tetris",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        objButtons, objButtons[1]);
                if (promptResult == 0) {
                    System.exit(0);
                }
            }
        });

    }
}
