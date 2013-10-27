package se.liu.ida.adany869.tddc69.project.continent;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Show information about the continents; name, owner and the value of the continent.
 */
public class ContinentInfoComponent extends JComponent{
    private JLabel extraArmiesLabel = new JLabel();
    private Continent continent;
    private static final int BORDER_SIZE = 5;

    public ContinentInfoComponent(Continent continent) {
        this.continent = continent;
        this.setLayout(new MigLayout());
        this.extraArmiesLabel.setText(this.continent.getName() + ": " + Integer.toString(continent.getContinentValue()));
        this.extraArmiesLabel.setForeground(this.continent.getColor());
        this.add(this.extraArmiesLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.continent.getOwner() != null){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(this.continent.getOwner().getColor());
            g2.setStroke(new BasicStroke(BORDER_SIZE));
            g2.drawRect(0, BORDER_SIZE*2, this.getWidth(), this.getHeight()-BORDER_SIZE*4);
        }
    }
}
