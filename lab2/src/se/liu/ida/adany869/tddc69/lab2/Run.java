package se.liu.ida.adany869.tddc69.lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Run {

    public static void main(String[] args) {
        final Board b = new Board();
        b.generateNewFallingPoly();
        final TetrisFrame frame = new TetrisFrame(b);
        TetrisTextView TTV = new TetrisTextView();
        Class c = TTV.getClass();
        try{
        Class[] param = new Class[1];
        param[0] = Board.class;
        Board[] param2 = new Board[1];
        param2[0] = b;
        Method m = c.getMethod("convertToText", param);
        System.out.println("m: " + m.invoke(TTV, param2));
        // m.invoke(TTV, param2) = TTV.convertToText(b);
        }
        catch(NoSuchMethodException e) {
            System.out.println(e.toString());
        }
        catch(InvocationTargetException e) {
            System.out.println(e.toString());
        }
        catch(IllegalAccessException e) {
            System.out.println(e.toString());
        }

    }
}