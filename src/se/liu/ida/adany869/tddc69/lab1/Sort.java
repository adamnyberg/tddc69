package se.liu.ida.adany869.tddc69.lab1;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    public static void main(String[] args) {

        ArrayList arguments = new ArrayList();

        for (int i = 0; i < args.length; i++) {
            arguments.add(args[i]);
        }

        Collections.sort(arguments);

        for (Object s : arguments) {
            System.out.println(s);
        }
    }
}
