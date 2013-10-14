package se.liu.ida.adany869.tddc69.project.regions;

import net.miginfocom.swing.MigLayout;
import se.liu.ida.adany869.tddc69.project.Continent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RegionComponent extends JComponent implements Observer {
    private Region region;
    private int height;
    private int width;
    private JLabel armyText = new JLabel();
    private boolean isFocused = false;
    private Color backgroundColor;
    private ArrayList<Continent> continents;

    private static final int INIT_HEIGHT = 150;
    private static final int INIT_WIDTH = 150;
    private static final int BORDER_SIZE = 8;

    @Override
    public void update(Observable o, Object arg) {
        updateArmy();
        updateFocus();
        this.backgroundColor = this.region.getOwner().getColor();
        repaint();
    }

    public RegionComponent(Region region, int yPos, int xPos, int height, int width, RegionController regionController, ArrayList<Continent> continents) {
        this.setLayout(new MigLayout());
        this.region = region;
        this.height = height;
        this.width = width;
        this.continents = continents;
        this.backgroundColor = this.region.getOwner().getColor();
        this.setBounds(xPos, yPos, width, height);
        armyText.setText(Integer.toString(region.getArmies()));
        this.add(new JLabel(region.getName()));
        this.add(armyText, "wrap");
        region.addObserver(this);
        this.addMouseListener(regionController);
        this.setVisible(true);
    }

    public RegionComponent(Region region, int yPos, int xPos, RegionController regionController, ArrayList<Continent> continents) {
        this(region, yPos, xPos, INIT_HEIGHT, INIT_WIDTH, regionController, continents);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
        repaint();
    }

    public void switchFocused(){
        isFocused = !isFocused;
        repaint();
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

    public void unHighlightNeighbours() {
        this.backgroundColor = region.getOwner().getColor();
        this.repaint();
    }

    public void update(ActionEvent e){
        System.out.println("regComp.update: " + e.getActionCommand());
        invokeMethod(e.getActionCommand(), null);
    }

    public void invokeMethod(String methodName, Object[] params){
        Class c = this.getClass();

        Class[] param;
        try{
            if (params != null){
                param = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    param[i] = params[i].getClass();
                }
            }
            else param = null;
            //param[0] = params.class;
            //Board[] param2 = new Board[1];
            //param2[0] = b;
            Method m = c.getMethod(methodName, param);
            m.invoke(this, params);
            // m.invoke(TTV, param2) = TTV.convertToText(b);
        }
        catch(NoSuchMethodException e) {
            System.out.println(e.toString());
            System.out.println("Class: " + c);
        }
        catch(InvocationTargetException e) {
            System.out.println(e.toString());
        }
        catch(IllegalAccessException e) {
            System.out.println(e.toString());
        }
    }

    //Used through invokeMethod().
    public void updateArmy(){
        armyText.setText(Integer.toString(region.getArmies()));
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
