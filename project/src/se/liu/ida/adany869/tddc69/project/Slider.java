package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Slider extends JComponent{
    private int armySize = 0;
    private RegionComponent parent;

    final JSlider armySlider;

    JButton sliderTerminateButton = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeSelf();
            resetArmySize();

        }
    });



    JButton sliderConfirmButton = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setArmySize();
        }
    });

    public Slider(int maxArmySize, RegionComponent parent) {
        this.parent = parent;
        armySlider = new JSlider(0, maxArmySize);
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

    private void removeSelf(){
        parent.remove(this);
    }

    public int getValue(){
        return armySize;
    }

}
