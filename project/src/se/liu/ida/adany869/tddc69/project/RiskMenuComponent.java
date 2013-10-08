package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;
import se.liu.ida.adany869.tddc69.project.state.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RiskMenuComponent extends JComponent{
    private RiskWorld risk;

    private JButton nextPlayerButton = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            reinforceButton.setEnabled(true);
            setActiveButton(reinforceButton);
            attackButton.setEnabled(true);
            risk.switchPlayer();
            risk.resetFocus();
            risk.setActionState(new ReinforceState(risk));
        }
    });

    private ActionButton reinforceButton = new ActionButton(true, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setActiveButton(reinforceButton);
            risk.regionController.resetFocus();
            risk.setActionState(new ReinforceState(risk));

        }
    });

    private ActionButton attackButton = new ActionButton(false, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setActiveButton(attackButton);
            reinforceButton.setEnabled(false);
            risk.setActionState(new AttackState(risk));
        }
    });

    private ActionButton fortifyButton = new ActionButton(true, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setActiveButton(reinforceButton);
            reinforceButton.setEnabled(false);
            attackButton.setEnabled(false);
            risk.setActionState(new FortifyState(risk));
        }
    });

    public RiskMenuComponent(RiskWorld risk) {
        this.setLayout(new MigLayout());
        this.risk = risk;
        this.addPlayersInfo();
        this.addStateButtons();
    }

    private void addPlayersInfo() {
        risk.getPlayers()[0].addCard();
        risk.getPlayers()[0].addCard();
        PlayerComponent player0 = new PlayerComponent(risk.getPlayers()[0]);
        //player0.enableCardsButton(true);
        this.add(player0);
        this.add(new PlayerComponent(risk.getPlayers()[1]));
    }

    private void addStateButtons(){
        reinforceButton.setText("Reinforce");
        this.add(reinforceButton);

        attackButton.setText("Attack");
        this.add(attackButton);

        fortifyButton.setText("Fortify");
        this.add(fortifyButton);

        nextPlayerButton.setText("Next Player");
        this.add(nextPlayerButton);
    }

    public void setActiveButton(ActionButton actionButton){
        attackButton.setActive(false);
        reinforceButton.setActive(false);
        fortifyButton.setActive(false);
        actionButton.setActive(true);
    }
}
