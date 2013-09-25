package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegionComponent extends JComponent implements Observer{

    private Region region;
    private Mouse mouseListener = new Mouse();
    private int height;
    private int width;
    private JLabel armyText = new JLabel();
    private boolean isFocused = false;
    private Border border = new Border();

    private static final int INIT_HEIGHT = 100;
    private static final int INIT_WIDTH = 100;

    private class Border extends JComponent{
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255, 215, 0));
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(0,0,height,width);
        }
    }

    public RegionComponent(Region region, int height, int width) {
        this.setLayout(new MigLayout());
        this.region = region;
        this.height = height;
        this.width = width;
        armyText.setText(Integer.toString(region.getArmies()));
        this.add(new JLabel(region.getName()), "span");
        this.add(armyText, "span");
        region.addObserver(this);
        this.addMouseListener(mouseListener);
    }


    public RegionComponent(Region region) {
        this(region, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public void switchFocused(){
        isFocused = !isFocused;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Painting");
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(region.getPlayer().getColor());
        g2.fillRect(0,0, height, width);
        if (isFocused){
            drawBorder(g2);
        }
    }

    public Region getRegion(){
        return region;
    }

    public void update(ActionEvent e){
        System.out.println("regComp.update: " + e.getActionCommand());
        invokeMethod(e.getActionCommand(), null);
    }

    public void invokeMethod(String methodName, Object[] params){
        RegionComponent subClass = this;
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

    private void drawBorder(Graphics2D g2){
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color(255,215,0));
        g2.drawRect(0,0,width,height);
    }

}
