package se.liu.ida.adany869.tddc69.project.regions;

import net.miginfocom.swing.MigLayout;
import se.liu.ida.adany869.tddc69.project.continent.Continent;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class RegionComponent extends JComponent implements Observer {
    private Region region;
    private int height;
    private int width;
    private JLabel armyText = new JLabel();
    private boolean isFocused = false;
    private Color backgroundColor;
    private List<Continent> continents;

    private static final int INIT_HEIGHT = 100;
    private static final int INIT_WIDTH = 100;
    private static final int BORDER_SIZE = 5;

    @Override
    public void update(Observable o, Object arg) {
        updateArmy();
        updateFocus();
        this.backgroundColor = this.region.getOwner().getColor();
        repaint();
    }

    public RegionComponent(Region region, int yPos, int xPos, int height, int width, RegionController regionController, List<Continent> continents) {
        this.setLayout(new MigLayout());
        this.region = region;
        this.height = height;
        this.width = width;
        this.continents = continents;
        this.backgroundColor = this.region.getOwner().getColor();
        this.setBounds(xPos, yPos, width, height);
        this.armyText.setText(Integer.toString(region.getArmySize()));
        this.addNameLabels();

        this.add(armyText, "wrap");
        this.region.addObserver(this);
        this.addMouseListener(regionController);
        this.setVisible(true);
    }

    private void addNameLabels() {
        Pattern pattern = Pattern.compile(" ");
        String[] regionNameArray = pattern.split(this.region.getName());
        for (String s : regionNameArray) {
            this.add(new JLabel(s), "wrap");
        }
    }

    public RegionComponent(Region region, int yPos, int xPos, RegionController regionController, List<Continent> continents) {
        this(region, yPos, xPos, INIT_HEIGHT, INIT_WIDTH, regionController, continents);
    }


    @SuppressWarnings("RefusedBequest")
    @Override
    //Override to make it as we want it.
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, this.getHeight(), this.getWidth());
        g2.setStroke(new BasicStroke(BORDER_SIZE));

        for (int i = 0; i < this.continents.size(); i++) {
            g2.setColor(this.continents.get(i).getColor());
            int incest = BORDER_SIZE*i;
            g2.drawRect(BORDER_SIZE/2 + incest, BORDER_SIZE/2 + incest,
                    this.getHeight() - incest * 2 - BORDER_SIZE, this.getWidth() - incest*2 - BORDER_SIZE);
        }

        if (isFocused){
            drawBorder(g2);
        }
    }

    public Region getRegion(){
        return region;
    }

    public void updateArmy(){
        armyText.setText(Integer.toString(region.getArmySize()));
    }

    public void updateFocus(){
        isFocused = region.isFocused();
    }

    private void drawBorder(Graphics2D g2){
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.BLACK);
        g2.drawRect(0,0,width,height);
    }
}
