package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.RiskWorld;
import se.liu.ida.adany869.tddc69.project.regions.Region;

/**
 * An AbstractState. This state is active in the beginning of a players turn, giving him/her the ability to
 * reinforce the regions.
 */
public class ReinforceState extends AbstractState {
    public ReinforceState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void onRegionClick(Region region) {
        if (region.getOwner().equals(this.risk.getActivePlayer())){
            region.getOwner().addArmyToRegion(region);
        }
    }

    @Override
    public boolean isRelevantNeighbour(Region focused, Region neighbour) {
        return false;
    }
}
