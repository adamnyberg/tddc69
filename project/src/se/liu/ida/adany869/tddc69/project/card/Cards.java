package se.liu.ida.adany869.tddc69.project.card;

import java.util.Random;

/**
 * Players get cards during the game. They can trade these in, but only if they have certain combinations of them.
 * This enum class holds the different card values.
 */
public enum Cards {
    INFANTRY, CAVALRY, ARTILLERY;

    private static final int SIZE = values().length;
    private static final Random RANDOM = new Random();

    public static Cards getRandom() {
        return values()[RANDOM.nextInt(SIZE)];
    }
}
