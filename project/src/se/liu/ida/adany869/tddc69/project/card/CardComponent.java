package se.liu.ida.adany869.tddc69.project.card;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    @Override
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
        System.out.println("click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
