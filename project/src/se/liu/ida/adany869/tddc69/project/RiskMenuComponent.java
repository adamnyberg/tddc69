package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;
import se.liu.ida.adany869.tddc69.project.Networking.RiskClient;
import se.liu.ida.adany869.tddc69.project.Networking.RiskNetwork;
import se.liu.ida.adany869.tddc69.project.Networking.RiskOverNetwork;
import se.liu.ida.adany869.tddc69.project.continent.Continent;
import se.liu.ida.adany869.tddc69.project.continent.ContinentInfoComponent;
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
            risk.nextPlayer();
            RiskClient.sendRisk(risk);
        }
    });

    private ActionButton reinforceButton = new ActionButton(true, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setActiveButton(reinforceButton);
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

    private ActionButton fortifyButton = new ActionButton(false, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setActiveButton(fortifyButton);
            reinforceButton.setEnabled(false);
            attackButton.setEnabled(false);
            risk.setActionState(new FortifyState(risk));
            risk.resetFocus();
        }
    });

    public RiskMenuComponent(RiskWorld risk) {
        this.setLayout(new MigLayout());
        this.risk = risk;
        this.addPlayersInfo();
        this.addStateButtons();
        this.addContinentsInfo();
    }

    private void addPlayersInfo() {
        for (Player player : risk.getPlayers()) {
            this.add(new PlayerComponent(player));
        }
    }

    private void addStateButtons(){
        reinforceButton.setText("Reinforce");
        this.add(reinforceButton);

        attackButton.setText("Attack");
        this.add(attackButton);

        fortifyButton.setText("Fortify");
        this.add(fortifyButton);

        nextPlayerButton.setText("Next Player");
        this.add(nextPlayerButton, "wrap");
    }

    private void addContinentsInfo() {
        for (Continent continent : risk.getContinents()) {
            this.add(new ContinentInfoComponent(continent));
        }
    }

    public void setActiveButton(ActionButton actionButton){
        attackButton.setActive(false);
        reinforceButton.setActive(false);
        fortifyButton.setActive(false);
        actionButton.setActive(true);
    }
}
