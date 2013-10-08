package se.liu.ida.adany869.tddc69.project.state;
import se.liu.ida.adany869.tddc69.project.*;

public class AttackState implements ActionState {
    @Override
    public void doSomething(Region regionA, Region regionB) {
        SliderPane slider = new SliderPane(0, regionA.getArmies()-1, "Select amount to attack with: ");
        int armySize = slider.getValue();
        Battle battle = new Battle(regionA, armySize, regionB);
        battle.runBattle();
    }

    @Override
    public void highlightRelevantNeighbours() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
