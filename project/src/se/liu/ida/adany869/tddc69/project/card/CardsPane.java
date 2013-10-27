package se.liu.ida.adany869.tddc69.project.card;

import se.liu.ida.adany869.tddc69.project.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Every player has a card pane which shows the player's cards and makes it possible to trade them in.
 */
public class CardsPane {
    private Player player;
    private CardComponent[] cardComponents = null;
    private List<CardComponent> selectedCards = new ArrayList<>();
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
        this.dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
            CardComponent[] localCardComponents = new CardComponent[player.getCards().size()];
            for (int i = 0; i < localCardComponents.length; i++) {
                localCardComponents[i] = new CardComponent(player.getCards().get(i));
            }
            this.cardComponents = localCardComponents;
        return localCardComponents;
    }

    private boolean isSelectedTradeable() { // NOT a FUCKING TYPO, IDIOT!
        this.selectedCards = new ArrayList<>();
        for (CardComponent cardComponent : this.cardComponents) {
            if (cardComponent.isSelected()) {
                this.selectedCards.add(cardComponent);
            }
        }
        return this.selectedCards.size() == 3
                &&
                ((this.selectedCards.get(0).getCard() == this.selectedCards.get(1).getCard() &&   // same cards
                this.selectedCards.get(1).getCard() == this.selectedCards.get(2).getCard())
                ||
                (this.selectedCards.get(0).getCard() != this.selectedCards.get(1).getCard() &&  // different cards
                this.selectedCards.get(0).getCard() != this.selectedCards.get(2).getCard() &&
                this.selectedCards.get(1).getCard() != this.selectedCards.get(2).getCard()));
    }
}
