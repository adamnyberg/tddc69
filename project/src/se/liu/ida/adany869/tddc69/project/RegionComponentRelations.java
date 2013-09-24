package se.liu.ida.adany869.tddc69.project;

import java.util.ArrayList;

public class RegionComponentRelations {
    private class Relation{
        RegionComponent regionComponentA;
        RegionComponent regionComponentB;

        private Relation(RegionComponent regionComponentA, RegionComponent regionComponentB) {
            this.regionComponentA = regionComponentA;
            this.regionComponentB = regionComponentB;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Relation comparingRelation = (Relation) o;

            if (this.regionComponentA == null || this.regionComponentB == null) return false;
            if (this.regionComponentA == comparingRelation.regionComponentA ||
                    this.regionComponentA == comparingRelation.regionComponentB){
                if (this.regionComponentB == comparingRelation.regionComponentB ||
                        this.regionComponentB == comparingRelation.regionComponentA){
                    return true;
                }
            }
            return false;
        }
    }

    private ArrayList<RegionComponent[]> relations = new ArrayList<RegionComponent[]>();

    private boolean isInArray(RegionComponent regionA, RegionComponent regionB){
        for (int i = 0; i < relations.size(); i++) {
             if (relations.get(i).equals(new RegionComponent[]{regionA, regionB})){
                return true;
             }
        }
        return false;
    }

    public void addRelation(RegionComponent regionA, RegionComponent regionB){
        if (!isInArray(regionA, regionB)){
            RegionComponent[] objects = new RegionComponent[]{regionA, regionB};
            relations.add(objects);
        }
    }

    public ArrayList<RegionComponent[]> getRelations(){
        return relations;
    }

    public RegionComponent[] getRelation(int index){
        return relations.get(index);
    }
}
