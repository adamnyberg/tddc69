package se.liu.ida.adany869.tddc69.project.card;

import java.util.Random;

public enum Cards {
    INFANTRY, CAVALRY, ARTILLERY;

    private static final Cards[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Cards getRandom() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
