package se.liu.ida.adany869.tddc69.project.card;

public class Deck {
    private static final Deck DECK = new Deck();
    private int cardsTraded = 0;

    protected Deck() {
    }

    public static Deck getInstance() {

        return DECK;
    }

    public Cards getCard() {
        return Cards.getRandom();
    }

    public int tradeCards() {
        int armies;
        if (cardsTraded <= 4) {
            armies = 4 + cardsTraded * 2;
        }
        else {
            armies = (cardsTraded - 2) * 5;
        }
        cardsTraded++;
        return armies;
    }
}