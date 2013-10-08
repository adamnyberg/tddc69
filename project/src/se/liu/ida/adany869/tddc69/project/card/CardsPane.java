package se.liu.ida.adany869.tddc69.project.card;

import se.liu.ida.adany869.tddc69.project.Player;

import javax.swing.*;

public class CardsPane {
    Player player;
    public CardsPane(Player player) {
        this.player = player;

        JOptionPane cardsPane = new JOptionPane();
        cardsPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        cardsPane.setMessage(new Object[]{this.getCardComponents()});
        cardsPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        cardsPane.setOptions(new String[]{"Cancel", "Trade cards"});

        JDialog dialog = cardsPane.createDialog(cardsPane, "Trade cards");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

        if(cardsPane.getValue().equals("Trade cards")){
            System.out.println("trade");
            System.out.println(Deck.getInstance().tradeCards());
            

        } else {
            System.out.println("no trade");
        }
    }

    private CardComponent[] getCardComponents() {
        CardComponent[] cardComponents = new CardComponent[player.getCards().size()];
        for (int i = 0; i < cardComponents.length; i++) {
            cardComponents[i] = new CardComponent(player.getCards().get(i));
        }
        return cardComponents;
    }
}
