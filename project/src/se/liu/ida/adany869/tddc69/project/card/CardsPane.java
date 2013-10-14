package se.liu.ida.adany869.tddc69.project.card;

import se.liu.ida.adany869.tddc69.project.Player;

import javax.swing.*;
import java.util.ArrayList;

public class CardsPane {
    private Player player;
    private CardComponent[] cardComponents = null;
    private ArrayList<CardComponent> selectedCards = new ArrayList<>();
    private JOptionPane cardsPane = new JOptionPane();
    private JDialog dialog;

    public CardsPane(Player player) {
        this.player = player;

        this.cardsPane.setMessageType(JOptionPane.DEFAULT_OPTION);
        this.cardsPane.setMessage(new Object[]{this.getCardComponents()});
        this.cardsPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        this.cardsPane.setOptions(new Object[]{"Cancel", "Trade cards"});

        this.dialog = cardsPane.createDialog(cardsPane, "Trade cards");
        this.dialog.setAlwaysOnTop(true);
        this.showDialog();
    }

    private void showDialog() {
        dialog.setVisible(true);

        if(cardsPane.getValue() != null &&
                cardsPane.getValue().equals("Trade cards") &&
                this.isSelectedTradeable()){

            player.addReserve(Deck.getInstance().tradeCards());
            for (CardComponent selectedCard : selectedCards) {
                player.removeCard(selectedCard.getCard());
            }

        } else if (!cardsPane.getValue().equals("Cancel")) {
            // no trade
            showDialog();
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

    private boolean isSelectedTradeable() { // NOT a FUCKING TYPO, IDIOT!
        this.selectedCards = new ArrayList<>();
        for (CardComponent cardComponent : this.cardComponents) {
            if (cardComponent.isSelected()) {
                this.selectedCards.add(cardComponent);
                System.out.println(cardComponent.getCard());
            }
        }
        if (this.selectedCards.size() != 3) return false;
        return (this.selectedCards.get(0).getCard() == this.selectedCards.get(1).getCard() &&   // same cards
                this.selectedCards.get(1).getCard() == this.selectedCards.get(2).getCard())
                ||
                (this.selectedCards.get(0).getCard() != this.selectedCards.get(1).getCard() &&  // different cards
                this.selectedCards.get(0).getCard() != this.selectedCards.get(2).getCard() &&
                this.selectedCards.get(1).getCard() != this.selectedCards.get(2).getCard());
    }
}
