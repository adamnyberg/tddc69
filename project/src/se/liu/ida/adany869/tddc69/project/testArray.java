package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class testArray {
    public static void main(String[] args) {
        ArrayList<Region> test = new ArrayList<>();
        Region region = new Region("test");
        test.add(region);
        for (Region region1 : test) {
            System.out.println(region1.getName());
        }
        test.remove(region);
        for (Region region1 : test) {
            System.out.println(region1.getName());
        }

    }
}
