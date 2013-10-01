package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RiskMenuComponent extends JComponent{
    private RiskWorld risk;
    private ButtonGroup stateGroup = new ButtonGroup();
    private JRadioButton reinforceRadioButton = new JRadioButton("Reinforce", true);
    private JRadioButton attackRadioButton = new JRadioButton("Attack", false);
    private JButton nextPlayerButton = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            risk.switchPlayer();
            System.out.println("Next player");
        }
    });
    public RiskMenuComponent(RiskWorld risk) {
        this.setLayout(new MigLayout());
        this.risk = risk;
        this.addPlayersInfo();
        this.addStateButtons();
    }

    private void addPlayersInfo() {
        this.add(new PlayerComponent(risk.getPlayers()[0]));
        this.add(new PlayerComponent(risk.getPlayers()[1]));
    }

    private void addStateButtons(){
        this.stateGroup.add(reinforceRadioButton);
        this.stateGroup.add(attackRadioButton);
        this.add(reinforceRadioButton);
        this.add(attackRadioButton);
        nextPlayerButton.setText("Next Player");
        this.add(nextPlayerButton);
    }


}
