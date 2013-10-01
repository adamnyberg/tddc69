package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskFrame  extends JFrame{
    private final RiskWorld risk;
    private MenuObserver menuObserver;

    public RiskFrame(RiskWorld risk) {
        super("Rwhisky");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.risk = risk;
        createMenus();

        RiskComponent riskComponent = new RiskComponent(this.risk);
        this.setContentPane(riskComponent);
        this.pack();
        this.setVisible(true);
    }

    public void setMenuObserver(MenuObserver observer){
        this.menuObserver = observer;
    }

    public void notifyMenuObserver(ActionEvent e){
        this.menuObserver.update(e);
    }

    public final void closeFrame() {
        this.setVisible(false);
    }

    private void createMenus(){
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("Rwhisky");

        final JMenuItem restart = new JMenuItem("Restart", 'R');
        final JMenuItem quit = new JMenuItem("Quit", 'Q');

        menu.add(restart);
        menu.add(quit);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyMenuObserver(e);
            }
        });

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyMenuObserver(e);
                closeFrame();
            }
        });
    }
}
