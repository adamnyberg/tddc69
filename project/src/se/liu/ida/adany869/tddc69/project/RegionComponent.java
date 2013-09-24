package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegionComponent extends JComponent implements Observer{

    private Region region;
    private int height;
    private int width;
    private JLabel armyText = new JLabel();

    private static final int INIT_HEIGHT = 100;
    private static final int INIT_WIDTH = 100;

    public RegionComponent(Region region, int height, int width) {
        this.setLayout(new MigLayout());
        this.region = region;
        this.height = height;
        this.width = width;
        armyText.setText(Integer.toString(region.getArmies()));
        this.add(armyText, "span");
    }

    public RegionComponent(Region region) {
        this(region, INIT_HEIGHT, INIT_WIDTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fillRect(0,0, height, width);
    }

    public Region getRegion(){
        return region;
    }

    public void update(ActionEvent e){
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
        }
        catch(InvocationTargetException e) {
            System.out.println(e.toString());
        }
        catch(IllegalAccessException e) {
            System.out.println(e.toString());
        }
    }

    private void updateArmy(){
        System.out.println("jhh");
        armyText.setText(Integer.toString(region.getArmies()));
    }
}
