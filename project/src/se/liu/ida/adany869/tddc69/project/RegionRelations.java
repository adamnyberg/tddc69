package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class RegionRelations {
    private ArrayList<Region[]> relations = new ArrayList<Region[]>();

    public void addRelation(Region objectA, Region objectB){
        Region[] objects = new Region[]{objectA, objectB};
        relations.add(objects);
    }

    public ArrayList<Region[]> getRelations(){
        return (ArrayList<Region[]>) relations;
    }

    public Region[] getRelation(int index){
        return (Region[]) relations.get(index);
    }
}
