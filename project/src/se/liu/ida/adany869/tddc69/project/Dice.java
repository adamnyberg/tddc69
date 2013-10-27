package se.liu.ida.adany869.tddc69.project;

import java.util.Random;

/**
 * Utility class for attacks between
 */
public class Dice{
    private int value = throwDice();

    public int throwDice(){
        value = new Random().nextInt(6)+1;
        return value;
    }

    public int getValue() {
        return value;
    }
}
