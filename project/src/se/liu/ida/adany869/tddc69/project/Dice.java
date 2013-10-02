package se.liu.ida.adany869.tddc69.project;

import java.util.Random;

public class Dice{
    private int value = throwDice();
    private static Random randGen = new Random();

    public int throwDice(){
        value = randGen.nextInt(6)+1;
        return value;
    }

    public int getValue() {
        return value;
    }
}
