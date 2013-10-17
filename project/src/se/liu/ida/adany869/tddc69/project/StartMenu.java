package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;

public class StartMenu {
    private static final int MAX_NUMBER_OF_PLAYERS = 6;
    private static final int MIN_NUMBER_OF_PLAYERS = 2;
    private static JOptionPane optionPane = new JOptionPane();

    private static class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    public static int numberOfPlayersOptionPane(){
        JFrame parent = new JFrame();
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Integer[] numberOfPlayersOptions = new Integer[MAX_NUMBER_OF_PLAYERS-1];
        for (int i = MIN_NUMBER_OF_PLAYERS; i <= MAX_NUMBER_OF_PLAYERS; i++) {
            numberOfPlayersOptions[i-MIN_NUMBER_OF_PLAYERS] = i;
        }
        Object[] optionPaneMessage = new Object[2];
        JTextArea textArea = new JTextArea("How many players?");
        JComboBox<Integer> dropDown = new JComboBox<>(numberOfPlayersOptions);
        optionPaneMessage[0] = textArea;
        optionPaneMessage[1] = dropDown;

        createOptionPane(optionPaneMessage);
        return dropDown.getItemAt(dropDown.getSelectedIndex());
    }

    public static ArrayList<String> nameOfPlayersOptionPane(int numberOfPlayers){
        Object[] optionPaneMessage = new Object[numberOfPlayers+1];
        JTextArea textArea = new JTextArea("Enter the name of the players:");
        optionPaneMessage[0] = textArea;
        for (int i = 1; i <= numberOfPlayers; i++) {
            JTextField playerName = new JTextField();
            playerName.setDocument(new JTextFieldLimit(15));
            playerName.setText("Player " + i);
            optionPaneMessage[i] = playerName;
        }

        createOptionPane(optionPaneMessage);

        ArrayList<String> namesOfPlayers = new ArrayList<>();
        for (int i = 1; i < optionPaneMessage.length; i++) {
            namesOfPlayers.add(((JTextField)optionPaneMessage[i]).getText());
        }
        return namesOfPlayers;
    }

    private static void createOptionPane(Object[] message){
        JFrame parent = new JFrame();
        optionPane.setMessage(new Object[]{message});
        optionPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        JDialog dialog = optionPane.createDialog(parent, "Rwhisky");
        dialog.setAlwaysOnTop (true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (optionPane.getValue() == null){
            System.exit(0);
        }
    }
}