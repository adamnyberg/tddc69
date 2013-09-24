package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class RiskWorld {
    private Region[] regions;
    private Player[] players;
    private RegionRelations relations;

    public RiskWorld(Region[] regions, Player[] players) {
        this.regions = regions;
        this.players = players;
        relations = new RegionRelations();
    }

    public Region[] getRegions() {
        return regions;
    }

    public RegionRelations getRegionRelations(){
        return relations;
    }

    public void addRelation(Region regionA, Region regionB){
        relations.addRelation(regionA, regionB);
    }
}
