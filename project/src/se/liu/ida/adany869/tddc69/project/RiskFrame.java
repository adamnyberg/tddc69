package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskFrame  extends JFrame{
    private final RiskWorld risk;
    private JTextArea textArea;

    public RiskFrame(RiskWorld risk) {
        super("Rwhisky");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.risk = risk;
        this.setLayout(new MigLayout());
        createMenus();

        RiskPanel riskPanel = new RiskPanel(this.risk);
        this.setContentPane(riskPanel);
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
