package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class Region {
    private int armies;
    private String name;
    private ArrayList<Region> neighbours = new ArrayList<Region>();

    public Region(String name) {
        this.name = name;
    }

    public ArrayList<Region> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList neighbours) {
        this.neighbours = neighbours;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }

    public void addNeighbour(Region region){
        System.out.println("Region: " + region);
        System.out.println("Regionname: " + region.name);
        neighbours.add(region);
    }
}
