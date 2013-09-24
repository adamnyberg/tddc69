package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class Player {

    private String name;

    private ArrayList<Region> regions;

    public Player(String name) {
        this(name, null);
    }
    public Player(String name, ArrayList<Region> regions) {
        System.out.println("Added player: " + name);
        this.name = name;
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }
}
