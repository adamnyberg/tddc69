package se.liu.ida.adany869.tddc69.project.card;

import se.liu.ida.adany869.tddc69.project.Player;

import javax.swing.*;
import java.util.ArrayList;

public class CardsPane {
    Player player;
    CardComponent[] cardComponents = null;

    public CardsPane(Player player) {
        this.player = player;

        JButton tradeCardsButton = new JButton("Trade cards");
        tradeCardsButton.setEnabled(false);

        JOptionPane cardsPane = new JOptionPane();
        cardsPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        cardsPane.setMessage(new Object[]{this.getCardComponents()});
        cardsPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        cardsPane.setOptions(new Object[]{"Cancel", tradeCardsButton});

        JDialog dialog = cardsPane.createDialog(cardsPane, "Trade cards");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

        if(cardsPane.getValue() != null &&
                cardsPane.getValue().equals("Trade cards") &&
                this.isSelectedTradable()){
            System.out.println("trade");
            if (isSelectedTradable()) {
                player.addReserve(Deck.getInstance().tradeCards());
                System.out.println("Tradable");
            }


        } else {
            System.out.println("no trade");
        }
    }

    private CardComponent[] getCardComponents() {
        if (this.cardComponents == null) {
            CardComponent[] cardComponents = new CardComponent[player.getCards().size()];
            for (int i = 0; i < cardComponents.length; i++) {
                cardComponents[i] = new CardComponent(player.getCards().get(i));
            }
            this.cardComponents = cardComponents;
        }
        return this.cardComponents;
    }

    private boolean isSelectedTradable() {
        ArrayList<CardComponent> selectedCards = new ArrayList<>();
        for (CardComponent cardComponent : this.cardComponents) {
            if (cardComponent.isSelected()) {
                selectedCards.add(cardComponent);
            }
        }
        if (selectedCards.size() != 3) return false;
        if ((selectedCards.get(0) == selectedCards.get(1) && selectedCards.get(1) == selectedCards.get(2)) ||
                (selectedCards.get(0) != selectedCards.get(1) && selectedCards.get(1) != selectedCards.get(2))) {
            return true;
        }
        return false;
    }
}
