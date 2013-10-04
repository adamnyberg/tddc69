package se.liu.ida.adany869.tddc69.project;

public class Battle {
    private Region regionA;
    private int armiesA;
    private Region regionB;

    public Battle(Region regionA, int armiesA, Region regionB) {
        this.regionA = regionA;
        this.regionB = regionB;
        this.armiesA = armiesA;
    }

    public void runBattle(){
        boolean ongoingBattle = true;
        while (ongoingBattle){
            armiesA -= regionA.attack(regionB, (armiesA >= 3 ? 3 : armiesA));
            ongoingBattle = (armiesA > 0 && regionB.getArmies() > 0);
        }
        if (regionB.getArmies() <= 0){
            regionB.getPlayer().removeRegion(regionB);
            regionA.getPlayer().addRegion(regionB);
            regionB.setArmies(armiesA);
            regionA.addArmy(-armiesA);
        }
    }
}
