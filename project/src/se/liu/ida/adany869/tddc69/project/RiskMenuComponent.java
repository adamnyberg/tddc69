package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RiskMenuComponent extends JComponent{
    private RiskWorld risk;
    private ButtonGroup stateGroup = new ButtonGroup();
    private JRadioButton reinforceRadioButton = new JRadioButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            risk.regionController.resetFocus();
            risk.setActionState("reinforce");
        }
    });
    private JRadioButton attackRadioButton = new JRadioButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            risk.setActionState("attack");
        }
    });
    private JButton nextPlayerButton = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            risk.regionController.resetFocus();
            stateGroup.setSelected(reinforceRadioButton.getModel(), true);
            risk.switchPlayer();
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
        reinforceRadioButton.setText("Reinforce");
        attackRadioButton.setText("Attack");
        stateGroup.setSelected(reinforceRadioButton.getModel(), true);
        this.add(reinforceRadioButton);
        this.add(attackRadioButton);
        nextPlayerButton.setText("Next Player");
        this.add(nextPlayerButton);
    }

    private String getSelectedActionButton(){
        if (reinforceRadioButton.getModel().isSelected()) return reinforceRadioButton.getName();
        return "";
    }


}
