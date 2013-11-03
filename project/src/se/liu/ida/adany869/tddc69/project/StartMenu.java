package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;

/**
 * An utility class for getting input panes. These are used to set the number of players and their names.
 */
public final class StartMenu {
    private static final int MAX_NUMBER_OF_PLAYERS = 6;
    private static final int MIN_NUMBER_OF_PLAYERS = 2;
    private static final int MAX_NAME_LENGTH = 15;
    private static JOptionPane optionPane = new JOptionPane();

    private StartMenu() {
    }

    private static class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
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
        parent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        JTextArea textArea = new JTextArea("Enter the names of the players:");
        optionPaneMessage[0] = textArea;
        for (int i = 1; i <= numberOfPlayers; i++) {
            JTextField playerName = new JTextField();
            playerName.setDocument(new JTextFieldLimit(MAX_NAME_LENGTH));
            playerName.setText("Player " + i);
            optionPaneMessage[i] = playerName;
        }

        ArrayList<String> namesOfPlayers = new ArrayList<>();
        boolean namesIsOK = true;
        createOptionPane(optionPaneMessage);
        for (int i = 1; i < optionPaneMessage.length; i++) {
            String name = ((JTextComponent)optionPaneMessage[i]).getText();
            if (name.isEmpty() || namesOfPlayers.contains(name)){
                namesIsOK = false;
                namesOfPlayers.clear();
                break;
            }
            namesOfPlayers.add(name);
        }

        while (!namesIsOK){
            namesIsOK = true;
            textArea.setText("All names must be unique and at least 1 character long!\nEnter names:");
            createOptionPane(optionPaneMessage);
            for (int i = 1; i < optionPaneMessage.length; i++) {
                String name = ((JTextComponent)optionPaneMessage[i]).getText();
                if (name.isEmpty() || namesOfPlayers.contains(name)){
                    namesIsOK = false;
                    namesOfPlayers.clear();
                    break;
                }
                namesOfPlayers.add(name);
            }
        }
        return namesOfPlayers;
    }

    //A method for multiplaying on several computers
    public static String enterYourNamePane(String descriptiveText){
        Object[] optionPaneMessage = new Object[2];

        JTextArea descriptiveTextArea = new JTextArea(descriptiveText);
        optionPaneMessage[0] = descriptiveTextArea;
        JTextField playerName = new JTextField();
        playerName.setDocument(new JTextFieldLimit(MAX_NAME_LENGTH));
        optionPaneMessage[1] = playerName;

        createOptionPane(optionPaneMessage);

        String nameOfPlayer = ((JTextComponent)optionPaneMessage[1]).getText();
        while (nameOfPlayer.isEmpty()){
            descriptiveTextArea.setText("Your name needs to be at least 1 character long. \n"+descriptiveText);
            createOptionPane(optionPaneMessage);
            nameOfPlayer = ((JTextComponent)optionPaneMessage[1]).getText();
        }
        return nameOfPlayer;
    }

    private static void createOptionPane(Object[] message){
        JFrame parent = new JFrame();
        optionPane.setMessage(new Object[]{message});
        optionPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        JDialog dialog = optionPane.createDialog(parent, "Rwhisky");
        dialog.setAlwaysOnTop (true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (optionPane.getValue() == null){
            System.exit(0);
        }
    }
}