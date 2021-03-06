package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;
import se.liu.ida.adany869.tddc69.project.card.CardsPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Each PlayerComponent observes a Player object, displaying information about the player on the screen;
 * i.e. the army reserve size, and if the player been defeated.
 */
public class PlayerComponent extends JComponent implements Observer {
    private Player player = null;
    private JLabel armyReserveLabel;
    private JButton cardsButton = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showCards();
        }
    });
    private JLabel nameLabel;

    private static final int HEIGHT = 90;
    private static final int WIDTH = 150;
    private static final int BORDER_SIZE = 5;
    private static final float DEFEATED_OPACITY = 0.3f;

    public PlayerComponent(Player player) {
        this.setLayout(new MigLayout());
        this.player = player;
        this.player.addObserver(this);
        this.nameLabel = new JLabel(player.getName());
        this.add(nameLabel, "wrap");

        this.armyReserveLabel = new JLabel("Reserv: " + Integer.toString(player.getArmyReserve()));
        this.add(this.armyReserveLabel, "wrap");

        this.cardsButton.setText("Cards: " + this.player.getCards().size());
        this.add(this.cardsButton);

        this.setBounds(0, 0, WIDTH + 2 * BORDER_SIZE, HEIGHT + 2 * BORDER_SIZE);
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    //Override to make it as we want it.
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (this.player.isDefeated()){
            float[] rgb = new float[3];
            rgb = this.player.getColor().getRGBColorComponents(rgb);
            g2.setColor(new Color(rgb[0], rgb[1], rgb[2], DEFEATED_OPACITY));
            nameLabel.setText(player.getName() + " (DEFEATED)");
        }
        else {
            g2.setColor(player.getColor());
        }
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        this.cardsButton.setEnabled(player.isActive());

        if (this.player.isActive()) {
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(BORDER_SIZE));
            g2.drawRect(0, 0, WIDTH, HEIGHT);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.armyReserveLabel.setText("Reserv: " + Integer.toString(player.getArmyReserve()));
        this.cardsButton.setText("Cards: " + this.player.getCards().size());
        this.repaint();
    }

    private void showCards() {
        //Doesn't have to save the CardsPane in a variable. It will handle itself.
        //noinspection ResultOfObjectAllocationIgnored
        new CardsPane(this.player);
    }
}
