package se.liu.ida.adany869.tddc69.project.state;
import se.liu.ida.adany869.tddc69.project.*;

import se.liu.ida.adany869.tddc69.project.regions.Region;

/**
 * The attack state specifies what should happen when the player clicks on regions after clicking the attack button.
 */
public class AttackState extends ActionState {

    public AttackState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void onRegionClick(Region region) {
        if (this.risk.hasFocused()){
            if (this.risk.getFocused().equals(region)){
                this.risk.resetFocus();
            }
            else if (!risk.getActivePlayer().equals(region.getOwner())){
                Region attacker = this.risk.getFocused();
                if (attacker.isNeighbour(region)){
                    SliderPane slider = new SliderPane(0, attacker.getArmySize()-1, "Select amount to attack with: ");
                    int armySize = slider.getValue();
                    Battle battle = new Battle(attacker, armySize, region);
                    battle.runBattle();
                    this.risk.resetFocus();
                }
            }
        }
        else if (this.risk.getActivePlayer().equals(region.getOwner())) {
            this.risk.setFocused(region);
        }
    }

    @Override
    public boolean isRelevantNeighbour(Region focused, Region neighbour) {
        return focused.getNeighbours().contains(neighbour) &&
                !focused.getOwner().equals(neighbour.getOwner());
    }
}
