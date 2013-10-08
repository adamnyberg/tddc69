package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SliderPane {
    int value = 0;
    int maxSize;
    int minSize;
    public SliderPane(int minSize, int maxSize, String descriptiveText) {
        JFrame parent = new JFrame();
        this.maxSize = maxSize;
        this.minSize = minSize;

        JOptionPane optionPane = new JOptionPane();
        JTextField textField = new JTextField();
        JSlider slider = getSlider(optionPane, textField);
        textField.setText(new Integer(slider.getValue()).toString());
        optionPane.setMessage(new Object[]{descriptiveText, slider, textField});
        optionPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        optionPane.setInputValue(minSize);

        JDialog dialog = optionPane.createDialog(parent, "My Slider");
        dialog.setAlwaysOnTop (true);
        dialog.setVisible(true);

        if(optionPane.getValue() != null && optionPane.getValue().equals(JOptionPane.OK_OPTION)){
            value = Integer.valueOf(textField.getText());
            if (value > maxSize){
                value = maxSize;
            }
        };
    }

    private JSlider getSlider(final JOptionPane optionPane, final JTextField textField){
        JSlider slider = new JSlider();
        slider.setMaximum(maxSize);
        slider.setMinimum(minSize);
        slider.setValue(maxSize);
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

    public int getValue() {
        return value;
    }
}