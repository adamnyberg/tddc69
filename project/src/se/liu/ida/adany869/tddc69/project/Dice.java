package se.liu.ida.adany869.tddc69.project;

import java.util.Random;

public class Dice{
   private static Random randGen = new Random();

    public static int throwDice(){
        return randGen.nextInt(6)+1;
    }
}
