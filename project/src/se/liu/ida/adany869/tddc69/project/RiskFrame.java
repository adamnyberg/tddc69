package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RiskFrame  extends JFrame{
    private final RiskWorld risk;
    private Observer menuObserver;

    public RiskFrame(RiskWorld risk) {
        super("Rwhisky");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.risk = risk;
        this.setLayout(new MigLayout());
        createMenus();

        RiskPanel riskPanel = new RiskPanel(this.risk);
        this.setContentPane(riskPanel);
        this.pack();
        this.setVisible(true);
    }

    public void setMenuObserver(Observer observer){
        this.menuObserver = observer;
    }

    public void notifyMenuObserver(ActionEvent e){
        menuObserver.update(e);
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
                notifyMenuObserver(e);
            }
        });
    }
}
