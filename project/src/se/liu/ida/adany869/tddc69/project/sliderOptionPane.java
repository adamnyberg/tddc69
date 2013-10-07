package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SliderOptionPane {
    int armySize = 0;
    int maxSize;
    public SliderOptionPane(int maxSize) {
        JFrame parent = new JFrame();
        this.maxSize = maxSize;

        JOptionPane optionPane = new JOptionPane();
        JTextField textField = new JTextField();
        JSlider slider = getSlider(optionPane, textField);
        textField.setText(new Integer(slider.getValue()).toString());
        optionPane.setMessage(new Object[]{"Select amount to attack with:", slider, textField});
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        optionPane.setInputValue(0);
        JDialog dialog = optionPane.createDialog(parent, "My Slider");
        dialog.setVisible(true);
        armySize = Integer.valueOf(textField.getText());
    }

    private JSlider getSlider(final JOptionPane optionPane, final JTextField textField){
        JSlider slider = new JSlider();
        slider.setMaximum(maxSize);
        slider.setValue(0);
        slider.setMajorTickSpacing(maxSize);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);


        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider theSlider = (JSlider) e.getSource();
                Integer value = new Integer(theSlider.getValue());
                textField.setText(value.toString());
                if (!theSlider.getValueIsAdjusting()){
                    optionPane.setInputValue(value);

                }
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }

    public int getArmySize() {
        return armySize;
    }
}
