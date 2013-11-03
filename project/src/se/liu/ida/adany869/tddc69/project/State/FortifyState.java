package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.RiskWorld;
import se.liu.ida.adany869.tddc69.project.SliderPane;
import se.liu.ida.adany869.tddc69.project.regions.Region;

/**
 * A state which gives the players the ability to move their troops between regions. This is the last thing a player
 * can do before ending its turn.
 */
public class FortifyState extends AbstractState {
    public FortifyState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void onRegionClick(Region region) {
        if (this.risk.hasFocused()){
            if (this.risk.getFocused().equals(region)){
                this.risk.resetFocus();
            }
            else if (isRelevantNeighbour(this.risk.getFocused(), region)){
                Region fortifier = this.risk.getFocused();
                SliderPane slider = new SliderPane(0, fortifier.getArmySize()-1, "Select amount to move: ");
                int fortifySize = slider.getValue();
                region.addArmy(fortifySize);
                fortifier.addArmy(-fortifySize);
                this.risk.resetFocus();
            }
        }
        else if (this.risk.getActivePlayer().equals(region.getOwner())) {
            this.risk.setFocused(region);
        }
    }

    @Override
    public boolean isRelevantNeighbour(Region focused, Region neighbour) {
        return focused.getNeighbours().contains(neighbour) &&
                focused.getOwner().equals(neighbour.getOwner());
    }
}
