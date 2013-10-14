package se.liu.ida.adany869.tddc69.project.state;
import se.liu.ida.adany869.tddc69.project.*;

import se.liu.ida.adany869.tddc69.project.regions.Region;

public class AttackState extends ActionState {

    public AttackState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void doSomething(Region region) {
        if (this.risk.hasFocused()){
            if (this.risk.getFocused() == region){
                this.risk.resetFocus();
            }
            else if (risk.getActivePlayer() != region.getOwner()){
                Region attacker = this.risk.getFocused();
                if (attacker.isNeighbour(region)){
                    SliderPane slider = new SliderPane(0, attacker.getArmies()-1, "Select amount to attack with: ");
                    int armySize = slider.getValue();
                    Battle battle = new Battle(attacker, armySize, region);
                    battle.runBattle();
                    this.risk.resetFocus();
                }
            }
        }
        else if (this.risk.getActivePlayer() == region.getOwner()) {
            this.risk.setFocused(region);
        }
    }

    @Override
    public boolean isRelevantNeighbour(Region focused, Region neighbour) {
        return focused.getNeighbours().contains(neighbour) &&
                focused.getOwner() != neighbour.getOwner();
    }
}
