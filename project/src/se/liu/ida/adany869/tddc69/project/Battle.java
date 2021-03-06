package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.Region;

/**
 * The battle class handles battles between regions. It also handles the results and makes the changes accordingly.
 * Every battle consists of smaller attacks. These attacks are handled by the regions them self, but called from here.
 */
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
        Player playerA = regionA.getOwner();
        Player playerB = regionB.getOwner();
        while (ongoingBattle){

            armiesA -= regionA.attack(regionB, (armiesA >= 3 ? 3 : armiesA));
            ongoingBattle = (armiesA > 0 && regionB.getArmySize() > 0);
        }
        if (regionB.getArmySize() <= 0){
            playerB.removeRegion(regionB);
            playerB.checkDefeated();
            playerA.addRegion(regionB);
            playerA.setAttackedAndWon(true);
            if (armiesA > 3){
                regionB.setArmySize(3);
                regionA.addArmy(-3);
                SliderPane slider = new SliderPane(0, armiesA-3, "Select amount to move: ");
                int fortifySize = slider.getValue();
                regionB.addArmy(fortifySize);
                regionA.addArmy(-fortifySize);
            }
            else {
                regionB.setArmySize(armiesA);
                regionA.addArmy(-armiesA);
            }
        }
    }
}
