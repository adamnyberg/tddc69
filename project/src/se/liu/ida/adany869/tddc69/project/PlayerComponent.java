package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;
import se.liu.ida.adany869.tddc69.project.card.CardsPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class PlayerComponent extends JComponent implements Observer {
    private Player player = null;
    private JLabel armyReserveLabel;
    private JButton cardsButton = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showCards();
        }
    });;

    private static int HEIGHT = 90;
    private static int WIDTH = 150;
    private static int BORDER_SIZE = 5;

    public PlayerComponent(Player player) {
        this.setLayout(new MigLayout());
        this.player = player;
        this.player.addObserver(this);

        this.add(new JLabel(player.getName()), "wrap");

        this.armyReserveLabel = new JLabel("Reserv: " + Integer.toString(player.getArmyReserve()));
        this.add(this.armyReserveLabel, "wrap");

        this.cardsButton.setText("Cards: " + this.player.getCards().size());
        this.add(this.cardsButton);

        this.setBounds(0, 0, WIDTH + 2 * BORDER_SIZE, HEIGHT + 2 * BORDER_SIZE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(player.getColor());
        g2.fillRect(0,0, WIDTH, HEIGHT);

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

    public void enableCardsButton(boolean b) {
        this.cardsButton.setEnabled(b);
    }

    private void showCards() {
        CardsPane cardsPane = new CardsPane(this.player);
    }
}
