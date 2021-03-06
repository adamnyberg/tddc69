package se.liu.ida.adany869.tddc69.lab2;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Holds the information about every square on the Board class.
 */
public enum SquareType {
    I, J, L, O, S, T, Z, OUTSIDE, EMPTY;

    private static final int NUMBER_OF_POLIES = 7;

    private static final List<SquareType> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));

    public static SquareType getSquareType(int index)  {
        return VALUES.get(index);
    }

    public static int getNumberOfTypes(){
        return NUMBER_OF_POLIES;
    }
}