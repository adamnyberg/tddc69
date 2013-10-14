package se.liu.ida.adany869.tddc69.project.continent;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ContinentInfoComponent extends JComponent{
    private JLabel extraArmiesLabel = new JLabel();

    public ContinentInfoComponent(Continent continent) {
        this.setLayout(new MigLayout());
        this.extraArmiesLabel.setText(continent.getName() + ": " + Integer.toString(continent.getContinentValue()));
        this.extraArmiesLabel.setForeground(continent.getColor());
        this.add(this.extraArmiesLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("painted ContinentInfo");
    }
}
