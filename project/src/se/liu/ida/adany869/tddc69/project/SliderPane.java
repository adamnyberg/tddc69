package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 * A slider pane is an optionpane which gives the user the ability to easily choose a numeric value.
 * It's main purpose is to make it easy to chose the size of an army to attack or move.
 */
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
        PlainDocument document = (PlainDocument) textField.getDocument();
        document.setDocumentFilter(new IntFilter(optionPane, this.maxSize));
        JSlider slider = getSlider(optionPane, textField);
        textField.setText(Integer.toString(slider.getValue()));
        optionPane.setMessage(new Object[]{descriptiveText, slider, textField});
        optionPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        optionPane.setInputValue(minSize);
        JDialog dialog = optionPane.createDialog(parent, "Välj värde");


        dialog.setAlwaysOnTop (true);
        dialog.setVisible(true);
        if(optionPane.getValue() != null && optionPane.getValue().equals(JOptionPane.OK_OPTION)){
            value = Integer.valueOf(textField.getText());
            if (value > maxSize){
                value = maxSize;
            }
        }
    }

    private JSlider getSlider(final JOptionPane optionPane, final JTextField textField){
        JSlider slider = new JSlider();
        slider.setMaximum(maxSize);
        slider.setMinimum(minSize);
        slider.setValue(maxSize);
        slider.setMajorTickSpacing(maxSize);
        slider.setPaintTicks(true);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider theSlider = (JSlider) e.getSource();
                Integer sliderValue = theSlider.getValue();
                textField.setText(sliderValue.toString());
                if (!theSlider.getValueIsAdjusting()){
                    optionPane.setInputValue(sliderValue);
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

//A text document which hinders faulty inputs. Sliders can only have integer values.
class IntFilter extends DocumentFilter {
    JOptionPane parent;
    int maxSize;

    IntFilter(JOptionPane parent, int maxSize) {
        this.parent = parent;
        this.maxSize = maxSize;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string,
                             AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);

        if (isInt(sb.toString())) {
            if (Integer.parseInt(sb.toString())> maxSize){
                string = Integer.toString(maxSize);
                remove(fb, 0, doc.getLength());
            }
            super.insertString(fb, offset, string, attr);
        } else {
            JOptionPane.showMessageDialog(parent, new JTextArea("Only integers can be entered here!"));
        }
    }


    private boolean isInt(String text) {
        //Test to see if the string onlcy contain integers.
        //noinspection UnusedCatchParameter
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (isInt(sb.toString())) {
            if (Integer.parseInt(sb.toString())> maxSize){
                text = Integer.toString(maxSize);
                super.replace(fb, 0, doc.getLength()+sb.toString().length(), text, attrs);
            }
            else{
                super.replace(fb, offset, length, text, attrs);
            }
        } else {
            JOptionPane.showMessageDialog(parent, new JTextArea("Only integers can be entered here!"));
        }

    }
}