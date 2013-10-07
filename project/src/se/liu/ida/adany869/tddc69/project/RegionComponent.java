package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

public class RegionComponent extends JComponent implements Observer {
    private Region region;
    private int height;
    private int width;
    private JLabel armyText = new JLabel();
    private boolean isFocused = false;

    private static final int INIT_HEIGHT = 150;
    private static final int INIT_WIDTH = 150;

    @Override
    public void update(Observable o, Object arg) {
        updateArmy();
        repaint();
    }

    public RegionComponent(Region region, int yPos, int xPos, int height, int width, RegionController regionController) {
        this.setLayout(new MigLayout());
        this.region = region;
        this.height = height;
        this.width = width;
        this.setBounds(xPos, yPos, width, height);
        armyText.setText(Integer.toString(region.getArmies()));
        this.add(new JLabel(region.getName()));
        this.add(armyText, "wrap");
        region.addObserver(this);
        this.addMouseListener(regionController);
        this.setVisible(true);
    }

    public RegionComponent(Region region, int yPos, int xPos, RegionController regionController, int index) {
        this(region, yPos, xPos, INIT_HEIGHT, INIT_WIDTH, regionController);
        //this.add(new JLabel("Index: " + Integer.toString(index)));
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
        //System.out.println("Painted region: " + region.getName());
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(region.getPlayer().getColor());
        g2.fillRect(0, 0, this.getHeight(), this.getWidth());

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(0, 0, this.getHeight(), this.getWidth());

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



    /*public class Slider extends JComponent{
        int armySize = 0;

        final JSlider armySlider = new JSlider(0, region.getArmies()-1);

        JButton sliderTerminateButton = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(armySlider);
                remove(sliderTerminateButton);
                remove(sliderConfirmButton);
                resetArmySize();
            }
        });



        JButton sliderConfirmButton = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setArmySize();

            }
        });

        public Slider() {
            this.setLayout(new MigLayout());
            sliderTerminateButton.setText("Cancel");
            sliderConfirmButton.setText("OK");
            this.add(armySlider, "wrap");
            this.add(sliderTerminateButton);
            this.add(sliderConfirmButton);
        }

        private void resetArmySize(){
            armySize = 0;
        }

        private void setArmySize(){
            armySize = armySlider.getValue();
        }
    }*/



}
