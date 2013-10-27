package se.liu.ida.adany869.tddc69.project.card;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Shows the cards in the card pane.
 */
public class CardComponent extends JComponent implements MouseListener {

    private Cards card;
    private static final int WIDTH = 180;
    private static final int HEIGHT = 60;
    private static final int BORDER = 1;
    private boolean selected = false;

    public CardComponent(Cards card) {
        this.card = card;
        this.setLayout(new MigLayout());

        this.add(new JLabel(card.toString()));
        this.addMouseListener(this);
    }

    public Cards getCard() {
        return card;
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
        if (this.selected) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRect(0, 0, WIDTH, HEIGHT - BORDER);
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void pressed() {
        this.selected = !this.selected;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CardComponent cardComponent = (CardComponent) e.getSource();
        cardComponent.pressed();
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
