package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
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
            reinforceButton.setEnabled(true);
            reinforceButton.setActive(true);
            setActiveButton(reinforceButton);
            stateGroup.setSelected(reinforceRadioButton.getModel(), true);
            risk.switchPlayer();
        }
    });

    private actionButton reinforceButton = new actionButton(true, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //reinforceButton.add(new ButtonBorder(reinforceButton.getWidth(), reinforceButton.getHeight()));
            setActiveButton(reinforceButton);
            risk.regionController.resetFocus();
            risk.setActionState("reinforce");
        }
    });

    private actionButton attackButton = new actionButton(false, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //attackButton.add(new ButtonBorder(attackButton.getWidth(), attackButton.getHeight()));
            setActiveButton(attackButton);
            reinforceButton.setEnabled(false);
            risk.setActionState("attack");
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
        /*this.stateGroup.add(reinforceRadioButton);
        this.stateGroup.add(attackRadioButton);
        reinforceRadioButton.setText("Reinforce");
        attackRadioButton.setText("Attack");*/
        reinforceButton.setText("Reinforce");
        attackButton.setText("Attack");
        stateGroup.setSelected(reinforceRadioButton.getModel(), true);
        //this.add(reinforceRadioButton);
        //this.add(attackRadioButton);
        this.add(reinforceButton);
        this.add(attackButton);
        nextPlayerButton.setText("Next Player");
        this.add(nextPlayerButton);
    }

    private String getSelectedActionButton(){
        if (reinforceRadioButton.getModel().isSelected()) return reinforceRadioButton.getName();
        return "";
    }

    public RiskMenuComponent getRiskMenuComponent() {
        return this;
    }

    public void setActiveButton(actionButton actionButton){
        attackButton.setActive(false);
        reinforceButton.setActive(false);
        actionButton.setActive(true);
    }
}
