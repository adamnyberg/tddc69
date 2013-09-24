package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class GenericRelations<T> {
    private ArrayList<T[]> relations = new ArrayList<T[]>();

    public void addRelation(T objectA, T objectB){
        T[] objects = (T[]) new Object[]{objectA, objectB};
        relations.add(objects);
    }

    public ArrayList<T[]> getRelations(){
        return relations;
    }
}
