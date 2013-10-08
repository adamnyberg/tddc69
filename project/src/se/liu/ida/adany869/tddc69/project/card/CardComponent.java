package se.liu.ida.adany869.tddc69.project.card;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CardComponent extends JComponent {
    private Cards card;

    public CardComponent(Cards card) {
        this.card = card;
        this.setLayout(new MigLayout());

        this.add(new JLabel(card.toString()));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
