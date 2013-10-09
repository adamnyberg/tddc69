package se.liu.ida.adany869.tddc69.project.state;
import se.liu.ida.adany869.tddc69.project.*;

import se.liu.ida.adany869.tddc69.project.regions.Region;

public class AttackState extends ActionState {

    public AttackState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void doSomething(Region region) {
        if (this.parent.hasFocused()){
            if (this.parent.getFocused() == region){
                this.parent.resetFocus();
            }
            else if (parent.getActivePlayer() != region.getOwner()){
                Region attacker = this.parent.getFocused();
                if (attacker.isNeighbour(region)){
                    SliderPane slider = new SliderPane(0, attacker.getArmies()-1, "Select amount to attack with: ");
                    int armySize = slider.getValue();
                    Battle battle = new Battle(attacker, armySize, region);
                    battle.runBattle();
                    this.parent.resetFocus();
                }
            }
        }
        else if (parent.getActivePlayer() == region.getOwner()) {
            parent.setFocused(region);
        }
    }

    @Override
    public void highlightRelevantNeighbours() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
