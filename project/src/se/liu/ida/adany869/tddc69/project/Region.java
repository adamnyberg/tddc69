package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class Region {
    private int armies;
    private ArrayList<Region> neighbours;
    //private Region[] neighbours;

    public Region() {
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
        neighbours.add(region);
    }
}
