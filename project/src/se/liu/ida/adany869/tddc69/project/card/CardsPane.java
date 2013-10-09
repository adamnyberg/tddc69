package se.liu.ida.adany869.tddc69.project.card;

import se.liu.ida.adany869.tddc69.project.Player;

import javax.swing.*;
import java.util.ArrayList;

public class CardsPane {
    Player player;
    CardComponent[] cardComponents = null;
    ArrayList<CardComponent> selectedCards = new ArrayList<>();

    public CardsPane(Player player) {
        this.player = player;

        JOptionPane cardsPane = new JOptionPane();
        cardsPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        cardsPane.setMessage(new Object[]{this.getCardComponents()});
        cardsPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        cardsPane.setOptions(new Object[]{"Cancel", "Trade cards"});

        JDialog dialog = cardsPane.createDialog(cardsPane, "Trade cards");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

        if(cardsPane.getValue() != null &&
                cardsPane.getValue().equals("Trade cards") &&
                this.isSelectedTradable()){

            player.addReserve(Deck.getInstance().tradeCards());
            for (CardComponent selectedCard : selectedCards) {
                player.removeCard(selectedCard.getCard());
            }

        } else {
            // no trade
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
        this.selectedCards = new ArrayList<>();
        for (CardComponent cardComponent : this.cardComponents) {
            if (cardComponent.isSelected()) {
                this.selectedCards.add(cardComponent);
                System.out.println(cardComponent.getCard());
            }
        }
        if (this.selectedCards.size() != 3) return false;
        if (this.selectedCards.get(0).getCard() == this.selectedCards.get(1).getCard() &&
                this.selectedCards.get(1).getCard() == this.selectedCards.get(2).getCard()) {
            return true;
        }
        if (this.selectedCards.get(0).getCard() != this.selectedCards.get(1).getCard() &&
                this.selectedCards.get(0).getCard() != this.selectedCards.get(2).getCard() &&
                this.selectedCards.get(1).getCard() != this.selectedCards.get(2).getCard()) {
            return true;
        }
        return false;
    }
}
